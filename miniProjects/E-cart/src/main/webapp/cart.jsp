<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecart.model.Product" %>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container mt-4">
    <h2>Your Shopping Cart</h2>

    <%
        List<Product> cartItems = (List<Product>) request.getAttribute("cartItems");
        Double total = (Double) request.getAttribute("total");
    %>

    <c:if test="${empty cartItems}">
        <p>Your cart is empty.</p>
    </c:if>

    <c:if test="${not empty cartItems}">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <% for (Product p : cartItems) { %>
                    <tr>
                        <td><img src="<%= p.getImageUrl() %>" width="80"></td>
                        <td><%= p.getName() %></td>
                        <td><%= p.getDescription() %></td>
                        <td>$<%= p.getPrice() %></td>
                        <td><%= p.getQuantity() %></td>
                        <td>$<%= p.getPrice() * p.getQuantity() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <h4>Total: $<%= total %></h4>
        <a href="checkout.jsp" class="btn btn-primary">Proceed to Checkout</a>
    </c:if>
</div>
</body>
</html>
