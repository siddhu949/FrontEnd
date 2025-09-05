<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.todo.serv.TodoServ" %>
<%@ page import="com.todo.model.Todo" %>
<%
    String todoIdStr = request.getParameter("todoId");
    int todoId = Integer.parseInt(todoIdStr);
    Todo t = TodoServ.getTodoById(todoId); // Create a method in your servlet to fetch todo by ID
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Todo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Todo</h2>
    <form action="updateTodo" method="post">
        <input type="hidden" name="todoId" value="<%= t.getTodoId() %>">
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="todoTitle" class="form-control" value="<%= t.getTodoTitle() %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Description</label>
            <input type="text" name="todoDesc" class="form-control" value="<%= t.getTodoDesc() %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Target DateTime</label>
            <input type="datetime-local" name="targetDatetime" class="form-control" 
                   value="<%= t.getTargetDatetime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Todo</button>
        <a href="todo.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
