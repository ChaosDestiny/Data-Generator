package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Organization;
import model.Person;

public class PersonDAO implements ObjectDAO<Person> {
	public PersonDAO() {

	}
	
	public void findUtilities(Person person, ResultSet rs) throws SQLException {
		person.setEntity_id(rs.getString(1));
		person.setJob(rs.getString(2));
		person.setAge(rs.getInt(3));
	}

	public void createUtilities(Person person, PreparedStatement ps) throws SQLException {
		ps.setString(1, person.getEntity_id());
		ps.setString(2, person.getJob());
		ps.setInt(3, person.getAge());
	}
	
	public void create(Person person) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO person VALUES (?, ?, ?)");
			this.createUtilities(person, ps);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Person person) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE person SET job=?, age=? WHERE person_id=?");
			ps.setString(1, person.getJob());
			ps.setInt(2, person.getAge());
			ps.setString(3, person.getEntity_id());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Person find(Object entity_id) {
		String entity_id_str = (String) entity_id;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE person_id=" + entity_id_str);

			Person person = new Person();
			this.findUtilities(person, rs);
			stmt.close();
			return person;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void remove(Person person) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM person WHERE person_id=" + person.getEntity_id());
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void createBatch(List<Person> persons) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO person VALUES (?, ?, ?)");
			for (Person i : persons) {
				this.createUtilities(i, ps);
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Person> findAll() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person");
			List<Person> persons = new ArrayList<>();
			while (rs.next()) {
				Person person = new Person();
				this.findUtilities(person, rs);
				persons.add(person);
			}
			stmt.close();
			return persons;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
