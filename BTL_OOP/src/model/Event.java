package model;

import java.sql.Date;

public class Event extends Entity {
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Event(String name, String description, Date date) {
		super(name, description);
		this.name = name;
		this.date = date;
		this.description = description;
	}

}
