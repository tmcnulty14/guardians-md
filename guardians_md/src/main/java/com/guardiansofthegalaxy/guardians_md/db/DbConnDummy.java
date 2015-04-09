package com.guardiansofthegalaxy.guardians_md.db;

import java.util.ArrayList;
import java.util.List;


public class DbConnDummy implements DbConn  {

    @Override
    public boolean registerPatient(Patient patient) {
        return false;
    }

    @Override
    public Patient getPatient(int patientID) {
        return null;
    }

    @Override
    public boolean updatePatient(Patient updatedPatient) {
        return false;
    }

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
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        return false;
    }

    @Override
    public Visit getVisit(int visitID) {
        return null;
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
    public boolean addComments(int visitID, String comments) {
        return false;
    }

    @Override
    public boolean updateComments(int visitID, String comments) {
        return false;
    }

    @Override
    public ArrayList<Prescription> getVisitPrescriptions(int visitID) {
        return null;
    }

    @Override
    public boolean createPrescription(Prescription newPrescription) {
        return false;
    }

    @Override
    public ArrayList<LabOrder> getVisitLabOrders(int visitID) {
        return null;
    }

    @Override
    public boolean createLabOrder(LabOrder newLabOrder) {
        return false;
    }

    @Override
    public List<Patient> findPatient(String searchTerm, String searchBy) {
        return null;
    }

    @Override
    public List<Visit> findVisit(String searchTerm, String searchBy) {
        return null;
    }

    @Override
    public String[] getPatientSearchTypes() {
        return null;
    }

    @Override
    public String[] getVisitSearchTypes() {
        return null;
    }
}
