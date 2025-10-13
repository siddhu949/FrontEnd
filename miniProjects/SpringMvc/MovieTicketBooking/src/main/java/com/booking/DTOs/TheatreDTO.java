package com.booking.DTOs;




public class TheatreDTO {
	public TheatreDTO(int theatreId, String theatreName, String city, String address) {
		
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.city = city;
		this.address = address;
	}
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
	private int theatreId;
    private String theatreName;
    private String city;
    private String address;
}
