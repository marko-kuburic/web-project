package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.AddShelfDto;
import acs.uns.ac.rs.webproject.dto.BookDto;
import acs.uns.ac.rs.webproject.dto.DeleteShelfDto;
import acs.uns.ac.rs.webproject.dto.ShelfDto;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.Role;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.service.ShelfService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class  ShelfController {
    @Autowired
    private ShelfService shelfService;

    @Autowired
    private UserService userService;
    @GetMapping("/api/shelves")
    public ResponseEntity<List<Shelf>> getShelves(){
        List<Shelf> shelfList = shelfService.findAll();
        //List<ShelfDto> shelfDtos = new ArrayList<ShelfDto>();
        if(shelfList.size() == 0)
            return new ResponseEntity(shelfList, HttpStatus.NOT_FOUND);

        /*for(Shelf shelf : shelfList)
        {
            ShelfDto shelfDto = new ShelfDto (shelf);
            shelfDtos.add(shelfDto);
        }*/

        return new ResponseEntity(shelfList, HttpStatus.OK);
    }

    @GetMapping("/api/shelves/{id}")
    public ResponseEntity<Shelf> getShelf(@PathVariable(name = "id") Long id){
        Shelf shelf = shelfService.findOne(id);
        if(shelf == null)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
       // ShelfDto shelfDto = new ShelfDto(shelf);

        return new ResponseEntity(shelf, HttpStatus.OK);
    }

    @GetMapping("/api/shelves/search/{name}")
    public ResponseEntity<List<Shelf>> getAllByName(@PathVariable("name") String name){
        List<Shelf> shelfList = shelfService.findAllByName(name);
        if(shelfList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
       /* List<ShelfDto> shelfDtos = new ArrayList<ShelfDto>();


        for(Shelf shelf : shelfList)
        {
            ShelfDto shelfDto = new ShelfDto(shelf);
            shelfDtos.add(shelfDto);
        }*/

        return new ResponseEntity(shelfList, HttpStatus.OK);
    }

    @PostMapping("/api/save-shelf")
    public String saveShelf(@RequestBody Shelf shelf) {
        this.shelfService.save(shelf);
        return "Successfully saved a shelf!";
    }

    @PostMapping("/add-shelf")
    public ResponseEntity addShelf(@RequestBody AddShelfDto addShelfDto, HttpSession session){

        User loggedUser = (User) session.getAttribute("user");
        if(loggedUser == null)
            return new ResponseEntity("Have to be logged in!", HttpStatus.FORBIDDEN);
        Long userId = loggedUser.getId();


       /* if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);*/
       /*User loggedUser = (User) session.getAttribute("user");
        if(loggedUser == null) {
            return new ResponseEntity("Nema sesije", HttpStatus.FORBIDDEN);
        }
        if(addShelfDto.getName().isEmpty())
            return new ResponseEntity("Emtpy field NAME!", HttpStatus.NO_CONTENT);*/

        Shelf shelf = new Shelf(addShelfDto);
        this.shelfService.save(shelf);
        shelf = shelfService.findByName(addShelfDto.getName());

        if(!this.userService.addShelf(shelf, userService.getById(userId)))
            return new ResponseEntity("Failed to add shelf (name already exists)!", HttpStatus.FORBIDDEN);


        shelf.setUser(loggedUser);


        this.shelfService.save(shelf);
       this.userService.save(loggedUser);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @DeleteMapping("/delete-shelf/{id}")
    public ResponseEntity deleteShelf(@PathVariable(name = "id") Long shelfId,HttpSession session){
        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null) {
            return new ResponseEntity("Have to be logged in!", HttpStatus.FORBIDDEN);
        }
        if(!shelfService.exists(shelfId))
        {
            return new ResponseEntity("No shelf with that id in the database", HttpStatus.NOT_FOUND);
        }
        /*if(shelfService.isPrimary(shelfId))
        {
            return new ResponseEntity("Cant be deleted, its primary!", HttpStatus.FORBIDDEN);
        }*/
        Long userId = loggedUser.getId();
        if(userService.deleteShelf(shelfId, userId)){

            User u = userService.getById(userId);
            System.out.print(u.getShelves());
            userService.save(u);

            return new ResponseEntity("Success", HttpStatus.OK);
        }

        return new ResponseEntity("U got no shelves with that id.", HttpStatus.FORBIDDEN);
    }
}
