package model;

public class Country extends Entity {
	protected String capital;
	
	public Country(String name, String description, String capital) {
		super(name, description);
		this.name = name;
		this.capital = capital;
		this.description = description;
	}
}
