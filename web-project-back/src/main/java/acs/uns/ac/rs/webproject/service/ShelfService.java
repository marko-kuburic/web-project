package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.repository.ShelfRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    private UserService userService;

    public Shelf findOne(Long id)
    {
        Optional<Shelf> foundShelf = shelfRepository.findById(id);
        if (foundShelf.isPresent())
            return foundShelf.get();

        return null;
    }

    public Shelf findByName(String name) {return shelfRepository.getByName(name);}
    public List<Shelf> findAllByName(String name){return shelfRepository.findAllByName(name);}

    public List<Shelf> findAll(){ return shelfRepository.findAll();}

    public boolean save(Shelf shelf){
        shelfRepository.save(shelf);
        return true;
    }

    /*public Shelf addShelf(String name){

        Shelf newShelf = shelfRepository.getByName(name);
        if(newShelf == null || !newShelf.getName().equals(name))
            return null;
        return  newShelf;
    }*/

    public boolean exists(Long id)
    {
        return shelfRepository.findById(id)!=null;
    }

    public boolean existsShelfName(String name) {
        return shelfRepository.getByName(name) != null;
    }

    public User getUser(long id) {

        User user = userService.getById(id);
        if(user == null || user.getId() != id)
            return null;

        return user;

    }

    public Shelf createShelf(String name, boolean isPrimary)
    {
        Shelf newShelf = new Shelf(name, isPrimary);
        return newShelf;
    }

    public boolean isOnShelf(Book book, Shelf shelf)
    {
        for(ShelfItem shelfItem: shelf.getItems())
        {
            if(shelfItem.getBook().getId() == book.getId())
                return true;
        }
        return false;
    }
    public boolean isOnPrimary(Book book, User user)
    {
        Set<Shelf> list = user.getShelves();
        for(Shelf sh : list)
            if(sh.getPrimary())
                if(isOnShelf(book, sh))
                    return true;

        return false;

    }
    public boolean isPrimary(long shelfId)
    {
        Shelf read = findByName("Read");
        Shelf wantToRead = findByName("Want to Read");
        Shelf currentlyReading = findByName("Currently read");

        return read.getId() == shelfId || wantToRead.getId() == shelfId || currentlyReading.getId() == shelfId;
    }


    public boolean addBook(Book book, Shelf shelf)
    {
        ShelfItem item = new ShelfItem(null, book);

        return shelf.addItem(item);
    }

    public boolean addShelfItem(ShelfItem shelfItem, Shelf shelf)
    {
        return shelf.addItem(shelfItem);
    }

    public boolean removeBook(Book book, Shelf shelf)
    {
      if(!isOnShelf(book,shelf)) {
          return false;
      }


        for(ShelfItem shelfItem: shelf.getItems())
        {

            if(shelfItem.getBook().getId() == book.getId()) {

                 shelf.removeItem(shelfItem);
                 save(shelf);
                 return true;
            }

        }
        return false;
    }

    public boolean removeBookFromEverywhere(ShelfItem si, User user)
    {


        if(user.getShelves() == null)
            return false;
            
        for(Shelf shelf : user.getShelves())
        {
            si.setShelf(null);
            shelf.removeItem(si);
            save(shelf);
        }
        return true;
    }


  /*  public Shelf findOneByShelfItemId(Long itemId)
    {
        for(Shelf shelf : shelfRepository.findAll())
        {
            for(She)
        }
    }*/



}
