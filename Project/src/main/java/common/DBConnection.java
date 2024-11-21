package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// Define the database properties
	private static final String URL = "jdbc:mysql://localhost:3306/webshop?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "1234";

	private static Connection connection = null;

	// DB 접속하여 연결 리턴
	public static Connection openConnection() {
		if (connection != null)
			return connection;
		else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				// System.out.println("연결완료!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}

}
