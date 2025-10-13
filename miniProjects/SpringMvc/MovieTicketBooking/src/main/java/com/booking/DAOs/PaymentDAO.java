package com.booking.DAOs;

import com.booking.DTOs.PaymentDTO;
import java.util.List;

public interface PaymentDAO {
    boolean addPayment(PaymentDTO payment);                  // Add new payment
    PaymentDTO getPaymentById(int paymentId);               // Fetch single payment
    List<PaymentDTO> getPaymentsByBooking(int bookingId);   // All payments for a booking
    boolean updatePaymentStatus(int paymentId, String status); // Update status (pending/completed/failed)
}
