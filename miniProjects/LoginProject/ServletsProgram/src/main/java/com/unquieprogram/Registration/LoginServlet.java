package com.unquieprogram.Registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.jdi.connect.spi.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public LoginServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		// TODO Auto-generated method stub
		String uemail =request.getParameter("username");
		String upwd =request.getParameter("password");
		
		
		
		RequestDispatcher rd=null;
		HttpSession session =request.getSession();
		
		if(uemail==null||uemail=="") {
			request.setAttribute("status","invalidEmail");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		if(upwd==null||upwd=="") {
			request.setAttribute("status","invalidPasssword");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet?useSSL=FALSE","root","root");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM users WHERE uemail=? AND upwd=?");{
			ps.setString(1, uemail);
			ps.setString(2, upwd);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				session.setAttribute("name",rs.getString("uname"));
				rd=request.getRequestDispatcher("index.jsp");
			}
			else {
				request.setAttribute("status","failed");
				rd=request.getRequestDispatcher("login.jsp");
				
			}
			rd.forward(request, response);
		} }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
