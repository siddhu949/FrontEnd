package com.booking.DAOs;

import java.util.List;

import com.booking.DTOs.ShowDTO;

public interface ShowDAO {
	boolean addShow(ShowDTO show);
    ShowDTO getShowById(int showId);
    List<ShowDTO> getShowsByMovie(int movieId);
    List<ShowDTO> getShowsByTheatre(int theatreId);
    boolean updateShow(ShowDTO show);
    boolean deleteShow(int showId);
}
