package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Country;
import model.Fact;


public class CountryDAO implements ObjectDAO<Country>{
	public CountryDAO() {

	}
	public void findUtilities(Country country, ResultSet rs) throws SQLException {
		country.setEntityId(rs.getString(1));
		country.setCapital(rs.getString(2));
	}
	
	public void createUtilities(Country country, PreparedStatement ps) throws SQLException{
		ps.setString(1, country.getEntityId());
		ps.setString(2, country.getCapital());
	}
	public void create(Country country) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO country VALUES (?, ?)");
			this.createUtilities(country, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Country country) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE country SET capital=? WHERE country_id=?");
			ps.setString(1, country.getCapital());
			ps.setString(2, country.getEntityId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Country find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM country WHERE country_id=" + entity_id_str);

			Country country = new Country();
			this.findUtilities(country, rs);
			stmt.close();
			return country;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Country country) {
		try {
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DELETE FROM country WHERE id=" + country.getEntityId());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Country> countries) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO country VALUES (?, ?)");
			for (Country i: countries) {
				this.createUtilities(i, ps);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Country> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM country");
			List<Country> countries = new ArrayList<>();
			while (rs.next()) {
				Country country = new Country();
				this.findUtilities(country, rs);
				countries.add(country);
			}
			stmt.close();
			return countries;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
