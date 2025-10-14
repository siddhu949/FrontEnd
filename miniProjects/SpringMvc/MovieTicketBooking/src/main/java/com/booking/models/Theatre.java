package com.booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class Theatre {
	
	private int theatreId;
	private String theatreName;
	private String city;
	private String address;
	private int totalScreens;
}
