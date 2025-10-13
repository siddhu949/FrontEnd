package com.booking.DTOs;

import java.sql.Timestamp;

public class RatingDTO {

    private int ratingId;
    private int userId;
    private int movieId;
    private double rating;  // e.g., 4.5
    private String comment;
    private Timestamp createdOn;

    public RatingDTO() {}

    public RatingDTO(int ratingId, int userId, int movieId, double rating, String comment, Timestamp createdOn) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
        this.createdOn = createdOn;
    }

    // Getters and Setters
    public int getRatingId() { return ratingId; }
    public void setRatingId(int ratingId) { this.ratingId = ratingId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Timestamp getCreatedOn() { return createdOn; }
    public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; }
}
