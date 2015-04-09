package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class PrescriptionPanel extends JPanel {

	public JPanel pnInjec, pnPO;
	//labels and text areas for the general practice section

	public JRadioButton rbIntramu, rbIntravas, rbSubcuta;
	public JLabel lblPO;
	public JTextArea txtaPO;

	public void build(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Prescriptions"));
		createInjecPanel();
		createPOPanel();
		add(pnInjec, BorderLayout.WEST);
		add(pnPO, BorderLayout.CENTER);
	}

	public void createInjecPanel(){
		pnInjec = new JPanel();
		pnInjec.setLayout(new GridLayout(3,1));
        pnInjec.setBorder(BorderFactory.createTitledBorder("Injections"));
        rbIntramu = new JRadioButton("Intramuscular Injection (IM)");
        rbIntramu.setFont(new Font("Times New Roman", 0, 16));
        rbIntravas = new JRadioButton("Intravascular Injection (IV)");
        rbIntravas.setFont(new Font("Times New Roman", 0, 16));
        rbSubcuta = new JRadioButton("Subcutaneous Injection (SC)");
        rbSubcuta.setFont(new Font("Times New Roman", 0, 16));

        pnInjec.add(rbIntramu);
        pnInjec.add(rbIntravas);
        pnInjec.add(rbSubcuta);

	}

	public void createPOPanel(){
		pnPO = new JPanel();
		pnPO.setLayout(new BorderLayout());
        pnPO.setBorder(BorderFactory.createTitledBorder("PO (Oral Medication)"));
        lblPO = new JLabel("Enter the Prescription: ");
		lblPO.setFont(new Font("Times New Roman", 0, 16));

        txtaPO = new JTextArea(50,50);
		txtaPO.setFont(new Font("Times New Roman", 0, 16));

  		JScrollPane scroll = new JScrollPane(txtaPO);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnPO.add(lblPO, BorderLayout.NORTH);
        pnPO.add(scroll, BorderLayout.CENTER);

	}
}