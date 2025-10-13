package com.booking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello, Spring 6 + Jakarta EE 10 + Tomcat 10 + Java 17!");
        return "index"; // resolves to /WEB-INF/views/home.jsp
    }
}
