package com.booking.DTOs;

import java.sql.Timestamp;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private int ratingId;
    private int userId;
    private int movieId;
    private double rating;  // e.g., 4.5
    private String comment;
    private Timestamp createdOn;
}
