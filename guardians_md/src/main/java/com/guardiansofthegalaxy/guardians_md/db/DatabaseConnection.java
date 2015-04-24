package com.guardiansofthegalaxy.guardians_md.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DatabaseConnection implements DbConn {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://guardiansmddatabase.czanmkabbmcd.us-west-2.rds.amazonaws.com:3306";	// Amazon AWS instance

	//  Database credentials
	static final String DB_USER = "root";	//default
	static final String DB_PASS = "starlord";// AWS RDS password

	private Connection conn = null;
	private Statement stmt = null;

	/**
	 * Constructor. Calls the connect() method and does some printing.
	 **/
	public DatabaseConnection() {
		boolean initialized = false;

		try {
			initialized = connect();
		} catch(SQLException se) {
			System.out.println("Could not connect to the database." + "\n" + se.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println("Could not register the JDBC driver.");
		}

		System.out.println(initialized ? "New database initialized." : "Using preexisting database.");
	}

	/**
	 * Connects to MYSQL and looks for an existing database to use. If no existing database is found, then it creates a new one.
	 * @return True if a new database was created.
	 **/
	private boolean connect() throws SQLException, ClassNotFoundException {
		boolean dbInitialized;
		// Register the JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		// Open a connection
		conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
		stmt = conn.createStatement();

		try {
			//stmt.execute("DROP DATABASE gotg_md");
			initializeDatabase();
			dbInitialized = true;
		} catch(SQLException e) {
			dbInitialized = false;
			String sql = "USE gotg_md";
			stmt.execute(sql);
		}

		return dbInitialized;
	}

	/**
	 * Creates the database and all of its tables.
	 * @throws SQLException if the database already exists.
	 **/
	private void initializeDatabase() throws SQLException {
		String sql = "CREATE DATABASE gotg_md";
		stmt.execute(sql);

		sql = "USE gotg_md";
		stmt.execute(sql);

		sql = 	"CREATE TABLE patient" +
				"(patient_id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
				"first_name VARCHAR(48) NOT NULL," +
				"last_name VARCHAR(48) NOT NULL," +
				"birthdate DATE," +
				"gender CHAR(6)," +
				"address VARCHAR(48)," +
				"address_2 VARCHAR(48)," + 
				"city VARCHAR(48)," +
				"state VARCHAR(48)," +
				"zipcode VARCHAR(10)," +
				"country VARCHAR(48)," +
				"insurance_provider VARCHAR(48)," +
				"insurance_account_number VARCHAR(24)," +
				"PRIMARY KEY(patient_id));";
		stmt.execute(sql);

		sql = 	"CREATE TABLE user" +
				"(username VARCHAR(12) NOT NULL," +
				"first_name VARCHAR(48) NOT NULL," +
				"last_name VARCHAR(48) NOT NULL," +
				"specialty VARCHAR(96)," +
				"pager_number VARCHAR(14)," +
				"position CHAR(6) NOT NULL," +
				"password_salt CHAR(48) NOT NULL," +
				"password_hash CHAR(48) NOT NULL," +
				"PRIMARY KEY(username));";
		stmt.execute(sql);

		sql = 	"CREATE TABLE general_practice_visit" +
				"(visit_id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
				"patient_id INT UNSIGNED NOT NULL," +
				"doctor_id VARCHAR(12) NOT NULL," +
				"date DATE NOT NULL," +
				"field_a TEXT," +
				"field_b TEXT," +
				"field_c TEXT," +
				"field_d TEXT," +
				"field_e TEXT," +
				"field_f TEXT," +
				"field_g TEXT," +
				"comments TEXT," +
				"FOREIGN KEY(doctor_id) REFERENCES user(username) ON DELETE CASCADE," +
				"FOREIGN KEY(patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE," +
				"PRIMARY KEY(visit_id));";
		stmt.execute(sql);

		sql = 	"CREATE TABLE lab_order" +
				"(lab_order_id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
				"visit_id INT UNSIGNED NOT NULL," +
				"lab_name VARCHAR(48)," + 
				"test_name VARCHAR(96)," +
				"results TEXT," +
				"FOREIGN KEY(visit_id) REFERENCES general_practice_visit(visit_id) ON DELETE CASCADE," +
				"PRIMARY KEY(lab_order_id));";
		stmt.execute(sql);

		sql = 	"CREATE TABLE prescription" +
				"(prescription_id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
				"visit_id INT UNSIGNED NOT NULL," +
				"medication_type VARCHAR(48)," + 
				"medication_name VARCHAR(96)," +
				"FOREIGN KEY(visit_id) REFERENCES general_practice_visit(visit_id) ON DELETE CASCADE," +
				"PRIMARY KEY(prescription_id));";
		stmt.execute(sql);

		DatabasePopulator pop = new DatabasePopulator(this);
		pop.populateDatabase();
	}

	/**
	 * Inserts a new Patient object into the patient table of the database.
	 * @return True if the Patient was inserted successfully.
	 **/
	public boolean registerPatient(Patient patient) {
		boolean error = false;
		String sql = "INSERT INTO patient VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, patient.getFirstName());
			regStmt.setString(2, patient.getLastName());
			regStmt.setString(3, patient.getBirthdate());
			regStmt.setString(4, patient.getGender());
			regStmt.setString(5, patient.getAddress1());
			regStmt.setString(6, patient.getAddress2());
			regStmt.setString(7, patient.getCity());
			regStmt.setString(8, patient.getState());
			regStmt.setString(9, patient.getZipcode());
			regStmt.setString(10, patient.getCountry());
			regStmt.setString(11, patient.getInsuranceProvider());
			regStmt.setString(12, patient.getInsuranceNumber());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("registerPatient()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("registerPatient()", "", se2);
			}
		}

		return !error;
	}

	/**
	 * Retrieves a patient from the patient table.
	 * @return The Patient with the specified id, null if no patient was found with that id.
	 **/
	public Patient getPatient(int patientID) {
		String sql = "SELECT * FROM patient WHERE patient_id=?;";
		PreparedStatement selectStmt = null;
		ResultSet patientResult = null;
		Patient patient = null;
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, patientID);
			patientResult = selectStmt.executeQuery();

			if(patientResult.next()) {
				patient = new Patient(patientResult.getInt(1), patientResult.getString(2), patientResult.getString(3), 
					patientResult.getString(4), patientResult.getString(5), patientResult.getString(6), 
					patientResult.getString(7), patientResult.getString(8), patientResult.getString(9), 
					patientResult.getString(10), patientResult.getString(11), patientResult.getString(12),
					patientResult.getString(13));
			}
		} catch(SQLException se) {
			logSQLException("getPatient()", "", se);
		} finally {
			try {
				if(selectStmt != null) {
				selectStmt.close();
			}
			if(patientResult != null) {
				patientResult.close();
			}
			} catch(SQLException se2) {
				logSQLException("getPatient()", "", se2);
			}
		}
		return patient;
	}

	/**
	 * Deletes the specified patient from the database.
	 * @return True if a patient with that id was successfully deleted from the database.
	 **/
	public boolean deletePatient(int patientID) {
		boolean error = false;
		String sql = "DELETE FROM patient WHERE patient_id=?;";
		PreparedStatement selectStmt = null;
		Patient patient = null;
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, patientID);
			selectStmt.execute();
		} catch(SQLException se) {
			logSQLException("deletePatient()", "", se);
			error = true;
		} finally {
			try {
				if(selectStmt != null) {
				selectStmt.close();
			}
			} catch(SQLException se2) {
				logSQLException("deletePatient()", "", se2);
			}
		}
		return !error;
	}

	/**
	 * Updates the specified Patient with any new information in the object.
	 * @return True if the patient was sucessfully updated.
	 **/
	public boolean updatePatient(Patient updatedPatient) {
		boolean error = false;
		String sql = "UPDATE patient SET first_name=?, last_name=?, birthdate=?, gender=?, address=?, address_2=?, " + 
					 "city=?, state=?, zipcode=?, country=?, insurance_provider=?, insurance_account_number=? " + 
					 "WHERE patient_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, updatedPatient.getFirstName());
			regStmt.setString(2, updatedPatient.getLastName());
			regStmt.setString(3, updatedPatient.getBirthdate());
			regStmt.setString(4, updatedPatient.getGender());
			regStmt.setString(5, updatedPatient.getAddress1());
			regStmt.setString(6, updatedPatient.getAddress2());
			regStmt.setString(7, updatedPatient.getCity());
			regStmt.setString(8, updatedPatient.getState());
			regStmt.setString(9, updatedPatient.getZipcode());
			regStmt.setString(10, updatedPatient.getCountry());
			regStmt.setString(11, updatedPatient.getInsuranceProvider());
			regStmt.setString(12, updatedPatient.getInsuranceNumber());
			regStmt.setInt(13, updatedPatient.getPatientID());
			regStmt.execute();
			regStmt.close();

			return true;
		} catch(SQLException se) {
			logSQLException("updatePatient()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("updatePatient()", "", se2);
			}
		}
		return !error;
	}

