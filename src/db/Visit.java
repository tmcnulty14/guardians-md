package db;

import java.util.ArrayList;
import java.util.List;

public class Visit {
    private int visitID;
    private int patientID;
    private String doctorID;
    private String date;
    private String comments;
    
    private List<String> mdFields;
    private List<LabOrder> labOrders;
    private List<Prescription> prescriptions;
    
    /**
     * Constructor for creating a new GP visit with no prescriptions or lab orders.
     */
    public Visit(int patientID, int doctorID, String date, List<String> mdFields) {
        this(patientID, doctorID, date, mdFields, new ArrayList<>(), new ArrayList<>());
    }
    
    /**
     * Constructor for creating a new GP visit with prescriptions and/or lab orders.
     */
    public Visit(int patientID, int doctorID, String date, List<String> mdFields,
            List<LabOrder> labOrders, List<Prescription> prescriptions) {
        this(null, patientID, doctorID, date, mdFields, labOrders, prescriptions);
    }
    
    /**
     * Constructor for loading a visit from the database.
     */
    public Visit(int visitID, int patientID, int doctorID, String date, List<String> mdFields,
            List<LabOrder> labOrders, List<Prescription> prescriptions) {
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

    public String setDoctorID(String doctorID) {
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

    public List<String> getMdFields() {
        return mdFields;
    }

    public List<LabOrder> getLabOrders() {
        return labOrders;
    }

    public void addLabOrder(LabOrder labOrder) {
        labOrders.add(labOrder);
    }

    public List<Prescription> getPrescriptions() {
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