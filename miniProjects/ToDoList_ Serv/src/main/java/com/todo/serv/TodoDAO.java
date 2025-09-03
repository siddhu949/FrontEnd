package com.todo.serv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    private static final String INSERT_TODO = 
        "INSERT INTO todo_items (todo_title, todo_desc, target_datetime, todo_status_code, created_by, created_date) VALUES (?, ?, ?, ?, ?, NOW())";

    private static final String SELECT_ALL_TODO = 
        "SELECT * FROM todo_items";

    private static final String UPDATE_TODO = 
        "UPDATE todo_items SET todo_desc=?, target_datetime=?, todo_status_code=?, modified_by=?, modified_date=NOW() WHERE todo_id=?";

    private static final String DELETE_TODO = 
        "DELETE FROM todo_items WHERE todo_id=?";

    // Insert a new Todo
    public void insertTodo(Todo todo) throws Exception {
        try {
			try (Connection con = DbUtility.connect();
			     PreparedStatement ps = con.prepareStatement(INSERT_TODO)) {

			    ps.setString(1, todo.getTodoTitle());
			    ps.setString(2, todo.getTodoDesc());
			    ps.setTimestamp(3, Timestamp.valueOf(todo.getTargetDatetime()));
			    ps.setString(4, todo.getTodoStatusCode());
			    ps.setString(5, todo.getCreatedBy());
			    ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Select all Todos
    public List<Todo> selectAllTodos() throws Exception {
        List<Todo> todos = new ArrayList<>();
        try {
			try (Connection con = DbUtility.connect();
			     PreparedStatement ps = con.prepareStatement(SELECT_ALL_TODO);
			     ResultSet rs = ps.executeQuery()) {

			    while (rs.next()) {
			        Todo t = new Todo();
			        t.setTodoId(rs.getInt("todo_id"));
			        t.setTodoTitle(rs.getString("todo_title"));
			        t.setTodoDesc(rs.getString("todo_desc"));
			        t.setTargetDatetime(rs.getTimestamp("target_datetime").toLocalDateTime());
			        t.setTodoStatusCode(rs.getString("todo_status_code"));
			        t.setCreatedBy(rs.getString("created_by"));
			        t.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
			        t.setModifiedBy(rs.getString("modified_by"));
			        t.setModifiedDate(rs.getTimestamp("modified_date") != null ? rs.getTimestamp("modified_date").toLocalDateTime() : null);
			        todos.add(t);
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return todos;
    }

    // Update Todo
    public boolean updateTodo(Todo todo) throws Exception {
        try (Connection con = DbUtility.connect();
             PreparedStatement ps = con.prepareStatement(UPDATE_TODO)) {

            ps.setString(1, todo.getTodoDesc());
            ps.setTimestamp(2, Timestamp.valueOf(todo.getTargetDatetime()));
            ps.setString(3, todo.getTodoStatusCode());
            ps.setString(4, todo.getModifiedBy());
            ps.setInt(5, todo.getTodoId());
            return ps.executeUpdate() > 0;
        }
    }

    // Delete Todo
    public boolean deleteTodo(int id) throws Exception {
        try (Connection con = DbUtility.connect();
             PreparedStatement ps = con.prepareStatement(DELETE_TODO)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
