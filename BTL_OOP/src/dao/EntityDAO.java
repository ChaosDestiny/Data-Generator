package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Entity;

public class EntityDAO implements ObjectDAO<Entity> {
	public EntityDAO() {

	}

	public void create(Entity entity) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO entity VALUES (?, ?, ?, ?)");
			ps.setString(1, entity.getEntity_id());
			ps.setString(2, entity.getName());
			ps.setString(3, entity.getDescription());
			ps.setString(4, entity.getEntity_name());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Entity entity) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE entity SET name=?, description=?, entity_name=? WHERE entity_id=?");
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getDescription());
			ps.setString(3, entity.getEntity_name());
			ps.setString(4, entity.getEntity_id());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Entity find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM entity WHERE entity_id=" + entity_id_str);

			Entity entity = new Entity();
			entity.setEntity_id(rs.getString(1));
			entity.setName(rs.getString(2));
			entity.setDescription(rs.getString(3));
			entity.setName(rs.getString(4));
			stmt.close();
			return entity;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Entity entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM entity WHERE entity_id=" + entity.getEntity_id());
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void createBatch(List<Entity> entitys) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO entity VALUES (?, ?, ?)");
			for (Entity i : entitys) {
				ps.setString(1, i.getEntity_id());
				ps.setString(2, i.getName());
				ps.setString(3, i.getDescription());
				ps.setString(4, i.getEntity_name());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Entity> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM entity");
			List<Entity> entitys = new ArrayList<>();
			while (rs.next()) {
				Entity entity = new Entity();
				entity.setEntity_id(rs.getString(1));
				entity.setName(rs.getString(2));
				entity.setDescription(rs.getString(3));
				entity.setName(rs.getString(4));
				entitys.add(entity);
			}
			stmt.close();
			return entitys;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
