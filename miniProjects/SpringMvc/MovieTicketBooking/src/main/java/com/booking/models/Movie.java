package com.booking.models;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class Movie {

	private int movieId;
	private String movieTitle;
	private String genre;
	private String language;
	private int duration;
	private Date releaseDate;
	private double rating;

}
