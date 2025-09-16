package com.logreg.controllers;

import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Hardcoded users
        users.add(new User("john", "password123"));
        users.add(new User("alice", "alicepass"));
        users.add(new User("bob", "bobpass"));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u; // return the first match
            }
        }
        return null; // no match found
    }

    public List<User> getAllUsers() {
        return users;
    }
}