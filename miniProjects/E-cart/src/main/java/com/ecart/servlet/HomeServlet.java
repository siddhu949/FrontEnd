package com.ecart.servlet;

import com.ecart.model.Product;
import com.ecart.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {

            // If products table empty → seed default products
            String countQuery = "SELECT COUNT(*) AS total FROM products";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(countQuery)) {
                if (rs.next() && rs.getInt("total") == 0) {
                    String insertSQL = "INSERT INTO products (name, price, description, image_url) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                        // Product 1
                        ps.setString(1, "Smartphone");
                        ps.setDouble(2, 499.0);
                        ps.setString(3, "Latest smartphone with amazing features");
                        ps.setString(4, "https://via.placeholder.com/300x200.png?text=Phone");
                        ps.executeUpdate();

                        // Product 2
                        ps.setString(1, "Laptop");
                        ps.setDouble(2, 899.0);
                        ps.setString(3, "High performance laptop for work and gaming");
                        ps.setString(4, "https://via.placeholder.com/300x200.png?text=Laptop");
                        ps.executeUpdate();

                        // Product 3
                        ps.setString(1, "Headphones");
                        ps.setDouble(2, 199.0);
                        ps.setString(3, "Noise-cancelling over-ear headphones");
                        ps.setString(4, "https://via.placeholder.com/300x200.png?text=Headphones");
                        ps.executeUpdate();
                    }
                }
            }

            // Always fetch products
            String selectSQL = "SELECT * FROM products";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getDouble("price"));
                    p.setDescription(rs.getString("description"));
                    p.setImageUrl(rs.getString("image_url"));
                    products.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // send products to JSP
        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
