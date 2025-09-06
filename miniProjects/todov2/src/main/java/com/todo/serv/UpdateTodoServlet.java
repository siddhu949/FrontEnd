package com.todo.serv;

import com.todo.model.Todo;
import com.todo.DAO.TodoDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateTodo")
public class UpdateTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TodoDAO todoDAO = new TodoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");

            int todoId = Integer.parseInt(request.getParameter("todoId"));
            String title = request.getParameter("todoTitle");
            String desc = request.getParameter("todoDesc");
            String targetStr = request.getParameter("targetDatetime");

            LocalDateTime targetDateTime;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                targetDateTime = LocalDateTime.parse(targetStr, formatter);
            } catch (Exception e) {
                targetDateTime = LocalDateTime.now();
            }

            Todo t = new Todo();
            t.setTodoId(todoId);
            t.setTodoTitle(title);
            t.setTodoDesc(desc);
            t.setTargetDatetime(targetDateTime);
            t.setModifiedBy(username);

            todoDAO.updateTodo(t);
            session.setAttribute("successMessage", "Todo updated successfully!");
            response.sendRedirect("todo");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("todo.jsp?msg=error");
        }
    }
}
