package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

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

	public LabTestsPanel(){
		setLayout(new GridLayout(1,3));
		setBorder(BorderFactory.createTitledBorder("Labratory Tests"));
		createHemLabPanel();
		createRadLabPanel();
		createTestsPanel();

        setBackground(Color.WHITE);

        add(pnHemLab);
		add(pnRadLab);
		add(pnTests);
	}

	public void createHemLabPanel(){
		pnHemLab = new JPanel();
        pnHemLab.setBackground(Color.WHITE);
        pnHemLab.setLayout(new GridLayout(5,1));
        pnHemLab.setBorder(BorderFactory.createTitledBorder("Hematologic Laboratory"));

        rbRed = new JRadioButton("Red Blood Cell Test");
		rbRed.setFont(new Font("Times New Roman", 0, 16));
        rbRed.setBackground(Color.WHITE);

        rbWhite = new JRadioButton("White Blood Test");
		rbWhite.setFont(new Font("Times New Roman", 0, 16));
        rbWhite.setBackground(Color.WHITE);

        rbLiver = new JRadioButton("Liver Function Test");
		rbLiver.setFont(new Font("Times New Roman", 0, 16));
        rbLiver.setBackground(Color.WHITE);

        rbRenal = new JRadioButton("Renal Function Test");
		rbRenal.setFont(new Font("Times New Roman", 0, 16));
        rbRenal.setBackground(Color.WHITE);

        rbEletrol = new JRadioButton("Electrol Test");
		rbEletrol.setFont(new Font("Times New Roman", 0, 16));
        rbEletrol.setBackground(Color.WHITE);

        pnHemLab.add(rbRed);
        pnHemLab.add(rbWhite);
        pnHemLab.add(rbLiver);
        pnHemLab.add(rbRenal);
        pnHemLab.add(rbEletrol);

	}

	public void createRadLabPanel(){
		pnRadLab = new JPanel();
        pnRadLab.setBackground(Color.WHITE);
        pnRadLab.setLayout(new GridLayout(3,1));
        pnRadLab.setBorder(BorderFactory.createTitledBorder("Radiologic Laboratory"));

        rbXray = new JRadioButton("X-Ray");
		rbXray.setFont(new Font("Times New Roman", 0, 16));
        rbXray.setBackground(Color.WHITE);

        rbCompTom = new JRadioButton("Computed Tomography (CT)");
		rbCompTom.setFont(new Font("Times New Roman", 0, 16));
        rbCompTom.setBackground(Color.WHITE);

        rbMagRes = new JRadioButton("Magnetic Resonance Image (MRI)");
		rbMagRes.setFont(new Font("Times New Roman", 0, 16));
        rbMagRes.setBackground(Color.WHITE);

        pnRadLab.add(rbXray);
        pnRadLab.add(rbCompTom);
        pnRadLab.add(rbMagRes);

	}

	public void createTestsPanel(){
		pnTests = new JPanel();
        pnTests.setBackground(Color.WHITE);

        pnTests.setLayout(new GridLayout(3,1));
        pnTests.setBorder(BorderFactory.createTitledBorder("Additional Tests"));
        rbUrin = new JRadioButton("Urinary Test");
		rbUrin.setFont(new Font("Times New Roman", 0, 16));
        rbUrin.setBackground(Color.WHITE);

        rbStool = new JRadioButton("Stool Test");
		rbStool.setFont(new Font("Times New Roman", 0, 16));
        rbStool.setBackground(Color.WHITE);

        pnTests.add(rbUrin);
        pnTests.add(rbStool);

	}


	public void editLabTests(){


	}


	public void readOnlyLabTests(){



	}
}