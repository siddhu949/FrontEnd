package com.booking.DTOs;

import java.sql.Timestamp;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private int bookingId;
    private int userId;
    private int showId;
    private Timestamp bookingTime;
    private double totalPrice;
    private String status;  // pending, confirmed, canceled
}
