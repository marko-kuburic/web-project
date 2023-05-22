package acs.uns.ac.rs.webproject.controller;

import acs.uns.ac.rs.webproject.dto.*;
import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import acs.uns.ac.rs.webproject.entity.Book;
import acs.uns.ac.rs.webproject.entity.Shelf;
import acs.uns.ac.rs.webproject.entity.ShelfItem;
import acs.uns.ac.rs.webproject.service.AccountActivationRequestService;
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

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AccountActivationRequestService acctivationService;

    @GetMapping("/home")
    public String home(){
        return "index.html";
    }
    
    @GetMapping("/")
    public String root(){
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login-form";
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
        if(registerDto.getUsername().isEmpty() || registerDto.getPassword().isEmpty() || registerDto.getEmail().isEmpty() || registerDto.getName().isEmpty() || registerDto.getSurname().isEmpty())
            return "redirect:/register-form";

        if(!userService.userCheck(registerDto.getUsername(), registerDto.getEmail()))
            return "redirect:/register-form";

        if(!registerDto.getPassword2().equals(registerDto.getPassword()))
            return "redirect:/register-form";

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

        return "redirect:/home";
    }

    @GetMapping("/author-activation-form")
    public String activation(Model model){
        ActivationDto activationDto = new ActivationDto();
        model.addAttribute("activation", activationDto);
        return "activation.html";
    }

  /*  @PostMapping("/add-shelf-form")  //nez sta ce mi ovo stvarno
    public String addShelf(Model model){
        AddShelfDto addShelfDto = new AddShelfDto();
        model.addAttribute("shelf", addShelfDto);
        return "register.html"; //treba videt ovo**********************************
    }*/

    @PutMapping("/update-rofile")
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

    @PostMapping("/author-request")
    public String activation(@ModelAttribute ActivationDto activationDto){
        if(activationDto.getMail().isEmpty() || activationDto.getPhoneNumber().isEmpty() || activationDto.getUsername().isEmpty() || activationDto.getPassword().isEmpty() || activationDto.getName().isEmpty() || activationDto.getSurname().isEmpty())
            return "redirect:/register-form/activation-form";

        if(!userService.userCheck(activationDto.getUsername(), activationDto.getMail()))
            return "redirect:/register-form";

        if(!activationDto.getPassword2().equals(activationDto.getPassword()))
            return "redirect:/register-form";
        
        AccountActivationRequest request = new AccountActivationRequest(activationDto);
        System.out.print(request);
        this.acctivationService.save(request);
        return "redirect:/home";
    }

}
