package model;

public class Entity {
	protected String entityId;
	protected String name;
	protected String description;
	private String entityName;
	
	public Entity() {
		
	}
	public Entity(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entity_id) {
		this.entityId = entity_id;
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
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
