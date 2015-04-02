package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;

public class MedicalPanel extends JPanel {

	//panels to hold each section of the general registration information
	public PatientInformationPanel pnReg;
	public GeneralPracticePanel pnGenPract;
	public LabTestsPanel pnLabTests;
	public PrescriptionPanel pnPresc;
	public NursingPanel pnNurs;
	public JPanel pnButton;

	public JButton btnExit, btnSubmit;


	public void build(){
		setLayout(new GridLayout(6,1));
		//TODO: call the build panels methods
		pnReg = new PatientInformationPanel();
		pnReg.build();

		pnGenPract = new GeneralPracticePanel();
		pnGenPract.build();

		pnLabTests = new LabTestsPanel();
		pnLabTests.build();

		pnPresc = new PrescriptionPanel();
		pnPresc.build();

		pnNurs = new NursingPanel();
		pnNurs.build();

		pnButton = new JPanel();
		pnButton.setLayout(new GridLayout(1,8));

		btnExit = new JButton("Exit");
		btnExit.setBounds(20,30,50,30);
		btnExit.addActionListener(new ButtonListener());

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(20,30,50,30);
		btnSubmit.addActionListener(new ButtonListener());

		pnButton.add(new JLabel("   "));
		pnButton.add(new JLabel("    "));
		pnButton.add(new JLabel("   "));
		pnButton.add(new JLabel("    "));
		pnButton.add(new JLabel("   "));
		pnButton.add(new JLabel("    "));
		pnButton.add(btnExit);
		pnButton.add(btnSubmit);

		add(pnReg);
		add(pnGenPract);
		add(pnLabTests);
		add(pnPresc);
		add(pnNurs);
		add(pnButton);

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e){

			Object source = e.getSource();
			if(source == btnExit){
				System.exit(0);
				//todo: return to main page
			}
			if(source == btnSubmit){
				//todo: create new objects and submit
			}


		}




	}

}