// User methods
	/**
	 * Inserts a new User into the user table. Also handles hashing and salting their password.
	 * @return True if the user is successfully added to the database.
	 **/
	public boolean registerUser(User user, String password) {
		boolean error = false;
		String[] encryptedPass = PasswordEncryption.encryptPassword(password);
		//System.out.println("Register Salt:Hash | " + encryptedPass[0] + ":" + encryptedPass[1]);
		String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, user.getUsername());
			regStmt.setString(2, user.getFirstName());
			regStmt.setString(3, user.getLastName());
			regStmt.setString(4, user.getSpecialty());
			regStmt.setString(5, user.getPagerNumber());
			regStmt.setString(6, user.hasDoctorPrivileges() ? "Doctor" : "Nurse");
			regStmt.setString(7, encryptedPass[0]);
			regStmt.setString(8, encryptedPass[1]);

			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("registerUser()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("registerUser()", "", se2);
			}
		}
		return !error;
	}

	/**
	 * Checks if a user's username and password are a valid pair of login credentials.
	 * Uses the PasswordEncryption class to check the password.
	 * @return True if the user with the given username also has the given password.
	 **/
	public boolean validateLogin(String username, String password) {
		boolean error = false;
		String salt = "";
		String hash = "";

		String sql = "SELECT password_salt AS 'salt', password_hash AS 'hash' FROM user WHERE username=?;";
		PreparedStatement selectStmt = null;
		ResultSet encryptedPass = null;

		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setString(1, username);
			encryptedPass = selectStmt.executeQuery();
			
			if(encryptedPass.next()) {
				salt = encryptedPass.getString("salt");
				hash = encryptedPass.getString("hash");
			}
			
			selectStmt.close();
			encryptedPass.close();
		} catch (SQLException se) {
			logSQLException("validateLogin()", "", se);
			error = true;
		} finally {
			try {
				if(selectStmt != null)
					selectStmt.close();
				if(encryptedPass != null)
					encryptedPass.close();
			} catch(SQLException se2) {
				logSQLException("validateLogin()", "", se2);
			}
		}

		//System.out.println("Validate Salt:Hash | " + salt + ":" + hash);
		return !error && salt.length()==48 && hash.length()==48 && PasswordEncryption.checkPassword(password, salt, hash);
	}

	/**
	 * Gets the user with the specified username from the database.
	 * @return The User object with that username. Null if no such user exists.
	 **/
	public User getUser(String username) {
		String sql = "SELECT * FROM user WHERE username=?;";
		PreparedStatement selectStmt = null;
		ResultSet userResult = null;
		User user = null;
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setString(1, username);
			userResult = selectStmt.executeQuery();

			if(userResult.next()) {
				user = new User(userResult.getString(1), userResult.getString(2), userResult.getString(3), 
								userResult.getString(4), userResult.getString(5), userResult.getString(6).equals("Doctor"));
			}
		} catch(SQLException se) {
			logSQLException("getUser()", "", se);
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(userResult != null) {
					userResult.close();
				}
			} catch(SQLException se2) {
				logSQLException("getUser()", "", se2);
			}
		}
		return user;
	}

	/**
	 * Deletes the user with the specified username from the database.
	 * @return True if the user was successfully deleted from the database.
	 **/
	public boolean deleteUser(String username) {
		boolean error = false;
		String sql = "DELETE FROM user WHERE username=?;";
		PreparedStatement selectStmt = null;
		Patient patient = null;
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setString(1, username);
			selectStmt.execute();
		} catch(SQLException se) {
			logSQLException("deleteUser()", "", se);
			error = true;
		} finally {
			try {
				if(selectStmt != null) {
				selectStmt.close();
			}
			} catch(SQLException se2) {
				logSQLException("deleteUser()", "", se2);
			}
		}
		return !error;
	}

	/**
	 * Updates the specified user with new information.
	 * @return True if the user was successfully updated.
	 **/
	public boolean updateUser(User user) {
		boolean error = false;
		String sql = "UPDATE user SET first_name=?, last_name=?, specialty=?, pager_number=?, position=? " +
					 "WHERE username=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, user.getFirstName());
			regStmt.setString(2, user.getLastName());
			regStmt.setString(3, user.getSpecialty());
			regStmt.setString(4, user.getPagerNumber());
			regStmt.setString(5, user.hasDoctorPrivileges() ? "Doctor" : "Nurse");
			regStmt.setString(6, user.getUsername());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("updateUser()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("updateUser()", "", se2);
			}
		}
		return !error;
	}

	/**
	 * @return True if the given username does not exist in the user table.
	 **/
	public boolean checkUsernameAvailable(String username) {
		String sql = "SELECT COUNT(*) FROM user WHERE username=?;";
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		boolean available = false;
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setString(1, username);
			rs = selectStmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 0) {
				available = true;
			}
		} catch(SQLException se) {
			logSQLException("checkUsernameAvailable()", "", se);
		}finally {
			try {
				if(selectStmt!= null) {
					selectStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("checkUsernameAvailable()", "", se2);
			}
		}
		return available;
	}

