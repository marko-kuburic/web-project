package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.AddShelfDto;
import acs.uns.ac.rs.webproject.dto.DeleteShelfDto;
import acs.uns.ac.rs.webproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.service.ShelfService;

import java.util.List;

@RestController
public class  ShelfController {
    @Autowired
    private ShelfService shelfService;

    private UserService userService;
    @GetMapping("/api/shelfs")
    public List<Shelf> getShelfs(){
        List<Shelf> shelfList = shelfService.findAll();
        return shelfList;
    }

    @GetMapping("/api/shelfs/{id}")
    public Shelf getShelf(@PathVariable(name = "id") Long id){
        Shelf shelf = shelfService.findOne(id);
        return shelf;
    }

    @GetMapping("/api/shelfs/search/{name}")
    public List<Shelf> getAllByName(@PathVariable("name") String name){
        List<Shelf> shelfList = shelfService.findAllByName(name);
        return shelfList;
    }

    @PostMapping("/api/save-shelf")
    public String saveShelf(@RequestBody Shelf shelf) {
        this.shelfService.save(shelf);
        return "Successfully saved a shelf!";
    }

    @PostMapping("/add-shelf")
    public String addShelf(@ModelAttribute AddShelfDto addShelfDto){

        if(addShelfDto.getName().isEmpty())
            return "fail"; //treba videt sta cemo

        if(shelfService.getUser(addShelfDto.getUserId()) == null)
        {
            return "fail"; //treba videt sta cemo
        }

        Shelf shelf = new Shelf(addShelfDto);
        this.shelfService.save(shelf);
        this.userService.addShelf(shelf, userService.getById(addShelfDto.getUserId()));
        return "success"; //treba videti gde ga slat************************
    }

    @DeleteMapping("/delete-shelf")
    public void deleteShelf(@ModelAttribute DeleteShelfDto delShelfDto){
        if(!userService.exists(delShelfDto.getUserID()))
        {
            return ;
        }
        userService.deleteShelf(delShelfDto.getId(), delShelfDto.getUserID());
    }
}
