package com.booking.DAOs;

import com.booking.DTOs.BookmarkDTO;
import java.util.List;

public interface BookmarkDAO {
    boolean addBookmark(BookmarkDTO bookmark);           // Add a movie to bookmarks
    boolean removeBookmark(int bookmarkId);             // Remove a bookmarked movie
    List<BookmarkDTO> getBookmarksByUser(int userId);   // Get all bookmarks of a user
    boolean isBookmarked(int userId, int movieId);      // Check if movie is already bookmarked
}
