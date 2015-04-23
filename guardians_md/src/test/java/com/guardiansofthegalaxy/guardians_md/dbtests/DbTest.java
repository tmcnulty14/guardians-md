package com.guardiansofthegalaxy.guardians_md.dbtests;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;
import org.junit.*;
import java.util.ArrayList;

public class DbTest {
	private final static String DEFAULT_USERNAME = "doctor";
	private final static String DEFAULT_PASSWORD = "password";

	private static DatabaseConnection database = null;

	@BeforeClass
	public static void connectToDatabase() {
		database = new DatabaseConnection();
	}

	@AfterClass
	public static void closeDatabase() {
		database.close();
		database = null;
	}

	@Test
	/**
	 * Tests methods associated with patients.
	 **/
	public void patientTest() {
		// Register our test patient.
		Patient testPatient = registerTestPatient(database);
		Assert.assertNotNull(testPatient);

		// Check that the database can CORRECTLY store and access a patient record.	
		int id = testPatient.getPatientID();
		Assert.assertTrue(id > 0);
		Patient dbPatient = database.getPatient(id);
		testPatient.setPatientID(id);
		Assert.assertEquals(testPatient, dbPatient);

		// Update the patient's last name to smith and check that the database registers the update.
		testPatient.setLastName("Smith");
		Assert.assertTrue(database.updatePatient(testPatient));
		dbPatient = database.getPatient(id);
		Assert.assertEquals(testPatient, dbPatient);

		// Delete the patient record and double check that it was deleted.
		Assert.assertTrue(database.deletePatient(id));
		Assert.assertNull(database.getPatient(id));
	}

	@Test
	/**
	 * Tests methods associated with users.
	 **/
	public void userTest() {// Check that the username is available
		Assert.assertTrue(database.checkUsernameAvailable(DEFAULT_USERNAME));

		// Register the user
		User testUser = registerTestUser(database);
		Assert.assertNotNull(testUser);
		Assert.assertFalse(database.checkUsernameAvailable(DEFAULT_USERNAME));

		// Check that the user can login.
		Assert.assertTrue(database.validateLogin(DEFAULT_USERNAME, DEFAULT_PASSWORD));

		// Check that the database can CORRECTLY store and access a user record.
		User dbUser = database.getUser(DEFAULT_USERNAME);
		Assert.assertEquals(testUser, dbUser);

		// Update the user's specialty to surgery and check that the database registers the update.
		testUser.setSpecialty("Surgery");
		Assert.assertTrue(database.updateUser(testUser));
		dbUser = database.getUser(DEFAULT_USERNAME);
		Assert.assertEquals(testUser, dbUser);

		// Delete the user record and double check that it was deleted.
		Assert.assertTrue(database.deleteUser(DEFAULT_USERNAME));
		Assert.assertNull(database.getUser(DEFAULT_USERNAME));
		Assert.assertFalse(database.validateLogin(DEFAULT_USERNAME, DEFAULT_PASSWORD));
		Assert.assertTrue(database.checkUsernameAvailable(DEFAULT_USERNAME));
	}

	@Test
	/**
	 * Tests methods associated with visits.
	 **/
	public void visitTest() {
		Visit testVisit	= registerTestVisit(database);
		int visitId = testVisit.getVisitID();

		// Check the database's actual view of the visit
		Visit dbVisit = database.getVisit(visitId);
		Assert.assertEquals(testVisit, dbVisit);

		// Test update
		testVisit.setDate("2000-01-01");
		testVisit.getLabOrders().get(0).setResults("Test was negative.");
		Assert.assertTrue(database.updateVisit(testVisit));
		dbVisit = database.getVisit(visitId);
		Assert.assertEquals(testVisit, dbVisit);

		// Test update with new prescription and lab orders
		Prescription newPrescription = new Prescription("AllergyMed", "Benadryl");
		testVisit.addPrescription(newPrescription);
		LabOrder newLabOrder = new LabOrder("X-Ray", "Chest X-ray");
		testVisit.addLabOrder(newLabOrder);
		Assert.assertTrue(database.updateVisit(testVisit));
		newPrescription.setPrescriptionID(database.getMaxPrescriptionId());
		newLabOrder.setLabOrderID(database.getMaxLabOrderId());
		dbVisit = database.getVisit(visitId);
		Assert.assertEquals(testVisit, dbVisit);

		// Test update with deleted prescription and lab orders
		testVisit.getLabOrders().remove(newLabOrder);
		testVisit.getPrescriptions().remove(newPrescription);
		Assert.assertTrue(database.updateVisit(testVisit));
		dbVisit = database.getVisit(visitId);
		Assert.assertEquals(testVisit, dbVisit);

		// Test add comments
		String addedComments = " added";
		testVisit.setComments(testVisit.getComments() + "\n" + addedComments);
		Assert.assertTrue(database.addComments(visitId, addedComments));
		dbVisit = database.getVisit(visitId);
		Assert.assertEquals(testVisit, dbVisit);

		// Test update comments
		String updatedComments = "updated comments";
		testVisit.setComments(updatedComments);
		Assert.assertTrue(database.updateComments(visitId, updatedComments));
		dbVisit = database.getVisit(visitId);
		Assert.assertEquals(testVisit, dbVisit);

		Assert.assertTrue(database.deleteVisit(visitId));
		Assert.assertTrue(database.deletePatient(database.getMaxPatientId()));
		Assert.assertTrue(database.deleteUser(DEFAULT_USERNAME));
	}

