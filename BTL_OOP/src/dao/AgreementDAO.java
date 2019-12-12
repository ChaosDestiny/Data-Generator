package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class AgreementDAO implements ObjectDAO<Agreement>{
	public AgreementDAO() {
		
	}
	public void findUtilities(Agreement agreement, ResultSet rs) throws SQLException {
		agreement.setEntityId(rs.getString(1));
		agreement.setContractDate(rs.getDate(2));
	}
	
	public void createUtilities(Agreement agreement, PreparedStatement ps) throws SQLException{
		ps.setString(1, agreement.getEntityId());
		ps.setDate(2, agreement.getContractDate());
	}
	public void create(Agreement agreement) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO agreement VALUES (?, ?)");
			createUtilities(agreement, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Agreement agreement) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE agreement SET contract_date=? WHERE agreement_id=?");
			ps.setDate(1, agreement.getContractDate());
			ps.setString(2, agreement.getEntityId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Agreement find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM agreement WHERE agreement_id=" + entity_id_str);

			Agreement agreement = new Agreement();
			findUtilities(agreement, rs);
			stmt.close();
			return agreement;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Agreement agreement) {
		try {
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM agreement WHERE agreement_id=" + agreement.getEntityId());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Agreement> agreements) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO agreement VALUES (?, ?)");
			for (Agreement i: agreements) {
				createUtilities(i, ps);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Agreement> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM agreement");
			List<Agreement> agreements = new ArrayList<>();
			while (rs.next()) {
				Agreement agreement = new Agreement();
				findUtilities(agreement, rs);
				agreements.add(agreement);
			}
			stmt.close();
			return agreements;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
