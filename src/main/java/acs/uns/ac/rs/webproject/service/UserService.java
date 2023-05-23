package acs.uns.ac.rs.webproject.service;

import acs.uns.ac.rs.webproject.dto.UserDto;
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
    private ShelfService shelfservice;

    public User findOne(Long id) {
        Optional<User> foundUser = this.userRepository.findById(id);
        if (foundUser.isPresent())
            return foundUser.get();

        return null;
    }

    public List<User> findAllByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAllBySurname(String surname) {
        return userRepository.findBySurname(surname);
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

        if(shelfservice.findByName(shelf.getName())!=null)
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
                 shelves.remove(shelf);
                 userRepository.save(user);
                 return true;
            }
        }
        return false;
    }

    public void updateUser(UserDto userDto)
    {
        if(userDto.getPass()!=null)
            userRepository.findById(userDto.getUserId()).setPassword(userDto.getPass());

        if(userDto.getMail()!=null)
            userRepository.findById(userDto.getUserId()).setMail(userDto.getMail());

        if(userDto.getAboutMe()!=null)
            userRepository.findById(userDto.getUserId()).setBio(userDto.getAboutMe());

        if(userDto.getSurname()!=null)
            userRepository.findById(userDto.getUserId()).setSurname(userDto.getSurname());

        if(userDto.getImage()!=null)
            userRepository.findById(userDto.getUserId()).setImage(userDto.getImage());

        if(userDto.getBirthday()!=null)
            userRepository.findById(userDto.getUserId()).setBirthDate(userDto.getBirthday());

        if(userDto.getName()!=null)
            userRepository.findById(userDto.getUserId()).setName(userDto.getName());
    }


}
