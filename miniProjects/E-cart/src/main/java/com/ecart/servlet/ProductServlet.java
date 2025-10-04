package com.ecart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.ecart.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Hardcoded products
        String[][] products = {
                {"Smartphone", "499", "https://via.placeholder.com/300x200.png?text=Phone"},
                {"Laptop", "899", "https://via.placeholder.com/300x200.png?text=Laptop"},
                {"Headphones", "199", "https://via.placeholder.com/300x200.png?text=Headphones"},
                {"Smartwatch", "149", "https://via.placeholder.com/300x200.png?text=Smartwatch"},
                {"Tablet", "299", "https://via.placeholder.com/300x200.png?text=Tablet"}
        };

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (String[] p : products) {
                ps.setString(1, p[0]);
                ps.setDouble(2, Double.parseDouble(p[1]));
                ps.setString(3, p[2]);
                ps.executeUpdate();
            }

            response.getWriter().println("Products inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error inserting products: " + e.getMessage());
        }
    }
}
