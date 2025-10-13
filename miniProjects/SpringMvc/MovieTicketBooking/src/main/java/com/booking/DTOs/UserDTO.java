package com.booking.DTOs;


public class UserDTO {
	private int userId;
	private String userName;
	private String email;
	private String city;
	private String preferredGenre;
	
	 // No-argument constructor
    public UserDTO() {}

    // All-argument constructor
    public UserDTO(int userId, String userName, String email, String city, String preferredGenre) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.city = city;
        this.preferredGenre = preferredGenre;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
