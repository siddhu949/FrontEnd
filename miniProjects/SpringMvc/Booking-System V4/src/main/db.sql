-- ============================================
-- MOVIE BOOKING SYSTEM - COMPLETE SQL SETUP
-- ============================================
-- Run this entire file to set up the database

CREATE DATABASE movie_booking_db;
USE movie_booking_db;

-- 1. Users Table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Movies Table
CREATE TABLE movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_name VARCHAR(200) NOT NULL,
    genre VARCHAR(100),
    duration INT NOT NULL,
    rating DECIMAL(3,1),
    release_date DATE,
    description TEXT,
    language VARCHAR(50),
    image_url VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 3. Theatres Table
CREATE TABLE theatres (
    theatre_id INT PRIMARY KEY AUTO_INCREMENT,
    theatre_name VARCHAR(200) NOT NULL,
    location VARCHAR(200) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address TEXT,
    phone_number VARCHAR(15),
    total_screens INT DEFAULT 1,
    facilities TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 4. Screens Table
CREATE TABLE screens (
    screen_id INT PRIMARY KEY AUTO_INCREMENT,
    theatre_id INT NOT NULL,
    screen_name VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL,
    screen_type VARCHAR(50),
    FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id) ON DELETE CASCADE
);

-- 5. Seats Table
CREATE TABLE seats (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    screen_id INT NOT NULL,
    seat_row VARCHAR(5) NOT NULL,
    seat_number INT NOT NULL,
    seat_type VARCHAR(20) DEFAULT 'Standard',
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (screen_id) REFERENCES screens(screen_id) ON DELETE CASCADE,
    UNIQUE KEY unique_seat (screen_id, seat_row, seat_number)
);

-- 6. Shows Table
CREATE TABLE shows (
    show_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT NOT NULL,
    screen_id INT NOT NULL,
    show_date DATE NOT NULL,
    show_time TIME NOT NULL,
    base_price DECIMAL(10,2) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE,
    FOREIGN KEY (screen_id) REFERENCES screens(screen_id) ON DELETE CASCADE,
    UNIQUE KEY unique_show (screen_id, show_date, show_time)
);

-- 7. Bookings Table
CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    show_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_seats INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    booking_status VARCHAR(20) DEFAULT 'CONFIRMED',
    payment_status VARCHAR(20) DEFAULT 'PAID',
    payment_method VARCHAR(50),
    ticket_number VARCHAR(50) UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES shows(show_id) ON DELETE CASCADE
);

-- 8. Booking_Seats Table (Many-to-Many relationship)
CREATE TABLE booking_seats (
    booking_seat_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    seat_id INT NOT NULL,
    seat_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id) ON DELETE CASCADE,
    UNIQUE KEY unique_booking_seat (booking_id, seat_id)
);

-- 9. Show Seat Status Table (Track seat availability for each show)
CREATE TABLE show_seat_status (
    status_id INT PRIMARY KEY AUTO_INCREMENT,
    show_id INT NOT NULL,
    seat_id INT NOT NULL,
    is_booked BOOLEAN DEFAULT FALSE,
    booking_id INT NULL,
    FOREIGN KEY (show_id) REFERENCES shows(show_id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id) ON DELETE CASCADE,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE SET NULL,
    UNIQUE KEY unique_show_seat (show_id, seat_id)
);

-- Indexes for better query performance
CREATE INDEX idx_movies_active ON movies(is_active);
CREATE INDEX idx_theatres_city ON theatres(city);
CREATE INDEX idx_shows_date ON shows(show_date);
CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_show ON bookings(show_id);
CREATE INDEX idx_seat_status_show ON show_seat_status(show_id);

-- ============================================
-- INSERT SAMPLE DATA
-- ============================================

-- Insert Users
INSERT INTO users (username, password, email, full_name, phone_number) VALUES
('john_doe', 'password123', 'john@example.com', 'John Doe', '9876543210'),
('jane_smith', 'password456', 'jane@example.com', 'Jane Smith', '9876543211'),
('test_user', 'test123', 'test@example.com', 'Test User', '9999999999');

