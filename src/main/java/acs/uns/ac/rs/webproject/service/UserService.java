package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    public User findOne(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent())
            return foundUser.get();

        return null;
    }

    public List<User> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }

    public List<User> findAllBySurname(String surname) {
        return userRepository.findAllBySurname(surname);
    }

    public List<User> findAllByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User login(String username, String password) {

        User user = userRepository.getByUsername(username);
        if (user == null || !user.getPassword().equals(password))
            return null;
        return user;

    }

    public boolean userCheck(String username, String email) {

        User user1 = userRepository.getByUsername(username);
        User user2 = userRepository.getByMail(email);

        if (user1 == null && user2 == null)
            return true;
        return false;
    }

    public boolean exists(long id) {

        return userRepository.findById(id) != null;
    }

    public User getById(long id) {
        return userRepository.findById(id);
    }

    public boolean containsShelfName(String name, User user)
    {
        Set<Shelf> shelves = user.getShelves();
        for(Shelf shelf : shelves)
        {
            if(shelf.getName().equals(name))
                return true;
        }
        return false;
    }
    public boolean addShelf(Shelf shelf, User user)
    {
        if(shelf==null)
            return false;

        user.addShelf(shelf);
        return true;

    }

    public boolean deleteShelf(long shelfId, long userId)
    {
        User user = getById(userId);
        Set<Shelf> shelves = user.getShelves();
        for(Shelf shelf : shelves)
        {
            if(shelf.getId() == shelfId)
            {
                return shelves.remove(shelf);
            }
        }
        return false;
    }


}
