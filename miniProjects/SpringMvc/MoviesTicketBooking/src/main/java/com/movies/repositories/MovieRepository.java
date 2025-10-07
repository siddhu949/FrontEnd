package com.movies.repositories;

import com.movies.models.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movies", (rs, rowNum) ->
                new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("price")
                ));
    }

    public int save(Movie movie) {
        return jdbcTemplate.update(
                "INSERT INTO movies(title, genre, price) VALUES (?, ?, ?)",
                movie.getTitle(), movie.getGenre(), movie.getPrice()
        );
    }
}
