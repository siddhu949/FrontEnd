# 🎬 Movie Ticket Booking Application

A **Spring MVC-based web application** for booking movie tickets. The application allows users to register, log in, browse movies, book tickets, make payments, review movies, and manage bookmarks. The backend uses **JDBC with MySQL** and the frontend is built with **JSP**.

---

## 🛠️ Technology Stack

- **Backend:** Java, Spring MVC  
- **Frontend:** JSP  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Password Security:** Spring Security BCrypt  
- **Logging:** SLF4J + Logback  

---

## 📂 Project Structure

MovieTicketBooking/
├── src/main/java/com/booking
│ ├── Controllers/
│ │ └── Handles HTTP requests, maps URLs to services
│ ├── DAO/
│ │ └── Interfaces for database operations
│ ├── DAOImpl/
│ │ └── DAO implementations using JDBC
│ ├── DTOs/
│ │ └── Data Transfer Objects for communication between layers
│ ├── ServiceImpl/
│ │ └── Business logic (seat availability, booking flow, payments)
│ └── Utils/
│ └── PasswordEncryptor, DBConnection
├── src/main/resources/
│ └── Configuration files (DB, Spring beans)
├── src/main/webapp/WEB-INF/views/
│ └── JSP pages for frontend
├── pom.xml
└── README.md


---

## 🗄️ Database Design

**Database:** `moviebookingdb`

**Tables:**

1. **users** – User registration details  
2. **movies** – Movie information (title, synopsis, cast, genre, rating, formats)  
3. **theatres** – Theatre name, address, city  
4. **shows** – Movie shows with time, date, theatre, and format  
5. **seats** – Show seats, type, price, availability  
6. **bookings** – Tickets booked by users with status and total price  
7. **payments** – Payment details linked to booking and user  
8. **rating** – User ratings and comments for movies  
9. **bookmark** – User bookmarked/favorite movies  

---

## 🔄 Application Flow

1. **Controller Layer** – Handles user requests (registration, login, booking, payment).  
2. **Service Layer** – Contains **business logic**:
   - Encrypting passwords  
   - Checking seat availability  
   - Booking tickets  
   - Handling payments  
   - Managing reviews and bookmarks  
3. **DAO Layer** – Direct interaction with **MySQL database** using JDBC.  
4. **DTO Layer** – Data transfer objects for carrying information between DAO, Service, and Controller.  
5. **Frontend JSP Pages** – Display data and forms to the user.

┌─────────────┐
│ User │
└─────┬───────┘
│ Sends request
▼
┌─────────────┐
│ Controller │ Handles HTTP requests (registration, login, booking)
└─────┬───────┘
│ Calls Service layer
▼
┌─────────────┐
│ ServiceImpl │ Business logic:
│ │ - Encrypt password
│ │ - Seat availability check
│ │ - Booking flow
│ │ - Payment processing
│ │ - Reviews & bookmarks
└─────┬───────┘
│ Calls DAO layer
▼
┌─────────────┐
│ DAO / JDBC │ Direct DB operations
└─────┬───────┘
│ Executes queries
▼
┌─────────────┐
│ MySQL DB │
└─────────────┘

---

## ⚡ Features

### User Module
- Register and login with **encrypted passwords**
- View and edit user profile

### Movie Module
- Browse all movies
- Search by title, genre, or language
- View detailed movie information including cast and rating

### Theatre & Show Module
- Browse theatres in a city
- View available shows for a movie in a theatre
- Show details include format and timings

### Seat & Booking Module
- Display seat availability for a show
- Book multiple seats
- Update seat status on booking

### Payment Module
- Process payments
- Update booking status to confirmed
- Store transaction details

### Review & Bookmark Module
- Add and view movie ratings and comments
- Bookmark favorite movies for later reference

---

## 🔧 Setup Instructions

1. **Clone the repository:**
```bash
git clone https://github.com/<username>/MovieTicketBooking.git
```
2.**Import project in Eclipse or IntelliJ as a Maven project.**

3.**Configure MySQL Database:**
```
CREATE DATABASE moviebookingdb;
```
- Execute the provided SQL script to create tables.
4.**Update database credentials in DBConnection.java:**
```
private static final String URL = "jdbc:mysql://localhost:3306/moviebookingdb";
private static final String USER = "root";
private static final String PASS = "password";
```
5.**Build the project:**
```
mvn clean install
```
6.**Run on Tomcat:**

- Deploy MovieTicketBooking.war to Apache Tomcat 10+

- Access in browser: http://localhost:8080/MovieTicketBooking

## 💡 Notes

### Password Security: User passwords are encrypted using Spring Security BCrypt before saving to the database.

### Seat Management: Only available seats can be booked; booked seats are marked unavailable.

### DTO Layer: Ensures clean separation between database and business logic.

### Logging: Uses SLF4J + Logback for easy debugging.

## 🔗 References

### Spring MVC Guide

### Spring Security BCrypt

### JDBC MySQL Tutorial

### SLF4J Documentation

### Logback Documentation