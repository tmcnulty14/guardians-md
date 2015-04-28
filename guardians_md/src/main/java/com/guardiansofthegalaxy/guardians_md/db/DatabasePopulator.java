package com.guardiansofthegalaxy.guardians_md.db;

/**
 * This class is used by the DatabaseConnection to populate the database with sample data when it is initialized.
 **/
public class DatabasePopulator {
	DatabaseConnection db;

	/**
	 * Constructor which takes in a DatabaseConnection.
	 **/
	public DatabasePopulator(DatabaseConnection dbConn) {
		this.db = dbConn;
	}

	/**
	 * Populates the database with default patients, doctors, and nurses.
	 **/
	public void populateDatabase() {
        populatePatient(new String[] {"Trevor", "Gorman", "1991-12-06", "male", "32 Morse Rd", "", "Framingham", "MA", "01701", "United States", "Harvard Pilgrim Healthcare", "HP275889101"});
        populatePatient(new String[] {"Craig", "Behenna", "1992-05-20", "male", "43 Deacon St", "", "Medway", "MA", "02053", "United States", "Blue Cross Blue Shield", "ZGP776454338"});
        populatePatient(new String[] {"Sofia", "Brooks", "1988-07-25", "female", "6 Saxon St", "", "Lowell", "MA", "01850", "United States", "Blue Cross Blue Shield", "ZGP637559732"});
        populatePatient(new String[] {"Madison", "Smith", "1979-09-06", "female", "13 Maple St", "", "Watertown", "MA", "02471", "United States", "Tufts Health Plan", "S12385743"});
        populatePatient(new String[] {"Alexander", "Harris", "1990-10-22", "male", "5 Long Ave", "", "Framingham", "MA", "01701", "United States", "Fallon Health", "FP11794657"});
        populatePatient(new String[] {"Olivia", "Parker", "1989-11-07", "female", "22 Hemlock Dr", "", "Lowell", "MA", "01850", "United States", "Blue Cross Blue Shield", "ZGP745634889"});
        populatePatient(new String[] {"Emily", "Butler", "1977-08-17", "female", "21 Brook St", "", "Newton", "MA", "02458", "United States", "United Healthcare", "UP975647221"});
        populatePatient(new String[] {"David", "Cooper", "1981-03-08", "male", "74 Taft Rd", "", "Boston", "MA", "02108", "United States", "Harvard Pilgrim Healthcare", "HP472297547"});
        populatePatient(new String[] {"Logan", "Lee", "1986-03-17", "male", "18 Hudson St", "", "Amherst", "MA", "01002", "United States", "Fallon Health", "FP27764855"});
        populatePatient(new String[] {"John", "Butler", "1960-11-01", "male", "44 Cold Rd", "", "Worcester", "MA", "01602", "United States", "Minuteman Health", "MP126446578"});
        populatePatient(new String[] {"William", "King", "1991-10-05", "male", "31 Winter St", "", "Needham", "MA", "02492", "United States", "Minuteman Health", "MP900741332"});
        populatePatient(new String[] {"Scarlett", "Thompson", "1969-12-12", "female", "Bryant Ln", "", "Waltham", "MA", "02454", "United States", "Tufts Health Plan", "S65309122"});
        populatePatient(new String[] {"Ryan", "Jones", "1958-03-16", "male", "2 Cleveland Ave", "", "Fitchburg", "MA", "01420", "United States", "United Healthcare", "UP332746905"});
        populatePatient(new String[] {"Hannah", "Nelson", "1984-01-23", "female", "81 Warwick Rd", "", "Newton", "MA", "02458", "United States", "Harvard Pilgrim Healthcare", "HP622843110"});
        populatePatient(new String[] {"Michael", "Anderson", "1972-08-16", "male", "51 Colt Rd", "", "Needham", "MA", "02492", "United States", "Harvard Pilgrim Healthcare", "MP510628521"});
        populatePatient(new String[] {"Lily", "Bailey", "1982-11-19", "female", "14 River Rd", "", "Fitchburg", "MA", "01420", "United States", "Fallon Health", "FP73210035"});
        populatePatient(new String[] {"Samantha", "Johnson", "1966-10-05", "female", "31 Winter St", "", "Needham", "MA", "02492", "United States", "Minuteman Health", "MP900741332"});
        populatePatient(new String[] {"Noelle", "Griffin", "1992-10-05", "female", "13 Forest Ave", "", "Natick", "MA", "01760", "United States", "Tufts Health Plan", "S13375224"});
        populatePatient(new String[] {"Daniel", "Robinson", "1971-05-17", "male", "10 Avon Dr", "", "Medway", "MA", "02053", "United States", "Minuteman Health", "MP123775344"});
        populatePatient(new String[] {"Alexis", "Young", "1991-05-17", "female", "29 Boon Rd", "", "Medford", "MA", "02153", "United States", "Harcard Pilgrim Healthcare", "HP288517931"});
        populatePatient(new String[] {"Elizabeth", "Scott", "1984-02-28", "female", "6 Long Hill Rd", "", "Waltham", "MA", "02454", "United States", "Harvard Pilgrim Healthcare", "HP771456222"});
        populatePatient(new String[] {"Jayden", "Brown", "1978-10-05", "male", "2 Park Ave", "", "Amherst", "MA", "01002", "United States", "Tufts Health Plan", "S12217443"});
        populatePatient(new String[] {"Jackson", "Cox", "1971-04-11", "male", "44 Potter Rd", "", "Framingham", "MA", "01701", "United States", "Blue Cross Blue Shield", "ZGP374512340"});
        populatePatient(new String[] {"Connor", "Peterson", "1963-12-24", "male", "6 Omaha Ave", "", "Watertown", "MA", "02471", "United States", "Blue Cross Blue Shield", "ZGP999566321"});
        populatePatient(new String[] {"Korey", "Gorman", "1991-07-21", "female", "24 Morse rd", "", "Framingham", "MA", "01701", "United States", "Harvard Pilgrim Healthcare", "HP2758891-02"});
        populatePatient(new String[] {"Krista", "Dyan", "1990-01-07", "female", "52 Grove St", "", "Natick", "MA", "01760", "United States", "Blue Cross Blue Shield", "ZGP922137655"});
        populatePatient(new String[] {"Ethan", "Smith", "1959-12-01", "male", "10 Pine St", "", "Lowell", "MA", "01850", "United States", "United Healthcare", "UP003375682"});
        populatePatient(new String[] {"Anna", "Bennett", "1961-06-13", "female", "28 Olive Ln", "", "Worcester", "MA", "01602", "United States", "Fallon Health", "FP37411170"});
        populatePatient(new String[] {"Jenna", "Robinson", "1959-10-01", "female", "1 King Rd", "", "Natick", "MA", "01760", "United States", "Tufts Health Plan", "S99399772"});
        populatePatient(new String[] {"Paul", "Durham", "1972-03-20", "male", "82 Highland St", "", "Boston", "MA", "02108", "United States", "Tufts Health Plan", "S72337765"});
        populatePatient(new String[] {"Jimmy", "Mason", "1989-04-07", "male", "31 Talon St", "", "Waltham", "MA", "02454", "United States", "Harvard Pilgrim Healthcare", "HP664511273"});
        

		populateDoctor(new String[]{"drdoctorson", "Doc", "Doctorson", "Doctoring", "555-555-5555", "password"});

        populateDoctor(new String[]{"Nstewart", "Nick", "Stewart", "General Practitioner", "508-372-2218", "Rome1958"});
        populateDoctor(new String[]{"Tcampbell", "Troy", "Campbell", "General Practitioner", "508-375-7643", "TheGuild1"});
        populateDoctor(new String[]{"Jcabal", "Johannes", "Cabal", "Surgeon", "508-231-5448", "CaneSword!"});
        populateDoctor(new String[]{"Mrufo", "Michael", "Rufo", "Surgeon", "508-372-2218", "MyPasswordIsLong"});
        populateDoctor(new String[]{"Rcornwell", "Robert", "Cornwell", "Cardiologist", "508-541-9074", "1356TheBook"});
        

		populateNurse(new String[]{"nrsnurse", "Nurse", "Nurse", "Nursing", "555-555-5555", "password"});
        
        populateNurse(new String[]{"Sbrown", "Sofia", "Brown", "Surgical Nursing", "508-221-5094", "Rose87"});
        populateNurse(new String[]{"Clanagan", "Claire", "Lanagan", "Pediatric Nursing", "508-377-5885", "Haze1881"});
        populateNurse(new String[]{"Khoward", "Kevin", "Howard", "Surgical Nursing", "508-932-1337", "North77"});
        populateNurse(new String[]{"Swilson", "Savannah", "Wilson", "Burn Nursing", "508-765-5005", "HollyJolly2"});
        populateNurse(new String[]{"Amiller", "Ashley", "Miller", "Cardiac Nursing", "508-532-1235", "TheCuteOne"});
        populateNurse(new String[]{"Bsanders", "Brianna", "Sanders", "Radiology Nursing", "508-977-9515", "Family43"});
        populateNurse(new String[]{"Bhughes", "Bella", "Hughes", "Genetics Nursing", "508-239-1135", "Support42"});
        populateNurse(new String[]{"Dwarfield", "Dominic", "Warfield", "Radiology Nursing", "508-678-7746", "HotTubParty"});
        populateNurse(new String[]{"Lnelson", "Lauren", "Nelson", "Critical Care Nursing", "508-546-9831", "Nurse1991"});
        populateNurse(new String[]{"Mgreen", "Madison", "Green", "Maternal-Child Nursing", "508-352-5215", "Fairy56"});
	}

	/**
	 * Populates the database with a patient whose parameters match the given parameters.
	 **/
	public boolean populatePatient(String[] params) {
		if(params.length < 12) {
			return false;
		}
		Patient p = new Patient(params[0], params[1], params[2], params[3], 
										  params[4], params[5], params[6], params[7], params[8], 
										  params[9], params[10], params[11]);
		return db.registerPatient(p);
	}

	/**
	 * Populates the database with a doctor whose parameters match the given parameters.
	 **/
	public boolean populateDoctor(String[] params) {
		if(params.length < 5) {
			return false;
		}
		User u = new User(params[0], params[1], params[2], params[3], 
										  params[4], true);
		return db.registerUser(u, params[5]);
	}

	/**
	 * Populates the database with a nurse whose parameters match the given parameters.
	 **/
	public boolean populateNurse(String[] params) {
		if(params.length < 5) {
			return false;
		}
		User u = new User(params[0], params[1], params[2], params[3], 
										  params[4], false);
		return db.registerUser(u, params[5]);
	}
}