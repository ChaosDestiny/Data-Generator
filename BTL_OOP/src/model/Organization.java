package model;

import java.sql.Date;

public class Organization extends Entity {
	
	private String headquarter;
	private Date founding_date;
	
	public Organization(String name, String description, String headquarter, Date founding_date) {
		super(name, description);
		this.name = name;
		this.headquarter = headquarter;
		this.description = description;
		this.founding_date = founding_date;
	}
	
	public String getHeadquarter() {
		return headquarter;
	}

	public Date getFounding_date() {
		return founding_date;
	}
}
