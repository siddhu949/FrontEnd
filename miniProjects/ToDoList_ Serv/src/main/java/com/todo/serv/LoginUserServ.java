package com.todo.serv;





import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginUserServ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Login</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        out.println("</head><body><div class='container mt-5'>");
        out.println("<h2>Login</h2>");
        out.println("<form method='post'>");
        out.println("<div class='mb-3'><input type='text' name='username' class='form-control' placeholder='Username' required></div>");
        out.println("<div class='mb-3'><input type='password' name='password' class='form-control' placeholder='Password' required></div>");
        out.println("<button type='submit' class='btn btn-primary'>Login</button>");
        out.println("</form>");
        out.println("<p class='mt-3'>Don't have an account? <a href='register'>Register here</a></p>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DbUtility.connect()) {
            String sql = "SELECT userId FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password); // Plain text for now
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // User authenticated
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        session.setMaxInactiveInterval(30 * 60); // 30 min session

                        response.sendRedirect("todo"); // Redirect to Todo page
                    } else {
                        out.println("<p class='text-danger'>Invalid username or password. Try again.</p>");
                        out.println("<a href='login'>Back to login</a>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p class='text-danger'>Error: " + e.getMessage() + "</p>");
        }
    }
}
