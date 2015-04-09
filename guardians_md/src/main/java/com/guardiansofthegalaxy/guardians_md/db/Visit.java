package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import java.util.ArrayList;

public class Visit {
    private int visitID;
    private int patientID;
    private String doctorID;
    private String date;
    private String comments;

    private ArrayList<String> mdFields;
    private ArrayList<LabOrder> labOrders;
    private ArrayList<Prescription> prescriptions;

    /**
     * Constructor for creating a new GP visit with no prescriptions or lab orders.
     */
    public Visit(int patientID, String doctorID, String date, ArrayList<String> mdFields) {
        this(patientID, doctorID, date, mdFields, new ArrayList<LabOrder>(), new ArrayList<Prescription>());
    }

    /**
     * Constructor for creating a new GP visit with prescriptions and/or lab orders.
     */
    public Visit(int patientID, String doctorID, String date, ArrayList<String> mdFields,
            ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions) {
        this(-1, patientID, doctorID, date, mdFields, labOrders, prescriptions);
    }

    /**
     * Constructor for loading a visit from the database.
     */
    public Visit(int visitID, int patientID, String doctorID, String date, ArrayList<String> mdFields,
            ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions) {
        this.visitID = visitID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.mdFields = mdFields;
        this.labOrders = labOrders;
        this.prescriptions = prescriptions;
    }

    public int getVisitID() {
        return visitID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<String> getMdFields() {
        return mdFields;
    }

    public ArrayList<LabOrder> getLabOrders() {
        return labOrders;
    }

    public void addLabOrder(LabOrder labOrder) {
        labOrders.add(labOrder);
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        String str = "GP Visit #" + visitID + " Patient: " + patientID + " Doctor: " + doctorID + " Date: " + date;
        return str;
    }
}