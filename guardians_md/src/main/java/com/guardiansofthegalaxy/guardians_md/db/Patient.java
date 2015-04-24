package com.guardiansofthegalaxy.guardians_md.db;

public class Patient {
	private int patientID;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String gender;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String insuranceProvider;
	private String insuranceNumber;

	/**
	* All fields are assumed to be NOT NULL in DB. patientID is skipped for creating a new patient, because of AUTO_INCREMENT.
	*/
	public Patient(String firstName, String lastName, String birthdate, String gender,
			String address1, String address2, String city, String state, String zipcode, String country,
			String insuranceProvider, String insuranceNumber) {
        this.patientID = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.insuranceProvider = insuranceProvider;
        this.insuranceNumber = insuranceNumber;
    }

	/**
	* For creating Patient data objects for existing patients for search results, etc...
	*/
	public Patient(int patientID, String firstName, String lastName, String birthdate,
			String gender, String address1, String address2, String city, String state, String zipcode,
			String country, String insuranceProvider, String insuranceNumber) {
		this.patientID = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.insuranceProvider = insuranceProvider;
		this.insuranceNumber = insuranceNumber;
	}

	// Getters

	public int getPatientID() {
		return patientID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCountry() {
		return country;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	// Setters can be used to change this patient's info and return the updated object to the DbConn.
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String setAddress2() {
		return address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	/**
	* Can modify this to include more fields; kept short to be displayed in search results
	*/
	@Override
	public String toString() {
	//	String str = String.format("Patient ID#%s Name: %d %s Birthdate: %s Gender: %s",
	//		patientID, firstName, lastName, birthdate.toString(), gender);

        String str = "Patient ID:" + patientID + " Name: " + firstName + " " + lastName +
                " Birthdate: " + birthdate + " Gender: " + gender;
		return str;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}

		if(!(o instanceof Patient)) {
			return false;
		}

		Patient p = (Patient)o;

		//System.out.println("Comparing patient " + p.getPatientID() + " to " + this.patientID);

		return p.getPatientID() == this.patientID &&
			   p.getFirstName().equals(this.firstName) &&
			   p.getLastName().equals(this.lastName) &&
			   p.getBirthdate().equals(this.birthdate) &&
			   p.getGender().equals(this.gender) &&
			   p.getAddress1().equals(this.address1) &&
			   p.getAddress2().equals(this.address2) &&
			   p.getCity().equals(this.city) &&
			   p.getState().equals(this.state) &&
			   p.getZipcode().equals(this.zipcode) &&
			   p.getCountry().equals(this.country) &&
			   p.getInsuranceProvider().equals(this.insuranceProvider) &&
			   p.getInsuranceNumber().equals(this.insuranceNumber);
	}
}