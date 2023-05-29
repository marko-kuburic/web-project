package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.Review2Dto;
import acs.uns.ac.rs.webproject.dto.ShelfDto;
import acs.uns.ac.rs.webproject.dto.ShelfItemDto;
import acs.uns.ac.rs.webproject.entity.Review;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.service.ShelfItemService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class  ShelfItemController {
    @Autowired
    private ShelfItemService shelfItemService;



    @GetMapping("/api/shelfItems")
    public ResponseEntity<List<ShelfItem>> getShelfItems(){
        List<ShelfItem> shelfItemList = shelfItemService.findAll();
        //List<ShelfItemDto> shelfItemDtos = new ArrayList<ShelfItemDto>();
        if(shelfItemList.size() == 0)
            return new ResponseEntity(shelfItemList, HttpStatus.NOT_FOUND);
       /* for(ShelfItem shelfItem : shelfItemList)
        {
            if(shelfItem!=null) {
                ShelfItemDto shelfItemDto = new ShelfItemDto(shelfItem);
                shelfItemDtos.add(shelfItemDto);
            }
        }
*/
        return new ResponseEntity(shelfItemList, HttpStatus.OK);
    }

    @GetMapping("/api/shelfItems/{id}")
    public ResponseEntity<ShelfItem> getShelfItem(@PathVariable(name = "id") Long id){
        ShelfItem shelfItem = shelfItemService.findOne(id);
        if(shelfItem == null)
            return new ResponseEntity(shelfItem, HttpStatus.NOT_FOUND);
        //ShelfItemDto shelfItemDto = new ShelfItemDto(shelfItem);

        return new ResponseEntity(shelfItem, HttpStatus.OK);
    }

    @GetMapping("/api/shelfItems/search/{title}")
    public ResponseEntity<List<ShelfItem>> getAllByName(@PathVariable("title") String title){
        List<ShelfItem> shelfItemList = shelfItemService.findAllByTitle(title);
        //List<ShelfItemDto> shelfItemDtos = new ArrayList<ShelfItemDto>();
        if(shelfItemList.size()==0)
            return new ResponseEntity(shelfItemList, HttpStatus.NOT_FOUND);
       /* for(ShelfItem shelfItem : shelfItemList)
        {
            ShelfItemDto shelfItemDto = new ShelfItemDto(shelfItem);
            shelfItemDtos.add(shelfItemDto);
        }
*/
        return new ResponseEntity(shelfItemList, HttpStatus.OK);
    }

    @PostMapping("/api/save-shelfItem")
    public String saveShelfItem(@RequestBody ShelfItem shelfItem) {
        this.shelfItemService.save(shelfItem);
        return "Successfully saved a shelfItem!";
    }

}
