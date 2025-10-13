package com.booking.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.booking.DTOs.BookingDTO;
import com.booking.Utils.DBConnection;

public class BookingDAOImpl  implements BookingDAO{

	@Override
	public boolean addBooking(BookingDTO booking) {
		 String sql = "INSERT INTO bookings (user_id, show_id, total_price, status) VALUES (?, ?, ?, ?)";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	            ps.setInt(1, booking.getUserId());
	            ps.setInt(2, booking.getShowId());
	            ps.setDouble(3, booking.getTotalPrice());
	            ps.setString(4, booking.getStatus());

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                ResultSet rs = ps.getGeneratedKeys();
	                if (rs.next()) {
	                    booking.setBookingId(rs.getInt(1)); // set generated bookingId
	                }
	                return true;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	}

	@Override
	public BookingDTO getBookingById(int bookingId) {
		 String sql = "SELECT * FROM bookings WHERE booking_id = ?";
	        BookingDTO booking = null;

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, bookingId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                booking = new BookingDTO(
	                        rs.getInt("booking_id"),
	                        rs.getInt("user_id"),
	                        rs.getInt("show_id"),
	                        rs.getTimestamp("booking_time"),
	                        rs.getDouble("total_price"),
	                        rs.getString("status")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booking;
	}

	@Override
	public List<BookingDTO> getBookingsByUser(int userId) {
		 List<BookingDTO> bookings = new ArrayList<>();
	        String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY booking_time DESC";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                bookings.add(new BookingDTO(
	                        rs.getInt("booking_id"),
	                        rs.getInt("user_id"),
	                        rs.getInt("show_id"),
	                        rs.getTimestamp("booking_time"),
	                        rs.getDouble("total_price"),
	                        rs.getString("status")
	                ));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return bookings;
	}

	@Override
	public boolean updateBookingStatus(int bookingId, String status) {
		String sql = "UPDATE bookings SET status = ?, modified_on = CURRENT_TIMESTAMP WHERE booking_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, bookingId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean deleteBooking(int bookingId) {
		String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	}


