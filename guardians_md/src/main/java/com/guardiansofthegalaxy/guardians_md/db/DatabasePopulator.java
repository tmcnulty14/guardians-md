package com.guardiansofthegalaxy.guardians_md.db;

public class DatabasePopulator {
	DatabaseConnection db;

	public static void main(String args[]) {
		DatabasePopulator dbPop = new DatabasePopulator();

		//dbPop.populatePatient(new String[]{"Patience", "Patientson", "1990-03-31", "female", 
		//								  "100 State St", "", "Framingham", "MA", "01701", 
		//								  "United States", "GenericProvider Healthcare", "GP1836009-01"});

		//dbPop.populateDoctor(new String[]{"drdoctorson", "Doc", "Doctorson", "Doctoring", "555-555-5555", "password"});
		//dbPop.populateNurse(new String[]{"nrsnurse", "Nurse", "Nurse", "Nursing", "555-555-5555", "password"});
	}

	public DatabasePopulator() {
		this.db = new DatabaseConnection();
	}

	public boolean populatePatient(String[] params) {
		if(params.length < 12) {
			return false;
		}
		Patient p = new Patient(params[0], params[1], params[2], params[3], 
										  params[4], params[5], params[6], params[7], params[8], 
										  params[9], params[10], params[11]);
		return db.registerPatient(p);
	}

	public boolean populateDoctor(String[] params) {
		if(params.length < 5) {
			return false;
		}
		User u = new User(params[0], params[1], params[2], params[3], 
										  params[4], true);
		return db.registerUser(p, params[5]);
	}

	public boolean populateNurse(String[] params) {
		if(params.length < 5) {
			return false;
		}
		User u = new User(params[0], params[1], params[2], params[3], 
										  params[4], false);
		return db.registerUser(p, params[5]);
	}
}