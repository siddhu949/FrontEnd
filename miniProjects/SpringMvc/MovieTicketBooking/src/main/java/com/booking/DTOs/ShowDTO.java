package com.booking.DTOs;

import java.sql.Date;
import java.sql.Time;



public class ShowDTO {
	private int showId;
    private int movieId;          // which movie
    private int theatreId;        // which theatre
    public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public ShowDTO(int showId, int movieId, int theatreId, Date showDate, Time showTime, String format) {
		super();
		this.showId = showId;
		this.movieId = movieId;
		this.theatreId = theatreId;
		this.showDate = showDate;
		this.showTime = showTime;
		this.format = format;
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
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	private Date showDate;        // date of show
    private Time showTime;        // time of show
    private String format;        // e.g., 2D, 3D, IMAX
}
