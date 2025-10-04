package com.ecart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecart.model.User;
import com.ecart.util.DBUtil;

public class UserDAO {

    // Register User
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (userName, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            return ps.executeUpdate() > 0; // true if inserted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Validate user login
    public User validateUser(String userName, String password) {
        User user = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, userName FROM users WHERE userName=? AND password=?")) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("userName"), "", ""); // set email/password empty
                user.setId(rs.getInt("id")); // important!
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
