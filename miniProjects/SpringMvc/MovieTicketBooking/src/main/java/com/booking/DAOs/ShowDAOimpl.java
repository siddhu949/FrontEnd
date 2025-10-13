package com.booking.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking.DTOs.ShowDTO;
import com.booking.Utils.DBConnection;

public class ShowDAOimpl implements ShowDAO {

	@Override
	public boolean addShow(ShowDTO show) {
		 String sql = "INSERT INTO shows (movie_id, theatre_id, show_date, show_time, format) VALUES (?, ?, ?, ?, ?)";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, show.getMovieId());
	            ps.setInt(2, show.getTheatreId());
	            ps.setDate(3, show.getShowDate());
	            ps.setTime(4, show.getShowTime());
	            ps.setString(5, show.getFormat());

	            int rows = ps.executeUpdate();
	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	}

	@Override
	public ShowDTO getShowById(int showId) {
		 String sql = "SELECT * FROM shows WHERE show_id = ?";
	        ShowDTO show = null;

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, showId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                show = new ShowDTO(
	                        rs.getInt("show_id"),
	                        rs.getInt("movie_id"),
	                        rs.getInt("theatre_id"),
	                        rs.getDate("show_date"),
	                        rs.getTime("show_time"),
	                        rs.getString("format")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return show;
	}

	@Override
	public List<ShowDTO> getShowsByMovie(int movieId) {
		List<ShowDTO> shows = new ArrayList<>();
        String sql = "SELECT * FROM shows WHERE movie_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                shows.add(new ShowDTO(
                        rs.getInt("show_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("theatre_id"),
                        rs.getDate("show_date"),
                        rs.getTime("show_time"),
                        rs.getString("format")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shows;
	}

	@Override
	public List<ShowDTO> getShowsByTheatre(int theatreId) {
		List<ShowDTO> shows = new ArrayList<>();
        String sql = "SELECT * FROM shows WHERE theatre_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, theatreId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                shows.add(new ShowDTO(
                        rs.getInt("show_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("theatre_id"),
                        rs.getDate("show_date"),
                        rs.getTime("show_time"),
                        rs.getString("format")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shows;
	}

	@Override
	public boolean updateShow(ShowDTO show) {
		 String sql = "UPDATE shows SET movie_id = ?, theatre_id = ?, show_date = ?, show_time = ?, format = ? WHERE show_id = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, show.getMovieId());
	            ps.setInt(2, show.getTheatreId());
	            ps.setDate(3, show.getShowDate());
	            ps.setTime(4, show.getShowTime());
	            ps.setString(5, show.getFormat());
	            ps.setInt(6, show.getShowId());

	            int rows = ps.executeUpdate();
	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	}

	@Override
	public boolean deleteShow(int showId) {
		 String sql = "DELETE FROM shows WHERE show_id = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, showId);
	            int rows = ps.executeUpdate();
	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}


