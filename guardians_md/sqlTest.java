import java.sql.*;

/**
 * You need to download the Java Database Connector (JDBC) here (http://dev.mysql.com/downloads/connector/j/). 
 * Once downloaded, you will need to move the .jar file which was part of the .zip you downloaded for the JDBC. 
 * Move the JAR file to the folder: /Library/Java/Extensions
 **/
public class sqlTest {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306";	// Use MySQL workbench home window to figure out what comes after localhost

	//  Database credentials
	static final String USER = "root";	//default
	static final String PASS = "";		//no password by default
	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		try {
			System.out.println("Connecting to database...");
			conn = connectToDatabase();

			// A Statement object is used to execute SQL statements. It has 3 methods:
			// Statement.execute(String sql), which simply executes the SQL statement.
			// Statement.executeUpdate(String sql), which executes a SQL statement that changes the database (i.e. UPDATE/INSERT).
			// Statement.executeQuery(String sql), which executes a SQL statement that returns a Result Set (i.e. SELECT).
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			try {
				System.out.print("Checking for database...     ");
				initializeDatabase(stmt);
				System.out.println("Not found.  Created new database.");
			} catch(SQLException se) {
				System.out.println("Found!      Using existing database.");
				// Database already exists, continue.
			}

			// Use the groupproject database
			sql = "USE groupproject";
			stmt.execute(sql);

			// Show initial state of members table
			printMembers(stmt);

			// Remove Trevor from the list
			System.out.println("Deleting member 3.");
			sql = "DELETE FROM members WHERE memberID=3";
			stmt.executeUpdate(sql);

			// Show members table
			printMembers(stmt);

			// Add Trevor back into the group
			System.out.println("Adding Trevor Gorman as member 3.");
			sql = "INSERT INTO members values (3, 'Trevor Gorman', '100 State St', 'Framingham, MA');";
			stmt.executeUpdate(sql);

			// Show members table
			printMembers(stmt);

			// Update Thomas' address
			sql = "UPDATE members SET city='Westwood, MA' WHERE name='Thomas McNulty';";
			stmt.executeUpdate(sql);
			System.out.println("Setting Thomas McNulty's city to Westwood.");

			// Show members table
			printMembers(stmt);

			// Change Thomas' address back
			sql = "UPDATE members SET city='Framingham, MA' WHERE name='Thomas McNulty';";
			stmt.executeUpdate(sql);
		// Crazy incantation of error catching
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(ClassNotFoundException e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}

	private static void printMembers(Statement stmt) throws SQLException {
		String sql = "SELECT * FROM members";
		ResultSet rs = stmt.executeQuery(sql);

		// Extract data from result set.
		while(rs.next()) { // Advances to the next entry (row) in the result set.
			// Retrieve entry's attributes by column name
			int id  = rs.getInt("memberID");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String city = rs.getString("city");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Name: " + name);
			System.out.print(", Address: " + address);
			System.out.println(", City: " + city);
		}

		// Close result set.
		try {
			rs.close();
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

	/**
	 * Establishes and returns a Connection object for the database.
	 **/
	private static Connection connectToDatabase() throws SQLException, ClassNotFoundException {
		//STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		//STEP 3: Open a connection
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
		return conn;
	}

	/**
	 * Initialize the database. Only needs to be run once, when your database is empty.
	 **/
	private static void initializeDatabase(Statement stmt) throws SQLException {
		// Creates a new database
		String sql = "CREATE DATABASE groupproject";
		stmt.execute(sql);	// executeUpdate method is used for SQL statements which change the database without returning information.

		// Use the groupproject database
		sql = "USE groupproject";
		stmt.execute(sql);

		// Creates a new table
		sql = "CREATE TABLE members" + 
				"(memberID INT UNSIGNED NOT NULL, " +
				"name CHAR(30) NOT NULL, " +
				"address CHAR(40), " + 
				"city CHAR(20), " +
				"PRIMARY KEY(memberID));";
		stmt.execute(sql);

		String[] members = {"Thomas McNulty", "Sarah Hakkoum", "Christina Reid", "Trevor Gorman"};
		for(int i=0; i<4; i++) {
			// There's a better way to do this using PreparedStatement objects but I haven't gotten that 100% figured out yet.
			sql = "INSERT INTO members values (" + i + ", '" + members[i] + "', '100 State St', 'Framingham, MA');";
			stmt.executeUpdate(sql);
		}
	}
}