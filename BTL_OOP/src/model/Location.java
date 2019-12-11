package model;

public class Location extends Entity {

	private String country;
	
	public Location() {
		super();
	}
	
	public Location(String name, String description, String country) {
		super(name, description);
		this.name = name;
		this.description = description;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
