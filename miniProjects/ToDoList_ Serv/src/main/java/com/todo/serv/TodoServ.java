package com.todo.serv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo")
public class TodoServ extends HttpServlet {
    private TodoDAO todoDAO;

    @Override
    public void init() {
        todoDAO = new TodoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            List<Todo> list = todoDAO.selectAllTodos();
            String json = new Gson().toJson(list);
            response.getWriter().write(json);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Todo todo = new Todo();
        todo.setTodoTitle(request.getParameter("title"));
        todo.setTodoDescription(request.getParameter("description"));
        todo.setTodoStatus(request.getParameter("status"));
        todo.setTodoTargetDate(java.sql.Date.valueOf(request.getParameter("targetDate")));
        todo.setTodoTargetTime(request.getParameter("targetTime"));
        todo.setCreatedBy("webuser");

        try {
            todoDAO.insertTodo(todo);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

//package com.todo.serv;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/todo")
//public class TodoServ extends HttpServlet {
//    private TodoDAO todoDAO;
//
//    @Override
//    public void init() {
//        todoDAO = new TodoDAO();
//    }
//
//    // GET - fetch all todos
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        try {
//            List<Todo> list = todoDAO.selectAllTodos();
//            String json = new com.google.gson.Gson().toJson(list);
//            response.getWriter().write(json);
//        } catch (Exception e) {
//            response.sendError(500, e.getMessage());
//        }
//    }
//
//    // POST - add new todo
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Todo todo = new Todo();
//        todo.setTodoTitle(request.getParameter("title"));
//        todo.setTodoDescription(request.getParameter("description"));
//        todo.setTodoStatus(request.getParameter("status"));
//        todo.setTodoTargetDate(java.sql.Date.valueOf(request.getParameter("targetDate"))); // expects yyyy-MM-dd
//        todo.setTodoTargetTime(request.getParameter("targetTime"));
//        todo.setCreatedBy("webuser");
//
//        try {
//            todoDAO.insertTodo(todo);
//            response.setStatus(HttpServletResponse.SC_CREATED);
//        } catch (Exception e) {
//            response.sendError(500, e.getMessage());
//        }
//    }
//}