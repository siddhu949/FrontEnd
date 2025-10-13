package com.booking.DAOs;


import com.booking.DTOs.BookmarkDTO;
import com.booking.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDAOImpl implements BookmarkDAO {

    @Override
    public boolean addBookmark(BookmarkDTO bookmark) {
        String sql = "INSERT INTO bookmark (movie_id, user_id) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, bookmark.getMovieId());
            ps.setInt(2, bookmark.getUserId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    bookmark.setBookmarkId(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeBookmark(int bookmarkId) {
        String sql = "DELETE FROM bookmark WHERE bookmark_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookmarkId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<BookmarkDTO> getBookmarksByUser(int userId) {
        List<BookmarkDTO> bookmarks = new ArrayList<>();
        String sql = "SELECT * FROM bookmark WHERE user_id = ? ORDER BY created_on DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bookmarks.add(new BookmarkDTO(
                        rs.getInt("bookmark_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("created_on")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookmarks;
    }

    @Override
    public boolean isBookmarked(int userId, int movieId) {
        String sql = "SELECT * FROM bookmark WHERE user_id = ? AND movie_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ResultSet rs = ps.executeQuery();

            return rs.next();  // true if exists

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
