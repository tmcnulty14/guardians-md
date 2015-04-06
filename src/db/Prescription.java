package db;

public class Prescription {
	private int prescriptionID;

	// Visits will have to already be created before prescriptions can be added to them; can't know visitID beforehand.
	private int visitID;
	private String medType;
	private String medName;

	/**
	* Constructor for creating a brand new prescription.
	*/
	public Prescription(int visitID, String medType, String medName) {
		this(-1, visitID, medType, medName);
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
}