-- Insert Movies
INSERT INTO movies (movie_name, genre, duration, rating, release_date, description, language, is_active) VALUES
('Inception', 'Sci-Fi', 148, 8.8, '2010-07-16', 'A thief who steals corporate secrets through dream-sharing technology', 'English', TRUE),
('The Dark Knight', 'Action', 152, 9.0, '2008-07-18', 'Batman faces the Joker in an epic battle', 'English', TRUE),
('Interstellar', 'Sci-Fi', 169, 8.7, '2014-11-07', 'A team of explorers travel through a wormhole in space', 'English', TRUE),
('Avengers Endgame', 'Action', 181, 8.4, '2019-04-26', 'The Avengers assemble for a final battle', 'English', TRUE),
('Parasite', 'Thriller', 132, 8.6, '2019-05-30', 'A poor family schemes to become employed by a wealthy family', 'Korean', TRUE);

-- Insert Theatres
INSERT INTO theatres (theatre_name, location, city, address, phone_number, total_screens, facilities) VALUES
('PVR Cinemas', 'Downtown Mall', 'Mumbai', '123 Mall Road, Downtown, Mumbai', '022-12345678', 5, 'Parking,Food & Beverages,Wheelchair Access'),
('INOX Theatre', 'City Center', 'Mumbai', '456 City Center, Mumbai', '022-23456789', 4, 'Parking,Food & Beverages,Wheelchair Access,3D'),
('Cinepolis', 'Metro Mall', 'Mumbai', '789 Metro Mall, Mumbai', '022-34567890', 6, 'Parking,Food & Beverages,Wheelchair Access,IMAX');

-- Insert Screens
INSERT INTO screens (theatre_id, screen_name, total_seats, screen_type) VALUES
(1, 'Screen 1', 96, 'Standard'),
(1, 'Screen 2', 120, '3D'),
(2, 'Screen 1', 96, 'Standard'),
(2, 'Screen 2', 150, 'IMAX'),
(3, 'Screen 1', 96, 'Standard'),
(3, 'Screen 2', 180, '4DX');

-- Create Procedure to Insert Seats
DELIMITER //
CREATE PROCEDURE insert_seats_for_screen(IN p_screen_id INT, IN p_base_price DECIMAL(10,2))
BEGIN
    DECLARE v_row VARCHAR(5);
    DECLARE v_seat_num INT;
    DECLARE v_price DECIMAL(10,2);
    DECLARE v_seat_type VARCHAR(20);
    DECLARE row_chars VARCHAR(8) DEFAULT 'ABCDEFGH';
    DECLARE i INT DEFAULT 1;
    DECLARE j INT;
    
    WHILE i <= 8 DO
        SET v_row = SUBSTRING(row_chars, i, 1);
        SET j = 1;
        
        WHILE j <= 12 DO
            -- Premium seats in middle columns (5-8)
            IF j >= 5 AND j <= 8 THEN
                SET v_seat_type = 'Premium';
                SET v_price = p_base_price + 50;
            ELSE
                SET v_seat_type = 'Standard';
                SET v_price = p_base_price;
            END IF;
            
            INSERT INTO seats (screen_id, seat_row, seat_number, seat_type, price)
            VALUES (p_screen_id, v_row, j, v_seat_type, v_price);
            
            SET j = j + 1;
        END WHILE;
        
        SET i = i + 1;
    END WHILE;
END//
DELIMITER ;

-- Call procedure to insert seats for all screens
CALL insert_seats_for_screen(1, 200.00);
CALL insert_seats_for_screen(2, 250.00);
CALL insert_seats_for_screen(3, 200.00);
CALL insert_seats_for_screen(4, 300.00);
CALL insert_seats_for_screen(5, 200.00);
CALL insert_seats_for_screen(6, 350.00);

-- ============================================
-- INSERT SHOWS FOR ALL MOVIES
-- ============================================

