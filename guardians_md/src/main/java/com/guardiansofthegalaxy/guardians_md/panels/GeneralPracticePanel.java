package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class GeneralPracticePanel extends JPanel {

	//labels and text areas for the general practice section
	public JLabel lblComp, lblPresIll, lblPsHist, lblRevSym, lblPhysEx, lblImp, lblDiag,lblLabTest, lblPresc;
	public JTextField txtComp,txtPresIll, txtPsHist,txtRevSym, txtPhysEx, txtImp, txtDiag;


	public void build(){

		setLayout(new GridLayout(7,2));
		setBorder(BorderFactory.createTitledBorder("General Practice"));

		lblComp = new JLabel("Chief Complaint");
		lblComp.setFont(new Font("Times New Roman", 0, 16));

		txtComp = new JTextField(256);
		txtComp.setFont(new Font("Times New Roman", 0, 16));

		lblPresIll = new JLabel("Present Illness");
		lblPresIll.setFont(new Font("Times New Roman", 0, 16));

		txtPresIll = new JTextField(256);
		txtPresIll.setFont(new Font("Times New Roman", 0, 16));

		lblPsHist = new JLabel("Past History");
		lblPsHist.setFont(new Font("Times New Roman", 0, 16));

		txtPsHist = new JTextField(256);
		txtPsHist.setFont(new Font("Times New Roman", 0, 16));

		lblRevSym = new JLabel("Review of the Symptom");
		lblRevSym.setFont(new Font("Times New Roman", 0, 16));

		txtRevSym = new JTextField(256);
		txtRevSym.setFont(new Font("Times New Roman", 0, 16));

		lblPhysEx = new JLabel("Physical Exam");
		lblPhysEx.setFont(new Font("Times New Roman", 0, 16));

		txtPhysEx = new JTextField(256);
		txtPhysEx.setFont(new Font("Times New Roman", 0, 16));

		lblImp = new JLabel("Impression");
		lblImp.setFont(new Font("Times New Roman", 0, 16));

		txtImp = new JTextField(256);
		txtImp.setFont(new Font("Times New Roman", 0, 16));

		lblDiag = new JLabel("Diagnosis");
		lblDiag.setFont(new Font("Times New Roman", 0, 16));

		txtDiag = new JTextField(256);
		txtDiag.setFont(new Font("Times New Roman", 0, 16));

		add(lblComp);
		add(txtComp);
		add(lblPresIll);
		add(txtPresIll);
		add(lblPsHist);
		add(txtPsHist);
		add(lblRevSym);
		add(txtRevSym);
		add(lblPhysEx);
		add(txtPhysEx);
		add(lblImp);
		add(txtImp);
		add(lblDiag);
		add(txtDiag);
	}

	public void readOnlyGeneralPractice(){
		txtComp.setEditable(false);
		txtPresIll.setEditable(false);
		txtPsHist.setEditable(false);
		txtRevSym.setEditable(false);
		txtPhysEx.setEditable(true);
		txtImp.setEditable(false);
		txtDiag.setEditable(false);
	}


	public void editGeneralPractice(){
		txtComp.setEditable(true);
		txtPresIll.setEditable(true);
		txtPsHist.setEditable(true);
		txtRevSym.setEditable(true);
		txtPhysEx.setEditable(true);
		txtImp.setEditable(true);
		txtDiag.setEditable(true);



	}


}