package com.booking.DTOs;

import java.util.Date;

public class MovieDTO {
    
    private int movieId;
    private String movieTitle;
    private String synopsis;
    private String cast;
    private String crew;
    private Date releaseDate;
    private String posterUrl;
    private double rating;
    private String genre;
    private String language;
    private String formats;

    //  Default constructor
    public MovieDTO() {
    }

    //  Parameterized constructor
    public MovieDTO(int movieId, String movieTitle, String synopsis, String cast, String crew,
                    Date releaseDate, String posterUrl, double rating,
                    String genre, String language, String formats) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.synopsis = synopsis;
        this.cast = cast;
        this.crew = crew;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.genre = genre;
        this.language = language;
        this.formats = formats;
    }

    //  Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    //  toString() for debugging
    @Override
    public String toString() {
        return "MovieDTO{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", cast='" + cast + '\'' +
                ", crew='" + crew + '\'' +
                ", releaseDate=" + releaseDate +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", formats='" + formats + '\'' +
                '}';
    }
}
