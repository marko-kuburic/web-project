package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.service.UserService;

import java.util.List;

@RestController
public class  UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> userList = userService.findAll();
        return userList;
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        return user;
    }

    @GetMapping("/api/users/search/{name}")
    public List<User> getAllByName(@PathVariable("name") String name){
        List<User> userList = userService.findAllByName(name);
        return userList;
    }
    @GetMapping("/api/users/search/{surname}")
    public List<User> getAllBySurname(@PathVariable("surname") String surname){
        List<User> userList = userService.findAllBySurname(surname);
        return userList;
    }



    @PostMapping("/api/save-user")
    public String saveUser(@RequestBody User user) {
        this.userService.save(user);
        return "Successfully saved an user!";
    }

}
