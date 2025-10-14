package com.booking.DTOs;

import java.sql.Date;
import java.sql.Time;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {

    private int showId;
    private int movieId;     // which movie
    private int theatreId;   // which theatre
    private Date showDate;   // date of show
    private Time showTime;   // time of show
    private String format;   // e.g., 2D, 3D, IMAX
}
