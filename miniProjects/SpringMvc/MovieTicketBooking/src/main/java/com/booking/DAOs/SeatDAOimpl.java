package com.booking.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking.DTOs.SeatDTO;
import com.booking.Utils.DBConnection;

public class SeatDAOimpl implements SeatDAO {

	@Override
	public boolean addSeat(SeatDTO seat) {
		String sql = "INSERT INTO seats (show_id, seat_number, seat_type, price, is_available, is_handicapped) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, seat.getShowId());
            ps.setString(2, seat.getSeatNumber());
            ps.setString(3, seat.getSeatType());
            ps.setDouble(4, seat.getPrice());
            ps.setBoolean(5, seat.isAvailable());
            ps.setBoolean(6, seat.isHandicapped());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public SeatDTO getSeatById(int seatId) {
		 String sql = "SELECT * FROM seats WHERE seat_id = ?";
	        SeatDTO seat = null;

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, seatId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                seat = new SeatDTO(
	                        rs.getInt("seat_id"),
	                        rs.getInt("show_id"),
	                        rs.getString("seat_number"),
	                        rs.getString("seat_type"),
	                        rs.getDouble("price"),
	                        rs.getBoolean("is_available"),
	                        rs.getBoolean("is_handicapped")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return seat;
	}

	@Override
	public List<SeatDTO> getSeatsByShow(int showId) {
		 List<SeatDTO> seats = new ArrayList<>();
	        String sql = "SELECT * FROM seats WHERE show_id = ?";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, showId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                seats.add(new SeatDTO(
	                        rs.getInt("seat_id"),
	                        rs.getInt("show_id"),
	                        rs.getString("seat_number"),
	                        rs.getString("seat_type"),
	                        rs.getDouble("price"),
	                        rs.getBoolean("is_available"),
	                        rs.getBoolean("is_handicapped")
	                ));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return seats;
	}

	@Override
	public boolean updateSeat(SeatDTO seat) {
		String sql = "UPDATE seats SET seat_number = ?, seat_type = ?, price = ?, is_available = ?, is_handicapped = ? WHERE seat_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, seat.getSeatNumber());
            ps.setString(2, seat.getSeatType());
            ps.setDouble(3, seat.getPrice());
            ps.setBoolean(4, seat.isAvailable());
            ps.setBoolean(5, seat.isHandicapped());
            ps.setInt(6, seat.getSeatId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean deleteSeat(int seatId) {
		 String sql = "DELETE FROM seats WHERE seat_id = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, seatId);
	            int rows = ps.executeUpdate();
	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	}

	@Override
	public boolean markSeatUnavailable(int seatId) {
		 String sql = "UPDATE seats SET is_available = FALSE WHERE seat_id = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, seatId);
	            int rows = ps.executeUpdate();
	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}


