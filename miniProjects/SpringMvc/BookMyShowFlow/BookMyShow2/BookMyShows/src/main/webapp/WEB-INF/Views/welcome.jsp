<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
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
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            background: rgba(255, 255, 255, 0.9);
            transition: transform 0.3s, box-shadow 0.3s;
            width: 100%;
            max-width: 500px;
            margin: 20px;
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
            padding-bottom: 0;
        }

        .card-title {
            color: #333;
            font-weight: 600;
            font-size: 28px;
            margin-bottom: 15px;
        }

        .card-body {
            padding: 2rem;
        }

        .btn-custom {
            display: block;
            width: 100%;
            margin-bottom: 15px;
            border-radius: 10px;
            padding: 12px;
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
    </style>
</head>
<body>
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Welcome, ${name}!</h3>
        </div>
        <div class="card-body">
            <p class="mb-4">You have successfully logged in.</p>
            
            <a href="<c:url value='/ratings/rate-movie'/>" class="btn-custom">🎬 Rate a Movie</a>
            <a href="<c:url value='/ratings/movie-rating'/>" class="btn-custom">⭐ Get Movie Rating</a>
            <a href="<c:url value='/logout'/>" class="btn-custom">🚪 Logout</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
