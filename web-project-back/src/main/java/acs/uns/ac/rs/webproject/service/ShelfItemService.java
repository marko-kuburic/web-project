package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.repository.ShelfItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfItemService {

    @Autowired
    private ShelfItemRepository shelfItemRepository;

    public ShelfItem findOne(Long id)
    {
        Optional<ShelfItem> foundShelfItem = shelfItemRepository.findById(id);
        if (foundShelfItem.isPresent())
            return foundShelfItem.get();

        return null;
    }

    public void addReview(Review review, ShelfItem shelfItem){
        shelfItem.setReview(review);
    }
    public List<ShelfItem> findAllByTitle(String title){return shelfItemRepository.findAllByTitle(title);}

    public List<ShelfItem> findAll(){ return shelfItemRepository.findAll();}

    public ShelfItem save(ShelfItem shelfItem){ return shelfItemRepository.save(shelfItem);}

}
