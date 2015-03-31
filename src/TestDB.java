import java.sql.*;

public class TestDB {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Good to go.");
		}
		catch (Exception e) {
			System.out.println("JDBC Driver error " + e);
		}
	}
}