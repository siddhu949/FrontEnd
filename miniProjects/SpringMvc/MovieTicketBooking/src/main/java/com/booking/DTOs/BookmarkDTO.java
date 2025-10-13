package com.booking.DTOs;

import java.sql.Timestamp;

public class BookmarkDTO {

    private int bookmarkId;
    private int movieId;
    private int userId;
    private Timestamp createdOn;

    public BookmarkDTO() {}

    public BookmarkDTO(int bookmarkId, int movieId, int userId, Timestamp createdOn) {
        this.bookmarkId = bookmarkId;
        this.movieId = movieId;
        this.userId = userId;
        this.createdOn = createdOn;
    }

    // Getters and Setters
    public int getBookmarkId() { return bookmarkId; }
    public void setBookmarkId(int bookmarkId) { this.bookmarkId = bookmarkId; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Timestamp getCreatedOn() { return createdOn; }
    public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; }
}
