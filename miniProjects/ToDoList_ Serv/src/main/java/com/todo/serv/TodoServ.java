package com.todo.serv;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/todo")
public class TodoServ extends HttpServlet {

    private TodoDAO todoDAO = new TodoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // --- Handle status update ---
        String updateId = request.getParameter("updateStatus");
        String newStatus = request.getParameter("status");
        if (updateId != null && newStatus != null) {
            Todo todo = new Todo();
            todo.setTodoId(Integer.parseInt(updateId));
            todo.setTodoStatusCode(newStatus);

            HttpSession session = request.getSession(false);
            todo.setModifiedBy(session != null ? (String) session.getAttribute("username") : "Guest");

            // **Set modified date to now**
            todo.setModifiedDate(LocalDateTime.now());

            try {
                todoDAO.updateTodo(todo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("todo"); // reload page
            return;
        }


        // --- Normal page rendering ---
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        String username = session != null ? (String) session.getAttribute("username") : "Guest";

        List<Todo> todos;
        try {
            todos = todoDAO.selectAllTodos();
        } catch (Exception e) {
            e.printStackTrace();
            todos = null;
        }

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><title>Todo List</title>");
        html.append("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        html.append("</head><body>");

        // Navbar
        html.append("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>")
            .append("<div class='container-fluid'>")
            .append("<a class='navbar-brand' href='#'>TodoList</a>")
            .append("<div class='d-flex ms-auto'>")
            .append("<span class='navbar-text text-white me-3'>").append(username).append("</span>")
            .append("<a href='logout' class='btn btn-outline-light btn-sm'>Logout</a>")
            .append("</div></div></nav>");

        html.append("<div class='container mt-4'>");

        // Heading
        html.append("<h2 class='mb-3'>Todo List</h2>");

        // Form to add new Todo
        html.append("<form method='post' class='mb-4'>")
            .append("<div class='mb-3 row g-2'>")
            .append("<div class='col-md-3'><input type='text' name='todoTitle' class='form-control' placeholder='Task Title' required></div>")
            .append("<div class='col-md-3'><input type='text' name='todoDesc' class='form-control' placeholder='Description'></div>")
            .append("<div class='col-md-4'><input type='datetime-local' name='targetDatetime' class='form-control' required></div>")
            .append("<div class='col-md-2 d-flex'>")
            .append("<input type='text' name='status' class='form-control me-2' value='Pending' readonly>")
            .append("<button type='submit' class='btn btn-primary flex-shrink-0'>Add Task</button>")
            .append("</div></div></form>");

        // Dynamic Table
        html.append("<table class='table table-bordered'>")
            .append("<thead class='table-dark'><tr>")
            .append("<th>ID</th><th>Title</th><th>Description</th><th>Target DateTime</th><th>Status</th><th>Created At</th><th>Actions</th>")
            .append("</tr></thead><tbody>");

        if (todos != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            for (Todo t : todos) {
                html.append("<tr>");
                html.append("<td>").append(t.getTodoId()).append("</td>");
                html.append("<td>").append(t.getTodoTitle()).append("</td>");
                html.append("<td>").append(t.getTodoDesc()).append("</td>");
                html.append("<td>").append(t.getTargetDatetime() != null ? t.getTargetDatetime().format(formatter) : "N/A").append("</td>");

                // Status as full word
                String statusText = switch (t.getTodoStatusCode()) {
                    case "P" -> "Pending";
                    case "I" -> "In Progress";
                    case "C" -> "Completed";
                    default -> "Unknown";
                };
                html.append("<td>").append(statusText).append("</td>");

                // Created date safe
                html.append("<td>").append(t.getCreatedDate() != null ? t.getCreatedDate().format(formatter) : "N/A").append("</td>");

                /// Action buttons: only next status
                html.append("<td>");
                switch (t.getTodoStatusCode()) {
                    case "P":
                        html.append("<a href='todo?updateStatus=").append(t.getTodoId())
                            .append("&status=I' class='btn btn-sm btn-info me-1'>In Progress</a>");
                        break;
                    case "I":
                        html.append("<a href='todo?updateStatus=").append(t.getTodoId())
                            .append("&status=C' class='btn btn-sm btn-success'>Completed</a>");
                        break;
                    case "C":
                        html.append("<span class='text-success'>Done</span>");
                        break;
                }
                html.append("</td>");


                html.append("</tr>");
            }
        }

        html.append("</tbody></table></div></body></html>");
        out.println(html.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read form parameters
        String title = request.getParameter("todoTitle");
        String desc = request.getParameter("todoDesc");
        String targetDatetimeStr = request.getParameter("targetDatetime");

        LocalDateTime target;
        try {
            if (targetDatetimeStr != null && !targetDatetimeStr.isEmpty()) {
                // datetime-local input → ISO_LOCAL_DATE_TIME
                target = LocalDateTime.parse(targetDatetimeStr.replace("T", "T")); // browser uses "yyyy-MM-ddTHH:mm"
            } else {
                target = LocalDateTime.now();
            }
        } catch (Exception e) {
            e.printStackTrace();
            target = LocalDateTime.now();
        }

        // Create Todo object
        Todo todo = new Todo();
        todo.setTodoTitle(title);
        todo.setTodoDesc(desc);
        todo.setTargetDatetime(target);
        todo.setTodoStatusCode("P"); // default Pending

        // Get username from session
        HttpSession session = request.getSession(false);
        todo.setCreatedBy(session != null ? (String) session.getAttribute("username") : "Guest");

        // Insert into database
        try {
            todoDAO.insertTodo(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to the todo page
        response.sendRedirect("todo");
    }
}
