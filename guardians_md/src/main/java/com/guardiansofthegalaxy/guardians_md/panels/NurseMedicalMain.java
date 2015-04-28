package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

public class NurseMedicalMain extends MedicalMainPanel {


	public NurseMedicalMain(DbConn database) {
		super(database);
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
		pnLabTests.ckRed.setEnabled(false);
		pnLabTests.ckWhite.setEnabled(false);
		pnLabTests.ckLiver.setEnabled(false);
		pnLabTests.ckRenal.setEnabled(false);
		pnLabTests.ckEletrol.setEnabled(false);

		pnLabTests.ckXray.setEnabled(false);
		pnLabTests.ckCompTom.setEnabled(false);
		pnLabTests.ckMagRes.setEnabled(false);

		pnLabTests.ckUrin.setEnabled(false);
		pnLabTests.ckStool.setEnabled(false);

	}


	public void disablePrescriptions(){

		pnPresc.ckIntramu.setEnabled(false);
		pnPresc.ckIntravas.setEnabled(false);
		pnPresc.ckSubcuta.setEnabled(false);
		pnPresc.ckOral.setEnabled(false);
	}

}