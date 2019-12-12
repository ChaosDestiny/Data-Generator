package model;

import java.sql.Date;

import dao.EntityDAO;

public class Fact {
	private String relationship, factId;
	private Date time, extractTime;
//	protected Entity subject, object;
	protected String articleId;
	private String subjectId, objectId;
	

	
	


	public Fact(String relationship, String factId, Date time, Date extractTime, String articleId, String subjectId,
			String objectId) {
		super();
		this.relationship = relationship;
		this.factId = factId;
		this.time = time;
		this.extractTime = extractTime;
		this.articleId = articleId;
		this.subjectId = subjectId;
		this.objectId = objectId;
	}



	public Fact() {
		
	}
	
	

	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	public String getObjectId() {
		return objectId;
	}


	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}


	
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

	public String getRelationship() {
		return relationship;
	}
	
	public String getFactId() {
		return factId;
	}
	public void setFactId(String fact_id) {
		this.factId = fact_id;
	}
	
	
	
	public String getArticleId() {
		return articleId;
	}



	public Date getTime() {
		return time;
	}

	public Date getExtractTime() {
		return extractTime;
	}
}
