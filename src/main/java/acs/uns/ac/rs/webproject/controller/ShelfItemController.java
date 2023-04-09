package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.service.ShelfItemService;

import java.util.List;

@RestController
public class  ShelfItemController {
    @Autowired
    private ShelfItemService shelfItemService;

    @GetMapping("/api/shelfItems")
    public List<ShelfItem> getShelfItems(){
        List<ShelfItem> shelfItemList = shelfItemService.findAll();
        return shelfItemList;
    }

    @GetMapping("/api/shelfItems/{id}")
    public ShelfItem getShelfItem(@PathVariable(name = "id") Long id){
        ShelfItem shelfItem = shelfItemService.findOne(id);
        return shelfItem;
    }

    @GetMapping("/api/shelfItems/search/{title}")
    public List<ShelfItem> getAllByName(@PathVariable("title") String title){
        List<ShelfItem> shelfItemList = shelfItemService.findAllByTitle(title);
        return shelfItemList;
    }

    @PostMapping("/api/save-shelfItem")
    public String saveShelfItem(@RequestBody ShelfItem shelfItem) {
        this.shelfItemService.save(shelfItem);
        return "Successfully saved a shelfItem!";
    }

}
