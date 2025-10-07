package com.movies.controllers;

import com.movies.models.Movie;
import com.movies.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", service.getAllMovies());
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        service.addMovie(movie);
        return "redirect:/";
    }
}
