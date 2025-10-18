package com.booking.controller;

import com.booking.model.*;
import com.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    // Home Page - Display all active movies
    @GetMapping("/")
    public String home(Model model) {
        try {
            List<Movie> movies = bookingService.getAllMovies();
            model.addAttribute("movies", movies);
            System.out.println("Loaded " + movies.size() + " movies"); // Debug log
        } catch (Exception e) {
            System.err.println("Error loading movies: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("movies", new ArrayList<>());
            model.addAttribute("error", "Unable to load movies. Please try again later.");
        }
        return "home";
    }
    
    // Movies Page - Same as home but can be extended
    @GetMapping("/movies")
    public String moviesPage(Model model) {
        try {
            List<Movie> movies = bookingService.getAllMovies();
            model.addAttribute("movies", movies);
        } catch (Exception e) {
            System.err.println("Error loading movies: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("movies", new ArrayList<>());
            model.addAttribute("error", "Unable to load movies. Please try again later.");
        }
        return "movies";
    }
    
    // About Page
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    
    // Search Movies
    @GetMapping("/search")
    public String searchMovies(@RequestParam(value = "movieName", required = false) String movieName, 
                               @RequestParam(value = "theatre", required = false) String theatre,
                               Model model) {
        try {
            List<Movie> movies;
            
            if (movieName != null && !movieName.trim().isEmpty()) {
                movies = bookingService.searchMovies(movieName.trim());
                System.out.println("Search results for '" + movieName + "': " + movies.size() + " movies"); // Debug log
            } else {
                movies = bookingService.getAllMovies();
            }
            
            model.addAttribute("movies", movies);
            model.addAttribute("searchTerm", movieName);
            
            if (movies.isEmpty()) {
                model.addAttribute("message", "No movies found matching your search.");
            }
        } catch (Exception e) {
            System.err.println("Error searching movies: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("movies", new ArrayList<>());
            model.addAttribute("error", "Search failed. Please try again.");
        }
        return "home";
    }
    
    // Theatre Selection Page - Show theatres and shows for selected movie
    @GetMapping("/theatres")
    public String showTheatres(@RequestParam(value = "movieId") int movieId,
                               @RequestParam(value = "date", required = false) String date,
                               Model model) {
        try {
            // Get movie details
            Movie movie = bookingService.getMovieById(movieId);
            model.addAttribute("movieId", movieId);
            model.addAttribute("movieName", movie.getMovieName());
            
            // Set date (current date if not provided)
            String selectedDate = (date != null && !date.isEmpty()) ? date : getCurrentDate();
            model.addAttribute("selectedDate", selectedDate);
            
            // Get theatres showing this movie
            List<Theatre> theatres = bookingService.getTheatresByMovieId(movieId);
            
            // For each theatre, get shows for the selected date
            Map<Integer, List<Show>> theatreShowsMap = new HashMap<>();
            for (Theatre theatre : theatres) {
                List<Show> shows = bookingService.getShowsByTheatreAndMovie(movieId, theatre.getTheatreId(), selectedDate);
                if (!shows.isEmpty()) {
                    theatreShowsMap.put(theatre.getTheatreId(), shows);
                }
            }
            
            model.addAttribute("theatres", theatres);
            model.addAttribute("theatreShowsMap", theatreShowsMap);
            
            System.out.println("Movie: " + movie.getMovieName() + ", Theatres: " + theatres.size()); // Debug log
            
            if (theatres.isEmpty()) {
                model.addAttribute("message", "No theatres available for this movie at the moment.");
            }
            
        } catch (Exception e) {
            System.err.println("Error loading theatres: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Unable to load theatre information. Please try again.");
            return "error";
        }
        
        return "theatres";
    }
    
    // Seat Selection Page
    @GetMapping("/seats")
    public String showSeats(@RequestParam(value = "showId") int showId,
                           Model model) {
        
        // Get show details
        Show show = bookingService.getShowById(showId);
        
        model.addAttribute("showId", showId);
        model.addAttribute("movieId", show.getMovieId());
        model.addAttribute("movieName", show.getMovieName());
        model.addAttribute("theatreName", show.getTheatreName());
        model.addAttribute("showTime", show.getShowTime().toString());
        model.addAttribute("date", show.getShowDate().toString());
        
        // Get seats layout
        Map<String, List<Seat>> seats = bookingService.getSeatsForShow(showId);
        model.addAttribute("seats", seats);
        
        // Get booked seats
        List<Integer> bookedSeatIds = bookingService.getBookedSeats(showId);
        model.addAttribute("bookedSeatIds", bookedSeatIds);
        
        return "seats";
    }
    
    // Booking Summary Page (Check Login)
    @PostMapping("/booking-summary")
    public String bookingSummary(@RequestParam(value = "showId") int showId,
                                 @RequestParam(value = "selectedSeats") String selectedSeats,
                                 HttpSession session,
                                 Model model) {
        
        // Check if user is logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Store booking details in session for after login
            Map<String, String> pendingBooking = new HashMap<>();
            pendingBooking.put("showId", String.valueOf(showId));
            pendingBooking.put("selectedSeats", selectedSeats);
            session.setAttribute("pendingBooking", pendingBooking);
            return "redirect:/login";
        }
        
        // Get show details
        Show show = bookingService.getShowById(showId);
        
        // Parse seat IDs
        List<Integer> seatIds = Arrays.stream(selectedSeats.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        // Get seat labels
        List<String> seatLabels = new ArrayList<>();
        for (Integer seatId : seatIds) {
            Seat seat = bookingService.getSeatById(seatId);
            seatLabels.add(seat.getSeatLabel());
        }
        
        // Calculate price breakdown
        Map<String, BigDecimal> priceBreakdown = bookingService.calculatePriceBreakdown(seatIds);
        
        model.addAttribute("showId", showId);
        model.addAttribute("movieName", show.getMovieName());
        model.addAttribute("theatreName", show.getTheatreName());
        model.addAttribute("showTime", show.getShowTime().toString());
        model.addAttribute("date", show.getShowDate().toString());
        model.addAttribute("selectedSeats", String.join(",", seatLabels));
        model.addAttribute("selectedSeatIds", selectedSeats);
        model.addAttribute("ticketPrice", priceBreakdown.get("ticketPrice"));
        model.addAttribute("convenienceFee", priceBreakdown.get("convenienceFee"));
        model.addAttribute("gst", priceBreakdown.get("gst"));
        model.addAttribute("totalPrice", priceBreakdown.get("total"));
        
        return "booking-summary";
    }
    
    // Login Page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
    
    // Login Process
    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password,
                       HttpSession session,
                       Model model) {
        
        // Validate user from database
        User user = bookingService.validateUser(username, password);
        
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            
            // Check if there's a pending booking
            @SuppressWarnings("unchecked")
            Map<String, String> pendingBooking = (Map<String, String>) session.getAttribute("pendingBooking");
            
            if (pendingBooking != null) {
                session.removeAttribute("pendingBooking");
                
                int showId = Integer.parseInt(pendingBooking.get("showId"));
                String selectedSeats = pendingBooking.get("selectedSeats");
                
                // Redirect to booking summary with parameters
                return "redirect:/booking-summary?showId=" + showId + "&selectedSeats=" + selectedSeats;
            }
            
            return "redirect:/";
        }
        
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
    
    // Register Page
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }
    
    // Register Process
    @PostMapping("/register")
    public String register(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "email") String email,
                          @RequestParam(value = "fullName") String fullName,
                          @RequestParam(value = "phoneNumber") String phoneNumber,
                          Model model) {
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        
        boolean success = bookingService.registerUser(user);
        
        if (success) {
            model.addAttribute("success", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("error", "Username or email already exists");
            return "register";
        }
    }
    
    // Confirm Booking and Generate Ticket
    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam(value = "showId") int showId,
                                @RequestParam(value = "selectedSeatIds") String selectedSeatIds,
                                @RequestParam(value = "movieName") String movieName,
                                @RequestParam(value = "theatreName") String theatreName,
                                @RequestParam(value = "showTime") String showTime,
                                @RequestParam(value = "date") String date,
                                @RequestParam(value = "selectedSeats") String selectedSeats,
                                @RequestParam(value = "totalPrice") String totalPrice,
                                HttpSession session,
                                Model model) {
        
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        try {
            // Parse seat IDs
            List<Integer> seatIds = Arrays.stream(selectedSeatIds.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            
            // Create booking
            Booking booking = bookingService.createBooking(user.getUserId(), showId, seatIds);
            
            // Get seat labels for display
            List<String> seatLabels = bookingService.getSeatLabelsForBooking(booking.getBookingId());
            
            model.addAttribute("ticketId", booking.getTicketNumber());
            model.addAttribute("bookingId", booking.getBookingId());
            model.addAttribute("username", user.getFullName());
            model.addAttribute("movieName", movieName);
            model.addAttribute("theatreName", theatreName);
            model.addAttribute("showTime", showTime);
            model.addAttribute("date", date);
            model.addAttribute("selectedSeats", String.join(",", seatLabels));
            model.addAttribute("totalPrice", booking.getTotalAmount());
            model.addAttribute("bookingDate", new SimpleDateFormat("yyyy-MM-dd").format(booking.getBookingDate()));
            
            return "ticket";
            
        } catch (Exception e) {
            model.addAttribute("error", "Booking failed: " + e.getMessage());
            return "error";
        }
    }
    
    // My Bookings Page
    @GetMapping("/my-bookings")
    public String myBookings(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        List<Booking> bookings = bookingService.getUserBookings(user.getUserId());
        model.addAttribute("bookings", bookings);
        
        return "my-bookings";
    }
    
    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    // Helper method to get current date
    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}