package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Genre;
import acs.uns.ac.rs.webproject.service.GenreService;

import java.util.List;

@RestController
public class  GenreController {
    @Autowired
    private GenreService genreService;


    @GetMapping("/api/genres")
    public List<Genre> getGenres(){
        List<Genre> genreList = genreService.findAll();
        return genreList;
    }

    @GetMapping("/api/genres/{id}")
    public Genre getGenre(@PathVariable(name = "id") Long id){
        Genre genre = genreService.findOne(id);
        return genre;
    }

    @GetMapping("/api/genres/search/{genre}")
    public List<Genre> getAllByGenre(@PathVariable("genre") String genre){
        List<Genre> genreList = genreService.findAllByGenre(genre);
        return genreList;
    }

    @PostMapping("/api/save-genre")
    public String saveGenre(@RequestBody Genre genre) {
        this.genreService.save(genre);
        return "Successfully saved a genre!";
    }

}