	@Test
	/**
	 * Tests methods associated with searching.
	 **/
	public void testSearch() {
		// Register test data
		Visit v = registerTestVisit(database);
		Patient p = database.getPatient(database.getMaxPatientId());

		// Test searching for patients
		Assert.assertTrue(database.findPatient("Patience", "first_name").contains(p));
		Assert.assertTrue(database.findPatient("Patientson", "last_name").contains(p));
		Assert.assertTrue(database.findPatient("1990-03-31", "birthdate").contains(p));
		Assert.assertTrue(database.findPatient("100 State St", "address").contains(p));
		Assert.assertTrue(database.findPatient("Framingham", "city").contains(p));
		Assert.assertTrue(database.findPatient("MA", "state").contains(p));
		Assert.assertTrue(database.findPatient("01701", "zipcode").contains(p));
		Assert.assertTrue(database.findPatient("GP1836009-01", "insurance_account_number").contains(p));

		// Test searching for visits
		Assert.assertTrue(database.findVisit("" + p.getPatientID(), "patient_id").contains(v));
		Assert.assertTrue(database.findVisit(DEFAULT_USERNAME, "doctor_id").contains(v));
		Assert.assertTrue(database.findVisit(v.getDate(), "date").contains(v));

		// Clean up test data
		Assert.assertTrue(database.deleteVisit(v.getVisitID()));
		Assert.assertTrue(database.deletePatient(database.getMaxPatientId()));
		Assert.assertTrue(database.deleteUser(DEFAULT_USERNAME));

	}

	/**
	 * Helper method that populates the database with all data necessary for testing a visit.
	 * @return The Visit object before being stored in the database, but with all relevant IDs set correctly.
	 **/
	private Visit registerTestVisit(DatabaseConnection database) {
		// Set up the prescriptions and lab orders
		ArrayList<Prescription> prescriptions = new ArrayList<>();
		prescriptions.add(new Prescription("Medicine", "GenericMed"));
		ArrayList<LabOrder> labOrders = new ArrayList<>();
		labOrders.add(new LabOrder("GenericLab", "GenericTest"));

		// Get the test user and patient
		int patientId = registerTestPatient(database).getPatientID();
		registerTestUser(database);

		// Set up the md fields
		ArrayList<String> mdFields = new ArrayList<>();
		for(int i=0; i<7; i++) {
			mdFields.add("");
		}
		
		// Create the visit and store it in the database
		Visit testVisit = new Visit(patientId, DEFAULT_USERNAME, "1999-12-31", mdFields, labOrders, prescriptions, "comments");
		Assert.assertTrue(database.createVisit(testVisit));

		// Update the test object with correct id values from database
		int visitId = database.getMaxVisitId();
		int labId = database.getMaxLabOrderId();
		int prescriptionId = database.getMaxPrescriptionId();
		testVisit.setVisitID(visitId);
		testVisit.getPrescriptions().get(0).setPrescriptionID(prescriptionId);
		testVisit.getLabOrders().get(0).setLabOrderID(labId);

		return testVisit;
	}

	/**
	 * Helper method that populates the database with all data necessary for testing a patient.
	 * @return The Patient object before being stored in the database, but with the relevant ID set correctly.
	 **/
	private Patient registerTestPatient(DatabaseConnection database) {
		Patient p = new Patient("Patience", "Patientson", "1990-03-31", "female", 
										  "100 State St", "", "Framingham", "MA", "01701", 
										  "United States", "GenericProvider Healthcare", "GP1836009-01");
		Assert.assertTrue(database.registerPatient(p));
		int id = database.getMaxPatientId();
		p.setPatientID(id);
		return p;
	}

	/**
	 * Helper method that populates the database with all data necessary for testing a user.
	 * @return The User object before being stored in the database.
	 **/
	private User registerTestUser(DatabaseConnection database) {
		User u = new User(DEFAULT_USERNAME, "Doc", "Doctorson", "Doctoring", "555-555-5555", true);
		Assert.assertTrue(database.registerUser(u, DEFAULT_PASSWORD));
		return u;
	}
}
