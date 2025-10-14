package com.booking.DTOs;

import java.sql.Timestamp;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private int paymentId;
    private int bookingId;       // associated booking
    private int userId;          // who paid
    private int showId;          // which show
    private double amount;
    private String paymentMethod;   // e.g., CreditCard, UPI, NetBanking
    private String paymentStatus;   // pending, completed, failed
    private String transactionId;   // bank transaction/reference ID
    private Timestamp createdOn;    // payment time
}
