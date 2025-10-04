package com.ecart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ecart.util.DBUtil;

import java.io.IOException;
import java.sql.*;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        int productId = Integer.parseInt(request.getParameter("productId"));

        try (Connection conn = DBUtil.getConnection()) {
            // Check if the product is already in the cart
            String checkQuery = "SELECT id, quantity FROM cart WHERE user_id=? AND product_id=?";
            try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    // Product exists, increment quantity
                    int cartId = rs.getInt("id");
                    int quantity = rs.getInt("quantity") + 1;
                    String updateQuery = "UPDATE cart SET quantity=? WHERE id=?";
                    try (PreparedStatement ps2 = conn.prepareStatement(updateQuery)) {
                        ps2.setInt(1, quantity);
                        ps2.setInt(2, cartId);
                        ps2.executeUpdate();
                    }
                } else {
                    // Product not in cart, insert new
                    String insertQuery = "INSERT INTO cart(user_id, product_id, quantity) VALUES(?,?,1)";
                    try (PreparedStatement ps2 = conn.prepareStatement(insertQuery)) {
                        ps2.setInt(1, userId);
                        ps2.setInt(2, productId);
                        ps2.executeUpdate();
                    }
                }
            }

            // Update cart count in session
            String countQuery = "SELECT SUM(quantity) AS total FROM cart WHERE user_id=?";
            try (PreparedStatement ps = conn.prepareStatement(countQuery)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                int cartCount = 0;
                if(rs.next()) {
                    cartCount = rs.getInt("total");
                }
                session.setAttribute("cartCount", cartCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect back to home page
        response.sendRedirect("home");
    }

}
