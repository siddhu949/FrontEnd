package com.aditya.usemanagement.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.aditya.usermanagement.bean.UserDao;
import com.aditya.usermanagement.dao.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		userDao =new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		switch(action){
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
			try {
				insertUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    break;
			case "delete":
			try {
				deleteUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/update":
			try {
				updateUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/edit":
			try {
				editUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			default:
			try {
				listUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
			
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
    private void showNewForm(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("user-form.jsp");
        rd.forward(req, res);
    }
    private void insertUser(HttpServletRequest req,HttpServletResponse res)throws Exception {
    	String name=req.getParameter("name");
    	String email=req.getParameter("email");
    	String country=req.getParameter("country");
    	User newUser=new User(name,email,country);
    	userDao.insertUser(newUser);
    	res.sendRedirect("List");
    	
    }
    private void deleteUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
    	int id=Integer.parseInt(req.getParameter("id"));
    	try {
    		userDao.deleteUser(id);
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	res.sendRedirect("List");
    }
    private void editUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
    	User existingUser;
    	int id =Integer.parseInt(req.getParameter("id"));
    	try {
    	existingUser=userDao.selectUser(id);
    	RequestDispatcher rd=req.getRequestDispatcher("user=form.jsp");
    	req.setAttribute("user", existingUser);
    	rd.forward(req, res);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    private void updateUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
    	int id =Integer.parseInt(req.getParameter("id"));
    	String name=req.getParameter("name");
    	String email=req.getParameter("email");
    	String country=req.getParameter("country");
    	
    	User user=new User(name,email,country);
    	userDao.updateUser(user);
    	res.sendRedirect("List");
    	
    }
    private void listUser(HttpServletRequest req,HttpServletResponse res)throws Exception{
    	List<User> listUser=userDao.SelectAllusers();
    	req.setAttribute("listUser",listUser);
    	RequestDispatcher rd=req.getRequestDispatcher("user-list.jsp");
    	rd.forward(req, res);
    }
    
    

}
