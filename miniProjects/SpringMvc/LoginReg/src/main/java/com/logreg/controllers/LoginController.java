package com.logreg.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/auth") //  group routes under /auth
public class LoginController {

    @Autowired
    private UserService userService;

    // Show login page
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        //  If already logged in → skip login page
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/auth/welcome";
        }
        return "login";
    }

    // Handle login POST
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/auth/welcome";
    }

    // Welcome page (protected)
    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", loggedInUser);
        model.addAttribute("users", userService.getAllUsers());
        return "welcome";
    }

   
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clears session
        return "redirect:/auth/login?logout"; // optional query param for UI
    }
}

