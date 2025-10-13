package com.booking.models;

import java.sql.Timestamp;

public class User {
	private int userId;
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
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getModifyedOn() {
		return modifyedOn;
	}
	public void setModifyedOn(Timestamp modifyedOn) {
		this.modifyedOn = modifyedOn;
	}
	private String userName;
	private String email;
	private String password;
	private String city;
	private String preferredGenre;
	private Timestamp createdOn;
	private Timestamp modifyedOn;

}