// GP Visit methods
	/**
	 * @return The Visit object associated with the given visitID. Null if there is no such visit.
	 **/
	public Visit getVisit(int visitID) {
		String sql = "SELECT * FROM general_practice_visit WHERE visit_id=?;";
		PreparedStatement selectStmt = null;
		ResultSet visitResult = null;
		Visit visit = null;
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, visitID);
			visitResult = selectStmt.executeQuery();

			if(visitResult.next()) {
				ArrayList<String> mdFields = new ArrayList<>();
				mdFields.add(visitResult.getString(5));
				mdFields.add(visitResult.getString(6));
				mdFields.add(visitResult.getString(7));
				mdFields.add(visitResult.getString(8));
				mdFields.add(visitResult.getString(9));
				mdFields.add(visitResult.getString(10));
				mdFields.add(visitResult.getString(11));
				ArrayList<LabOrder> labOrders = getVisitLabOrders(visitID);
				ArrayList<Prescription> prescriptions = getVisitPrescriptions(visitID);
				visit = new Visit(visitResult.getInt(1), visitResult.getInt(2), visitResult.getString(3), visitResult.getString(4), mdFields, 
								labOrders, prescriptions, visitResult.getString(12));
			}
		} catch(SQLException se) {
			logSQLException("getVisit()", "", se);
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(visitResult != null) {
					visitResult.close();
				}
			} catch(SQLException se2) {
				logSQLException("getVisit()", "", se2);
			}
		}
		return visit;
	}

	/**
	 * Delets the visit with the specified visitID from the database.
	 * @return True if the visit was successfully deleted.
	 **/
	public boolean deleteVisit(int visitID) {
		boolean error = false;
		//String sql1 = "DELETE FROM prescription WHERE visit_id=?;";
		//String sql2 = "DELETE FROM lab_order WHERE visit_id=?;";
		String sql = "DELETE FROM general_practice_visit WHERE visit_id=?;";

		//PreparedStatement deleteStmt1 = null;
		//PreparedStatement deleteStmt2 = null;
		PreparedStatement deleteStmt = null;

		Visit visit = null;
		try {
			//deleteStmt1 = conn.prepareStatement(sql1);
			//deleteStmt2 = conn.prepareStatement(sql2);
			deleteStmt = conn.prepareStatement(sql);
			//deleteStmt1.setInt(1, visitID);
			//deleteStmt2.setInt(1, visitID);
			deleteStmt.setInt(1, visitID);

			//deleteStmt1.execute();
			//deleteStmt2.execute();
			deleteStmt.execute();
		} catch(SQLException se) {
			logSQLException("deleteVisit()", "", se);
			error = true;
		} finally {
			try {
				if(deleteStmt != null) {
					deleteStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("deleteVisit()", "", se2);
			}
		}
		return !error;
	}

	/**
	 * Creates a new visit in the general_practice_visit table based on the given Visit object.
	 * Also calls createPrescription() and createLabOrder() for all attached prescriptions and lab orders.
	 * @return True if the visit is successfully created.
	 **/
	public boolean createVisit(Visit newVisit) {
		boolean error = false;
		String sql = "INSERT INTO general_practice_visit VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, newVisit.getPatientID());
			regStmt.setString(2, newVisit.getDoctorID());
			regStmt.setString(3, newVisit.getDate());
			ArrayList<String> mdFields = newVisit.getMdFields();
			regStmt.setString(4, mdFields.get(0));
			regStmt.setString(5, mdFields.get(1));
			regStmt.setString(6, mdFields.get(2));
			regStmt.setString(7, mdFields.get(3));
			regStmt.setString(8, mdFields.get(4));
			regStmt.setString(9, mdFields.get(5));
			regStmt.setString(10, mdFields.get(6));
			regStmt.setString(11, newVisit.getComments());
			regStmt.execute();

			int visitId = getMaxVisitId();

			for(LabOrder labOrder : newVisit.getLabOrders()) {
				labOrder.setVisitID(visitId);
				createLabOrder(labOrder);
			}
			for(Prescription prescription : newVisit.getPrescriptions()) {
				prescription.setVisitID(visitId);
				createPrescription(prescription);
			}
		} catch(SQLException se) {
			logSQLException("createVisit()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("createVisit()", "", se2);
			}
		}

		return !error;
	}

	/**
	 * Updates the given visit with any new information.
	 * Currently, this does not support updating prescriptions and lab orders.
	 * @return True if the visit was successfully updated.
	 **/
	public boolean updateVisit(Visit updatedVisit) {
		boolean error = false;
		int visitId = updatedVisit.getVisitID();
		String sql = "UPDATE general_practice_visit SET patient_id=?, doctor_id=?, date=?, field_a=?, field_b=?, " +
					 "field_c=?, field_d=?, field_e=?, field_f=?, field_g=?, comments=? " +
					 "WHERE visit_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, updatedVisit.getPatientID());
			regStmt.setString(2, updatedVisit.getDoctorID());
			regStmt.setString(3, updatedVisit.getDate());
			ArrayList<String> mdFields = updatedVisit.getMdFields();
			regStmt.setString(4, mdFields.get(0));
			regStmt.setString(5, mdFields.get(1));
			regStmt.setString(6, mdFields.get(2));
			regStmt.setString(7, mdFields.get(3));
			regStmt.setString(8, mdFields.get(4));
			regStmt.setString(9, mdFields.get(5));
			regStmt.setString(10, mdFields.get(6));
			regStmt.setString(11, updatedVisit.getComments());
			regStmt.setInt(12, visitId);
			regStmt.execute();

			ArrayList<LabOrder> oldLabOrders = getVisitLabOrders(visitId);
			ArrayList<LabOrder> existingLabOrders = new ArrayList<>();
			for(LabOrder labOrder : updatedVisit.getLabOrders()) {
				boolean orderExists = false;
				for(LabOrder oldLab : oldLabOrders) {
					if(labOrder.getLabOrderID() == oldLab.getLabOrderID()) {
						orderExists = true;
						existingLabOrders.add(oldLab);
					}
				}
				if(orderExists) {
					updateLabOrder(labOrder);	
				} else {
					labOrder.setVisitID(visitId);
					createLabOrder(labOrder);
				}
			}
			for(LabOrder oldLab : oldLabOrders) {
				if(!existingLabOrders.contains(oldLab)) {
					deleteLabOrder(oldLab);
				}
			}
			ArrayList<Prescription> oldPrescriptions = getVisitPrescriptions(visitId);
			ArrayList<Prescription> existingPrescriptions = new ArrayList<>();
			for(Prescription prescription : updatedVisit.getPrescriptions()) {
				boolean orderExists = false;
				for(Prescription oldPrescription : oldPrescriptions) {
					if (prescription.getPrescriptionID() == oldPrescription.getPrescriptionID()) {
						orderExists = true;
						existingPrescriptions.add(oldPrescription);
					}
				}
				if(orderExists) {
					updatePrescription(prescription);	
				} else {
					prescription.setVisitID(visitId);
					createPrescription(prescription);
				}
			}
			for(Prescription oldPrescription : oldPrescriptions) {
				if(!existingPrescriptions.contains(oldPrescription)) {
					deletePrescription(oldPrescription);
				}
			}
		} catch(SQLException se) {
			logSQLException("updateVisit()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("updateVisit()", "", se2);
			}
		}
		return !error;
	}

	/**
	 * Updates the comments on the visit with the given id.
	 * Overwrites existing comments to match the new comments.
	 * @return True if the visit was successfully updated.
	 **/
	public boolean updateComments(int visitID, String comments) {
		boolean error = false;

		String sql = "UPDATE general_practice_visit SET comments=?" +
					 "WHERE visit_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, comments);
			regStmt.setInt(2, visitID);
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("addComments()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("addComments()", "", se2);
			}
		}
		return !error;
	} 

	/**
	 * Updates the comments on the visit with the given id.
	 * Appends the new comments onto a new line after the old comments.
	 * @return True if the visit was successfully updated.
	 **/
	public boolean addComments(int visitID, String comments) {
		boolean error = false;
		String lastComments = getVisit(visitID).getComments();

		String sql = "UPDATE general_practice_visit SET comments=?" +
					 "WHERE visit_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setString(1, lastComments + "\n" + comments);
			regStmt.setInt(2, visitID);
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("updateComments()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("updateComments()", "", se2);
			}
		}
		return !error;
	} 

