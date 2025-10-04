package com.ecart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.ecart.dao.UserDAO;
import com.ecart.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("Uname"); // matches login.jsp
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser(userName, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUserName());
            session.setAttribute("userId", user.getId()); // <-- add this line
            response.sendRedirect("index.jsp");
        }
 else {
            response.sendRedirect("login.jsp?error=Invalid Username or Password");
        }

    }
}
