package test;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;

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

		doctorMedicalMain = new DoctorMedicalMain();
		add(doctorMedicalMain);

	}

	public static void main(String[] args){

		DoctorPanelTest main = new DoctorPanelTest("Doctor Medical Main");
		main.setVisible(true);
	}


}