-- Inception shows (movie_id = 1)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(1, 1, CURDATE(), '10:00:00', 200.00, TRUE),
(1, 1, CURDATE(), '13:00:00', 200.00, TRUE),
(1, 1, CURDATE(), '16:00:00', 200.00, TRUE),
(1, 1, CURDATE(), '19:00:00', 250.00, TRUE),
(1, 1, CURDATE(), '22:00:00', 250.00, TRUE),
(1, 2, CURDATE(), '11:00:00', 300.00, TRUE),
(1, 2, CURDATE(), '14:00:00', 300.00, TRUE),
(1, 2, CURDATE(), '17:00:00', 300.00, TRUE),
(1, 2, CURDATE(), '20:00:00', 350.00, TRUE),
(1, 3, CURDATE(), '10:30:00', 200.00, TRUE),
(1, 3, CURDATE(), '13:30:00', 200.00, TRUE),
(1, 3, CURDATE(), '16:30:00', 200.00, TRUE),
(1, 3, CURDATE(), '19:30:00', 250.00, TRUE),
(1, 4, CURDATE(), '12:00:00', 400.00, TRUE),
(1, 4, CURDATE(), '15:00:00', 400.00, TRUE),
(1, 4, CURDATE(), '18:00:00', 450.00, TRUE),
(1, 4, CURDATE(), '21:00:00', 450.00, TRUE),
(1, 5, CURDATE(), '09:30:00', 200.00, TRUE),
(1, 5, CURDATE(), '12:30:00', 200.00, TRUE),
(1, 5, CURDATE(), '15:30:00', 200.00, TRUE),
(1, 5, CURDATE(), '18:30:00', 250.00, TRUE),
(1, 5, CURDATE(), '21:30:00', 250.00, TRUE),
(1, 6, CURDATE(), '11:30:00', 450.00, TRUE),
(1, 6, CURDATE(), '14:30:00', 450.00, TRUE),
(1, 6, CURDATE(), '17:30:00', 500.00, TRUE),
(1, 6, CURDATE(), '20:30:00', 500.00, TRUE);

-- The Dark Knight shows (movie_id = 2)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(2, 1, CURDATE(), '11:00:00', 200.00, TRUE),
(2, 1, CURDATE(), '14:00:00', 200.00, TRUE),
(2, 1, CURDATE(), '17:00:00', 200.00, TRUE),
(2, 1, CURDATE(), '20:00:00', 250.00, TRUE),
(2, 2, CURDATE(), '10:00:00', 300.00, TRUE),
(2, 2, CURDATE(), '13:00:00', 300.00, TRUE),
(2, 2, CURDATE(), '16:00:00', 300.00, TRUE),
(2, 2, CURDATE(), '19:00:00', 350.00, TRUE),
(2, 3, CURDATE(), '11:30:00', 200.00, TRUE),
(2, 3, CURDATE(), '14:30:00', 200.00, TRUE),
(2, 3, CURDATE(), '17:30:00', 200.00, TRUE),
(2, 3, CURDATE(), '20:30:00', 250.00, TRUE),
(2, 4, CURDATE(), '10:30:00', 400.00, TRUE),
(2, 4, CURDATE(), '13:30:00', 400.00, TRUE),
(2, 4, CURDATE(), '16:30:00', 450.00, TRUE),
(2, 4, CURDATE(), '19:30:00', 450.00, TRUE),
(2, 5, CURDATE(), '10:00:00', 200.00, TRUE),
(2, 5, CURDATE(), '13:00:00', 200.00, TRUE),
(2, 5, CURDATE(), '16:00:00', 200.00, TRUE),
(2, 5, CURDATE(), '19:00:00', 250.00, TRUE),
(2, 6, CURDATE(), '12:00:00', 450.00, TRUE),
(2, 6, CURDATE(), '15:00:00', 450.00, TRUE),
(2, 6, CURDATE(), '18:00:00', 500.00, TRUE),
(2, 6, CURDATE(), '21:00:00', 500.00, TRUE);

