package com.logreg.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth") // group routes under /auth
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Show registration page
    @GetMapping("/register")
    public String showForm() {
        return "registration";
    }

    // Handle registration form
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model) {
        if (userService.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists!");
            return "registration";
        }
        userService.addUser(new User(username, password));
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }
}
