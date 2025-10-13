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
	    public RegistrationDTO() {}

	    // All-argument constructor
	    public RegistrationDTO(String userName, String email, String password, String city, String preferredGenre) {
	        this.userName = userName;
	        this.email = email;
	        this.password = password;
	        this.city = city;
	        this.preferredGenre = preferredGenre;
	    }

	    // Getters and Setters
	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getPreferredGenre() {
	        return preferredGenre;
	    }

	    public void setPreferredGenre(String preferredGenre) {
	        this.preferredGenre = preferredGenre;
	    }
}
