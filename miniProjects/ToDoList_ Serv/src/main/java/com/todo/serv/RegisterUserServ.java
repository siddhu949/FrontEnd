package com.todo.serv;

import todo.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterUserServ extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Register</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        out.println("</head><body><div class='container mt-5'>");
        out.println("<h2>Register</h2>");
        out.println("<form method='post'>");
        out.println("<div class='mb-3'><input type='text' name='username' class='form-control' placeholder='Username' required></div>");
        out.println("<div class='mb-3'><input type='email' name='email' class='form-control' placeholder='Email'></div>");
        out.println("<div class='mb-3'><input type='password' name='password' class='form-control' placeholder='Password' required></div>");
        out.println("<button type='submit' class='btn btn-primary'>Register</button>");
        out.println("</form></div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (userDAO.checkUserExists(username)) {
                out.println("<p class='text-danger'>Username already exists. Try another.</p>");
            } else {
                userDAO.insertUser(username, password, email);
                out.println("<p class='text-success'>Registration successful! <a href='login'>Login here</a></p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p class='text-danger'>Error: " + e.getMessage() + "</p>");
        }
    }
}