package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

public class LabOrder {
	private int labOrderID;

	// Visits will have to already be created before labs can be added to them; can't know visitID beforehand.
	private int visitID;
	private String labName;
	private String testName;

	/**
	* Constructor for creating a brand new lab order.
	*/
	public LabOrder(String labName, String testName) {
		this(-1, -1, labName, testName);
	}

	/**
	* Constructor for retrieving lab orders from the database.
	*/
	public LabOrder(int labOrderID, int visitID, String labName, String testName) {
		this.labOrderID = labOrderID;
		this.visitID = visitID;
		this.labName = labName;
		this.testName = testName;
	}

	// Getters

	public int getLabOrderID() {
		return labOrderID;
	}

	public int getVisitID() {
		return visitID;
	}

	public String getLabName() {
		return labName;
	}

	public String getTestName() {
		return testName;
	}

	// Setters
	public void setLabOrderID(int labOrderID) {
		this.labOrderID = labOrderID;
	}

	public void setVisitID(int visitID) {
		this.visitID = visitID;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Override
	public String toString() {
		String str = String.format("Lab Order #%d GP Visit #%d Lab: %s Test: %s",
			labOrderID, visitID, labName, testName);

		return str;
	}

	@Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof LabOrder)) {
            return false;
        }

        LabOrder l = (LabOrder)o;

        return l.getLabOrderID() == this.labOrderID &&
        	   l.getVisitID() == this.visitID &&
        	   l.getLabName().equals(this.labName) &&
        	   l.getTestName().equals(this.testName);
	}
}