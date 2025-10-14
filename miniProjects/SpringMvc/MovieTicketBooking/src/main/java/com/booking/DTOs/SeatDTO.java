package com.booking.DTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    private int seatId;
    private int showId;           // which show the seat belongs to
    private String seatNumber;    // e.g., A1, B5
    private String seatType;      // e.g., Regular, VIP, Handicapped
    private double price;         // price of the seat
    private boolean isAvailable;  // true if seat is free
    private boolean isHandicapped;// true if reserved for handicapped
}
