package dao;

import java.sql.Connection;
import java.util.List;

import connection.JDBCMySQLConnection;

public interface ObjectDao<T> {
	public static final Connection connection = JDBCMySQLConnection.getConnection();
	
	List<T> findAll();

	public void update(T entity);

	public T find(Object id);

	public void remove(T entity);

	public void create(T entity);

	public void createBatch(List<T> entities);

}
