/**
 * Class: CSCI 400: Special Topic in Computer Science - GUI Programming
 * Instructor: Professor Jung
 * Date: Spring 2015
 * 
 * Group: The Guardians of the GUI
 * Group Members: Christina Reid
 * 				  Sara Hakkoum
 * 				  Thomas McNulty
 * 				  Trevor Gorman
 * 
 * Assignment: Project 2 - Medical Doctor software
 **/
package com.guardiansofthegalaxy.guardians_md.tuples;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

public class Prescription {
	private int prescriptionID;

	// Visits will have to already be created before prescriptions can be added to them; can't know visitID beforehand.
	private int visitID;
	private String medType;
	private String medName;

	/**
	* Constructor for creating a brand new prescription.
	*/
	public Prescription(String medType, String medName) {
		this(-1, -1, medType, medName);
	}

	/**
	* Constructor for retrieving existing prescriptions for a visit from the database.
	*/
	public Prescription(int prescriptionID, int visitID, String medType, String medName) {
		this.prescriptionID = prescriptionID;
		this.visitID = visitID;
		this.medType = medType;
		this.medName = medName;
	}

	// Getters

	public int getPrescriptionID() {
		return prescriptionID;
	}

	public int getVisitID() {
		return visitID;
	}

	public String getMedType() {
		return medType;
	}

	public String getMedName() {
		return medName;
	}

	// Setters
	public void setPrescriptionID(int prescriptionID) {
		this.prescriptionID = prescriptionID;
	}

	public void setVisitID(int visitID) {
		this.visitID = visitID;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	@Override
	public String toString() {
		String str = String.format("Prescription #%d GP Visit #%d Medication Type: %s Name: %s",
			prescriptionID, visitID, medType, medName);

		return str;
	}

 	@Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof Prescription)) {
            return false;
        }

        Prescription p = (Prescription)o;

        return p.getPrescriptionID() == this.prescriptionID &&
        	   p.getVisitID() == this.visitID &&
        	   p.getMedType().equals(this.medType) &&
        	   p.getMedName().equals(this.medName);
	}
}