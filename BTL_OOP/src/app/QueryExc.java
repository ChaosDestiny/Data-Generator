package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import connection.JDBCMySQLConnection;

public class QueryExc {
	public static final Connection connection = JDBCMySQLConnection.getConnection();
	public ResultSet exe(String query)  {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public void extractResult(ResultSet resultSet) {
		try {
			ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = resultSet.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		QueryExc q = new QueryExc();
		long a = System.currentTimeMillis();
		ResultSet rs = q.exe("select * from fact");
		long b = System.currentTimeMillis();
		q.extractResult(rs);
		System.out.println("Thời gian thực hiện truy vấn (ms): " + (b - a));
	}
}
