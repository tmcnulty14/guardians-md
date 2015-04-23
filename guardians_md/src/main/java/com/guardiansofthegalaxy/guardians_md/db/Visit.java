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
        this.visitID = -1;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.mdFields = mdFields;

        // Initialize lab orders, prescriptions, and comments to be empty
        labOrders = new ArrayList<>();
        prescriptions = new ArrayList<>();
        comments = "";
    }

    /**
     * Constructor for creating a new GP visit with prescriptions and/or lab orders.
     */
    public Visit(int patientID, String doctorID, String date, ArrayList<String> mdFields,
                 ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions, String comments) {
        this.visitID = -1;
        this.patientID = patientID;
        this.doctorID= doctorID;
        this.date = date;
        this.mdFields = mdFields;
        this.labOrders = labOrders;
        this.prescriptions = prescriptions;
        this.comments = comments;

    }

    /**
     * Constructor for loading a visit from the database.
     */
    public Visit(int visitID, int patientID, String doctorID, String date, ArrayList<String> mdFields,
                 ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions, String comments) {
        this.visitID = visitID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.mdFields = mdFields;
        this.labOrders = labOrders;
        this.prescriptions = prescriptions;
        this.comments = comments;
    }

    public int getVisitID() {
        return visitID;
    }

    public void setVisitID(int visitID) {
        this.visitID = visitID;
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

    public void setMdFields(ArrayList<String> mdFields) {
        this.mdFields = mdFields;
    }

    public ArrayList<LabOrder> getLabOrders() {
        return labOrders;
    }

    public void addLabOrder(LabOrder labOrder) {
        labOrders.add(labOrder);
    }

    public void setLabOrders(ArrayList<LabOrder> labOrders) {
        this.labOrders = labOrders;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        String str = "GP Visit #" + visitID + " Patient: " + patientID + " Doctor: " + doctorID + " Date: " + date;
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof Visit)) {
            return false;
        }

        Visit v = (Visit)o;

        /* Debugging purposes
        System.out.println(v.getMdFields());
        System.out.println(this.getMdFields());
        System.out.println(v.getLabOrders());
        System.out.println(this.getLabOrders());
        System.out.println(v.getPrescriptions());
        System.out.println(this.getPrescriptions());
        System.out.println(v.getComments());
        System.out.println(this.getComments());
        */

        return v.getVisitID() == this.visitID &&
               v.getPatientID() == this.patientID &&
               v.getDoctorID().equals(this.doctorID) &&
               v.getDate().equals(this.date) &&
               v.getComments().equals(this.comments) &&
               v.getMdFields().containsAll(this.mdFields) &&
               v.getLabOrders().containsAll(this.labOrders) &&
               v.getPrescriptions().containsAll(this.prescriptions);
    }
}