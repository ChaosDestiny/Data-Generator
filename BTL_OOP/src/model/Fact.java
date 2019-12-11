package model;

import java.sql.Date;

public class Fact {
	private String relationship, fact_id;
	private Date time, extract_time;
	protected Entity subject, object;
	protected Article article;
	
	public Fact(Entity subject, String relationship, Entity object, Date time, 
			Date extract_time, Article article) {
		this.relationship = relationship;
		this.subject = subject;
		this.object = object;
		this.article = article;
		this.extract_time = extract_time;
		this.time = time;
	}
	
	public String getSubject_id() {
		return subject.getEntity_id();
	}
	
	public String getObject_id() {
		return object.getEntity_id();
	}
	
	public String getRelationship() {
		return relationship;
	}
	
	public String getFact_id() {
		return fact_id;
	}
	public void setFact_id(String fact_id) {
		this.fact_id = fact_id;
	}
	
	public String getArticle_id() {
		return article.getArticle_id();
	}
	
	public Date getTime() {
		return time;
	}

	public Date getExtract_time() {
		return extract_time;
	}
}
