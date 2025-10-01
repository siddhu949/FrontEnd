package com.book.services;



import com.book.model.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {

    public User signupUser(String name, String email, String password) throws Exception;

    public boolean login(String email, String password) throws Exception;
    public User getUserByEmail(String email) throws Exception;

	public Long getCurrentUserId(HttpSession session);
}
