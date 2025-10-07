package com.movies.services;

import com.movies.models.Movie;
import com.movies.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<Movie> getAllMovies() {
        return repo.findAll();
    }

    public void addMovie(Movie movie) {
        repo.save(movie);
    }
}
