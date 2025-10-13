package com.booking.DTOs;




public class SeatDTO {
	    private int seatId;
	    private int showId;         // which show the seat belongs to
	    private String seatNumber;  // e.g., A1, B5
	    public SeatDTO(int seatId, int showId, String seatNumber, String seatType, double price, boolean isAvailable,
				boolean isHandicapped) {
			super();
			this.seatId = seatId;
			this.showId = showId;
			this.seatNumber = seatNumber;
			this.seatType = seatType;
			this.price = price;
			this.isAvailable = isAvailable;
			this.isHandicapped = isHandicapped;
		}
		public int getSeatId() {
			return seatId;
		}
		public void setSeatId(int seatId) {
			this.seatId = seatId;
		}
		public int getShowId() {
			return showId;
		}
		public void setShowId(int showId) {
			this.showId = showId;
		}
		public String getSeatNumber() {
			return seatNumber;
		}
		public void setSeatNumber(String seatNumber) {
			this.seatNumber = seatNumber;
		}
		public String getSeatType() {
			return seatType;
		}
		public void setSeatType(String seatType) {
			this.seatType = seatType;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public boolean isAvailable() {
			return isAvailable;
		}
		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}
		public boolean isHandicapped() {
			return isHandicapped;
		}
		public void setHandicapped(boolean isHandicapped) {
			this.isHandicapped = isHandicapped;
		}
		private String seatType;    // e.g., Regular, VIP, Handicapped
	    private double price;       // price of the seat
	    private boolean isAvailable;    // true if seat is free
	    private boolean isHandicapped;  // true if reserved for handicapped
}
