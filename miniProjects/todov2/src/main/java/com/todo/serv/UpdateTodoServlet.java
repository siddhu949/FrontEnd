package com.todo.serv;

import com.todo.model.Todo;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");

            String todoIdStr = request.getParameter("todoId");
            String title = request.getParameter("todoTitle");
            String desc = request.getParameter("todoDesc");
            String targetStr = request.getParameter("targetDatetime");

            if (todoIdStr == null || todoIdStr.trim().isEmpty()) {
                response.sendRedirect("todo.jsp?msg=invalidId");
                return;
            }

            LocalDateTime targetDateTime;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                targetDateTime = LocalDateTime.parse(targetStr, formatter);
            } catch (Exception e) {
                // Log the error for debugging
                System.err.println("Failed to parse targetDatetime: " + targetStr);
                targetDateTime = LocalDateTime.now();
            }

            Todo t = new Todo();
            t.setTodoId(Integer.parseInt(todoIdStr));
            t.setTodoTitle(title);
            t.setTodoDesc(desc);
            t.setTargetDatetime(targetDateTime);
            t.setModifiedBy(username); // Set the modifiedBy field

            TodoServ.updateTodo(t);
            response.sendRedirect("todo");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("todo.jsp?msg=error");
        }
    }

}
