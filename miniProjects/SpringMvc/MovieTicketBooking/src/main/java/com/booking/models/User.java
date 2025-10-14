package com.booking.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class User {
	private int userId;

	private String userName;
	private String email;
	private String password;
	private String city;
	private String preferredGenre;
	private Timestamp createdOn;
	private Timestamp modifyedOn;

}
