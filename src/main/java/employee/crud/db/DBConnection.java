package employee.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String db_url = "jdbc:postgresql://localhost:5432/employee_db";
	private static final String user_name = "postgres";
	private static final String password = "postgres";
	private static Connection conn;

	public static Connection getConnection() {

		System.out.println("=========Method Started=========");

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(db_url, user_name, password);

			if (conn != null) {
				System.out.println("==========Connection Success===========");
				return conn;
			} else {
				System.out.println("=========Connection Fail===============");
				return null;
			}

		} catch (Exception e) {
			System.out.println("DBConnection Exception e==> " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
	
	public static void main(String[] args) {
		System.out.println(DBConnection.getConnection());
	}

}
