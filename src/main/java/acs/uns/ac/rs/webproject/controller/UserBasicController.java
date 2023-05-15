package acs.uns.ac.rs.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import acs.uns.ac.rs.webproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import acs.uns.ac.rs.webproject.dto.LoginDto;
import acs.uns.ac.rs.webproject.dto.RegisterDto;
import acs.uns.ac.rs.webproject.entity.User;


@Controller
public class UserBasicController {
    
    @Autowired
    private UserService userService;

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

        User user = new User(registerDto);
        this.userService.save(user);

        return "redirect:/home";
    }

    @GetMapping("/register/author")
    public String activation(Model model){
        return "activation.html";
    }
}
