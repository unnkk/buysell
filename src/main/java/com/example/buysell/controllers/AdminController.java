package com.example.buysell.controllers;

import com.example.buysell.enums.Role;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Principal principal, Model model){
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/ban/{id}")
    public String banUser(@PathVariable Long id){
        userService.banUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit/")
    public String editUser(@RequestParam("userId") Long id, @RequestParam Map<String,String> form){
        System.out.println("yo");
        userService.changeUserRoles(id, form);
        return "redirect:/admin";
    }
}
