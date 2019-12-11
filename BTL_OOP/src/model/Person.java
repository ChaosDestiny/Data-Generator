package model;

public class Person extends Entity{
	private String job;
	private int age;
	public Person() {
		super();
	}
	public Person(String name, String description, String job, int age) {
		super(name, description);
		this.name = name;
		this.job = job;
		this.description = description;
		this.age = age;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}
