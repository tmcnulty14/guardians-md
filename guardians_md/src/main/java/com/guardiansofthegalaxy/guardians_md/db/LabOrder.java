package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

public class LabOrder {
	private int labOrderID;

	// Visits will have to already be created before labs can be added to them; can't know visitID beforehand.
	private int visitID;
	private String labName;
	private String testName;
	private String results;

	/**
	 * Constructor for creating a brand new lab order.
	 **/
	public LabOrder(String labName, String testName) {
		this(-1, -1, labName, testName, "");
	}

	/** 
	 * Constructor for creating a new lab order that already has results.
	 **/
	public LabOrder(String labName, String testName, String results) {
		this(-1, -1, labName, testName, results);
	}

	/**
	 * Constructor for retrieving lab orders from the database.
	 **/
	public LabOrder(int labOrderID, int visitID, String labName, String testName, String results) {
		this.labOrderID = labOrderID;
		this.visitID = visitID;
		this.labName = labName;
		this.testName = testName;
		this.results = results;
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

	public String getResults() {
		return results;
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

	public void setResults(String results) {
		this.results = results;
	}

	@Override
	public String toString() {
		String str = String.format("Lab Order #%d GP Visit #%d Lab: %s Test: %s Results: %s",
			labOrderID, visitID, labName, testName, results);

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
        	   l.getTestName().equals(this.testName) &&
        	   (l.getResults()==null ? this.results==null : l.getResults().equals(this.results));
	}
}