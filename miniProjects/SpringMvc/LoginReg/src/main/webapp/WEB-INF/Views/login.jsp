<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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

        /* Login card */
        .login-card {
            background: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
            width: 350px;
            text-align: center;
        }

        .login-card h2 {
            margin-bottom: 25px;
            color: #333;
            font-size: 28px;
        }

        .login-card input[type="text"],
        .login-card input[type="password"] {
            width: 90%;
            padding: 12px;
            margin: 10px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 16px;
            transition: 0.3s;
        }

        .login-card input[type="text"]:focus,
        .login-card input[type="password"]:focus {
            border-color: #5563DE;
            box-shadow: 0 0 5px rgba(85, 99, 222, 0.5);
            outline: none;
        }

        .login-card input[type="submit"] {
            width: 95%;
            padding: 12px;
            margin-top: 15px;
            background-color: #5563DE;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s;
        }

        .login-card input[type="submit"]:hover {
            background-color: #3f4fc8;
        }

        .login-card a {
            display: block;
            margin-top: 15px;
            color: #5563DE;
            text-decoration: none;
            font-size: 14px;
            transition: 0.2s;
        }

        .login-card a:hover {
            text-decoration: underline;
        }

        .login-card span {
            display: block;
            margin-top: 10px;
            font-size: 14px;
        }

        .login-card .error {
            color: #e74c3c;
        }

        .login-card .message {
            color: #27ae60;
        }

    </style>
</head>
<body>
    <div class="login-card">
        <h2>Login</h2>
        <form action="login" method="post">
            <input type="text" name="username" placeholder="Username" required /><br/>
            <input type="password" name="password" placeholder="Password" required /><br/>
            <input type="submit" value="Login" />
        </form>
        <a href="register">New user? Register here.</a>
        <span class="error">${error}</span>
        <span class="message">${message}</span>
    </div>
</body>
</html>