-- Interstellar shows (movie_id = 3)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(3, 1, CURDATE(), '09:00:00', 200.00, TRUE),
(3, 1, CURDATE(), '12:00:00', 200.00, TRUE),
(3, 1, CURDATE(), '15:00:00', 200.00, TRUE),
(3, 1, CURDATE(), '18:00:00', 250.00, TRUE),
(3, 1, CURDATE(), '21:00:00', 250.00, TRUE),
(3, 2, CURDATE(), '09:30:00', 300.00, TRUE),
(3, 2, CURDATE(), '12:30:00', 300.00, TRUE),
(3, 2, CURDATE(), '15:30:00', 300.00, TRUE),
(3, 2, CURDATE(), '18:30:00', 350.00, TRUE),
(3, 3, CURDATE(), '09:00:00', 200.00, TRUE),
(3, 3, CURDATE(), '12:00:00', 200.00, TRUE),
(3, 3, CURDATE(), '15:00:00', 200.00, TRUE),
(3, 3, CURDATE(), '18:00:00', 250.00, TRUE),
(3, 4, CURDATE(), '11:00:00', 400.00, TRUE),
(3, 4, CURDATE(), '14:00:00', 400.00, TRUE),
(3, 4, CURDATE(), '17:00:00', 450.00, TRUE),
(3, 4, CURDATE(), '20:00:00', 450.00, TRUE),
(3, 5, CURDATE(), '09:00:00', 200.00, TRUE),
(3, 5, CURDATE(), '12:00:00', 200.00, TRUE),
(3, 5, CURDATE(), '15:00:00', 200.00, TRUE),
(3, 5, CURDATE(), '18:00:00', 250.00, TRUE),
(3, 5, CURDATE(), '21:00:00', 250.00, TRUE),
(3, 6, CURDATE(), '10:00:00', 450.00, TRUE),
(3, 6, CURDATE(), '13:00:00', 450.00, TRUE),
(3, 6, CURDATE(), '16:00:00', 500.00, TRUE),
(3, 6, CURDATE(), '19:00:00', 500.00, TRUE);

-- Avengers Endgame shows (movie_id = 4)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(4, 1, CURDATE(), '10:00:00', 250.00, TRUE),
(4, 1, CURDATE(), '13:00:00', 250.00, TRUE),
(4, 1, CURDATE(), '16:00:00', 250.00, TRUE),
(4, 1, CURDATE(), '19:00:00', 300.00, TRUE),
(4, 1, CURDATE(), '22:00:00', 300.00, TRUE),
(4, 2, CURDATE(), '11:00:00', 300.00, TRUE),
(4, 2, CURDATE(), '14:00:00', 300.00, TRUE),
(4, 2, CURDATE(), '17:00:00', 300.00, TRUE),
(4, 2, CURDATE(), '20:00:00', 350.00, TRUE),
(4, 3, CURDATE(), '10:30:00', 250.00, TRUE),
(4, 3, CURDATE(), '13:30:00', 250.00, TRUE),
(4, 3, CURDATE(), '16:30:00', 250.00, TRUE),
(4, 3, CURDATE(), '19:30:00', 300.00, TRUE),
(4, 4, CURDATE(), '12:00:00', 400.00, TRUE),
(4, 4, CURDATE(), '15:00:00', 400.00, TRUE),
(4, 4, CURDATE(), '18:00:00', 450.00, TRUE),
(4, 4, CURDATE(), '21:00:00', 450.00, TRUE),
(4, 5, CURDATE(), '09:30:00', 250.00, TRUE),
(4, 5, CURDATE(), '12:30:00', 250.00, TRUE),
(4, 5, CURDATE(), '15:30:00', 250.00, TRUE),
(4, 5, CURDATE(), '18:30:00', 300.00, TRUE),
(4, 5, CURDATE(), '21:30:00', 300.00, TRUE),
(4, 6, CURDATE(), '11:30:00', 450.00, TRUE),
(4, 6, CURDATE(), '14:30:00', 450.00, TRUE),
(4, 6, CURDATE(), '17:30:00', 500.00, TRUE),
(4, 6, CURDATE(), '20:30:00', 500.00, TRUE);

