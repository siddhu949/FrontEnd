<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.todo.model.Todo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("index.jsp?msg=loginRequired");
        return;
    }
  
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
%>
<%
    List<Todo> todos = (List<Todo>) request.getAttribute("todos");
    System.out.println("JSP - Todos size: " + (todos != null ? todos.size() : "null")); // Debug statement
    if (todos == null || todos.isEmpty()) {
        out.println("<tr><td colspan='7' class='text-center'>No todos found.</td></tr>");
    } else {
        // Render the todos table
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .form-edit { display: none; }
        .text-view { display: block; }
    </style>
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

    <!-- Add Todo Form -->
    <form method="post" class="mb-4">
        <div class="mb-3 row g-2">
            <div class="col-md-3">
                <input type="text" name="todoTitle" class="form-control" placeholder="Task Title" required>
            </div>
            <div class="col-md-3">
                <input type="text" name="todoDesc" class="form-control" placeholder="Description">
            </div>
            <div class="col-md-4">
                <input type="datetime-local" name="targetDatetime" class="form-control" required>
            </div>
            <div class="col-md-2 d-flex">
                <input type="text" name="status" class="form-control me-2" value="P" readonly>
                <button type="submit" class="btn btn-primary flex-shrink-0">Add Task</button>
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
            <%
                if (todos == null || todos.isEmpty()) {
                    out.println("<tr><td colspan='7' class='text-center'>No todos found.</td></tr>");
                } else {
                    int rowNum = 1;
                    for (Todo t : todos) {
            %>
                        <tr>
                            <td><%= rowNum++ %></td>
                            <td>
                                <span class="text-view"><%= t.getTodoTitle() %></span>
                                <input type="text" class="form-control form-edit" value="<%= t.getTodoTitle() %>" data-id="<%= t.getTodoId() %>">
                            </td>
                            <td>
                                <span class="text-view"><%= t.getTodoDesc() %></span>
                                <input type="text" class="form-control form-edit" value="<%= t.getTodoDesc() %>">
                            </td>
                            <td>
                                <span class="text-view"><%= t.getTargetDatetime().format(formatter) %></span>
                                <input type="datetime-local" class="form-control form-edit"
                                       value="<%= t.getTargetDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) %>">
                            </td>
                            <td><%= t.getTodoStatusCode() %></td>
                            <td><%= t.getCreatedDate().format(formatter) %></td>
                            <td>
                                <%-- Status buttons --%>
                                <%
                                    switch (t.getTodoStatusCode()) {
                                        case "P":
                                %>
                                            <a href="todo?updateStatus=<%= t.getTodoId() %>&status=I" class="btn btn-sm btn-info me-1">In Progress</a>
                                <%
                                            break;
                                        case "I":
                                %>
                                            <a href="todo?updateStatus=<%= t.getTodoId() %>&status=C" class="btn btn-sm btn-success me-1">Completed</a>
                                <%
                                            break;
                                        case "C":
                                            break;
                                    }
                                %>
                                <button type="button" class="btn btn-sm btn-warning edit-btn">Edit</button>
                                <button type="button" class="btn btn-sm btn-success save-btn form-edit">Save</button>
                                <button type="button" class="btn btn-sm btn-secondary cancel-btn form-edit">Cancel</button>
                            </td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>

<footer class="bg-dark text-center text-white py-3 mt-5 fixed-bottom">
    <p class="mb-0">
        Made with <span style="color: red;">❤️</span> by
        <a href="https://www.konkorde.org/" target="_blank" class="text-decoration-none text-info">Konkorde</a>
    </p>
</footer>

<script>
    // Edit button: Show edit form
    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            let row = this.closest('tr');
            row.querySelectorAll('.text-view').forEach(e => e.style.display = 'none');
            row.querySelectorAll('.form-edit').forEach(e => e.style.display = 'block');
            row.querySelector('.edit-btn').style.display = 'none';
        });
    });

    // Cancel button: Hide edit form
    document.querySelectorAll('.cancel-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            let row = this.closest('tr');
            row.querySelectorAll('.text-view').forEach(e => e.style.display = 'block');
            row.querySelectorAll('.form-edit').forEach(e => e.style.display = 'none');
            row.querySelector('.edit-btn').style.display = 'block';
        });
    });

    // Save button: Submit the form
    document.querySelectorAll('.save-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            let row = this.closest('tr');
            let todoId = row.querySelector('input[data-id]').dataset.id;
            let title = row.querySelectorAll('input')[0].value;
            let desc = row.querySelectorAll('input')[1].value;
            let targetDatetime = row.querySelectorAll('input')[2].value;

            let form = document.createElement('form');
            form.method = 'post';
            form.action = 'updateTodo';
            form.innerHTML = `
                <input type="hidden" name="todoId" value="${todoId}">
                <input type="hidden" name="todoTitle" value="${title}">
                <input type="hidden" name="todoDesc" value="${desc}">
                <input type="hidden" name="targetDatetime" value="${targetDatetime}">
            `;
            document.body.appendChild(form);
            form.submit();
        });
    });
</script>
</body>
</html>
