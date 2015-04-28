package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.tuples.*;

import java.util.ArrayList;

/**
 * A dummy implementation of the DbConn interface. This class provides stub implementations of all of the
 * DbConn methods, which allows for panel testing without a working database connection.
 **/
public class DbConnDummy implements DbConn  {
// Patient methods
    @Override
    public boolean registerPatient(Patient patient) {
        return false;
    }

    @Override
    public Patient getPatient(int patientID) {
        return null;
    }

    @Override
    public boolean deletePatient(int patientID) {
        return false;
    }

    @Override
    public boolean updatePatient(Patient updatedPatient) {
        return false;
    }

// User methods
    @Override
    public boolean registerUser(User user, String password) {
        return false;
    }

    @Override
    public boolean validateLogin(String username, String password) {
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        return false;
    }

// Visit methods
    @Override
    public Visit getVisit(int visitID) {
        return null;
    }

    @Override
    public boolean deleteVisit(int visitID) {
        return false;
    }

    @Override
    public boolean createVisit(Visit newVisit) {
        return false;
    }

    @Override
    public boolean updateVisit(Visit updatedVisit) {
        return false;
    }

    @Override
    public boolean updateComments(int visitID, String comments) {
        return false;
    }

    @Override
    public boolean addComments(int visitID, String comments) {
        return false;
    }

    @Override
    public ArrayList<Visit> getRecentVisits() {
        return new ArrayList<Visit>();
    }

// Prescription methods
    @Override
    public ArrayList<Prescription> getVisitPrescriptions(int visitID) {
        return null;
    }

    @Override
    public boolean createPrescription(Prescription newPrescription) {
        return false;
    }

    @Override
    public boolean updatePrescription(Prescription prescription) {
        return false;
    }

    @Override
    public boolean deletePrescription(Prescription prescription) {
        return false;
    }

// LabOrder methods
    @Override
    public ArrayList<LabOrder> getVisitLabOrders(int visitID) {
        return null;
    }

    @Override
    public boolean createLabOrder(LabOrder newLabOrder) {
        return false;
    }

    @Override
    public boolean updateLabOrder(LabOrder labOrder) {
        return false;
    }

    @Override
    public boolean deleteLabOrder(LabOrder labOrder) {
        return false;
    }

// Search methods
    @Override
    public ArrayList<Patient> findPatient(String searchTerm, String searchBy) {
        return new ArrayList<Patient>();
    }

    @Override
    public ArrayList<Visit> findVisit(String searchTerm, String searchBy) {
        return new ArrayList<Visit>();
    }

    @Override
    public String[] getPatientSearchTypes() {
        return new String[] {"First Name", "Gender", "City"};
    }

    @Override
    public String[] getVisitSearchTypes() {
        return new String[] {"Visit ID", "Patient ID", "Date"};
    }

// MaxId methods
    public int getMaxPatientId() {
        return -1;
    }

    public int getMaxVisitId() {
        return -1;
    }

    public int getMaxPrescriptionId() {
        return -1;
    }

    public int getMaxLabOrderId() {
        return -1;
    }

// General methods
    public void close() {}
}
