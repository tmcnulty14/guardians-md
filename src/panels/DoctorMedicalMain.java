package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;
import db.*;
import java.util.Date.*;
import java.text.*;

public class DoctorMedicalMain extends MedicalMainPanel {

	private Patient patient;
	private Visit visit;


	public DoctorMedicalMain() {
		disableNursingCheckBox();
		loadPatientInformation();
	}

	public void disableNursingCheckBox(){
		pnNursComm.ckEditComm.setEnabled(false);
	}

	public void loadPatientInformation(){
		patient = MedicalConfigurator.getPatient();
		pnPat.txtFName.setText(patient.getFirstName());
		pnPat.txtLName.setText(patient.getLastName());
		pnPat.txtAddress1.setText(patient.getAddress());
		pnPat.txtAddress2.setText(patient.getAddress());
		pnPat.txtCity.setText(patient.getCity());
		pnPat.txtState.setText(patient.getState());
		pnPat.txtZip.setText(patient.getZipcode());
		pnPat.txtCountry.setText(patient.getCountry());
		pnPat.txtBirthDate.setText(patient.getBirthdate());
		pnPat.txtInsProv.setText(patient.getInsuranceProvider());
		pnPat.txtInsNum.setText(patient.getInsuranceNumber());

		setGender();
		try{
			setAge();
		}catch(ParseException pe){
		pe.printStackTrace();
		}

	}

	public void setGender(){
		if(patient.getGender().equalsIgnoreCase("F")){
			pnPat.ckFemale.setSelected(true);
		}
		else{
			pnPat.ckMale.setSelected(true);
		}
	}

	public void setAge() throws ParseException{

		DateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = formatter.parse(patient.getBirthdate());

		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(date);

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int ageInt = currentYear - Integer.parseInt(year);
		String age = String.valueOf(ageInt);
		pnPat.txtAge.setText(age);
	}


}