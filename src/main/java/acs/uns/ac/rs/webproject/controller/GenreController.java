package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Genre;
import acs.uns.ac.rs.webproject.service.GenreService;

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

    @GetMapping("/api/genres/search/{genre}")
    public ResponseEntity<List<Genre>> getAllByGenre(@PathVariable("genre") String genre){
        List<Genre> genreList = genreService.findAllByGenre(genre);
        if(genreList.size()==0)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity(genreList, HttpStatus.OK);
    }

    @PostMapping("/api/save-genre")
    public String saveGenre(@RequestBody Genre genre) {
        this.genreService.save(genre);
        return "Successfully saved a genre!";
    }

}
