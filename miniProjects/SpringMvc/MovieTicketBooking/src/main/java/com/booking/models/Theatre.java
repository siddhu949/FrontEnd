package com.booking.models;

public class Theatre {
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalScreens() {
		return totalScreens;
	}
	public void setTotalScreens(int totalScreens) {
		this.totalScreens = totalScreens;
	}
	private int theatreId;
	private String theatreName;
	private String city;
	private String address;
	private int totalScreens;
}
