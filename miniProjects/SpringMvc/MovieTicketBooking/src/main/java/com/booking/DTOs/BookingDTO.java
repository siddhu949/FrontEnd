package com.booking.DTOs;

import java.sql.Timestamp;

public class BookingDTO {

    private int bookingId;
    private int userId;
    private int showId;
    private Timestamp bookingTime;
    private double totalPrice;
    private String status;  // pending, confirmed, canceled

    public BookingDTO() {}

    public BookingDTO(int bookingId, int userId, int showId, Timestamp bookingTime, double totalPrice, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showId = showId;
        this.bookingTime = bookingTime;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getShowId() { return showId; }
    public void setShowId(int showId) { this.showId = showId; }
    public Timestamp getBookingTime() { return bookingTime; }
    public void setBookingTime(Timestamp bookingTime) { this.bookingTime = bookingTime; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
