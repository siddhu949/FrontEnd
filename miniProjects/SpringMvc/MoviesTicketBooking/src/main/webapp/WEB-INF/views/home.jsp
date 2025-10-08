<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>🎬 Movie Ticket Booking</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #141e30, #243b55);
            color: #fff;
            text-align: center;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        h1 {
            font-size: 3rem;
            margin-bottom: 0.5rem;
        }
        p {
            font-size: 1.2rem;
            opacity: 0.9;
        }
        a.button {
            margin-top: 20px;
            text-decoration: none;
            background: #ff4b2b;
            color: white;
            padding: 12px 28px;
            border-radius: 25px;
            font-weight: 600;
            transition: background 0.3s ease;
        }
        a.button:hover {
            background: #ff6f61;
        }
    </style>
</head>
<body>
    <h1>Welcome to Movie Ticket Booking 🎥</h1>
    <p>Book your favorite movies easily and quickly.</p>
    <a href="movies/list" class="button">Browse Movies</a>
</body>
</html>