// Prescription methods
	/**
	 * Returns a list of all of the prescriptions associated with the given visit id.
	 * @return The ArrayList of Prescriptions.
	 **/
	public ArrayList<Prescription> getVisitPrescriptions(int visitID) {
		String sql = "SELECT * FROM prescription WHERE visit_id=?;";
		PreparedStatement selectStmt = null;
		ResultSet prescriptionResult = null;
		ArrayList<Prescription> matches = new ArrayList<>();
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, visitID);
			prescriptionResult = selectStmt.executeQuery();
			
			while(prescriptionResult.next()) {
				Prescription p = new Prescription(prescriptionResult.getInt(1), prescriptionResult.getInt(2), 
												  prescriptionResult.getString(3), prescriptionResult.getString(4));
				matches.add(p);
			}
		} catch(SQLException se) {
			logSQLException("getVisitPrescriptions()", "", se);
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(prescriptionResult != null) {
					prescriptionResult.close();
				}
			} catch(SQLException se2) {
				logSQLException("getVisitPrescriptions()", "", se2);
			}
		}
		return matches;
	}

	/**
	 * Creates a new Prescription entry.
	 * @return True if the prescription was successfully inserted into the table.
	 **/
	public boolean createPrescription(Prescription newPrescription) {
		boolean error = false;
		String sql = "INSERT INTO prescription VALUES(NULL, ?, ?, ?);";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, newPrescription.getVisitID());
			regStmt.setString(2, newPrescription.getMedType());
			regStmt.setString(3, newPrescription.getMedName());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("createPrescription()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("createPrescription()", "", se2);
			}
		}

		return !error;
	}

	/**
	 * Updates a Prescription entry.
	 * @return True if the prescription was successfully updated.
	 **/
	public boolean updatePrescription(Prescription prescription) {
		boolean error = false;
		String sql = "UPDATE prescription SET visit_id=?, medication_type=?, medication_name=? WHERE prescription_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, prescription.getVisitID());
			regStmt.setString(2, prescription.getMedType());
			regStmt.setString(3, prescription.getMedName());
			regStmt.setInt(4, prescription.getPrescriptionID());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("updatePrescription()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("updatePrescription()", "", se2);
			}
		}

		return !error;
	}

	/**
	 * Delete a Prescription entry.
	 * @return True if the prescription was successfully deleted.
	 **/
	public boolean deletePrescription(Prescription prescription) {
		boolean error = false;
		String sql = "DELETE FROM prescription WHERE prescription_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, prescription.getPrescriptionID());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("deletePrescription()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("deletePrescription()", "", se2);
			}
		}

		return !error;
	}

