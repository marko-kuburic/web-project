package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.repository.ShelfRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    public Shelf findOne(Long id)
    {
        Optional<Shelf> foundShelf = shelfRepository.findById(id);
        if (foundShelf.isPresent())
            return foundShelf.get();

        return null;
    }

    public List<Shelf> findAllByName(String name){return shelfRepository.findAllByName(name);}

    public List<Shelf> findAll(){ return shelfRepository.findAll();}

    public Shelf save(Shelf shelf){ return shelfRepository.save(shelf);}

}
