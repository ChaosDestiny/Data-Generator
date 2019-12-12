package model;

import java.sql.Date;

public class Article {
	private String link, title, articleId;
	private Date publicationDate;
	
	public Article() {
		
	}
 	public Article(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String article_id) {
		this.articleId = article_id;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publication_date) {
		this.publicationDate = publication_date;
	}
}
