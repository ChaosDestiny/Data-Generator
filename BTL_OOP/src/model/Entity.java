package model;

public class Entity {
	protected String entity_id;
	protected String name;
	protected String description;
	private String entity_name;
	
	public Entity() {
		
	}
	public Entity(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
}
