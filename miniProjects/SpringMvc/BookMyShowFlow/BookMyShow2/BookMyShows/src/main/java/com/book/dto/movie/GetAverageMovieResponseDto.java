package com.book.dto.movie;
import com.book.dto.*;

import lombok.Data;

@Data
public class GetAverageMovieResponseDto {
    private double averageRating;
    private ResponseStatus status;
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
}
