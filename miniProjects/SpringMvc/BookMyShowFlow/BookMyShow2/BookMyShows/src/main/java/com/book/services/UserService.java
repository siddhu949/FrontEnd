package com.book.services;

import com.book.model.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    User signupUser(String name, String email, String password) throws Exception;
    boolean login(String email, String password) throws Exception;
    User getUserByEmail(String email) throws Exception;

    // Added for session handling
    Long getCurrentUserId(HttpSession session);
    boolean isUserLoggedIn(HttpSession session);
}
