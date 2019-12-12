package connection;

import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class JDBCMySQLConnection {
	private static JDBCMySQLConnection instance = new JDBCMySQLConnection();
	public static final String URL = "jdbc:mysql://localhost:3306/oop";
	public static final String USER = "root";
	public static final String PASS = "1201";
	public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	/**
	 * Get a connection to database
	 * 
	 * @return Connection object
	 */
	private JDBCMySQLConnection() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private Connection createConnection() {
		try {
			return (Connection) DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
	/**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = JDBCMySQLConnection.getConnection();
        if (connection != null) {
        	System.out.println("Ok");
        }
    }
}
