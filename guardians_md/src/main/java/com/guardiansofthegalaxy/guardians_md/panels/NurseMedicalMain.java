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

}