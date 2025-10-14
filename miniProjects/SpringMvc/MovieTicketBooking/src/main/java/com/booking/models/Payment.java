package com.booking.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor
public class Payment {


	
	private int payementId;
	private int bookingId;
	private double amount;
	private String paymentMode;
	private String paymentStatus;
	
}
