package com.todo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtility {

    private static final String URL = "jdbc:mysql://localhost:3306/tododb?useSSL=false";
    private static final String USER = "root";   // your MySQL username
    private static final String PASS = "root";   // your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
