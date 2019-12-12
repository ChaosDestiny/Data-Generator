package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Entity;
import model.Event;

public class EventDAO implements ObjectDAO<Event> {
	public EventDAO() {

	}

	public void findUtilities(Event event, ResultSet rs) throws SQLException {
		event.setEntityId(rs.getString(1));
		event.setDate(rs.getDate(2));
	}

	public void createUtilities(Event event, PreparedStatement ps) throws SQLException {
		ps.setString(1, event.getEntityId());
		ps.setDate(2, event.getDate());
	}
	
	public void create(Event event) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO event VALUES (?, ?)");
			this.createUtilities(event, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Event event) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE event SET time=? WHERE event_id=?");
			ps.setDate(1, event.getDate());
			ps.setString(2, event.getEntityId());
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
			this.findUtilities(event, rs);
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
	        stmt.executeUpdate("DELETE FROM event WHERE event_id=" + event.getEntityId());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Event> events) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO event VALUES (?, ?)");
			for (Event event: events) {
				this.createUtilities(event, ps);
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
				this.findUtilities(event, rs);
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
