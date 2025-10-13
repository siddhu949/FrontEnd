package com.booking.DAOs;

import java.util.List;

import com.booking.DTOs.TheatreDTO;

public interface TheatreDAO {
	boolean addTheatre(TheatreDTO theatre);
	TheatreDTO getTheatreById(int theatreId);
	List<TheatreDTO> getAllTheatres();
	List<TheatreDTO> getTheatreByCity(String city);
	boolean updateTheatre(TheatreDTO theatre);
	boolean deleteTheatre(int theatreId);

}
