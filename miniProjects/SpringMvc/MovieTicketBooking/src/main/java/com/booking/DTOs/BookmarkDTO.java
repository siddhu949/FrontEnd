package com.booking.DTOs;

import java.sql.Timestamp;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDTO {

    private int bookmarkId;
    private int movieId;
    private int userId;
    private Timestamp createdOn;
}
