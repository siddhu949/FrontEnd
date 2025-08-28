package com.servlets.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class RegistrationServ
 */
@WebServlet("/Register")
public class RegistrationServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("name");
		String upwd=request.getParameter("pass");
		String uemail=request.getParameter("email");
		String umobile=request.getParameter("contact");
		Connection con =null;
		RequestDispatcher rd=null;
		try {
			con=DbUtility.connect();
			PreparedStatement ps =con.prepareStatement("INSERT INTO users(uname,upwd,uemail,umobile)values(?,?,?,?)");
			ps.setString(1, uname);
			ps.setString(2, upwd);
			ps.setString(3, uemail);
			ps.setString(4, umobile);
			int rows =ps.executeUpdate();
		   
			if(rows>0) {
				request.setAttribute("status", "success");
				response.sendRedirect("index.html");
			}
			else {
				request.setAttribute("status","failed");
				request.setAttribute("status", "failed");
                rd = request.getRequestDispatcher("registration.html");
                rd.forward(request, response);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "error");
		    rd = request.getRequestDispatcher("/registration.html");
		    rd.forward(request, response);
		    
			
		}
		
		
	}

}