// LabOrder methods
	/**
	 * Returns a list of all of the lab orders associated with the given visit id.
	 * @return The ArrayList of LabOrders.
	 **/
	public ArrayList<LabOrder> getVisitLabOrders(int visitID) {
		String sql = "SELECT * FROM lab_order WHERE visit_id=?;";
		PreparedStatement selectStmt = null;
		ResultSet labResult = null;
		ArrayList<LabOrder> matches = new ArrayList<>();
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, visitID);
			labResult = selectStmt.executeQuery();
			
			while(labResult.next()) {
				LabOrder p = new LabOrder(labResult.getInt(1), labResult.getInt(2), 
												  labResult.getString(3), labResult.getString(4), labResult.getString(5));
				matches.add(p);
			}
		} catch(SQLException se) {
			logSQLException("getVisitLabOrders()", "", se);
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(labResult != null) {
					labResult.close();
				}
			} catch(SQLException se2) {
				logSQLException("getVisitLabOrders()", "", se2);
			}
		}
		return matches;
	}

	/**
	 * Creates a new lab order entry.
	 * @return True if the lab order was successfully created.
	 **/
	public boolean createLabOrder(LabOrder newLabOrder) {
		boolean error = false;
		String sql = "INSERT INTO lab_order VALUES(NULL, ?, ?, ?, ?);";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, newLabOrder.getVisitID());
			regStmt.setString(2, newLabOrder.getLabName_str());
			regStmt.setString(3, newLabOrder.getTestName_str());
			regStmt.setString(4, newLabOrder.getResults());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("createLabOrder()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("createLabOrder()", "", se2);
			}
		}

		return !error;
	}

	/**
	 * Updates a LabOrder entry.
	 * @return True if the LabOrder was successfully updated.
	 **/
	public boolean updateLabOrder(LabOrder labOrder) {
		boolean error = false;
		String sql = "UPDATE lab_order SET visit_id=?, lab_name=?, test_name=?, results=? WHERE lab_order_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, labOrder.getVisitID());
			regStmt.setString(2, labOrder.getLabName_str());
			regStmt.setString(3, labOrder.getTestName_str());
			regStmt.setString(4, labOrder.getResults());
			regStmt.setInt(5, labOrder.getLabOrderID());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("updateLabOrder()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("updateLabOrder()", "", se2);
			}
		}

		return !error;
	}

	/**
	 * Deletes a LabOrder entry.
	 * @return True if the LabOrder was successfully deleted.
	 **/
	public boolean deleteLabOrder(LabOrder labOrder) {
		boolean error = false;
		String sql = "DELETE FROM lab_order WHERE lab_order_id=?;";
		PreparedStatement regStmt = null;
		try {
			regStmt = conn.prepareStatement(sql);
			regStmt.setInt(1, labOrder.getLabOrderID());
			regStmt.execute();
		} catch(SQLException se) {
			logSQLException("deleteLabOrder()", "", se);
			error = true;
		} finally {
			try {
				if(regStmt != null) {
					regStmt.close();
				}
			} catch(SQLException se2) {
				logSQLException("deleteLabOrder()", "", se2);
			}
		}

		return !error;
	}

