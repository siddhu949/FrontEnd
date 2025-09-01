//package com.todo.serv;
//
//public class Test {
//	import java.sql.Connection;
//	import java.sql.DriverManager;
//	import java.sql.PreparedStatement;
//	import java.sql.ResultSet;
//	import java.sql.SQLException;
//	import java.time.LocalDateTime;
//	import java.time.format.DateTimeFormatter;
//	import java.util.Scanner;
//	class Task{
//		int id;
//		String title;
//		boolean completed;
//		String updatedAt;
//		String createdAt;
//		
//		   Task(int id, String title, boolean completed, String createdAt, String updatedAt) {
//		        this.id = id;
//		        this.title = title;
//		        this.completed = completed;
//		        this.createdAt = createdAt;
//		        this.updatedAt = updatedAt;
//		    }
//		   
//		   static String now() {
//			   return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		   }
//	}
//
//	public class Todo {
//		static Scanner sc = new Scanner(System.in);
//		static Connection con;
//			
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//			try {
//				//1.connect DB
//				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist_db","root","root");
//			
//			boolean running =true;
//			while (running) {
//				System.out.println("todo_list (with jdbc)");
//				System.out.println("add|delete|update|status|list|quit");
//				System.out.println("enter choice");
//				String choice =sc.next().toLowerCase();
//				
//				switch (choice) {
//				case "add":addTask();break;
//				case "delete":deleteTask();break;
//				case "status":toggleStatus();break;
//				case "list":listTask();break;
//				case "update":updateTask();break;
//				case "quit":running=false;break;
//				default:System.out.println("invalid option");
//				}
//			}
//			
//				con.close();}
//				catch(Exception e) {
//					e.printStackTrace();
//				}
//			
//		
//
//	}
//
//
//
//		
//
//
//
//		private static void addTask() throws SQLException  {
//			// TODO Auto-generated method stub
//			sc.nextLine();
//			System.err.println("enter Task");
//			String title =sc.nextLine();
//			String now =Task.now();
//			PreparedStatement ps =con.prepareStatement("INSERT INTO tasks(title,completed,createdAt) VALUES(?,?,?)");
//			ps.setString(1, title);
//			ps.setBoolean(2, false);
//			ps.setString(3, now);
//			ps.executeUpdate();
//			System.out.println("tasks added. _____");
//			
//			
//		}
//
//		private static void deleteTask() throws SQLException {
//			// TODO Auto-generated method stub
//			System.out.println("enter the task ID");
//			int id =sc.nextInt();
//			PreparedStatement ps =con.prepareStatement("DELETE FROM tasks WHERE ID =? ");
//			ps.setInt(1, id);
//			int rows =ps.executeUpdate();
//			System.out.println(rows>0?"tasks deleted.":"tasks not found");
//		}
//		
//
//		private static void toggleStatus() throws SQLException {
//			// TODO Auto-generated method stub
//			System.out.println("enter the task id");
//			int id =sc.nextInt();
//			PreparedStatement ps =con.prepareStatement("UPDATE tasks SET completed = NOT Completed ,updatedAT=? where id =?");
//			ps.setString(1, Task.now());
//			ps.setInt(2, id);
//			int rows=ps.executeUpdate();
//			System.out.println(rows>0?"stsus toggled.":"tasks not found");
//		}
//		
//		private static void listTask() throws SQLException{
//			// TODO Auto-generated method stub
//			java.sql.Statement st =con.createStatement();
//			ResultSet rs =st.executeQuery("SELECT * FROM TASKS");
//			
//			if(!rs.isBeforeFirst()) {
//				System.out.println("no tasks.");
//				return;
//			}
//			while(rs.next()){
//				String status =rs.getBoolean("completed")?"completed":"pending";
//				String upd =(rs.getString("updatedAt")!=null)?"|updated:"+rs.getString("UpdatedAt"):"";
//				System.out.println(rs.getInt("id") + ". " + rs.getString("title") +
//		                " [" + status + "] | Added: " + rs.getString("createdAt") + upd);
//			}
//			
//		}
//
//		
//		private static void updateTask() throws SQLException {
//			// TODO Auto-generated method stub
//			 System.out.print("Enter task id: ");
//		        int id = sc.nextInt();
//		        sc.nextLine();
//		        System.out.print("Enter new text: ");
//		        String newTitle = sc.nextLine();
//
//		        PreparedStatement ps = con.prepareStatement(
//		            "UPDATE tasks SET title=?, completed=?, updatedAt=? WHERE id=?");
//		        ps.setString(1, newTitle);
//		        ps.setBoolean(2, false);
//		        ps.setString(3, Task.now());
//		        ps.setInt(4, id);
//		        int rows = ps.executeUpdate();
//		        System.out.println(rows > 0 ? "Task updated." : "Task not found.");
//		}
//
//		
//	}
//}
