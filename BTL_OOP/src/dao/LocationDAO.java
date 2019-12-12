package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Location;


public class LocationDAO implements ObjectDAO<Location>{
	public LocationDAO() {

	}
	
	public void findUtilities(Location location, ResultSet rs) throws SQLException {
		location.setEntityId(rs.getString(1));
		location.setCountry(rs.getString(2));
	}

	public void createUtilities(Location location, PreparedStatement ps) throws SQLException {
		ps.setString(1, location.getEntityId());
		ps.setString(2, location.getCountry());
	}
	public void create(Location location) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO location VALUES (?, ?)");
			this.createUtilities(location, ps);
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
			ps.setString(2, location.getEntityId());
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
			this.findUtilities(location, rs);
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
	        stmt.executeUpdate("DELETE FROM location WHERE location_id=" + location.getEntityId());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Location> locations) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO location VALUES (?, ?)");
			for (Location location: locations) {
				this.createUtilities(location, ps);
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
				this.findUtilities(location, rs);
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
