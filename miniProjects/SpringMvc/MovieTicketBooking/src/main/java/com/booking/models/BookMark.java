package com.booking.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class BookMark {
 private int bookMarkId;
 private int userId;    
 private int movieId;  
 private Timestamp createdOn;

}
