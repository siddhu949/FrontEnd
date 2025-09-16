<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        /* Full-screen gradient background */
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #74ABE2, #5563DE);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
        }

        @keyframes gradientBG {
            0% {background-position: 0% 50%;}
            50% {background-position: 100% 50%;}
            100% {background-position: 0% 50%;}
        }

        /* Welcome card */
        .welcome-card {
            background: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
            width: 400px;
            text-align: center;
        }

        .welcome-card h2 {
            margin-bottom: 15px;
            color: #333;
            font-size: 28px;
        }

        .welcome-card h3 {
            margin-top: 20px;
            color: #555;
        }

        .welcome-card ul {
            list-style-type: none;
            padding: 0;
            margin-top: 10px;
        }

        .welcome-card ul li {
            background-color: #f1f1f1;
            margin: 5px 0;
            padding: 8px 12px;
            border-radius: 6px;
            color: #333;
        }

        .logout-btn {
            margin-top: 25px;
            padding: 12px 20px;
            background-color: #5563DE;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            transition: 0.3s;
        }

        .logout-btn:hover {
            background-color: #3f4fc8;
        }

    </style>
</head>
<body>
    <div class="welcome-card">
        <h2>Welcome, ${user.username}!</h2>
        <h3>All Registered Users:</h3>
        <ul>
            <c:forEach items="${users}" var="u">
                <li>${u.username}</li>
            </c:forEach>
        </ul>
        <form action="logout" method="get">
            <input type="submit" class="logout-btn" value="Logout" />
        </form>
    </div>
</body>
</html>
