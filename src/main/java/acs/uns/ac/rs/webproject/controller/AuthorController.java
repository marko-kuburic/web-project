package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Author;
import acs.uns.ac.rs.webproject.service.AuthorService;

import java.util.List;

@RestController
public class  AuthorController {
    @Autowired
    private AuthorService authorService;
    
    @GetMapping("/api/authors")
    public List<Author> getAuthors(){
        List<Author> authorList = authorService.findAll();
        return authorList;
    }

    @GetMapping("/api/authors/{id}")
    public Author getAuthor(@PathVariable(name = "id") Long id){
        Author author = authorService.findOne(id);
        return author;
    }

    @GetMapping("/api/authors/search/{name}")
    public List<Author> getAllByName(@PathVariable("name") String name){
        List<Author> authorList = authorService.findAllByName(name);
        return authorList;
    }
    @GetMapping("/api/authors/search/{surname}")
    public List<Author> getAllBySurname(@PathVariable("surname") String surname){
        List<Author> authorList = authorService.findAllBySurname(surname);
        return authorList;
    }
    @GetMapping("/api/authors/active")
    public List<Author> getAllByIsActive(){
        List<Author> authorList = authorService.findAllByIsActive(true);
        return authorList;
    }

    @PostMapping("/api/save-author")
    public String saveAuthor(@RequestBody Author author) {
        this.authorService.save(author);
        return "Successfully saved an author!";
    }

}
