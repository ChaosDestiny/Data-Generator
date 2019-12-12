package model;

import java.sql.Date;

public class Fact {
	private String relationship, factId;
	private Date time, extractTime;
	protected Entity subject, object;
	protected Article article;
	
	public Fact(Entity subject, String relationship, Entity object, Date time, 
			Date extract_time, Article article) {
		this.relationship = relationship;
		this.subject = subject;
		this.object = object;
		this.article = article;
		this.extractTime = extract_time;
		this.time = time;
	}
	public Fact() {
		
	}
	
	public String getSubject_id() {
		return subject.getEntityId();
	}
	
	public Entity getSubject() {
		return subject;
	}
	public void setSubject(Entity subject) {
		this.subject = subject;
	}
	public Entity getObject() {
		return object;
	}
	public void setObject(Entity object) {
		this.object = object;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setExtractTime(Date extract_time) {
		this.extractTime = extract_time;
	}
	public String getObject_id() {
		return object.getEntityId();
	}
	
	public String getRelationship() {
		return relationship;
	}
	
	public String getFactId() {
		return factId;
	}
	public void setFactId(String fact_id) {
		this.factId = fact_id;
	}
	
	public String getArticle_id() {
		return article.getArticleId();
	}
	
	public Date getTime() {
		return time;
	}

	public Date getExtractTime() {
		return extractTime;
	}
}
