package com.book.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // This will resolve to /WEB-INF/views/register.jsp
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This will resolve to /WEB-INF/views/login.jsp
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile"; // This will resolve to /WEB-INF/views/profile.jsp
    }
}
