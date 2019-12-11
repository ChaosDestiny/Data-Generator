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

	public void create(Fact fact) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fact VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, fact.getFact_id());
			ps.setString(2, fact.getSubject_id());
			ps.setString(3, fact.getRelationship());
			ps.setString(4, fact.getObject_id());
			ps.setString(5, fact.getArticle_id());
			ps.setDate(6, fact.getTime());
			ps.setDate(7, fact.getExtract_time());
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
			EntityDAO entity = new EntityDAO();
			ArticleDAO article = new ArticleDAO();
			fact.setFact_id(rs.getString(1));
			fact.setSubject(entity.find(rs.getString(2)));
			fact.setRelationship(rs.getString(3));
			fact.setObject(entity.find(rs.getString(4)));
			fact.setArticle(article.find(rs.getString(5)));
			fact.setTime(rs.getDate(6));
			fact.setExtract_time(rs.getDate(7));
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
				ps.setString(1, fact.getFact_id());
				ps.setString(2, fact.getSubject_id());
				ps.setString(3, fact.getRelationship());
				ps.setString(4, fact.getObject_id());
				ps.setString(5, fact.getArticle_id());
				ps.setDate(6, fact.getTime());
				ps.setDate(7, fact.getExtract_time());
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
				EntityDAO entity = new EntityDAO();
				ArticleDAO article = new ArticleDAO();
				fact.setFact_id(rs.getString(1));
				fact.setSubject(entity.find(rs.getString(2)));
				fact.setRelationship(rs.getString(3));
				fact.setObject(entity.find(rs.getString(4)));
				fact.setArticle(article.find(rs.getString(5)));
				fact.setTime(rs.getDate(6));
				fact.setExtract_time(rs.getDate(7));
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
