package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Date.*;
import java.text.*;

public class NurseMedicalMain extends MedicalMainPanel {

	private Patient patient;

	public NurseMedicalMain() {
		disableGeneralPractice();
		disableLabratoryTests();
		disablePrescriptions();

		loadPatientInformation();
	}

	public void disableGeneralPractice(){
		pnGenPract.txtComp.setEditable(false);
		pnGenPract.txtPresIll.setEditable(false);
		pnGenPract.txtPsHist.setEditable(false);
		pnGenPract.txtRevSym.setEditable(false);
		pnGenPract.txtPhysEx.setEditable(false);
		pnGenPract.txtImp.setEditable(false);
		pnGenPract.txtDiag.setEditable(false);

	}

	public void disableLabratoryTests(){
		pnLabTests.rbRed.setEnabled(false);
		pnLabTests.rbWhite.setEnabled(false);
		pnLabTests.rbLiver.setEnabled(false);
		pnLabTests.rbRenal.setEnabled(false);
		pnLabTests.rbEletrol.setEnabled(false);

		pnLabTests.rbXray.setEnabled(false);
		pnLabTests.rbCompTom.setEnabled(false);
		pnLabTests.rbMagRes.setEnabled(false);

		pnLabTests.rbUrin.setEnabled(false);
		pnLabTests.rbStool.setEnabled(false);

	}


	public void disablePrescriptions(){

		pnPresc.rbIntramu.setEnabled(false);
		pnPresc.rbIntravas.setEnabled(false);
		pnPresc.rbSubcuta.setEnabled(false);
		pnPresc.txtaPO.setEditable(false);
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