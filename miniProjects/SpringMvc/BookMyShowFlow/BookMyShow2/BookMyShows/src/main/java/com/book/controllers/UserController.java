package com.book.controllers;

import com.book.dto.LoginRequestDto;
import com.book.dto.LoginResponseDto;
import com.book.dto.ResponseStatus;
import com.book.dto.SignupUserRequestDTO;
import com.book.dto.SignupUserResponseDTO;
import com.book.model.User;
import com.book.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/signup")
    public String signupUserForm(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 Model model) {
        try {
            User user = userService.signupUser(name, email, password);
            model.addAttribute("message", "Signup successful! Please login.");
            logger.info("Signup successful for email: {}", email);
            return "login"; // Redirects to login.jsp
        } catch (Exception e) {
            logger.error("Signup failed for email: {}", email, e);
            model.addAttribute("message", "Signup failed. Try again.");
            return "register"; // Stays on register.jsp
        }
    }


    @PostMapping("/login")
    public String loginUserForm(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                Model model) {
        try {
            boolean loggedIn = userService.login(email, password);
            if (loggedIn) {
                User user = userService.getUserByEmail(email);
                model.addAttribute("name", user.getName());
                model.addAttribute("email", user.getEmail());
                model.addAttribute("userId", user.getId());
                logger.info("Login successful for email: {}", email);
                return "profile"; // profile.jsp
            } else {
                model.addAttribute("message", "Invalid email or password.");
                return "login";
            }
        } catch (Exception e) {
            logger.error("Login failed for email: {}", email, e);
            model.addAttribute("message", "Login failed due to server error.");
            return "login";
        }
    }


    @PostMapping("/api/signup")
    @ResponseBody
    public SignupUserResponseDTO signupUserApi(@RequestBody SignupUserRequestDTO requestDTO) {
        SignupUserResponseDTO response = new SignupUserResponseDTO();
        try {
            User user = userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setUserId(user.getId());
            logger.info("API signup successful for email: {}", user.getEmail());
        } catch (Exception e) {
            logger.error("API signup failed", e);
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }


    @PostMapping("/api/login")
    @ResponseBody
    public LoginResponseDto loginUserApi(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto response = new LoginResponseDto();
        try {
            boolean loggedIn = userService.login(requestDto.getEmail(), requestDto.getPassword());
            response.setLoggedIn(loggedIn);
            response.setResponseStatus(loggedIn ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE);
            logger.info("API login attempt for email: {}, status: {}", requestDto.getEmail(), response.getResponseStatus());
        } catch (Exception e) {
            logger.error("API login failed", e);
            response.setLoggedIn(false);
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}



