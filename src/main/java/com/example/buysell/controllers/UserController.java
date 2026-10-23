package com.example.buysell.controllers;

import com.example.buysell.entities.User;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) boolean error, Model model){
        if(error){
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(User user, Model model){
        if(!userService.createUser(user)) {
            model.addAttribute("errorMsg", "Пользователь с email " + user.getEmail()
                    + " уже существует");
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/users/{user}")
    public String userInfo(@PathVariable User user, Model model){
        model.addAttribute("user", user);

        return "userInfo";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
