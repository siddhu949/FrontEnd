<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Register</h2>
    <form action="register" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" name="username" id="username" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email (optional)</label>
            <input type="email" class="form-control" name="email" id="email">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>

    <%-- Messages --%>
    <% 
        String msg = request.getParameter("msg");
        if ("error".equals(msg)) {
    %>
        <div class="alert alert-danger mt-3">Registration failed. Try again.</div>
    <% 
        } else if ("registered".equals(msg)) {
    %>
        <div class="alert alert-success mt-3">Registration successful! Please <a href="index.jsp">login</a>.</div>
    <% 
        }
    %>
</div>
</body>
</html>
