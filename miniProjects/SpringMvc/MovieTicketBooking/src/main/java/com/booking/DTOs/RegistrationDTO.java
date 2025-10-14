package com.booking.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
	  private String userName;
	    private String email;
	    private String password;         // plain password from form
	    private String city;
	    private String preferredGenre;
	    // No-argument constructor
	   
}
