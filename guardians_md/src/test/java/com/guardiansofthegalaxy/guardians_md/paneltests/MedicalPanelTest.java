package com.guardiansofthegalaxy.guardians_md.paneltests;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;



public class  MedicalPanelTest extends JFrame{

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;
	public MedicalMainPanel medicalMainPanel;

	public MedicalPanelTest(String frameTitle)
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

		medicalMainPanel = new MedicalMainPanel(new DatabaseConnection());
		add(medicalMainPanel);

	}

	public static void main(String[] args){

		MedicalPanelTest main = new MedicalPanelTest("Medical Main");
		main.setVisible(true);
	}
}