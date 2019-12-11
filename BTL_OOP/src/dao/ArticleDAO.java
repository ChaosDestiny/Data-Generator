package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Article;

public class ArticleDAO implements ObjectDAO<Article>{
	public ArticleDAO() {

	}

	public void create(Article article) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO article VALUES (?, ?, ?, ?)");
			ps.setString(1, article.getArticle_id());
			ps.setString(2, article.getLink());
			ps.setString(3, article.getTitle());
			ps.setDate(4, article.getPublication_date());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Article article) {
		
	}

	public Article find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM article WHERE article_id=" + entity_id_str);

			Article article = new Article();
			article.setArticle_id(rs.getString(1));
			article.setLink(rs.getString(2));
			article.setTitle(rs.getString(3));
			article.setPublication_date(rs.getDate(4));
			stmt.close();
			return article;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Article article) {
		
	}

	public void createBatch(List<Article> articles) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO article VALUES (?, ?, ?, ?)");
			for (Article article : articles) {
				ps.setString(1, article.getArticle_id());
				ps.setString(2, article.getLink());
				ps.setString(3, article.getTitle());
				ps.setDate(4, article.getPublication_date());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Article> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM article");
			List<Article> articles = new ArrayList<>();
			while (rs.next()) {
				Article article = new Article();
				article.setArticle_id(rs.getString(1));
				article.setLink(rs.getString(2));
				article.setTitle(rs.getString(3));
				article.setPublication_date(rs.getDate(4));
				articles.add(article);
			}
			stmt.close();
			return articles;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
