package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.Visit;

import javax.swing.*;
import java.awt.*;

public class GeneralPracticePanel extends JPanel {

	//labels and text areas for the general practice section
	public JLabel lblComp, lblPresIll, lblPsHist, lblRevSym, lblPhysEx, lblImp, lblDiag,lblLabTest, lblPresc;
	public JTextField txtComp,txtPresIll, txtPsHist,txtRevSym, txtPhysEx, txtImp, txtDiag;


	public GeneralPracticePanel(){

		setLayout(new GridLayout(7,2));
		setBorder(BorderFactory.createTitledBorder("General Practice"));
        setBackground(Color.WHITE);

        lblComp = new JLabel("Chief Complaint");
		lblComp.setFont(new Font("Times New Roman", 0, 16));

		txtComp = new JTextField(256);
		txtComp.setFont(new Font("Times New Roman", 0, 16));
        txtComp.setBackground(Color.WHITE);

		lblPresIll = new JLabel("Present Illness");
		lblPresIll.setFont(new Font("Times New Roman", 0, 16));

		txtPresIll = new JTextField(256);
		txtPresIll.setFont(new Font("Times New Roman", 0, 16));
        txtPresIll.setBackground(Color.WHITE);

		lblPsHist = new JLabel("Past History");
		lblPsHist.setFont(new Font("Times New Roman", 0, 16));

		txtPsHist = new JTextField(256);
		txtPsHist.setFont(new Font("Times New Roman", 0, 16));
        txtPsHist.setBackground(Color.WHITE);

		lblRevSym = new JLabel("Review of the Symptom");
		lblRevSym.setFont(new Font("Times New Roman", 0, 16));

		txtRevSym = new JTextField(256);
		txtRevSym.setFont(new Font("Times New Roman", 0, 16));
        txtRevSym.setBackground(Color.WHITE);

		lblPhysEx = new JLabel("Physical Exam");
		lblPhysEx.setFont(new Font("Times New Roman", 0, 16));

		txtPhysEx = new JTextField(256);
		txtPhysEx.setFont(new Font("Times New Roman", 0, 16));
        txtPhysEx.setBackground(Color.WHITE);

		lblImp = new JLabel("Impression");
		lblImp.setFont(new Font("Times New Roman", 0, 16));

		txtImp = new JTextField(256);
		txtImp.setFont(new Font("Times New Roman", 0, 16));
        txtImp.setBackground(Color.WHITE);

		lblDiag = new JLabel("Diagnosis");
		lblDiag.setFont(new Font("Times New Roman", 0, 16));

		txtDiag = new JTextField(256);
		txtDiag.setFont(new Font("Times New Roman", 0, 16));
        txtDiag.setBackground(Color.WHITE);

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


    public void loadGeneralPracticeInformation() {
      Visit  visit = MedicalConfigurator.getActiveVisit();
        txtDiag.setText(visit.getMdFields().get(0));
        txtComp.setText(visit.getMdFields().get(1));
        txtImp.setText(visit.getMdFields().get(2));
        txtPhysEx.setText(visit.getMdFields().get(3));
        txtPresIll.setText(visit.getMdFields().get(4));
        txtPsHist.setText(visit.getMdFields().get(5));
        txtRevSym.setText(visit.getMdFields().get(6));
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