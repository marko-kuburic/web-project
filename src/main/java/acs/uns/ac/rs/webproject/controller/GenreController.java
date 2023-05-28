package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.BookDto;
import acs.uns.ac.rs.webproject.dto.GenreDto;
import acs.uns.ac.rs.webproject.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Genre;
import acs.uns.ac.rs.webproject.entity.Role;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.service.GenreService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
public class  GenreController {
    @Autowired
    private GenreService genreService;


    @GetMapping("/api/genres")
    public ResponseEntity<List<Genre>> getGenres(){
        List<Genre> genreList = genreService.findAll();
        if(genreList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity(genreList, HttpStatus.OK);
    }

    @GetMapping("/api/genres/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable(name = "id") Long id){
        Genre genre = genreService.findOne(id);
        if(genre == null)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity(genre, HttpStatus.OK);
    }

    @PostMapping("/add-genre")
    public ResponseEntity addBook(@RequestBody GenreDto genreDto, HttpSession session)
    {
        User loggedUser = (User) session.getAttribute("user");
        if(loggedUser == null)
            return new ResponseEntity("Have to be logged in!", HttpStatus.FORBIDDEN);
        if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Have to be an author!", HttpStatus.FORBIDDEN);
        Long userId = loggedUser.getId();


        Genre genre = new Genre(genreDto);
        if(!genreService.findAllByGenre(genre.getGenre()).isEmpty())
            return new ResponseEntity("Already exists!", HttpStatus.FORBIDDEN);

        genreService.save(genre);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @GetMapping("/api/genres/search/{genre}")
    public ResponseEntity<List<Genre>> getAllByGenre(@PathVariable("genre") String genre){
        List<Genre> genreList = genreService.findAllByGenre(genre);
        if(genreList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity(genreList, HttpStatus.OK);
    }

    @PostMapping("/api/save-genre")
    public ResponseEntity<String> saveGenre(@RequestBody Genre genre, HttpSession session) {

        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        this.genreService.save(genre);
        return new ResponseEntity<String>("Successfully saved a genre!", HttpStatus.OK);
    }

}
