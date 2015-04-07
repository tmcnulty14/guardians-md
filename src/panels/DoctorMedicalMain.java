package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;

public class DoctorMedicalMain extends MedicalMainPanel {

	public DoctorMedicalMain() {
		disableNursingCheckBox();
	}

	public void disableNursingCheckBox(){
		pnNursComm.ckEditComm.setEnabled(false);
	}


	public static void main(String[] args){

		DoctorMedicalMain main = new DoctorMedicalMain();
		main.setVisible(true);
	}
}