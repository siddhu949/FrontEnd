package com.todo.serv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
	
//CRUD OPERATIONS
private static final String INSERT_TODO ="INSERT INTO TodoList (todo_title, todo_description, todo_status, todo_targetdate, todo_targettime, created_by, created_date) VALUES (?, ?, ?, ?, ?, ?, NOW())";
private static final String SELECT_ALL_TODO="select * ffrom ToDoList";
private static final String UPDATE_TODO="UPDATE TodoList SET todo_description=?, todo_targetdate=?, todo_targettime=?, modified_by=?, modified_date=NOW() WHERE todo_id=?";
private static final String DELETE_TODO = "DELETE FROM TodoList WHERE todo_id=?";

//Insert Todo

public void insertTodo(Todo todo)throws Exception {
	try(Connection con=DbUtility.connect();
			PreparedStatement ps =con.prepareStatement(INSERT_TODO)){
		
		
		 ps.setString(1, todo.getTodoTitle());
         ps.setString(2, todo.getTodoDescription());
         ps.setString(3, todo.getTodoStatus());
         ps.setDate(4, todo.getTodoTargetDate());
         ps.setString(5, todo.getTodoTargetTime());
         ps.setString(6, todo.getCreatedBy());
         ps.executeUpdate();
	}
}

//SELECT ALL TODO
public List<Todo> selectAllTodos() throws Exception{
	List <Todo> todos=new ArrayList<>();
	try(Connection con =DbUtility.connect();
		PreparedStatement ps =con.prepareStatement(SELECT_ALL_TODO);
			ResultSet rs=ps.executeQuery()){
		while(rs.next()) {
			Todo t=new Todo();
			t.setTodo_id(rs.getInt("todo_id"));
			t.setTodoTitle(rs.getString("todo_title"));
            t.setTodoDescription(rs.getString("todo_description"));
            t.setTodoStatus(rs.getString("todo_status"));
            t.setTodoTargetDate(rs.getDate("todo_targetdate"));
            t.setTodoTargetTime(rs.getString("todo_targettime"));
            t.setCreatedBy(rs.getString("created_by"));
            t.setCreatedDate(rs.getDate("created_date"));
            t.setModifiedBy(rs.getString("modified_by"));
            t.setModifiedDate(rs.getDate("modified_date"));
            todos.add(t);
			
		}
		return todos;
		
	}
	
}

//update todo

public boolean updateTodo(Todo todo)throws Exception{
	boolean rowUpdated;
	try(Connection con =DbUtility.connect();
			PreparedStatement ps=con.prepareStatement(UPDATE_TODO)){
		ps.setString(1, todo.getTodoDescription());
		 ps.setDate(2, todo.getTodoTargetDate());
         ps.setString(3, todo.getTodoTargetTime());
         ps.setString(4, todo.getModifiedBy());
         ps.setInt(5, todo.getTodo_id());
         rowUpdated =ps.executeUpdate()>0;
         
	}
	return rowUpdated;
}

//delete Todo
public boolean deleteTodo(int id)throws Exception{
	boolean rowDeleted;
	try(Connection con =DbUtility.connect();
			PreparedStatement ps=con.prepareStatement(DELETE_TODO)){
		ps.setInt(1,id);
		rowDeleted=ps.executeUpdate()>0;
	}
	return rowDeleted;
}


}
