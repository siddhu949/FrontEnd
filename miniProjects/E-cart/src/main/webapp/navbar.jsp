<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    String userName = (String) session.getAttribute("username"); // ✅ consistent with LoginServlet
    Integer cartCount = (Integer) session.getAttribute("cartCount");
    if (cartCount == null) cartCount = 0;

    // Get current page name for highlighting
    String currentPage = request.getRequestURI();
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <!-- Brand (left side) -->
        <a class="navbar-brand fw-bold" href="index.jsp">eCart</a>

        <!-- Right side -->
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav ms-auto">
                <% if (userName != null) { %>
                    <li class="nav-item">
                        <a class="nav-link disabled">Welcome, <%= userName %></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%= currentPage.contains("CartServlet") ? "active" : "" %>" 
                           href="CartServlet">Cart (<%= cartCount %>)</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%= currentPage.contains("OrderServlet") ? "active" : "" %>" 
                           href="OrderServlet?action=view">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet">Logout</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
