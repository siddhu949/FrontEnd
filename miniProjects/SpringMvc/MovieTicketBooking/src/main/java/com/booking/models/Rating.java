package com.booking.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class Rating {
	    private int ratingId;
	    private int userId;       
	    private int movieId;      
	    private double rating;    
	    private String comment;
	    private Timestamp createdOn;
	
}
