package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.JDBCMySQLConnection;

public interface ObjectDAO<T> {
	public static final Connection connection = JDBCMySQLConnection.getConnection();
	
	List<T> findAll();

	public void update(T entity);

	public T find(Object id);

	public void remove(T entity);

	public void create(T entity);

	public void createBatch(List<T> entities);
	// Avoid Duplicating Code
	public void findUtilities(T entities, ResultSet rs) throws SQLException;
	// Avoid Duplicating Code
	public void createUtilities(T entities, PreparedStatement ps) throws SQLException;
}
