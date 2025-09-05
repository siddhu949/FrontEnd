package com.todo.serv;

import java.io.IOException;
import java.time.LocalDateTime;
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

        // --- Handle status update ---
        String updateId = request.getParameter("updateStatus");
        String newStatus = request.getParameter("status");
        if (updateId != null && newStatus != null) {
            try {
                int todoId = Integer.parseInt(updateId);
                Todo todo = todoDAO.selectTodoById(todoId);
                if (todo != null) {
                    todo.setTodoStatusCode(newStatus);
                    todo.setModifiedBy(username);
                    todoDAO.updateTodo(todo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("todo");
            return;
        }

        // --- Fetch user todos ---
        List<Todo> todos;
        try {
            todos = todoDAO.selectTodosByUser(userId);
            if (todos == null) {
                todos = new ArrayList<>();
            }
            System.out.println("Fetched todos: " + todos.size()); // Debug statement
        } catch (Exception e) {
            e.printStackTrace();
            todos = new ArrayList<>();
        }

        // Set attributes for JSP
        request.setAttribute("todos", todos);
        request.setAttribute("username", username);

        // Forward to JSP
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

        String title = request.getParameter("todoTitle");
        String desc = request.getParameter("todoDesc");
        String targetDatetimeStr = request.getParameter("targetDatetime");

        LocalDateTime target;
        try {
            target = LocalDateTime.parse(targetDatetimeStr);
        } catch (Exception e) {
            target = LocalDateTime.now();
        }

        Todo todo = new Todo();
        todo.setTodoTitle(title);
        todo.setTodoDesc(desc);
        todo.setTargetDatetime(target);
        todo.setTodoStatusCode("P");
        todo.setCreatedBy(username);
        todo.setUserId(userId);

        try {
            todoDAO.insertTodo(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("todo");
    }

    // ------------------ Static Methods ------------------

    /**
     * Fetch a single Todo by ID (used by edit functionality)
     */
    public static Todo getTodoById(int todoId) {
        TodoDAO todoDAO = new TodoDAO();
        try {
            return todoDAO.selectTodoById(todoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateTodo(Todo t) throws Exception {
        if (t == null || t.getTodoId() <= 0) {
            throw new IllegalArgumentException("Invalid Todo object");
        }

        TodoDAO todoDAO = new TodoDAO();
        Todo existing = todoDAO.selectTodoById(t.getTodoId());
        if (existing == null) {
            throw new Exception("Todo not found with ID: " + t.getTodoId());
        }

        existing.setTodoTitle(t.getTodoTitle());
        existing.setTodoDesc(t.getTodoDesc());
        existing.setTargetDatetime(t.getTargetDatetime());
        existing.setModifiedBy(t.getModifiedBy()); // Ensure this is set

        todoDAO.updateTodo(existing);
    }


}
