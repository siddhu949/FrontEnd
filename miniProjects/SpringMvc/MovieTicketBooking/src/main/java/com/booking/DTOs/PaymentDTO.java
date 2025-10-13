package com.booking.DTOs;

import java.sql.Timestamp;


public class PaymentDTO {
    private int paymentId;
    private int bookingId;       // associated booking
    private int userId;          // who paid
    private int showId;          // which show
    public int getPaymentId() {
		return paymentId;
	}
	public PaymentDTO(int paymentId, int bookingId, int userId, int showId, double amount, String paymentMethod,
			String paymentStatus, String transactionId, Timestamp createdOn) {
		super();
		this.paymentId = paymentId;
		this.bookingId = bookingId;
		this.userId = userId;
		this.showId = showId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.transactionId = transactionId;
		this.createdOn = createdOn;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	private double amount;
    private String paymentMethod;   // e.g., CreditCard, UPI, NetBanking
    private String paymentStatus;   // pending, completed, failed
    private String transactionId;   // bank transaction/reference ID
    private Timestamp createdOn;    // payment time
}
