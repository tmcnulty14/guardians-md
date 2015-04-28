package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.tuples.*;

import java.util.ArrayList;

/**
 * An interface describing everything that a database connection class should be able to do.
 * This interface was created during design, so that we could start working on the panels
 * before the database connection was implemented.
 **/
public interface DbConn {
    // Patient methods
    public boolean registerPatient(Patient patient);

    public Patient getPatient(int patientID);

    public boolean deletePatient(int patientID);

    public boolean updatePatient(Patient updatedPatient);

    // User methods
    public boolean registerUser(User user, String password);

    public boolean validateLogin(String username, String password); // Checks if a given login is valid

    public User getUser(String username);

    public boolean deleteUser(String username);

    public boolean updateUser(User user);

    public boolean checkUsernameAvailable(String username); // Checks if a given username is available

    // GP Visit methods
    public Visit getVisit(int visitID);

    public boolean deleteVisit(int visitID); // Deletes a visit and all its associated prescriptions and laborders.

    public boolean createVisit(Visit newVisit); // Also handles createPrescription and createLabOrder for all attached Prescriptions / LabOrders

    public boolean updateVisit(Visit updatedVisit); // Updates a visit and all attached prescriptions / lab orders.

    public boolean updateComments(int visitID, String comments); // Appends to existing comments

    public boolean addComments(int visitID, String comments); // Overwrites existing comments

    public ArrayList<Visit> getRecentVisits(); // Returns a list of all visits dated in the last week.

    // Prescription methods
    public ArrayList<Prescription> getVisitPrescriptions(int visitID); // Gets all prescriptions associated with a given visit

    public boolean createPrescription(Prescription newPrescription);

    public boolean updatePrescription(Prescription prescription);

    public boolean deletePrescription(Prescription prescription);

    // LabOrder methods
    public ArrayList<LabOrder> getVisitLabOrders(int visitID); // Gets all lab orders associated with a given visit

    public boolean createLabOrder(LabOrder newLabOrder);

    public boolean updateLabOrder(LabOrder labOrder);

    public boolean deleteLabOrder(LabOrder labOrder);

    // Search methods
    public ArrayList<Patient> findPatient(String searchTerm, String searchBy); // Finds all patients whose <searchBy> attribute equals 'searchTerm'

    public ArrayList<Visit> findVisit(String searchTerm, String searchBy); // Finds all visits whose <searchBy> attribute equals 'searchTerm'

    public String[] getPatientSearchTypes(); // Returns a list of strings, each of which represents an attribute you can search for patients by.

    public String[] getVisitSearchTypes(); // Returns a list of strings, each of which represents an attribute you can search for visits by.

    // MaxId methods - Return the largest value of the id column in the relevant table.
    public int getMaxPatientId();

    public int getMaxVisitId();

    public int getMaxPrescriptionId();

    public int getMaxLabOrderId();

    // General methods
    public void close(); // Closes the connection
}
