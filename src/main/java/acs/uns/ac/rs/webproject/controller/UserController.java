package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import acs.uns.ac.rs.webproject.dto.LoginDto;
import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import acs.uns.ac.rs.webproject.entity.Role;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.Status;
import acs.uns.ac.rs.webproject.entity.User;
import acs.uns.ac.rs.webproject.repository.AccountActivationRequestRepository;
import acs.uns.ac.rs.webproject.service.AccountActivationRequestService;
import acs.uns.ac.rs.webproject.service.ShelfService;
import acs.uns.ac.rs.webproject.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
public class  UserController {
    @Autowired
    private UserService userService;
    private AccountActivationRequestService activationService;
    private ShelfService shelfService;

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

    @GetMapping("/api/users/search/{username}")
    public List<User> getAllByUsername(@PathVariable("username") String username){
        List<User> userList = userService.findAllByUsername(username);
        return userList;
    }

    @PostMapping("/api/save-user")
    public String saveUser(@RequestBody User user) {
        user.setRole(Role.READER);
        this.userService.save(user);
        return "Successfully saved an user!";
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        User loggedEmployee = (User) session.getAttribute("employee");

        if (loggedEmployee == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        // proverimo da li su podaci validni
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        User loggedEmployee = userService.login(loginDto.getUsername(), loginDto.getPassword());
        if (loggedEmployee == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("employee", loggedEmployee);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PutMapping("/api/approve/{id}")
    public ResponseEntity approve(@PathVariable(name = "id") Long id, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        AccountActivationRequest acc = activationService.findOne(id);
        acc.setStatus(Status.APPROVED);

        User user = new User(acc.getName(), acc.getSurname(), acc.getUsername(), acc.getMail(), acc.getPassword(), acc.getBirthDate(), Role.AUTHOR);

        Shelf wantToRead = this.shelfService.createShelf("Want to Read", true);
        user.addShelf(wantToRead);
        this.shelfService.save(wantToRead);
        Shelf current = this.shelfService.createShelf("Currently reading", true);
        user.addShelf(current);
        this.shelfService.save(current);
        Shelf read = this.shelfService.createShelf("Read", true);
        user.addShelf(read);
        this.shelfService.save(read);

        this.userService.save(user);

        activationService.updateAccountActivationRequest(id, Status.APPROVED);


        return new ResponseEntity("Successfully aprooved", HttpStatus.OK);
    }

    @PutMapping("/api/reject/{id}")
    public ResponseEntity approve(@PathVariable(name = "id") Long id, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        AccountActivationRequest acc = activationService.findOne(id);
        acc.setStatus(Status.REJECTED);

        activationService.updateAccountActivationRequest(id, Status.APPROVED);


        return new ResponseEntity("Successfully rejected", HttpStatus.OK);
    }
}
