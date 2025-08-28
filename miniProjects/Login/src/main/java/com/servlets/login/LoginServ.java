package com.servlets.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServ
 */
@WebServlet("/Login")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname =request.getParameter("username");
		String pwd=request.getParameter("password");
	
		
		RequestDispatcher rd =null;
		HttpSession hs=request.getSession();
		Connection con =null;
		
		if(uname==null||uname=="") {
			request.setAttribute("status","invalidEmail");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		if(pwd==null||pwd=="") {
			request.setAttribute("status","invalidPasssword");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

		try {
			con=DbUtility.connect();
			PreparedStatement ps =con.prepareStatement("SELECT * FROM users WHERE uemail=? and upwd =?;");
			ps.setString(1, uname);
			ps.setString(2,pwd);
			
			ResultSet rs =ps.executeQuery();
			if(rs.next()) {
				hs.setAttribute("name",rs.getString("uname"));
				rd=request.getRequestDispatcher("viewall.html");
			}
			else {
				request.setAttribute("status", "failed");
				rd=request.getRequestDispatcher("login.html");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

}
