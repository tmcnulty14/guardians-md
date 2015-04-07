package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;

public class NurseMedicalMain extends MedicalMainPanel {

	public NurseMedicalMain() {
		disableGeneralPractice();
		disableLabratoryTests();
		disablePrescriptions();
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

	public static void main(String[] args){

		NurseMedicalMain main = new NurseMedicalMain();
		main.setVisible(true);


	}
}