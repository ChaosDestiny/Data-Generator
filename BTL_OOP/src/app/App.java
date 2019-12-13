package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import connection.JDBCMySQLConnection;

public class App {
	public static final Connection connection = JDBCMySQLConnection.getConnection();

	public static void main(String[] args) throws Exception {
		System.out.println("Chào mừng bạn đến với app của chúng tôi");
		int check = 10;
		Scanner scanner = new Scanner(System.in);
		while (check != 0) {
			System.out.println("Xây dựng cơ sở dữ liệu nhấn phím 1");
			System.out.println("Thực hiện truy vấn nhấn phím 2");
			System.out.println("Thoát nhấn phím 0");
			check = scanner.nextInt();
			if (check == 1) {

				Statement stmt = connection.createStatement();
				String sql = "SET SQL_SAFE_UPDATES = 0;";
				stmt.executeUpdate(sql);
				sql = "delete from fact;";
				stmt.executeUpdate(sql);
				sql = "delete from person;";
				stmt.executeUpdate(sql);
				sql = "delete from location;";
				stmt.executeUpdate(sql);
				sql = "delete from event;";
				stmt.executeUpdate(sql);
				sql = "delete from event;";
				stmt.executeUpdate(sql);
				sql = "delete from organization;";
				stmt.executeUpdate(sql);
				sql = "delete from country;";
				stmt.executeUpdate(sql);
				sql = "delete from agreement;";
				stmt.executeUpdate(sql);
				sql = "delete from entity;";
				stmt.executeUpdate(sql);
				sql = "delete from article;";
				stmt.executeUpdate(sql);
				stmt.close();

				// -------------------------------//
				/**
				 * Số lượng cụ thể: person : 10 country: 10 agreement: 10 event: 10 article 50
				 * location, fact, org : 100 - 3 lần lặp sau cứ gấp 10 lần lên. Chú ý: Trước khi
				 * insert lại thì thực hiện xóa cơ sở dữ liệu đã Câu lệnh:
				 * 
				 * use oop; SET SQL_SAFE_UPDATES = 0; delete from fact; delete from person;
				 * delete from location; delete from event; delete from organization; delete
				 * from country; delete from agreement; delete from entity; delete from article;
				 */
				Insertion insertion = new Insertion();
				GeneDataJson.run();
				insertion.person();
				insertion.organization();
				insertion.location();
				insertion.event();
				insertion.country();
				insertion.agreement();
				insertion.article();
				insertion.fact();
			} else if (check == 2) {
				System.out.println("Nhập câu truy vấn trên 1 dòng: ");
				scanner = new Scanner(System.in);
				String query = scanner.nextLine();
				QueryExc q = new QueryExc();
//				Thread.sleep(500);
				if (query != null) {
					long a = System.currentTimeMillis();
					ResultSet rs = q.exe(query);
					long b = System.currentTimeMillis();
					q.extractResult(rs);
					System.out.println("Thời gian thực hiện truy vấn (ms): " + (b - a));
				}
				
			}
		}
	}
}
