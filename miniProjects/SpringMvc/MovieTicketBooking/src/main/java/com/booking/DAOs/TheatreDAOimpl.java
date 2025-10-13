package com.booking.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.booking.DTOs.TheatreDTO;
import com.booking.Utils.DBConnection;

public class TheatreDAOimpl implements TheatreDAO {

	@Override
	public boolean addTheatre(TheatreDTO theatre) {
		  String sql = "INSERT INTO theatres (theatre_name, city, address) VALUES (?, ?, ?)";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            
	            ps.setString(1, theatre.getTheatreName());
	            ps.setString(2, theatre.getCity());
	            ps.setString(3, theatre.getAddress());

	            int rows = ps.executeUpdate();
	            return rows > 0; 
	}
catch(Exception e) {
	e.printStackTrace();
}
	        return false;
	}
	@Override
	public TheatreDTO getTheatreById(int theatreId) {
		 String sql = "SELECT * FROM theatres WHERE theatre_id = ?";
	        TheatreDTO theatre = null;

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            
	            ps.setInt(1, theatreId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                theatre = new TheatreDTO(
	                    rs.getInt("theatre_id"),
	                    rs.getString("theatre_name"),
	                    rs.getString("city"),
	                    rs.getString("address")
	                );
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return theatre;
	}

	@Override
	public List<TheatreDTO> getAllTheatres() {
		 List<TheatreDTO> theatres = new ArrayList<>();
	        String sql = "SELECT * FROM theatres";

	        try (Connection con = DBConnection.getConnection();
	             Statement st = con.createStatement();
	             ResultSet rs = st.executeQuery(sql)) {

	            while (rs.next()) {
	                theatres.add(new TheatreDTO(
	                    rs.getInt("theatre_id"),
	                    rs.getString("theatre_name"),
	                    rs.getString("city"),
	                    rs.getString("address")
	                ));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return theatres;
	}

	@Override
	public List<TheatreDTO> getTheatreByCity(String city) {
		List<TheatreDTO> theatres = new ArrayList<>();
        String sql = "SELECT * FROM theatres WHERE city = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                theatres.add(new TheatreDTO(
                    rs.getInt("theatre_id"),
                    rs.getString("theatre_name"),
                    rs.getString("city"),
                    rs.getString("address")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return theatres;
	}

	@Override
	public boolean updateTheatre(TheatreDTO theatre) {
		String sql = "UPDATE theatres SET theatre_name = ?, city = ?, address = ? WHERE theatre_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, theatre.getTheatreName());
            ps.setString(2, theatre.getCity());
            ps.setString(3, theatre.getAddress());
            ps.setInt(4, theatre.getTheatreId());

            int rows = ps.executeUpdate();
            return rows > 0; // success if updated

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean deleteTheatre(int theatreId) {
		 String sql = "DELETE FROM theatres WHERE theatre_id = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            
	            ps.setInt(1, theatreId);
	            int rows = ps.executeUpdate();
	            return rows > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

}
