package com.servlets.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/updateuser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        try {
            Connection con = DbUtility.connect();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET uname=?, upwd=? WHERE id=?");
            ps.setString(1, uname);
            ps.setString(2, upwd);
            ps.setInt(3, id);
            ps.executeUpdate();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("users.html");
    }

}
