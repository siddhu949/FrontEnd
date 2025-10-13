package com.booking.DAOs;

import java.util.List;
import com.booking.DTOs.SeatDTO;

public interface SeatDAO {
    boolean addSeat(SeatDTO seat);
    SeatDTO getSeatById(int seatId);
    List<SeatDTO> getSeatsByShow(int showId);
    boolean updateSeat(SeatDTO seat); 
    boolean deleteSeat(int seatId);
    boolean markSeatUnavailable(int seatId);       // Mark a seat as booked
}
//package com.booking.DAO;
//
//import com.booking.DTOs.PaymentDTO;
//
//public interface PaymentDAO {
//    boolean addPayment(PaymentDTO payment);
//    PaymentDTO getPaymentById(int paymentId);
//    boolean updatePaymentStatus(int paymentId, String status);
//}
//package com.booking.DAO;
//
//import java.util.List;
//import com.booking.DTOs.BookingDTO;
//
//public interface BookingDAO {
//    boolean createBooking(BookingDTO booking);
//    BookingDTO getBookingById(int bookingId);
//    List<BookingDTO> getBookingsByUser(int userId);
//    boolean cancelBooking(int bookingId);
//}
