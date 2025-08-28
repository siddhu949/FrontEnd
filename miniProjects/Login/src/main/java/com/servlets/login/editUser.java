package com.servlets.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class editUser
 */
@WebServlet("/editUser")
public class editUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String uname = request.getParameter("uname");
	        String upwd = request.getParameter("upwd");

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        out.println("<!DOCTYPE html><html><head><title>Edit User</title></head><body>");
	        out.println("<h2>Edit User</h2>");
	        out.println("<form method='post' action='updateuser'>");
	        out.println("<input type='hidden' name='id' value='" + id + "'/>");
	        out.println("Username: <input type='text' name='uname' value='" + uname + "'/><br/>");
	        out.println("Password: <input type='text' name='upwd' value='" + upwd + "'/><br/>");
	        out.println("<input type='submit' value='Update'/>");
	        out.println("</form>");
	        out.println("</body></html>");
	    }

}
