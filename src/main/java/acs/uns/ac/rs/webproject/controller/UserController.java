package acs.uns.ac.rs.webproject.controller;



import acs.uns.ac.rs.webproject.dto.ActivationDto;
import acs.uns.ac.rs.webproject.dto.BookDto;
import acs.uns.ac.rs.webproject.dto.UserDto;
import acs.uns.ac.rs.webproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import acs.uns.ac.rs.webproject.dto.LoginDto;
import acs.uns.ac.rs.webproject.dto.RegisterDto;
import acs.uns.ac.rs.webproject.repository.AccountActivationRequestRepository;
import acs.uns.ac.rs.webproject.repository.UserRepository;
import acs.uns.ac.rs.webproject.service.AccountActivationRequestService;
import acs.uns.ac.rs.webproject.service.AuthorService;
import acs.uns.ac.rs.webproject.service.ShelfService;
import acs.uns.ac.rs.webproject.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@RestController
public class  UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountActivationRequestService activationService;
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userService.findAll();
        /*List<UserDto> userDtos = new ArrayList<UserDto>();
        for(User user : userList)
        {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }*/
        if(userList.size() == 0)
            return new ResponseEntity(userList, HttpStatus.NOT_FOUND);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        if(user==null)
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        //UserDto userDto = new UserDto(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/api/users/search/{name}")
    public ResponseEntity<List<User>> getAllByName(@PathVariable("name") String name){

        List<User> userList = userService.findAllByName(name);
        /*List<UserDto> userDtos = new ArrayList<UserDto>();
        for(User user : userList)
        {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }*/
        if(userList.size() == 0)
            return new ResponseEntity(userList, HttpStatus.NOT_FOUND);
        return new ResponseEntity(userList, HttpStatus.OK);
    }
    @GetMapping("/api/users/search1/{surname}")
    public ResponseEntity<List<User>> getAllBySurname(@PathVariable("surname") String surname){
        List<User> userList = userService.findAllBySurname(surname);
       /* List<UserDto> userDtos = new ArrayList<UserDto>();
        for(User user : userList)
        {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }*/
        if(userList.size() == 0)
            return new ResponseEntity(userList, HttpStatus.NOT_FOUND);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("/api/users/search2/{username}")
    public ResponseEntity<List<User>> getAllByUsername(@PathVariable("username") String username){
        List<User> userList = userService.findAllByUsername(username);
       /* List<UserDto> userDtos = new ArrayList<UserDto>();
        for(User user : userList)
        {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }*/
        if(userList.size() == 0)
            return new ResponseEntity(userList, HttpStatus.NOT_FOUND);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @PostMapping("/api/save-user")
    public String saveUser(@RequestBody User user) {
        user.setRole(Role.READER);
        this.userService.save(user);
        return "Successfully saved an user!";
    }

    @PostMapping("/api/logout")
    public ResponseEntity Logout(HttpSession session){
        User loggedUser = (User) session.getAttribute("user");

        if (loggedUser == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        User loggedUser = userService.login(loginDto.getUsername(), loginDto.getPassword());
        if (loggedUser == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("user", loggedUser);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(registerDto.getUsername().isEmpty() || registerDto.getPassword().isEmpty() || registerDto.getEmail().isEmpty() || registerDto.getName().isEmpty() || registerDto.getSurname().isEmpty())
            return new ResponseEntity("You didn't enter all necessary data", HttpStatus.BAD_REQUEST);

        if(!userService.userCheck(registerDto.getUsername(), registerDto.getEmail()))
            return new ResponseEntity("User with that username or email already exists", HttpStatus.BAD_REQUEST);

        if(!registerDto.getPassword2().equals(registerDto.getPassword()))
            return new ResponseEntity("Password doesn't match", HttpStatus.BAD_REQUEST);

        User user = new User(registerDto);

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

        return new ResponseEntity("You sucessfully registered", HttpStatus.OK);
    }

    @PostMapping("/api/author-request")
    public ResponseEntity<String> activation(@RequestBody ActivationDto activationDto){
        if(activationDto.getMail().isEmpty() || activationDto.getPhoneNumber().isEmpty() || activationDto.getUsername().isEmpty() || activationDto.getPassword().isEmpty() || activationDto.getName().isEmpty() || activationDto.getSurname().isEmpty())
            return new ResponseEntity("You didn't enter all necessary data", HttpStatus.BAD_REQUEST);

        if(!userService.userCheck(activationDto.getUsername(), activationDto.getMail()))
            return new ResponseEntity("User with that username or email already exists", HttpStatus.BAD_REQUEST);

        if(!activationDto.getPassword2().equals(activationDto.getPassword()))
            return new ResponseEntity("Password doesn't match", HttpStatus.BAD_REQUEST);
        
        AccountActivationRequest request = new AccountActivationRequest(activationDto);

        this.activationService.save(request);
        return new ResponseEntity("You sucessfully apllied for author profile", HttpStatus.OK);
    }

    @PutMapping("/api/approve/{id}")
    public ResponseEntity<String> approve(@PathVariable(name = "id") Long id, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        
        AccountActivationRequest acc = this.activationService.findOne(id);

        Author author = new Author(acc.getName(), acc.getSurname(), acc.getUsername(), acc.getMail(), acc.getPassword(), acc.getBirthDate(), Role.AUTHOR, true);

        Shelf wantToRead = this.shelfService.createShelf("Want to Read", true);
        author.addShelf(wantToRead);
        this.shelfService.save(wantToRead);
        Shelf current = this.shelfService.createShelf("Currently reading", true);
        author.addShelf(current);
        this.shelfService.save(current);
        Shelf read = this.shelfService.createShelf("Read", true);
        author.addShelf(read);
        this.shelfService.save(read);

        this.authorService.save(author);

        acc.setStatus(Status.APPROVED);

        activationService.sendMail(acc, Status.APPROVED);

        this.activationService.save(acc);


        return new ResponseEntity("Successfully aprooved", HttpStatus.OK);
    }

    @PutMapping("/api/reject/{id}")
    public ResponseEntity<String> reject(@PathVariable(name = "id") Long id, HttpSession session){
        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        if(loggedUser.getRole() != Role.ADMIN)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        AccountActivationRequest acc = activationService.findOne(id);

        acc.setStatus(Status.REJECTED);

        activationService.sendMail(acc, Status.REJECTED);

        this.activationService.save(acc);

        return new ResponseEntity("Successfully rejected", HttpStatus.OK);
    }

    @PutMapping("/api/update-profile")
    public ResponseEntity<String> updateProfile(@RequestBody UserDto userDto, HttpSession session)
    {
        User loggedUser = (User) session.getAttribute("user");

        if(loggedUser == null)
            return new ResponseEntity("You are not logged in", HttpStatus.FORBIDDEN);

        if(userDto.getName() != null)
            loggedUser.setName(userDto.getName());
        if(userDto.getSurname() != null)
            loggedUser.setSurname(userDto.getSurname());
        if(userDto.getImage() != null)
            loggedUser.setImage(userDto.getImage());
        if(userDto.getAboutMe() != null)
            loggedUser.setBio(userDto.getAboutMe());
        if(userDto.getBirthday() != null)
            loggedUser.setBirthDate(userDto.getBirthday());

        if(userDto.getMail() != null || userDto.getPass() != null){
            User secondLogin = userService.login(loggedUser.getUsername(), userDto.getPassCheck());
        
            if (secondLogin == null)
                return new ResponseEntity<>("Password is incorrect!", HttpStatus.NOT_FOUND);

            if(userDto.getMail() != null)
                loggedUser.setMail(userDto.getMail());

            if(userDto.getPass() != null)
                loggedUser.setPassword(userDto.getPass());
        }
        
        userService.save(loggedUser);

        return new ResponseEntity("You have successfully updated profile", HttpStatus.OK);
    }



}
