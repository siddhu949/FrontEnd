package com.booking.DAOs;

import com.booking.DTOs.BookingDTO;
import java.util.List;

public interface BookingDAO {
    boolean addBooking(BookingDTO booking);             // Create new booking
    BookingDTO getBookingById(int bookingId);          // Fetch a single booking
    List<BookingDTO> getBookingsByUser(int userId);    // Get all bookings for a user
    boolean updateBookingStatus(int bookingId, String status); // Update booking status
    boolean deleteBooking(int bookingId);              // Cancel/Delete a booking
}
