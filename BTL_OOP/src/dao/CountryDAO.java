package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Country;


public class CountryDAO implements ObjectDao<Country>{
	public CountryDAO() {

	}

	public void create(Country country) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO country VALUES (?, ?)");
			ps.setString(1, country.getEntity_id());
			ps.setString(2, country.getCapital());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Event event) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE user SET time=? WHERE event_id=?");
			ps.setDate(1, event.getDate());
			ps.setString(2, event.getEntity_id());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Event find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE event_id=" + entity_id_str);

			Event event = new Event();
			event.setEntity_id(rs.getString(1));
			event.setDate(rs.getDate(2));
			stmt.close();
			return event;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Event event) {
		try {
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM event WHERE id=" + event.getEntity_id());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Event> events) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO event VALUES (?, ?)");
			for (Event i: events) {
				ps.setString(1, i.getEntity_id());
				ps.setDate(2, i.getDate());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Event> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM event");
			List<Event> events = new ArrayList<>();
			while (rs.next()) {
				Event event = new Event();
				event.setEntity_id(rs.getString(1));
				event.setDate(rs.getDate(2));
				events.add(event);
			}
			stmt.close();
			return events;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
