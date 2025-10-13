package com.booking.models;

import java.sql.Timestamp;

public class Rating {
	    private int ratingId;
	    private int userId;       
	    private int movieId;      
	    private double rating;    
	    private String comment;
	    private Timestamp createdOn;
		public int getRatingId() {
			return ratingId;
		}
		public void setRatingId(int ratingId) {
			this.ratingId = ratingId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getMovieId() {
			return movieId;
		}
		public void setMovieId(int movieId) {
			this.movieId = movieId;
		}
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public Timestamp getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(Timestamp createdOn) {
			this.createdOn = createdOn;
		}
}
