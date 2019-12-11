package model;

public class Country extends Entity {
	protected String capital;
	
	public Country() {
		super();
	}
	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Country(String name, String description, String capital) {
		super(name, description);
		this.name = name;
		this.capital = capital;
		this.description = description;
	}
}
