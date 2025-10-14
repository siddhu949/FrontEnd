package com.booking.models;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data               // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor // Generates all-args constructor
public class Booking {
    private int bookingId;
    private int userId;
    private int showId;
    private int noOfTickets;
    private double totalAmount;
    private Timestamp bookingDate;
    private String status;
}