-- Parasite shows (movie_id = 5)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(5, 1, CURDATE(), '11:00:00', 200.00, TRUE),
(5, 1, CURDATE(), '14:00:00', 200.00, TRUE),
(5, 1, CURDATE(), '17:00:00', 200.00, TRUE),
(5, 1, CURDATE(), '20:00:00', 250.00, TRUE),
(5, 2, CURDATE(), '10:30:00', 300.00, TRUE),
(5, 2, CURDATE(), '13:30:00', 300.00, TRUE),
(5, 2, CURDATE(), '16:30:00', 300.00, TRUE),
(5, 2, CURDATE(), '19:30:00', 350.00, TRUE),
(5, 3, CURDATE(), '11:30:00', 200.00, TRUE),
(5, 3, CURDATE(), '14:30:00', 200.00, TRUE),
(5, 3, CURDATE(), '17:30:00', 200.00, TRUE),
(5, 3, CURDATE(), '20:30:00', 250.00, TRUE),
(5, 4, CURDATE(), '11:30:00', 400.00, TRUE),
(5, 4, CURDATE(), '14:30:00', 400.00, TRUE),
(5, 4, CURDATE(), '17:30:00', 450.00, TRUE),
(5, 5, CURDATE(), '10:00:00', 200.00, TRUE),
(5, 5, CURDATE(), '13:00:00', 200.00, TRUE),
(5, 5, CURDATE(), '16:00:00', 200.00, TRUE),
(5, 5, CURDATE(), '19:00:00', 250.00, TRUE),
(5, 6, CURDATE(), '12:30:00', 450.00, TRUE),
(5, 6, CURDATE(), '15:30:00', 450.00, TRUE),
(5, 6, CURDATE(), '18:30:00', 500.00, TRUE);

-- ============================================
-- INITIALIZE SEAT STATUS FOR ALL SHOWS
-- ============================================

-- Initialize seat status for all shows
INSERT INTO show_seat_status (show_id, seat_id, is_booked)
SELECT s.show_id, st.seat_id, FALSE
FROM shows s
CROSS JOIN seats st
WHERE st.screen_id = s.screen_id;

-- ============================================
-- BOOK SOME SAMPLE SEATS FOR TESTING
-- ============================================

-- Book seats for Show 1 (Inception 10:00 AM)
UPDATE show_seat_status 
SET is_booked = TRUE
WHERE show_id = 1
AND seat_id IN (
    SELECT seat_id FROM seats 
    WHERE screen_id = 1 
    AND (
        (seat_row = 'A' AND seat_number IN (1, 2, 3)) OR
        (seat_row = 'B' AND seat_number IN (1, 2))
    )
);

-- Book seats for Show 2 (Inception 13:00 PM)
UPDATE show_seat_status 
SET is_booked = TRUE
WHERE show_id = 2
AND seat_id IN (
    SELECT seat_id FROM seats 
    WHERE screen_id = 1 
    AND (
        (seat_row = 'C' AND seat_number IN (5, 6, 7)) OR
        (seat_row = 'D' AND seat_number IN (5, 6))
    )
);

-- Book seats for Show 3 (Inception 16:00 PM)
UPDATE show_seat_status 
SET is_booked = TRUE
WHERE show_id = 3
AND seat_id IN (
    SELECT seat_id FROM seats 
    WHERE screen_id = 1 
    AND (
        (seat_row = 'E' AND seat_number IN (4, 5, 6, 7, 8)) OR
        (seat_row = 'F' AND seat_number IN (5, 6))
    )
);

-- ============================================
-- CREATE TEST BOOKING
-- ============================================

-- Create test booking linked to booked seats
SET @test_user_id = (SELECT user_id FROM users WHERE username = 'test_user');

-- Insert test booking
INSERT INTO bookings (user_id, show_id, booking_date, total_seats, total_amount, booking_status, payment_status, ticket_number)
VALUES (@test_user_id, 1, NOW(), 5, 1000.00, 'CONFIRMED', 'PAID', CONCAT('TKT', UNIX_TIMESTAMP()));

-- Get booking ID and link to booked seats
SET @test_booking_id = LAST_INSERT_ID();

UPDATE show_seat_status 
SET booking_id = @test_booking_id
WHERE show_id = 1 
AND is_booked = TRUE
AND booking_id IS NULL;

-- Drop Temporary Procedure
DROP PROCEDURE IF EXISTS insert_seats_for_screen;

-- ============================================
-- VERIFICATION QUERIES
-- ============================================

