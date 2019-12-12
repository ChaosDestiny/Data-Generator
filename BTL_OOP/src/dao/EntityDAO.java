package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Entity;

public class EntityDAO implements ObjectDAO<Entity> {
	public EntityDAO() {

	}
	
	public void findUtilities(Entity entity, ResultSet rs) throws SQLException {
		entity.setEntityId(rs.getString(1));
		entity.setName(rs.getString(2));
		entity.setDescription(rs.getString(3));
		entity.setName(rs.getString(4));
	}

	public void createUtilities(Entity entity, PreparedStatement ps) throws SQLException {
		ps.setString(1, entity.getEntityId());
		ps.setString(2, entity.getName());
		ps.setString(3, entity.getDescription());
		ps.setString(4, entity.getEntityName());
	}
	
	public void create(Entity entity) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO entity VALUES (?, ?, ?, ?)");
			this.createUtilities(entity, ps);
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
			ps.setString(3, entity.getEntityName());
			ps.setString(4, entity.getEntityId());
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
			this.findUtilities(entity, rs);
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
			stmt.executeUpdate("DELETE FROM entity WHERE entity_id=" + entity.getEntityId());
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void createBatch(List<Entity> entitys) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO entity VALUES (?, ?, ?, ?)");
			for (Entity i : entitys) {
				this.createUtilities(i, ps);
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
				this.findUtilities(entity, rs);
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
