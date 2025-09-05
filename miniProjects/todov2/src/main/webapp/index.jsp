<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Login</h2>
    <form action="login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" name="username" id="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password" required>
        </div>
        <button type="submit" class="btn btn-success">Login</button>
    </form>

    <!-- Register link -->
    <p class="mt-3">
        Not a member? 
        <a href="register.jsp" class="text-decoration-none">Register here</a>
    </p>

    <%-- Messages --%>
    <%
        String msg = request.getParameter("msg");
        if ("invalid".equals(msg)) {
    %>
        <div class="alert alert-danger mt-3">Invalid username or password.</div>
    <% } else if ("error".equals(msg)) { %>
        <div class="alert alert-danger mt-3">Something went wrong. Try again.</div>
    <% } else if ("loginRequired".equals(msg)) { %>
        <div class="alert alert-warning mt-3">Please login first.</div>
    <% } else if ("logout".equals(msg)) { %>
        <div class="alert alert-success mt-3">You have been logged out successfully.</div>
    <% } %>
</div>
</body>
</html>
