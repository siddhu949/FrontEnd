package com.todo.serv;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.todo.DAO.TodoDAO;
import com.todo.model.Todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo")
public class TodoServ extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TodoDAO todoDAO = new TodoDAO();

    // ------------------ GET ------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

        String action = request.getParameter("action");

        try {
            if ("changeStatus".equals(action)) {
                int todoId = Integer.parseInt(request.getParameter("todoId"));
                String newStatus = request.getParameter("newStatus");
                Todo todo = todoDAO.selectTodoById(todoId);
                if (todo != null && todo.getUserId() == userId) {
                    todo.setTodoStatusCode(newStatus);
                    todo.setModifiedBy(username);
                    todoDAO.updateTodo(todo);
                }
                response.sendRedirect("todo");
                return;
            } else if ("delete".equals(action)) {
                int todoId = Integer.parseInt(request.getParameter("todoId"));
                todoDAO.deleteTodo(todoId, userId);
                response.sendRedirect("todo");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- Fetch user todos ---
        List<Todo> todos;
        try {
            todos = todoDAO.selectTodosByUser(userId);
            if (todos == null) todos = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            todos = new ArrayList<>();
        }

        request.setAttribute("todos", todos);
        request.setAttribute("username", username);

        request.getRequestDispatcher("todo.jsp").forward(request, response);
    }

    // ------------------ POST ------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

        String todoIdStr = request.getParameter("todoId");
        String title = request.getParameter("todoTitle");
        String desc = request.getParameter("todoDesc");
        String targetDatetimeStr = request.getParameter("targetDatetime");

        LocalDateTime target;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            target = LocalDateTime.parse(targetDatetimeStr, formatter);
        } catch (Exception e) {
            target = LocalDateTime.now();
        }

        try {
            if (todoIdStr != null && !todoIdStr.isEmpty()) {
                // -------- Update existing --------
                int todoId = Integer.parseInt(todoIdStr);
                Todo existing = todoDAO.selectTodoById(todoId);
                if (existing != null && existing.getUserId() == userId) {
                    existing.setTodoTitle(title);
                    existing.setTodoDesc(desc);
                    existing.setTargetDatetime(target);
                    existing.setModifiedBy(username);
                    todoDAO.updateTodo(existing);
                }
            } else {
                // -------- Insert new --------
                Todo todo = new Todo();
                todo.setTodoTitle(title);
                todo.setTodoDesc(desc);
                todo.setTargetDatetime(target);
                todo.setTodoStatusCode("P");
                todo.setCreatedBy(username);
                todo.setUserId(userId);
                todoDAO.insertTodo(todo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("todo");
    }
}
