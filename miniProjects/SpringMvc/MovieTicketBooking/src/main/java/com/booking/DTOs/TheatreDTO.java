package com.booking.DTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreDTO {

    private int theatreId;
    private String theatreName;
    private String city;
    private String address;
}
