package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

public class LabOrder {
	private int labOrderID;

	// Visits will have to already be created before labs can be added to them; can't know visitID beforehand.
	private int visitID;
	private LabName labName;
	private TestName testName;
	private String results;

	/**
	 * Constructor for creating a brand new lab order.
	 *
     * @param labName
     * @param testName*/
	public LabOrder(LabName labName, TestName testName) {
		this(-1, -1, labName, testName, "");
	}

	/** 
	 * Constructor for creating a new lab order that already has results.
	 **/
	public LabOrder(LabName labName, TestName testName, String results) {
		this(-1, -1, labName, testName, results);
	}

	/**
	 * Constructor for retrieving lab orders from the database.
	 **/
	public LabOrder(int labOrderID, int visitID, LabName labName, TestName testName, String results) {
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

    public LabName getLabName_enum() {
        return labName;
    }

    public TestName getTestName_enum() {
        return testName;
    }

	public String getLabName_str() {
		return labName.toString();
	}

	public String getTestName_str() {
		return testName.toString();
	}

	public String getResults() {
		return results;
	}

    public String[] getResultList(){
        return results.split(",");
    }

	// Setters
	public void setLabOrderID(int labOrderID) {
		this.labOrderID = labOrderID;
	}

	public void setVisitID(int visitID) {
		this.visitID = visitID;
	}

	public void setLabName_str(String labName) {
		this.labName = LabName.valueOf(labName);
	}

	public void setTestName_str(String testName) {
		this.testName = TestName.valueOf(testName);
	}

    public void setLabName_enum(LabName labName) {
        this.labName = labName;
    }

    public void setTestName_enum(TestName testName) {
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
        	   l.getLabName_str().equals(this.labName) &&
        	   l.getTestName_str().equals(this.testName) &&
        	   (l.getResults()==null ? this.results==null : l.getResults().equals(this.results));
	}
}