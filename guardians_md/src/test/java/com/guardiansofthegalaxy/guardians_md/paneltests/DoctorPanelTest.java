package com.guardiansofthegalaxy.guardians_md.paneltests;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;
import javax.swing.*;


public class DoctorPanelTest extends JFrame {

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	public DoctorMedicalMain doctorMedicalMain;

	public DoctorPanelTest(String frameTitle)
	{
		super(frameTitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanels();

		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		//pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buildPanels(){

		doctorMedicalMain = new DoctorMedicalMain(new DatabaseConnection());
		add(doctorMedicalMain);

	}

	public static void main(String[] args){

		MedicalConfigurator.setActivePatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

		DoctorPanelTest main = new DoctorPanelTest("Doctor Medical Main");
		main.setVisible(true);

	}


}