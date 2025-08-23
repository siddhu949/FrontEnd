package com.aditya.usermanagement.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aditya.usermanagement.dao.User;

public class UserDao {
//	Connection con=DbUtility.connect();
	private String jdbcUrl ="jdbc.mysql://localhost:3306/userdb?useSSL=FALSE";
	private String jdbcUsername ="root";
	private String jdbcPassword ="root";
	private String jdbcDriver ="com.sql.jdbc/Driver";
	
	private static final String INSERT_USERS_SQL ="INSERT INTO users"+"(name,email,country) VALUES ="
			+ "(?,?,?);";
	private static final String SELECT_USER_BY_ID ="SELECT * FROM users WHERE id=?;";
	private static final String SELECT_ALL_USERS="SELECT * FROM users;";
	private static final String DELECT_USER_SQL ="DELETE FROM users WHERE id=?;";
	private static final String UPDATE_USERS_SQL ="UPDATE users SET name =?,email=?,country=? WHERE id=?;";
	public UserDao() {
		
	}
	protected Connection getConnection() {
		Connection con =null;
		try {
			Class.forName("jdbcDriver");
			con=DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	
	//insert user
	public void insertUser(User user) {
		Connection con =getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(INSERT_USERS_SQL);
			ps.setString(1,user.getName());
			ps.setString(2,user.getEmail());
			ps.setString(3,user.getCountry());
			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public User selectUser(int id) {
		User user=null;
		try(Connection con =getConnection()){
			PreparedStatement ps =con.prepareStatement(SELECT_USER_BY_ID);{
				ps.setInt(1, id);
				System.out.println(ps);
				ResultSet rs =ps.executeQuery();
				
				while(rs.next()) {
					String name=rs.getString("name");
					String email=rs.getString("email");
					String country=rs.getString("country");
					user =new User(id,name,email,country);
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> SelectAllusers(){
		List<User> users =new ArrayList<>();
		try(Connection con =getConnection()){
			PreparedStatement ps =con.prepareStatement(SELECT_ALL_USERS);
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id =rs.getInt("id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String country=rs.getString("country");
				users.add(new User(id,name,email,country));
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}
	public boolean deleteUser(int id) throws Exception {
		boolean rowDeleted;
		try(Connection con =getConnection()){
			PreparedStatement ps =con.prepareStatement(DELECT_USER_SQL);
			ps.setInt(1, id);
			rowDeleted=ps.executeUpdate()>0;
			
		} 
		return rowDeleted;
	}
	
	public boolean updateUser(User user) throws Exception  {
		boolean rowUpdated;
		try(Connection con=getConnection()){
			PreparedStatement ps =con.prepareStatement(UPDATE_USERS_SQL);
			System.out.println("updated user"+ps);
			ps.setString(1,user.getName());
			ps.setString(2,user.getEmail());
			ps.setString(3,user.getCountry());
			ps.setInt(4,user.getId());
			rowUpdated=ps.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
}
