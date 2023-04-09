package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id)
    {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent())
            return foundUser.get();

        return null;
    }

    public List<User> findAllByName(String name){return userRepository.findAllByName(name);}
    public List<User> findAllBySurname(String surname){return userRepository.findAllBySurname(surname);}
    public List<User> findAllByUsername(String username){return userRepository.findAllByUsername(username);}
    public List<User> findAll(){ return userRepository.findAll();}

    public User save(User user){ return userRepository.save(user);}

}
