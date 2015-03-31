package db;

import java.util.List;

public interface DbConn {
// Patient methods
	public void registerPatient(Patient patient);

	public Patient getPatient(int patientID);

	public void updatePatient(Patient updatedPatient);

// User methods
	public void registerUser(User user, String password);

	public boolean validateLogin(User user, String password);

	public User getUser(String username);

	public void updateUser(User user);

// GP Visit methods
	public Visit getVisit(int visitID);

	public void createVisit(Visit newVisit); // Also handles createPrescription and createLabOrder for all attached Prescriptions / LabOrders

	public void updateVisit(Visit updatedVisit); // Also handles createPrescription and createLabOrder for all attached Prescriptions / LabOrders

	public void addComments(int visitID, String comments); // Overwrites existing comments

	public void updateComments(int visitID, String comments); // Appends to existing comments

// Prescription methods
	public List<Prescription> getVisitPrescriptions(int visitID);

	public void createPrescription(Prescription newPrescription);

// LabOrder methods
	public List<LabOrder> getVisitLabOrders(int visitID);

	public void createLabOrder(LabOrder newLabOrder);

// Search methods
	public List<Patient> findPatient(String searchTerm, String searchBy);

	public List<Visit> findVisit(String searchTerm, String searchBy);

	public List<String> getPatientSearchTypes(); // Returns a list of strings, each of which represents an attribute you can search for patients by.

	public List<String> getVisitSearchTypes(); // Returns a list of strings, each of which represents an attribute you can search for visits by.


}