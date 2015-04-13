package com.guardiansofthegalaxy.guardians_md.paneltests;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;


public class NursePanelTest extends JFrame {

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	public NurseMedicalMain nurseMedicalMain;

	public NursePanelTest(String frameTitle)
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

		nurseMedicalMain = new NurseMedicalMain();
		add(nurseMedicalMain);

	}

	public static void main(String[] args){

		MedicalConfigurator.setActivePatient("Julie", "Roberts", "July 18, 2010", "F",
                "13 Hill Lane", "", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

		NursePanelTest main = new NursePanelTest("Nurse Medical Main");
		main.setVisible(true);
	}


}