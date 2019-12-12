package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Location;
import model.Organization;


public class OrganizationDAO implements ObjectDAO<Organization>{
	public OrganizationDAO() {
		
	}
	
	public void findUtilities(Organization organization, ResultSet rs) throws SQLException {
		organization.setEntityId(rs.getString(1));
		organization.setHeadquarter(rs.getString(2));
		organization.setFoundingDate(rs.getDate(3));
	}

	public void createUtilities(Organization organization, PreparedStatement ps) throws SQLException {
		ps.setString(1, organization.getEntityId());
		ps.setString(2, organization.getHeadquarter());
		ps.setDate(3, organization.getFoundingDate());
	}
	
	public void create(Organization organization) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO organization VALUES (?, ?, ?)");
			this.createUtilities(organization, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Organization organization) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE organization SET headquarter=?, founding_date=? WHERE organization_id=?");
			ps.setString(1, organization.getHeadquarter());
			ps.setDate(2, organization.getFoundingDate());
			ps.setString(3, organization.getEntityId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Organization find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM organization WHERE organization_id=" + entity_id_str);

			Organization organization = new Organization();
			this.findUtilities(organization, rs);
			stmt.close();
			return organization;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Organization organization) {
		try {
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM organization WHERE organization_id=" + organization.getEntityId());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Organization> organizations) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO organization VALUES (?, ?, ?)");
			for (Organization organization: organizations) {
				this.createUtilities(organization, ps);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Organization> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM organization");
			List<Organization> organizations = new ArrayList<>();
			while (rs.next()) {
				Organization organization = new Organization();
				this.findUtilities(organization, rs);
				organizations.add(organization);
			}
			stmt.close();
			return organizations;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