// Search methods
	/**
	 * Searches for any patients who match the given search term for the given searchBy field.
	 * @param searchTerm - The term to match to the patient's data
	 * @param searchBy - The name of the field to check against the search term
	 * @return An ArrayList of all matching Patients.
	 **/
	public ArrayList<Patient> findPatient(String searchTerm, String searchBy) {
		String sql = "SELECT patient_id FROM patient WHERE " + searchBy + "=?;";
		PreparedStatement selectStmt = null;
		ResultSet patientResult = null;
		ArrayList<Patient> matches = new ArrayList<>();
		try {
			selectStmt = conn.prepareStatement(sql);
			selectStmt.setString(1, searchTerm);
			patientResult = selectStmt.executeQuery();
			
			while(patientResult.next()) {
				int patientId = patientResult.getInt(1);
				matches.add(getPatient(patientId));
			}
		} catch(SQLException se) {
			logSQLException("findPatient()", "", se);
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(patientResult != null) {
					patientResult.close();
				}
			} catch(SQLException se2) {
				logSQLException("findPatient()", "", se2);
			}
		}
		return matches;
	}

	/**
	 * Searches for any visits who match the given search term for the given searchBy field.
	 * @param searchTerm - The term to match to the visit's data
	 * @param searchBy - The name of the field to check against the search term
	 * @return An ArrayList of all matching Visits.
	 **/
	public ArrayList<Visit> findVisit(String searchTerm, String searchBy) {
		String sql = "SELECT visit_id FROM general_practice_visit WHERE " + searchBy + "=?;";
		PreparedStatement selectStmt = null;
		ResultSet visitResult = null;
		ArrayList<Visit> matches = new ArrayList<>();
		try {
			selectStmt = conn.prepareStatement(sql);
			// Patient_id is an int, but all other possible search types are stored as strings.
			if(searchTerm.equals("patient_id")) {
				selectStmt.setInt(1, Integer.parseInt(searchTerm));
			} else {
				selectStmt.setString(1, searchTerm);
			}
			visitResult = selectStmt.executeQuery();
			
			while(visitResult.next()) {
				int visitId = visitResult.getInt(1);
				matches.add(getVisit(visitId));
			}
		} catch(SQLException se) {
			logSQLException("findVisit()", "", se);
		} catch(NumberFormatException e) {
			System.out.println("Number format exception when searching by patient id.");
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(visitResult != null) {
					visitResult.close();
				}
			} catch(SQLException se2) {
				logSQLException("findPatient()", "", se2);
			}
		}
		return matches;
	}

	/**
	 * @return The largest ID of any patient in the patient table.
	 **/
	public int getMaxPatientId() {
		return getMaxId("patient_id", "patient");
	}

	/**
	 * @return The largest ID of any visit in the general_practice_visit table.
	 **/
	public int getMaxVisitId() {
		return getMaxId("visit_id", "general_practice_visit");
	}

	/**
	 * @return The largest ID of any prescription in the prescription table.
	 **/
	public int getMaxPrescriptionId() {
		return getMaxId("prescription_id", "prescription");
	}

	/**
	 * @return The largest ID of any lab order in the lab_order table.
	 **/
	public int getMaxLabOrderId() {
		return getMaxId("lab_order_id", "lab_order");
	}

	/**
	 * @param idName - The name of the attribute being used as an id.
	 * @param tableName - The table which this attribute is the id of.
	 * @return The largest ID of any entry in the table.
	 **/
	private int getMaxId(String idName, String tableName) {
		String sql = "SELECT MAX(" + idName + ") FROM " + tableName + ";";
		PreparedStatement selectStmt = null;
		ResultSet result = null;
		int id = -1;
		try {
			selectStmt = conn.prepareStatement(sql);
			result = selectStmt.executeQuery();
			
			if(result.next()) {
				id = result.getInt(1);
			}
		} catch(SQLException se) {
			logSQLException("getMaxId()", idName, se);
		} finally {
			try {
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(result != null) {
					result.close();
				}
			} catch(SQLException se2) {
				logSQLException("getMaxId()", idName, se2);
			}
		}
		return id;
	}

	/**
	 * @return An array of strings, each of which represents an atribute of the patient table which it can be searched by.
	 **/
	public String[] getPatientSearchTypes() {
		return new String[]{"first_name", "last_name", "birthdate",  
							"address", "city", "state", "zipcode", "insurance_account_number"};
	} 

	/**
	 * @return An array of strings, each of which represents an atribute of the general_practice_visit table which it can be searched by.
	 **/
	public String[] getVisitSearchTypes() {
		return new String[]{"patient_id", "doctor_id", "date"};
	}

	/**
	 * @return An ArrayList of all visits created within the past 7 days.
	 **/
	public ArrayList<Visit> getRecentVisits() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, -7);
        String lastWeek = sdf.format(cal.getTime());

		ResultSet rs = null;
		ArrayList<Visit> matches = new ArrayList<>();

		try {
			rs = stmt.executeQuery("select * from general_practice_visit where date >= '" + lastWeek + "';");
			
			while(rs.next()) {
				int visitId = rs.getInt(1);
				matches.add(getVisit(visitId));
			}
		} catch(SQLException se) {
			logSQLException("getRecentVisits()", "", se);
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException se2) {
				logSQLException("getRecentVisits()", "", se2);
			}
		}

		return matches;
	}

	/**
	 * Called for logging whenever a SQLException is caught.
	 **/
	private void logSQLException(String method, String message, SQLException se) {
		System.out.println("SQLException in " + method + (message.length()>0 ? (": " + message) : ""));
		System.out.println(se.getMessage());
	}

	/**
	 * Closes the connection.
	 **/
	public void close() {
		try {
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch(SQLException se) {
			logSQLException("close", "", se);
		}
	}
}