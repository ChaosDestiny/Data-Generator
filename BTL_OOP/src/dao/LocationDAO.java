package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Location;


public class LocationDAO implements ObjectDAO<Location>{
	public LocationDAO() {

	}

	public void create(Location location) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO location VALUES (?, ?)");
			ps.setString(1, location.getEntity_id());
			ps.setString(2, location.getCountry());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Location location) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE location SET country=? WHERE location_id=?");
			ps.setString(1, location.getCountry());
			ps.setString(2, location.getEntity_id());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Location find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM location WHERE location_id=" + entity_id_str);

			Location location = new Location();
			location.setEntity_id(rs.getString(1));
			location.setCountry(rs.getString(2));
			stmt.close();
			return location;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Location location) {
		try {
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM location WHERE location_id=" + location.getEntity_id());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Location> locations) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO location VALUES (?, ?)");
			for (Location i: locations) {
				ps.setString(1, i.getEntity_id());
				ps.setString(2, i.getCountry());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Location> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM location");
			List<Location> locations = new ArrayList<>();
			while (rs.next()) {
				Location location = new Location();
				location.setEntity_id(rs.getString(1));
				location.setCountry(rs.getString(2));
				locations.add(location);
			}
			stmt.close();
			return locations;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
