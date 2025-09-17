//package com.book.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.book.dto.LoginRequestDto;
//import com.book.dto.LoginResponseDto;
//import com.book.dto.ResponseStatus;
//import com.book.dto.SignupUserRequestDTO;
//import com.book.dto.SignupUserResponseDTO;
//import com.book.model.User;
//import com.book.services.UserService;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/signup")
//    @ResponseBody
//    public SignupUserResponseDTO signupUser(@RequestBody SignupUserRequestDTO requestDTO) {
//        try {
//            User user = userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
//            SignupUserResponseDTO response = new SignupUserResponseDTO();
//            response.setResponseStatus(ResponseStatus.SUCCESS);
//            response.setName(user.getName());
//            response.setEmail(user.getEmail());
//            response.setUserId(user.getId());
//            return response;
//        } catch (Exception e) {
//            SignupUserResponseDTO response = new SignupUserResponseDTO();
//            response.setResponseStatus(ResponseStatus.FAILURE);
//            return response;
//        }
//    }
//
//    @PostMapping("/login")
//    @ResponseBody
//    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
//        try {
//            boolean loggedIn = userService.login(requestDto.getEmail(), requestDto.getPassword());
//            LoginResponseDto response = new LoginResponseDto();
//            response.setLoggedIn(loggedIn);
//            response.setResponseStatus(loggedIn ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE);
//            return response;
//        } catch (Exception e) {
//            LoginResponseDto response = new LoginResponseDto();
//            response.setLoggedIn(false);
//            response.setResponseStatus(ResponseStatus.FAILURE);
//            return response;
//        }
//    }
//}
package com.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.book.model.User;
import com.book.services.UserService;
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signupUserForm(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {
        try {
            User user = userService.signupUser(name, email, password);
            model.addAttribute("message", "Signup successful! Please login.");
            return "login"; // redirect to login.jsp
        } catch (Exception e) {
            model.addAttribute("message", "Signup failed. Try again.");
            return "register"; // stay on register.jsp
        }
    }

    @PostMapping("/login")
    public String loginUserForm(@RequestParam String email,
                                @RequestParam String password,
                                Model model) {
        try {
            boolean loggedIn = userService.login(email, password);
            if (loggedIn) {
                User user = userService.getUserByEmail(email);
                model.addAttribute("name", user.getName());
                model.addAttribute("email", user.getEmail());
                model.addAttribute("userId", user.getId());
                return "profile"; // profile.jsp
            } else {
                model.addAttribute("message", "Invalid email or password.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Login failed due to server error.");
            return "login";
        }
    }
}
