package com.todo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.todo.model.User;
import com.todo.connection.DbUtility;

import java.security.MessageDigest;

public class UserDAO {

    // --- Register user ---
    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, hashPassword(user.getPassword()));
            ps.setString(3, user.getEmail());
            return ps.executeUpdate() > 0;
        }
    }

    // --- Get user by username ---
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT user_id, username, password, email FROM users WHERE username = ?";
        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        }
        return null; // user not found
    }

    // --- Validate login ---
    public boolean validateLogin(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(hashPassword(password));
        }
        return false;
    }

    // --- Password hashing (SHA-256) ---
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
