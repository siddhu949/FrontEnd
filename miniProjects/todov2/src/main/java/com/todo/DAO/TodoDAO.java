package com.todo.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.todo.connection.DbUtility;
import com.todo.model.Todo;

public class TodoDAO {

    // ------------------ INSERT TODO ------------------
    public void insertTodo(Todo todo) throws SQLException {
        String sql = "INSERT INTO todo_items (todo_title, todo_desc, target_datetime, todo_status_code, created_by, user_id, created_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, todo.getTodoTitle());
            ps.setString(2, todo.getTodoDesc());
            ps.setTimestamp(3, Timestamp.valueOf(todo.getTargetDatetime()));
            ps.setString(4, todo.getTodoStatusCode());
            ps.setString(5, todo.getCreatedBy());
            ps.setInt(6, todo.getUserId());

            ps.executeUpdate();
        }
    }

    // ------------------ UPDATE TODO STATUS ------------------
    public void updateTodoStatus(int todoId, String status, String modifiedBy) throws SQLException {
        String sql = "UPDATE todo_items SET todo_status_code=?, modified_by=?, modified_date=NOW() WHERE todo_id=?";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, modifiedBy);
            ps.setInt(3, todoId);

            ps.executeUpdate();
        }
    }

    // ------------------ FULL UPDATE TODO ------------------
//    public void updateTodo(Todo todo) throws SQLException {
//        String sql = "UPDATE todo_items SET todo_title=?, todo_desc=?, target_datetime=?, modified_by=?, modified_date=NOW() WHERE todo_id=?";
//
//        try (Connection conn = DbUtility.connect();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, todo.getTodoTitle());
//            ps.setString(2, todo.getTodoDesc());
//            ps.setTimestamp(3, Timestamp.valueOf(todo.getTargetDatetime()));
//            ps.setString(4, todo.getModifiedBy());
//            ps.setInt(5, todo.getTodoId());
//
//            ps.executeUpdate();
//        }
//    }

    // ------------------ SELECT TODOS BY USER ------------------
    public List<Todo> selectTodosByUser(int userId) throws SQLException {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT todo_id, todo_title, todo_desc, target_datetime, todo_status_code, created_by, created_date, modified_by, modified_date, user_id " +
                     "FROM todo_items WHERE user_id = ? ORDER BY target_datetime ASC";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Todo todo = new Todo();
                todo.setTodoId(rs.getInt("todo_id"));
                todo.setTodoTitle(rs.getString("todo_title"));
                todo.setTodoDesc(rs.getString("todo_desc"));
                todo.setTargetDatetime(rs.getTimestamp("target_datetime").toLocalDateTime());
                todo.setTodoStatusCode(rs.getString("todo_status_code"));
                todo.setCreatedBy(rs.getString("created_by"));
                todo.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                todo.setModifiedBy(rs.getString("modified_by"));
                todo.setModifiedDate(rs.getTimestamp("modified_date") != null ? rs.getTimestamp("modified_date").toLocalDateTime() : null);
                todo.setUserId(rs.getInt("user_id"));

                todos.add(todo);
            }
        }

        return todos;
    }

    // ------------------ SELECT TODO BY ID ------------------
    public Todo selectTodoById(int todoId) throws SQLException {
        String sql = "SELECT todo_id, todo_title, todo_desc, target_datetime, todo_status_code, created_by, created_date, modified_by, modified_date, user_id " +
                     "FROM todo_items WHERE todo_id = ?";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, todoId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Todo todo = new Todo();
                todo.setTodoId(rs.getInt("todo_id"));
                todo.setTodoTitle(rs.getString("todo_title"));
                todo.setTodoDesc(rs.getString("todo_desc"));
                todo.setTargetDatetime(rs.getTimestamp("target_datetime").toLocalDateTime());
                todo.setTodoStatusCode(rs.getString("todo_status_code"));
                todo.setCreatedBy(rs.getString("created_by"));
                todo.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                todo.setModifiedBy(rs.getString("modified_by"));
                todo.setModifiedDate(rs.getTimestamp("modified_date") != null ? rs.getTimestamp("modified_date").toLocalDateTime() : null);
                todo.setUserId(rs.getInt("user_id"));
                return todo;
            } else {
                return null;
            }
        }
    }

    // ------------------ DELETE TODO ------------------
    public void deleteTodo(int todoId, int userId) throws SQLException {
        String sql = "DELETE FROM todo_items WHERE todo_id = ? AND user_id = ?";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, todoId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }
// // In TodoDAO.java
//    public void updateTodo(Todo todo) throws SQLException {
//        String sql = "UPDATE todo_items SET todo_title=?, todo_desc=?, target_datetime=?, modified_by=?, modified_date=NOW() WHERE todo_id=?";
//        try (Connection conn = DbUtility.connect();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, todo.getTodoTitle());
//            ps.setString(2, todo.getTodoDesc());
//            ps.setTimestamp(3, Timestamp.valueOf(todo.getTargetDatetime()));
//            ps.setString(4, todo.getModifiedBy());
//            ps.setInt(5, todo.getTodoId());
//            ps.executeUpdate();
//        }
//    }
    
//    public void updateTodo(Todo todo) throws SQLException {
//        String sql = "UPDATE todo_items SET todo_title=?, todo_desc=?, target_datetime=?, modified_by=?, modified_date=NOW() WHERE todo_id=?";
//        try (Connection conn = DbUtility.connect();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, todo.getTodoTitle());
//            ps.setString(2, todo.getTodoDesc());
//            ps.setTimestamp(3, Timestamp.valueOf(todo.getTargetDatetime()));
//            ps.setString(4, todo.getModifiedBy());
//            ps.setInt(5, todo.getTodoId());
//
//            int rowsAffected = ps.executeUpdate();
//            System.out.println("Rows affected in update: " + rowsAffected); // Debug statement
//        }
//    }
 // ------------------ FULL UPDATE TODO (includes status) ------------------
    public void updateTodo(Todo todo) throws SQLException {
        String sql = "UPDATE todo_items " +
                     "SET todo_title=?, todo_desc=?, target_datetime=?, todo_status_code=?, modified_by=?, modified_date=NOW() " +
                     "WHERE todo_id=? AND user_id=?";

        try (Connection conn = DbUtility.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, todo.getTodoTitle());
            ps.setString(2, todo.getTodoDesc());
            ps.setTimestamp(3, Timestamp.valueOf(todo.getTargetDatetime()));
            ps.setString(4, todo.getTodoStatusCode());   // <-- include status update
            ps.setString(5, todo.getModifiedBy());
            ps.setInt(6, todo.getTodoId());
            ps.setInt(7, todo.getUserId());

            int rows = ps.executeUpdate();
            System.out.println("UpdateTodo rows affected: " + rows);
        }
    }



}
