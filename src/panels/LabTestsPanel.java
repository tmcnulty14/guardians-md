package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class LabTestsPanel extends JPanel {

	public JPanel pnHemLab, pnRadLab, pnTests;
	//labels and text areas for the general practice section

	//labels for the subsections
	public JRadioButton rbRed, rbWhite, rbLiver, rbRenal, rbEletrol;
	public JRadioButton rbXray, rbCompTom, rbMagRes;
	public JRadioButton rbUrin, rbStool;

	public void build(){
		setLayout(new GridLayout(1,3));
		setBorder(BorderFactory.createTitledBorder("Labratory Tests"));
		createHemLabPanel();
		createRadLabPanel();
		createTestsPanel();

		add(pnHemLab);
		add(pnRadLab);
		add(pnTests);
	}

	public void createHemLabPanel(){
		pnHemLab = new JPanel();
		pnHemLab.setLayout(new GridLayout(5,1));
        pnHemLab.setBorder(BorderFactory.createTitledBorder("Hematologic Laboratory"));
        rbRed = new JRadioButton("Red Blood Cell Test");
		rbRed.setFont(new Font("Times New Roman", 0, 16));

        rbWhite = new JRadioButton("White Blood Test");
		rbWhite.setFont(new Font("Times New Roman", 0, 16));

        rbLiver = new JRadioButton("Liver Function Test");
		rbLiver.setFont(new Font("Times New Roman", 0, 16));

        rbRenal = new JRadioButton("Renal Function Test");
		rbRenal.setFont(new Font("Times New Roman", 0, 16));

        rbEletrol = new JRadioButton("Electrol Test");
		rbEletrol.setFont(new Font("Times New Roman", 0, 16));

        pnHemLab.add(rbRed);
        pnHemLab.add(rbWhite);
        pnHemLab.add(rbLiver);
        pnHemLab.add(rbRenal);
        pnHemLab.add(rbEletrol);

	}

	public void createRadLabPanel(){
		pnRadLab = new JPanel();
		pnRadLab.setLayout(new GridLayout(3,1));
        pnRadLab.setBorder(BorderFactory.createTitledBorder("Radiologic Laboratory"));
        rbXray = new JRadioButton("X-Ray");
		rbXray.setFont(new Font("Times New Roman", 0, 16));

        rbCompTom = new JRadioButton("Computed Tomography (CT)");
		rbCompTom.setFont(new Font("Times New Roman", 0, 16));

        rbMagRes = new JRadioButton("Magnetic Resonance Image (MRI)");
		rbMagRes.setFont(new Font("Times New Roman", 0, 16));

        pnRadLab.add(rbXray);
        pnRadLab.add(rbCompTom);
        pnRadLab.add(rbMagRes);

	}

	public void createTestsPanel(){
		pnTests = new JPanel();
		pnTests.setLayout(new GridLayout(3,1));
        pnTests.setBorder(BorderFactory.createTitledBorder("Additional Tests"));
        rbUrin = new JRadioButton("Urinary Test");
		rbUrin.setFont(new Font("Times New Roman", 0, 16));

        rbStool = new JRadioButton("Stool Test");
		rbStool.setFont(new Font("Times New Roman", 0, 16));

        pnTests.add(rbUrin);
        pnTests.add(rbStool);

	}


	public void editLabTests(){


	}


	public void readOnlyLabTests(){



	}
}