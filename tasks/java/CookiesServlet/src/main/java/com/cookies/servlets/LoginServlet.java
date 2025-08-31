package com.cookies.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String remember = req.getParameter("remember");

        if (uname.equals("admin") && pwd.equals("1234")) {
            Cookie c = new Cookie("username", uname);

            if (remember != null) {
                c.setMaxAge(60 * 60 * 24 * 7); // Permanent cookie: 7 days
            } else {
                c.setMaxAge(-1); // Temporary (session) cookie
            }

            res.addCookie(c);
            res.sendRedirect("profile");
        } else {
            out.println("<h3>Invalid username or password!</h3>");
            req.getRequestDispatcher("index.html").include(req, res);
        }
    }
}
