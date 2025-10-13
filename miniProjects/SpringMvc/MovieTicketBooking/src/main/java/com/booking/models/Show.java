package com.booking.models;

import java.sql.Date;
import java.sql.Time;

public class Show {

	private int showId;
	private int movieId;
	private int theatreId;
	private Date showdate;
	private Time showTime;
	private int avaliableSeats;
	private int totalSeats;
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public Date getShowdate() {
		return showdate;
	}
	public void setShowdate(Date showdate) {
		this.showdate = showdate;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	public int getAvaliableSeats() {
		return avaliableSeats;
	}
	public void setAvaliableSeats(int avaliableSeats) {
		this.avaliableSeats = avaliableSeats;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
}
