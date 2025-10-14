package com.booking.models;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class Show {

	private int showId;
	private int movieId;
	private int theatreId;
	private Date showdate;
	private Time showTime;
	private int avaliableSeats;
	private int totalSeats;
	
}
