package com.booking.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking.DTOs.MovieDTO;

public class MovieDAOimpl implements MovieDAO{
	
	private final String jdbcURL = "jdbc:mysql://localhost:3306/moviebookingdb";
    private final String dbUser = "root";
    private final String dbPassword = "root";


	public boolean addMovie(MovieDTO moviedto) {
		 String sql = "INSERT INTO movies(movie_title, synopsis, cast, crew, release_date, poster_url, rating, genre, language, formats) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	             PreparedStatement ps = conn.prepareStatement(sql)){
			  ps.setString(1, moviedto.getMovieTitle());
	            ps.setString(2, moviedto.getSynopsis());
	            ps.setString(3, moviedto.getCast());
	            ps.setDate(5, new java.sql.Date(moviedto.getReleaseDate().getTime()));
	            ps.setString(6, moviedto.getPosterUrl());
	            ps.setDouble(7, moviedto.getRating());
	            ps.setString(8, moviedto.getGenre());
	            ps.setString(9, moviedto.getLanguage());
	            ps.setString(10, moviedto.getFormats());
	            ps.setInt(11, moviedto.getMovieId());
	            ps.setString(4, moviedto.getCrew());
	            return ps.executeUpdate() > 0;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 return false;
			
		 }
		
		
	}


	public MovieDTO getMovieById(int movieId) {
		 String sql = "SELECT * FROM movies WHERE movie_id = ?";
	        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, movieId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                MovieDTO movie = new MovieDTO();
	                movie.setMovieId(rs.getInt("movie_id"));
	                movie.setMovieTitle(rs.getString("movie_title"));
	                movie.setSynopsis(rs.getString("synopsis"));
	                movie.setCast(rs.getString("cast"));
	                movie.setCrew(rs.getString("crew"));
	                movie.setReleaseDate(rs.getDate("release_date"));
	                movie.setPosterUrl(rs.getString("poster_url"));
	                movie.setRating(rs.getDouble("rating"));
	                movie.setGenre(rs.getString("genre"));
	                movie.setLanguage(rs.getString("language"));
	                movie.setFormats(rs.getString("formats"));
	                return movie;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}


	public List<MovieDTO> getAllMovies() {
		 String sql = "SELECT * FROM movies";
	        List<MovieDTO> movies = new ArrayList<>();
	        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                MovieDTO movie = new MovieDTO();
	                movie.setMovieId(rs.getInt("movie_id"));
	                movie.setMovieTitle(rs.getString("movie_title"));
	                movie.setSynopsis(rs.getString("synopsis"));
	                movie.setCast(rs.getString("cast"));
	                movie.setCrew(rs.getString("crew"));
	                movie.setReleaseDate(rs.getDate("release_date"));
	                movie.setPosterUrl(rs.getString("poster_url"));
	                movie.setRating(rs.getDouble("rating"));
	                movie.setGenre(rs.getString("genre"));
	                movie.setLanguage(rs.getString("language"));
	                movie.setFormats(rs.getString("formats"));
	                movies.add(movie);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return movies;
	}

	public boolean updateMovie(MovieDTO moviedto) {
		String sql = "UPDATE movies SET movie_title=?, synopsis=?, cast=?, crew=?, release_date=?, poster_url=?, rating=?, genre=?, language=?, formats=? WHERE movie_id=?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, moviedto.getMovieTitle());
            ps.setString(2, moviedto.getSynopsis());
            ps.setString(3, moviedto.getCast());
            ps.setString(4, moviedto.getCrew());
            ps.setDate(5, new java.sql.Date(moviedto.getReleaseDate().getTime()));
            ps.setString(6, moviedto.getPosterUrl());
            ps.setDouble(7, moviedto.getRating());
            ps.setString(8, moviedto.getGenre());
            ps.setString(9, moviedto.getLanguage());
            ps.setString(10, moviedto.getFormats());
            ps.setInt(11, moviedto.getMovieId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}


	public boolean deleteMovie(int movieId) {
		  String sql = "DELETE FROM movies WHERE movie_id=?";
	        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, movieId);
	            return ps.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}


