<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders - eCart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <h2>My Orders</h2>
        <hr>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("ordersResult");
            int lastOrderId = -1;
            if(rs != null) {
                while(rs.next()) {
                    int orderId = rs.getInt("order_id");
                    if(orderId != lastOrderId) {
                        if(lastOrderId != -1) { %>
                            </table><br>
                        <% }
        %>
                        <h4>Order #<%= orderId %></h4>
                        <p>Status: <%= rs.getString("status") %> | Date: <%= rs.getTimestamp("created_at") %></p>
                        <p>Total: $<%= rs.getDouble("total_price") %></p>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
        <%
                        lastOrderId = orderId;
                    }
        %>
                    <tr>
                        <td><%= rs.getString("name") %></td>
                        <td><%= rs.getInt("quantity") %></td>
                        <td>$<%= rs.getDouble("price") %></td>
                    </tr>
        <%
                }
                if(lastOrderId != -1) { %>
                    </tbody></table>
        <%      }
            } else { %>
                <p>No orders found.</p>
        <% } %>
    </div>

    <div class="footer text-center mt-4 mb-4">
        Made with ❤️ by Konkorde
    </div>

</body>
</html>
