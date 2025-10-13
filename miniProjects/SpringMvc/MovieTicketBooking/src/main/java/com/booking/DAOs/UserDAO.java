package com.booking.DAOs;

import com.booking.DTOs.RegistrationDTO;
import com.booking.DTOs.UserDTO;

public interface UserDAO {
	boolean registerUser(RegistrationDTO registrationdto);  // register new user
	UserDTO login(String email,String password);//login user
	UserDTO getUserById(int userId);//get the user by using user_id
}
