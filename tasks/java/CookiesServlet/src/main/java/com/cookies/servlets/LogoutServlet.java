package com.cookies.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    c.setMaxAge(0); // delete cookie
                    res.addCookie(c);
                }
            }
        }

        out.println("<h3>You are logged out!</h3>");
        req.getRequestDispatcher("index.html").include(req, res);
    }
}
