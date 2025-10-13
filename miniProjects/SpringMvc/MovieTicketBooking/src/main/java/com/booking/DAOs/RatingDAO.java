package com.booking.DAOs;

import com.booking.DTOs.RatingDTO;
import java.util.List;

public interface RatingDAO {

    boolean addRating(RatingDTO rating);                 // Add new rating
    List<RatingDTO> getRatingsByMovie(int movieId);      // Get all ratings for a movie
    List<RatingDTO> getRatingsByUser(int userId);        // Get all ratings by a user
    boolean updateRating(RatingDTO rating);              // Update rating and comment
    boolean deleteRating(int ratingId);                  // Delete a rating
}
