package com.cookies.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Cookie[] cookies = req.getCookies();
        String uname = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    uname = c.getValue();
                }
            }
        }

        if (uname != null) {
            out.println("<h2>Welcome, " + uname + "</h2>");
            out.println("<a href='logout'>Logout</a>");
        } else {
            out.println("<h3>Please login first.</h3>");
            req.getRequestDispatcher("index.html").include(req, res);
        }
    }
}
