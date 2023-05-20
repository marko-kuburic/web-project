package acs.uns.ac.rs.webproject.controller;

import acs.uns.ac.rs.webproject.dto.*;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.service.BookService;
import acs.uns.ac.rs.webproject.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import acs.uns.ac.rs.webproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import acs.uns.ac.rs.webproject.entity.User;


@Controller
public class UserBasicController {
    
    @Autowired
    private UserService userService;

    private ShelfService shelfService;

    private BookService bookService;

    @GetMapping("/home")
    public String home(){
        return "index.html";
    }
    
    @GetMapping("/")
    public String root(){
        return "redirect:/home";
    }

    @GetMapping("/login-form")
    public String login(Model model){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("login", loginDto);
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session){
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return "redirect:/login-form";

        User employee = userService.login(loginDto.getUsername(), loginDto.getPassword());
        if (employee == null)
            return "redirect:/login-form";

        session.setAttribute("employee", employee);
        return "redirect:/home";
    }

    @GetMapping("/register-form")
    public String register(Model model){
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("register", registerDto);
        return "register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto registerDto){
        if(registerDto.getUsername().isEmpty() || registerDto.getPassword().isEmpty() || registerDto.getEmail().isEmpty() || registerDto.getName().isEmpty() || registerDto.getUsername().isEmpty())
            return "redirect:/register-form";

        if(!userService.userCheck(registerDto.getUsername(), registerDto.getEmail()))
            return "redirect:/register-form";

        if(!registerDto.getPassword2().equals(registerDto.getPassword()))
            return "redirect:/register-form";

        User user = new User(registerDto);

        Shelf wantToRead = shelfService.createShelf("Want to Read", true);
        user.addShelf(wantToRead);
        shelfService.save(wantToRead);
        Shelf current = shelfService.createShelf("Currently reading", true);
        user.addShelf(current);
        shelfService.save(current);
        Shelf read = shelfService.createShelf("Read", true);
        user.addShelf(read);
        shelfService.save(read);

        this.userService.save(user);

        return "redirect:/home";
    }

    @GetMapping("/register/author")
    public String activation(Model model){
        return "activation.html";
    }

  /*  @PostMapping("/add-shelf-form")  //nez sta ce mi ovo stvarno
    public String addShelf(Model model){
        AddShelfDto addShelfDto = new AddShelfDto();
        model.addAttribute("shelf", addShelfDto);
        return "register.html"; //treba videt ovo**********************************
    }*/

    @PutMapping("/updateProfile")
    public String updateProfile(UserDto userDto)
    {
        if(userDto.getMail()!=null || userDto.getPass()!=null)
            return "redirecet:/update-profile-form";

        userService.updateUser(userDto);
        return "success";
    }

    @PostMapping("/update-profile-form")
    public String updateProfileForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "pass.html"; //treba videt ovo**********************************
    }






}
