package com.servlets.login;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtility {

    static final String URL = "jdbc:mysql://localhost:3306/servlet?useSSL=false&serverTimezone=UTC";
    static final String USER = "root"; // your MySQL username
    static final String PASS = "root"; // your MySQL password

    // Static block to load driver once when class is loaded
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
