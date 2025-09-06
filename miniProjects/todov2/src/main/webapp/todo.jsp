<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.todo.model.Todo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    List<Todo> todos = (List<Todo>) request.getAttribute("todos");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">TodoList</a>
        <div class="d-flex ms-auto">
            <span class="navbar-text text-white me-3"><%= username %></span>
            <a href="logout" class="btn btn-outline-light btn-sm">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h2 class="mb-3">Todo List</h2>

    <!-- Add/Edit Todo Form -->
    <form method="post" id="todoForm">
        <input type="hidden" name="todoId" id="todoId">
        <div class="mb-3 row g-2">
            <div class="col-md-3">
                <input type="text" name="todoTitle" id="todoTitle" class="form-control" placeholder="Task Title" required>
            </div>
            <div class="col-md-3">
                <input type="text" name="todoDesc" id="todoDesc" class="form-control" placeholder="Description">
            </div>
            <div class="col-md-4">
                <input type="datetime-local" name="targetDatetime" id="targetDatetime" class="form-control" required>
            </div>
            <div class="col-md-2 d-flex">
                <button type="submit" class="btn btn-primary flex-shrink-0" id="formBtn">Add Task</button>
            </div>
        </div>
    </form>

    <!-- Todo Table -->
    <table class="table table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Target DateTime</th>
                <th>Status</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% if (todos == null || todos.isEmpty()) { %>
                <tr><td colspan="7" class="text-center">No todos found.</td></tr>
            <% } else {
                int rowNum = 1;
                for (Todo t : todos) { %>
                <tr data-id="<%= t.getTodoId() %>">
                    <td><%= rowNum++ %></td>
                    <td><%= t.getTodoTitle() %></td>
                    <td><%= t.getTodoDesc() %></td>
                    <td><%= t.getTargetDatetime().format(formatter) %></td>
                    <td><%= t.getTodoStatusCode() %></td>
                    <td><%= t.getCreatedDate().format(formatter) %></td>
                    <td>
                        <!-- Status actions -->
                        <% switch (t.getTodoStatusCode()) {
                            case "P": %>
                                <a href="todo?action=changeStatus&todoId=<%= t.getTodoId() %>&newStatus=I" class="btn btn-sm btn-info me-1">In Progress</a>
                        <% break;
                            case "I": %>
                                <a href="todo?action=changeStatus&todoId=<%= t.getTodoId() %>&newStatus=C" class="btn btn-sm btn-success me-1">Completed</a>
                        <% break; } %>

                        <button class="btn btn-sm btn-warning edit-btn">Edit</button>
                        <a href="todo?action=delete&todoId=<%= t.getTodoId() %>" class="btn btn-sm btn-danger">Delete</a>
                    </td>
                </tr>
            <% }} %>
        </tbody>
    </table>
</div>



<script>
    // Edit button: populate top form
    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            let row = this.closest('tr');
            let todoId = row.dataset.id;
            let cells = row.children;

            document.getElementById('todoId').value = todoId;
            document.getElementById('todoTitle').value = cells[1].innerText;
            document.getElementById('todoDesc').value = cells[2].innerText;

            let dateStr = cells[3].innerText; // format: yyyy-MM-dd HH:mm
            let dt = dateStr.replace(' ', 'T'); // convert to input datetime-local
            document.getElementById('targetDatetime').value = dt;

            document.getElementById('formBtn').innerText = 'Update Task';
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    });
</script>
</body>
</html>
