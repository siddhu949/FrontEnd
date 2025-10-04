<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ecart.model.Product" %>

<%
    // Redirect to login if no session
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp?msg=loginRequired");
        return;
    }

    // Get the product list from HomeServlet
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - eCart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <h2 class="mb-4">Welcome, <%= username %>! to eCart</h2>

        <div class="row row-cols-1 row-cols-md-3 g-4">
            <% if (products != null) {
                for (Product p : products) { %>
                    <div class="col">
                        <div class="card h-100 shadow-sm">
                            <img src="<%= p.getImageUrl() %>" class="card-img-top" alt="<%= p.getName() %>">
                            <div class="card-body">
                                <h5 class="card-title"><%= p.getName() %></h5>
                                <p class="card-text">$<%= p.getPrice() %></p>
                                

                            <!-- Add to Cart -->
<form action="CartServlet" method="post" style="display:inline;">
    <input type="hidden" name="productId" value="<%= p.getId() %>">
    <button type="submit" class="btn btn-success">Add to Cart</button>
</form>

<!-- Buy Now -->
<a href="OrderServlet?productId=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>

                            </div>
                        </div>
                    </div>
            <%   }
               } else { %>
                   <p>No products available.</p>
            <% } %>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
