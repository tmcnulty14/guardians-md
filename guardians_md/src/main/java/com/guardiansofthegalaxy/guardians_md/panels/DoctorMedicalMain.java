package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Date.*;
import java.text.*;

public class DoctorMedicalMain extends MedicalMainPanel {


	public DoctorMedicalMain() {
		disableNursingCheckBox();
	}

	public void disableNursingCheckBox(){
		pnNursComm.ckEditComm.setEnabled(false);
	}


}