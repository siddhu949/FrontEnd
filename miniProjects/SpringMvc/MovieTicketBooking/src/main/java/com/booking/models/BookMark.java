package com.booking.models;

import java.sql.Timestamp;

public class BookMark {
 private int bookMarkId;
 private int userId;    
 private int movieId;  
 private Timestamp createdOn;
 public int getBookMarkId() {
	return bookMarkId;
 }
 public void setBookMarkId(int bookMarkId) {
	this.bookMarkId = bookMarkId;
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
 public Timestamp getCreatedOn() {
	return createdOn;
 }
 public void setCreatedOn(Timestamp createdOn) {
	this.createdOn = createdOn;
 }
}
