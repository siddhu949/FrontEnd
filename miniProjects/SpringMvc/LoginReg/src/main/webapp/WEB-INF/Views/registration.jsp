<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <style>
        /* Full-screen gradient background */
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #FF9A8B, #FF6A88, #FF99AC);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
        }

        @keyframes gradientBG {
            0% {background-position: 0% 50%;}
            50% {background-position: 100% 50%;}
            100% {background-position: 0% 50%;}
        }

        /* Registration card */
        .reg-card {
            background: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
            width: 350px;
            text-align: center;
        }

        .reg-card h2 {
            margin-bottom: 25px;
            color: #333;
            font-size: 28px;
        }

        .reg-card input[type="text"],
        .reg-card input[type="password"] {
            width: 90%;
            padding: 12px;
            margin: 10px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 16px;
            transition: 0.3s;
        }

        .reg-card input[type="text"]:focus,
        .reg-card input[type="password"]:focus {
            border-color: #FF6A88;
            box-shadow: 0 0 5px rgba(255, 106, 136, 0.5);
            outline: none;
        }

        .reg-card input[type="submit"] {
            width: 95%;
            padding: 12px;
            margin-top: 15px;
            background-color: #FF6A88;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s;
        }

        .reg-card input[type="submit"]:hover {
            background-color: #FF3A6F;
        }

        .reg-card a {
            display: block;
            margin-top: 15px;
            color: #FF6A88;
            text-decoration: none;
            font-size: 14px;
            transition: 0.2s;
        }

        .reg-card a:hover {
            text-decoration: underline;
        }

        .reg-card span {
            display: block;
            margin-top: 10px;
            font-size: 14px;
        }

        .reg-card .error {
            color: #e74c3c;
        }

    </style>
</head>
<body>
    <div class="reg-card">
        <h2>Registration</h2>
        <form action="register" method="post">
            <input type="text" name="username" placeholder="Username" required /><br/>
            <input type="password" name="password" placeholder="Password" required /><br/>
            <input type="submit" value="Register" />
        </form>
        <a href="login">Already have an account? Login here.</a>
        <span class="error">${error}</span>
    </div>
</body>
</html>
