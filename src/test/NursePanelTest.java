package test;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;

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

		NursePanelTest main = new NursePanelTest("Nurse Medical Main");
		main.setVisible(true);
	}


}