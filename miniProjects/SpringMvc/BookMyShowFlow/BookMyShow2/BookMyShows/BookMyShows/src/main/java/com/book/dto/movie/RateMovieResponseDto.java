package com.book.dto.movie;

import com.book.dto.*;
import com.book.model.*;

import lombok.Data;

@Data
public class RateMovieResponseDto {
    private MovieRating movieRating;
    public MovieRating getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	private ResponseStatus status;
}
