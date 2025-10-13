package com.booking.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.booking.DTOs.RatingDTO;
import com.booking.Utils.DBConnection;

public class RatingDAOImpl implements RatingDAO {

    @Override
    public boolean addRating(RatingDTO rating) {
        String sql = "INSERT INTO rating (user_id, movie_id, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, rating.getUserId());
            ps.setInt(2, rating.getMovieId());
            ps.setDouble(3, rating.getRating());
            ps.setString(4, rating.getComment());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    rating.setRatingId(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RatingDTO> getRatingsByMovie(int movieId) {
        List<RatingDTO> ratings = new ArrayList<>();
        String sql = "SELECT * FROM rating WHERE movie_id = ? ORDER BY created_on DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ratings.add(new RatingDTO(
                        rs.getInt("rating_id"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id"),
                        rs.getDouble("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_on")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }

    @Override
    public List<RatingDTO> getRatingsByUser(int userId) {
        List<RatingDTO> ratings = new ArrayList<>();
        String sql = "SELECT * FROM rating WHERE user_id = ? ORDER BY created_on DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ratings.add(new RatingDTO(
                        rs.getInt("rating_id"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id"),
                        rs.getDouble("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_on")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }

    @Override
    public boolean updateRating(RatingDTO rating) {
        String sql = "UPDATE rating SET rating = ?, comment = ?, created_on = CURRENT_TIMESTAMP WHERE rating_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, rating.getRating());
            ps.setString(2, rating.getComment());
            ps.setInt(3, rating.getRatingId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRating(int ratingId) {
        String sql = "DELETE FROM rating WHERE rating_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ratingId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
