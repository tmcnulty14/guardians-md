package db;

public class Patient {
	
	// Can't be null; can we decide on -1 to mean a new patient & pass null to DB?
	private int patientID;

	// First and last name separated in DB?
	private String firstName;
	private String lastName;
	private String birthdate;
	
	// Should this be a boolean? Any constraints in DB for gender of Patients? Error-handling? Constrained by having a combobox when creating a patient?
	private String gender;
	private String address;
	private String city;
	private String state;
	
	// Storing City and Zipcode only in Patient table will introduce inconsistency… Should possibly have a zip-city-state-country table...
	// That can be checked for consistency; One city can have multiple zipcodes...
	private String zipcode;
	private String country;
	private String insuranceProvider;
	private String insuranceNumber;

	/**
	* All fields are assumed to be NOT NULL in DB. patientID is skipped for creating a new patient, because of AUTO_INCREMENT.
	*/
	public Patient(String firstName, String lastName, String birthdate, String gender,
			String address, String city, String state, String zipcode, String country,
			String insuranceProvider, String insuranceNumber) {
		this(-1, firstName, lastName, birthdate, gender, address, city, state, zipcode,
			country, insuranceProvider, insuranceNumber);
	}

	/**
	* For creating Patient data objects for existing patients for search results, etc...
	*/
	public Patient(int patientID, String firstName, String lastName, String birthdate, 
			String gender, String address, String city, String state, String zipcode,
			String country, String insuranceProvider, String insuranceNumber) {
		this.patientID = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.address = address;
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

	public String getAddress() {
		return address;
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

	public void setAddress(String address) {
		this.address = address;
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
		String str = String.format("Patient ID#%s Name: %d %s Birthdate: %s Gender: %s",
			patientID, firstName, lastName, birthdate, gender);

		return str;
	}
}