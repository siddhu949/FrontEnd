package com.booking.models;

public class Payment {


	public int getPayementId() {
		return payementId;
	}
	public void setPayementId(int payementId) {
		this.payementId = payementId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	private int payementId;
	private int bookingId;
	private double amount;
	private String paymentMode;
	private String paymentStatus;
	
}
