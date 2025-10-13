package com.booking.DAOs;

import java.util.List;

import com.booking.DTOs.MovieDTO;

public interface MovieDAO {
	boolean addMovie(MovieDTO moviedto);
	MovieDTO getMovieById(int movieId);
	List<MovieDTO> getAllMovies();
	boolean updateMovie(MovieDTO moviedto);
	boolean deleteMovie(int movieId);
}
