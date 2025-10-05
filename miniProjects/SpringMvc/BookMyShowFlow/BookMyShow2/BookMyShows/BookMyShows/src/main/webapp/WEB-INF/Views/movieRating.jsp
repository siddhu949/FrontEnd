<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Movie Ratings</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .rating-display {
            font-weight: bold;
            color: #2a6496;
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

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Movie Ratings</h3>
        </div>
        <div class="card-body">

            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Duration (mins)</th>
                        <th>Release Date</th>
                        <th>Average Rating</th>
                        <th>Number of Ratings</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="movieRating" items="${movieRatings}">
                        <tr>
                            <td>${movieRating.movieId}</td>
                            <td>${movieRating.title}</td>
                            <td>${movieRating.genre}</td>
                            <td>${movieRating.duration}</td>
                            <td>${movieRating.releaseDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${movieRating.averageRating > 0}">
                                        <span class="rating-display">${movieRating.averageRating}</span>/5
                                    </c:when>
                                    <c:otherwise>
                                        Not rated yet
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${movieRating.ratingCount}</td>
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
