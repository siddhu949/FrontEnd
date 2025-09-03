package todo.dao;

import com.todo.serv.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public void insertUser(String username, String password, String email) throws Exception {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password); // Plain text for now
            ps.setString(3, email);

            ps.executeUpdate();
        }
    }

    public boolean checkUserExists(String username) throws Exception {
        String sql = "SELECT userId FROM users WHERE username = ?";
        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            return ps.executeQuery().next();
        }
    }
}