SELECT '=== DATABASE SETUP COMPLETE ===' as Status;
SELECT CONCAT('Total Users: ', COUNT(*)) as Info FROM users;
SELECT CONCAT('Total Movies: ', COUNT(*)) as Info FROM movies;
SELECT CONCAT('Total Theatres: ', COUNT(*)) as Info FROM theatres;
SELECT CONCAT('Total Screens: ', COUNT(*)) as Info FROM screens;
SELECT CONCAT('Total Seats: ', COUNT(*)) as Info FROM seats;
SELECT CONCAT('Total Shows: ', COUNT(*)) as Info FROM shows;
SELECT CONCAT('Total Seat Statuses: ', COUNT(*)) as Info FROM show_seat_status;


USE movie_booking_db;

-- ============================================
--  ADDITIONAL MOVIES
-- ============================================

INSERT INTO movies (movie_name, genre, duration, rating, release_date, description, language, is_active) VALUES
('Dune: Part Two', 'Sci-Fi', 166, 8.3, '2024-03-01', 'Paul Atreides unites with the Fremen to wage war against House Harkonnen.', 'English', TRUE),
('Oppenheimer', 'Biography', 180, 8.9, '2023-07-21', 'The story of J. Robert Oppenheimer and the creation of the atomic bomb.', 'English', TRUE),
('RRR', 'Action', 182, 8.0, '2022-03-25', 'Two revolutionaries fight against the British Raj.', 'Telugu', TRUE),
('Jawan', 'Action', 169, 7.5, '2023-09-07', 'A high-octane thriller starring Shah Rukh Khan in dual roles.', 'Hindi', TRUE),
('Leo', 'Thriller', 164, 7.8, '2023-10-19', 'A cafe owner’s hidden past catches up with him.', 'Tamil', TRUE),
('Spider-Man: No Way Home', 'Action', 148, 8.3, '2021-12-17', 'Spider-Man faces multiple villains from the multiverse.', 'English', TRUE),
('KGF Chapter 2', 'Action', 168, 8.4, '2022-04-14', 'Rocky battles new enemies to retain his empire.', 'Kannada', TRUE);

-- ============================================
--  ADDITIONAL THEATRES
-- ============================================

INSERT INTO theatres (theatre_name, location, city, address, phone_number, total_screens, facilities) VALUES
('PVR ICON', 'Phoenix MarketCity', 'Bengaluru', 'Whitefield Main Road, Bengaluru', '080-99998888', 5, 'IMAX,Parking,Food,VIP Lounge'),
('INOX GVK One', 'GVK One Mall', 'Hyderabad', 'Banjara Hills, Hyderabad', '040-11112222', 4, '3D,Food,Recliner Seats'),
('Cinepolis VR Mall', 'VR Mall', 'Chennai', 'Anna Nagar, Chennai', '044-22223333', 5, '4DX,Parking,Food'),
('Miraj Cinemas', 'Forum Mall', 'Kochi', 'Edappally, Kochi', '0484-77778888', 4, 'Recliners,Snacks,3D'),
('PVR Plaza', 'Connaught Place', 'Delhi', 'CP Main Circle, New Delhi', '011-55556666', 3, 'VIP,IMAX,Snacks');

-- ============================================
--  ADDITIONAL SCREENS (New Theatres)
-- ============================================

INSERT INTO screens (theatre_id, screen_name, total_seats, screen_type) VALUES
(4, 'Screen 3', 120, 'Standard'),
(4, 'Screen 4', 120, 'Standard'),
(5, 'Screen 3', 150, '3D'),
(6, 'Screen 1', 100, 'Standard'),
(7, 'Screen 1', 120, 'Standard'),
(8, 'Screen 1', 96, 'Standard'),
(9, 'Screen 1', 120, 'IMAX'),
(10, 'Screen 1', 96, 'Standard'),
(11, 'Screen 1', 120, 'Standard'),
(12, 'Screen 1', 150, '4DX');

-- ============================================
--  UNIFORM SEAT INSERTION (SAME PRICE)
-- ============================================

DELIMITER //
CREATE PROCEDURE insert_uniform_seats(IN p_screen_id INT, IN p_price DECIMAL(10,2))
BEGIN
    DECLARE v_row VARCHAR(5);
    DECLARE v_num INT;
    DECLARE v_row_letters VARCHAR(8) DEFAULT 'ABCDEFGH';
    DECLARE i INT DEFAULT 1;

    WHILE i <= 8 DO
        SET v_row = SUBSTRING(v_row_letters, i, 1);
        SET v_num = 1;
        WHILE v_num <= 12 DO
            INSERT INTO seats (screen_id, seat_row, seat_number, seat_type, price)
            VALUES (p_screen_id, v_row, v_num, 'Standard', p_price);
            SET v_num = v_num + 1;
        END WHILE;
        SET i = i + 1;
    END WHILE;
