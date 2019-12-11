package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Country;


public class CountryDAO implements ObjectDAO<Country>{
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

	public void update(Country country) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE country SET capital=? WHERE country_id=?");
			ps.setString(1, country.getCapital());
			ps.setString(2, country.getEntity_id());
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
			country.setEntity_id(rs.getString(1));
			country.setCapital(rs.getString(2));
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
	        stmt.executeUpdate("DELETE FROM country WHERE id=" + country.getEntity_id());
	        stmt.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	public void createBatch(List<Country> countries) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO country VALUES (?, ?)");
			for (Country i: countries) {
				ps.setString(1, i.getEntity_id());
				ps.setString(2, i.getCapital());
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
				country.setEntity_id(rs.getString(1));
				country.setCapital(rs.getString(2));
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
