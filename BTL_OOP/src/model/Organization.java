package model;

import java.sql.Date;

public class Organization extends Entity {
	private String headquarter;
	private Date foundingDate;
	
	public Organization() {
		super();
	}
	public void setHeadquarter(String headquarter) {
		this.headquarter = headquarter;
	}

	public void setFoundingDate(Date founding_date) {
		this.foundingDate = founding_date;
	}
	
	public Organization(String name, String description, String headquarter, Date founding_date) {
		super(name, description);
		this.name = name;
		this.headquarter = headquarter;
		this.description = description;
		this.foundingDate = founding_date;
	}
	
	public String getHeadquarter() {
		return headquarter;
	}

	public Date getFoundingDate() {
		return foundingDate;
	}
}
