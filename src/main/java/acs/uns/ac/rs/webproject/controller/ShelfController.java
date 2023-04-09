package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.service.ShelfService;

import java.util.List;

@RestController
public class  ShelfController {
    @Autowired
    private ShelfService shelfService;


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

}