END//
DELIMITER ;

-- Call for new screens (uniform pricing)
CALL insert_uniform_seats(7, 200.00);
CALL insert_uniform_seats(8, 250.00);
CALL insert_uniform_seats(9, 300.00);
CALL insert_uniform_seats(10, 200.00);
CALL insert_uniform_seats(11, 250.00);
CALL insert_uniform_seats(12, 350.00);

-- ============================================
--  ADDITIONAL SHOWS
-- ============================================

-- Dune: Part Two (movie_id = 6)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(6, 7, CURDATE(), '10:00:00', 200.00, TRUE),
(6, 7, CURDATE(), '13:00:00', 200.00, TRUE),
(6, 7, CURDATE(), '16:00:00', 200.00, TRUE),
(6, 7, CURDATE(), '19:00:00', 250.00, TRUE),
(6, 8, CURDATE(), '11:00:00', 250.00, TRUE),
(6, 8, CURDATE(), '14:00:00', 250.00, TRUE),
(6, 8, CURDATE(), '17:00:00', 250.00, TRUE),
(6, 8, CURDATE(), '20:00:00', 300.00, TRUE);

-- RRR (movie_id = 8)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(8, 9, CURDATE(), '09:30:00', 300.00, TRUE),
(8, 9, CURDATE(), '12:30:00', 300.00, TRUE),
(8, 9, CURDATE(), '15:30:00', 300.00, TRUE),
(8, 9, CURDATE(), '18:30:00', 350.00, TRUE),
(8, 9, CURDATE(), '21:30:00', 350.00, TRUE);

-- Spider-Man: No Way Home (movie_id = 11)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(11, 10, CURDATE(), '10:00:00', 200.00, TRUE),
(11, 10, CURDATE(), '13:00:00', 200.00, TRUE),
(11, 10, CURDATE(), '16:00:00', 200.00, TRUE),
(11, 10, CURDATE(), '19:00:00', 250.00, TRUE),
(11, 10, CURDATE(), '22:00:00', 250.00, TRUE);

-- KGF Chapter 2 (movie_id = 12)
INSERT INTO shows (movie_id, screen_id, show_date, show_time, base_price, is_active) VALUES
(12, 12, CURDATE(), '10:00:00', 350.00, TRUE),
(12, 12, CURDATE(), '13:00:00', 350.00, TRUE),
(12, 12, CURDATE(), '16:00:00', 350.00, TRUE),
(12, 12, CURDATE(), '19:00:00', 400.00, TRUE),
(12, 12, CURDATE(), '22:00:00', 400.00, TRUE);

-- ============================================
--  INITIALIZE SEAT STATUS FOR NEW SHOWS
-- ============================================

INSERT INTO show_seat_status (show_id, seat_id, is_booked)
SELECT s.show_id, st.seat_id, FALSE
FROM shows s
JOIN seats st ON st.screen_id = s.screen_id
WHERE s.show_id > (SELECT MAX(show_id) - 20 FROM shows);

-- ============================================
--  CLEANUP
-- ============================================

DROP PROCEDURE IF EXISTS insert_uniform_seats;

-- ============================================
--  FINAL CHECK
-- ============================================

SELECT '=== ADDITIONAL DATA INSERTED SUCCESSFULLY ===' AS Status;
SELECT CONCAT('Total Movies: ', COUNT(*)) AS Movies FROM movies;
SELECT CONCAT('Total Theatres: ', COUNT(*)) AS Theatres FROM theatres;
SELECT CONCAT('Total Screens: ', COUNT(*)) AS Screens FROM screens;
SELECT CONCAT('Total Seats: ', COUNT(*)) AS Seats FROM seats;
SELECT CONCAT('Total Shows: ', COUNT(*)) AS Shows FROM shows;
SELECT CONCAT('Total Seat Status: ', COUNT(*)) AS SeatStatus FROM show_seat_status;
