package com.book.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User signupUser(String name, String email, String password) throws Exception {
        // check if user already exists
        String checkSql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, email);
        if (count != null && count > 0) {
            throw new Exception("User with this email already exists");
        }

        // insert new user
        String hashedPassword = passwordEncoder.encode(password);
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, name, email, hashedPassword);

        // fetch the newly created user
        sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
            ),
            email
        );
    }

    @Override
    public boolean login(String email, String password) {
        try {
            String sql = "SELECT password FROM users WHERE email = ?";
            String storedPassword = jdbcTemplate.queryForObject(sql, String.class, email);
            return passwordEncoder.matches(password, storedPassword);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return false; // user not found
        }
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
                new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                ),
                email
            );
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new Exception("User not found with email: " + email);
        }
    }

	@Override
	public Long getCurrentUserId(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
