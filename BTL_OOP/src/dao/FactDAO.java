package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Entity;
import model.Fact;

public class FactDAO implements ObjectDAO<Fact>{
	public FactDAO() {
		
	}
	public void findUtilities(Fact fact, ResultSet rs) throws SQLException {
		EntityDAO entity = new EntityDAO();
		ArticleDAO article = new ArticleDAO();
		fact.setFactId(rs.getString(1));
		fact.setSubjectId(rs.getString(2));
		fact.setRelationship(rs.getString(3));
		fact.setObjectId(rs.getString(4));
		fact.setArticleId(rs.getString(5));;
		fact.setTime(rs.getDate(6));
		fact.setExtractTime(rs.getDate(7));
	}
	
	public void createUtilities(Fact fact, PreparedStatement ps) throws SQLException{
		ps.setString(1, fact.getFactId());
		ps.setString(2, fact.getSubjectId());
		ps.setString(3, fact.getRelationship());
		ps.setString(4, fact.getObjectId());
		ps.setString(5, fact.getArticleId());
		ps.setDate(6, fact.getTime());
		ps.setDate(7, fact.getExtractTime());
	}
	public void create(Fact fact) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fact VALUES (?, ?, ?, ?, ?, ?, ?)");
			createUtilities(fact, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Fact fact) {
		
	}

	public Fact find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM fact WHERE fact_id=" + entity_id_str);

			Fact fact = new Fact();
			findUtilities(fact, rs);
			stmt.close();
			return fact;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Fact fact) {
		
	}

	public void createBatch(List<Fact> facts) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fact VALUES (?, ?, ?, ?, ?, ?, ?)");
			for (Fact fact : facts) {
				createUtilities(fact, ps);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Fact> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM fact");
			List<Fact> facts = new ArrayList<>();
			while (rs.next()) {
				Fact fact = new Fact();
				findUtilities(fact, rs);
				facts.add(fact);
			}
			stmt.close();
			return facts;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
