package com.booking.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.booking.DTOs.RegistrationDTO;
import com.booking.DTOs.UserDTO;
import com.booking.Utils.PasswordEncryptor;

public class UserDAOimpl implements UserDAO {
	   private final String jdbcURL = "jdbc:mysql://localhost:3306/moviebookingdb";
	    private final String dbUser = "root";
	    private final String dbPassword = "root";
	    private final String registerUser ="INSERT INTO users(user_name, email, password, city, preferred_genre) VALUES (?,?,?,?,?)";
	    private final String loginUser ="SELECT * FROM users WHERE email =?";
	    private final String getUserId ="SELECT * FROM USERS WHERE user_id =?";
	    //register user
		@Override
		public boolean registerUser(RegistrationDTO registrationdto) {
			try {
				Connection con =DriverManager.getConnection(jdbcURL,dbUser,dbPassword);
				PreparedStatement ps =con.prepareStatement(registerUser);
				
				//encrypt the password using Bcrypt
				String hashPassword = PasswordEncryptor.encrypt(registrationdto.getPassword());
				ps.setString(1,registrationdto.getUserName());
	            ps.setString(2, registrationdto.getEmail());
	            ps.setString(3, hashPassword);
	            ps.setString(4, registrationdto.getCity());
	            ps.setString(5, registrationdto.getPreferredGenre());
	            
	            int rowsInserted = ps.executeUpdate();
	            return rowsInserted > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			return false;
		}
		}
	    
		@Override
		public UserDTO login(String email, String password) {
			try {
				Connection con =DriverManager.getConnection(jdbcURL,dbUser,dbPassword);
				PreparedStatement ps =con.prepareStatement(loginUser);
				 ps.setString(1, email);
				 
				 ResultSet rs = ps.executeQuery();
				 
				 if(rs.next()) {
					 String storedHashedPassword = rs.getString("password");
					 

		                // Check password with BCrypt
		                if (PasswordEncryptor.matches(password, storedHashedPassword)) {
		                    // Password matches, return UserDTO
		                    UserDTO user = new UserDTO();
		                    user.setUserId(rs.getInt("user_id"));
		                    user.setUserName(rs.getString("user_name"));
		                    user.setEmail(rs.getString("email"));
		                    user.setCity(rs.getString("city"));
		                    user.setPreferredGenre(rs.getString("preferred_genre"));
		                    return user;
		                }
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//get the user by id
		@Override
		public UserDTO getUserById(int userId) {
			try {
				Connection con =DriverManager.getConnection(jdbcURL,dbUser,dbPassword);
				PreparedStatement ps = con.prepareStatement(getUserId);
				ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();
	            
	            if(rs.next()) {
	            	UserDTO user = new UserDTO();
	            	user.setUserId(rs.getInt("userId"));
	            	user.setUserName(rs.getString("user_name"));
	            	user.setEmail(rs.getString("email"));
	            	user.setCity(rs.getString("city"));
	            	user.setPreferredGenre(rs.getString("preferred_genre"));
	            	return user;
	            }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	 
	    
}
