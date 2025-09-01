package com.todo.serv;


import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtility  {
	   static final String URL = "jdbc:mysql://localhost:3306/todo?useSSL=FALSE";
	    static final String USER = "root"; // your MySQL username
	    static final String PASS = "root"; // your MySQL password

	    public static Connection connect() throws Exception {
	        return DriverManager.getConnection(URL, USER, PASS);
	    }
	}


