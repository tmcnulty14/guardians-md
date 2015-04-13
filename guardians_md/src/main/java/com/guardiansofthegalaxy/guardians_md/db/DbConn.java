package com.guardiansofthegalaxy.guardians_md.db;

import java.util.ArrayList;

public interface DbConn {
    // Patient methods
    public boolean registerPatient(Patient patient);

    public Patient getPatient(int patientID);

    public boolean updatePatient(Patient updatedPatient);

    // User methods
    public boolean registerUser(User user, String password);

    public boolean validateLogin(String username, String password);

    public User getUser(String username);

    public boolean updateUser(User user);

    public boolean checkUsernameAvailable(String username);

    // GP Visit methods
    public Visit getVisit(int visitID);

    public boolean createVisit(Visit newVisit); // Also handles createPrescription and createLabOrder for all attached Prescriptions / LabOrders

    public boolean updateVisit(Visit updatedVisit); // Also handles createPrescription and createLabOrder for all attached Prescriptions / LabOrders

    public boolean addComments(int visitID, String comments); // Overwrites existing comments

    public boolean updateComments(int visitID, String comments); // Appends to existing comments

    // Prescription methods
    public ArrayList<Prescription> getVisitPrescriptions(int visitID);

    public boolean createPrescription(Prescription newPrescription);

    // LabOrder methods
    public ArrayList<LabOrder> getVisitLabOrders(int visitID);

    public boolean createLabOrder(LabOrder newLabOrder);

    // Search methods
    public ArrayList<Patient> findPatient(String searchTerm, String searchBy);

    public ArrayList<Visit> findVisit(String searchTerm, String searchBy);

    public String[] getPatientSearchTypes(); // Returns a list of strings, each of which represents an attribute you can search for patients by.

    public String[] getVisitSearchTypes(); // Returns a list of strings, each of which represents an attribute you can search for visits by.


}
