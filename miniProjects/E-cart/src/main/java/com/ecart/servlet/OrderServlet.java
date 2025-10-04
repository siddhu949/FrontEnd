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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        int productId = Integer.parseInt(request.getParameter("productId"));

        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // transaction

            // 1. Get product price
            String productQuery = "SELECT price FROM products WHERE id=?";
            ps1 = conn.prepareStatement(productQuery);
            ps1.setInt(1, productId);
            rs = ps1.executeQuery();

            double price = 0;
            if(rs.next()) {
                price = rs.getDouble("price");
            }

            // 2. Insert into orders
            String insertOrder = "INSERT INTO orders(user_id, total_price, status) VALUES(?,?,?)";
            ps2 = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
            ps2.setInt(1, userId);
            ps2.setDouble(2, price);
            ps2.setString(3, "Pending");
            ps2.executeUpdate();

            // Get generated order id
            int orderId = 0;
            ResultSet orderKeys = ps2.getGeneratedKeys();
            if(orderKeys.next()) {
                orderId = orderKeys.getInt(1);
            }

            // 3. Insert into order_items
            String insertItem = "INSERT INTO order_items(order_id, product_id, quantity, price) VALUES(?,?,?,?)";
            ps3 = conn.prepareStatement(insertItem);
            ps3.setInt(1, orderId);
            ps3.setInt(2, productId);
            ps3.setInt(3, 1);
            ps3.setDouble(4, price);
            ps3.executeUpdate();

            conn.commit(); // commit transaction

            // Redirect to orders page
            response.sendRedirect("orders.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try { if(rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if(ps1 != null) ps1.close(); } catch (SQLException ignored) {}
            try { if(ps2 != null) ps2.close(); } catch (SQLException ignored) {}
            try { if(ps3 != null) ps3.close(); } catch (SQLException ignored) {}
            try { if(conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT o.id AS order_id, o.total_price, o.status, o.created_at, " +
                         "oi.product_id, oi.quantity, oi.price, p.name " +
                         "FROM orders o " +
                         "JOIN order_items oi ON o.id = oi.order_id " +
                         "JOIN products p ON oi.product_id = p.id " +
                         "WHERE o.user_id=? ORDER BY o.created_at DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            request.setAttribute("ordersResult", rs); 
            // ⚠️ note: for production, better to map into DTOs, but for now keep rs simple

            request.getRequestDispatcher("orders.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
