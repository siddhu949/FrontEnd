package com.servlets.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/javascript");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DbUtility.connect();
            PreparedStatement ps = con.prepareStatement("SELECT id, uname, upwd FROM users");
            ResultSet rs = ps.executeQuery();

            out.print("var users = [");

            boolean first = true;
            while(rs.next()) {
                if (!first) out.print(","); // add comma between objects
                out.print("{id:" + rs.getInt("id") 
                        + ", uname:'" + rs.getString("uname") 
                        + "', upwd:'" + rs.getString("upwd") + "'}");
                first = false;
            }

            out.println("];");
            con.close();
        } catch(Exception e) {
            out.println("var users = [];");
            e.printStackTrace();
        }
    }

}
