<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rate Movie</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #6e8efb, #a777e3);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding: 20px;
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            background: rgba(255, 255, 255, 0.95);
            transition: transform 0.3s, box-shadow 0.3s;
            width: 100%;
            max-width: 900px;
            text-align: center;
            animation: fadeIn 0.6s ease-out;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
        }

        .card-header {
            background: transparent;
            border-bottom: none;
            padding: 1rem 2rem;
        }

        .card-title {
            color: #333;
            font-weight: 600;
            font-size: 28px;
        }

        .card-body {
            padding: 2rem;
        }

        .rating-form {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-top: 10px;
            justify-content: center;
        }

        select {
            padding: 5px 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        button {
            padding: 6px 15px;
            border-radius: 8px;
            border: none;
            background: linear-gradient(to right, #6e8efb, #a777e3);
            color: #fff;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(110, 142, 251, 0.4);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        .success-message {
            color: green;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .btn-custom {
            display: inline-block;
            margin-top: 15px;
            border-radius: 10px;
            padding: 10px 20px;
            font-weight: 600;
            border: none;
            background: linear-gradient(to right, #6e8efb, #a777e3);
            color: #fff;
            text-decoration: none;
            transition: all 0.3s;
        }

        .btn-custom:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(110, 142, 251, 0.4);
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Rate Movie</h3>
        </div>
        <div class="card-body">

            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>

            <c:if test="${not empty message}">
                <p class="success-message">${message}</p>
            </c:if>

            <c:if test="${not empty rating}">
                <div class="success-message">
                    Your rating of ${rating.rating} for movie ID ${rating.movieId} has been saved!
                </div>
            </c:if>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Duration (mins)</th>
                        <th>Release Date</th>
                        <th>Rate</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="movie" items="${movies}">
                        <tr>
                            <td>${movie.movieId}</td>
                            <td>${movie.title}</td>
                            <td>${movie.genre}</td>
                            <td>${movie.duration}</td>
                            <td>${movie.releaseDate}</td>
                            <td>
                                <form action="rate-movie" method="post" class="rating-form">
                                    <input type="hidden" name="movieId" value="${movie.movieId}">
                                    <select name="rating" required>
                                        <option value="">Select Rating</option>
                                        <option value="1">1 - Poor</option>
                                        <option value="2">2 - Fair</option>
                                        <option value="3">3 - Good</option>
                                        <option value="4">4 - Very Good</option>
                                        <option value="5">5 - Excellent</option>
                                    </select>
                                    <button type="submit">Rate</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="<c:url value='/bookmyshow/welcome'/>" class="btn-custom">🏠 Back to Welcome Page</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
