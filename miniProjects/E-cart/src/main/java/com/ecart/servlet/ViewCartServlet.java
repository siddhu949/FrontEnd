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

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        List<Product> cartItems = new ArrayList<>();
        double total = 0.0;

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT p.id, p.name, p.description, p.price, p.image_url, c.quantity " +
                         "FROM cart c JOIN products p ON c.product_id = p.id " +
                         "WHERE c.user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(rs.getDouble("price"));
                    p.setImageUrl(rs.getString("image_url"));

                    // store quantity in request scope
                    p.setQuantity(rs.getInt("quantity"));

                    cartItems.add(p);
                    total += p.getPrice() * p.getQuantity();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("total", total);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
