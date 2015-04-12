package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class NursingPanel extends JPanel {

	//labels and text areas for the general practice section
	public JTextArea txtaComm;
	public JCheckBox ckEditComm;

	public NursingPanel(){
		setLayout(new BorderLayout());
        setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder("Nursing Comments"));
        txtaComm = new JTextArea(5,26);
        txtaComm.setEditable(false);
        txtaComm.setFont(new Font("Times New Roman", 0, 16));
 		JScrollPane scroll = new JScrollPane(txtaComm);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	  	ckEditComm = new JCheckBox("Edit Nursing Comments");
	  	ckEditComm.setFont(new Font("Times New Roman", 0, 16));
        ckEditComm.setBackground(Color.WHITE);
	  	ckEditComm.addItemListener(new ItemListener() {
	  		public void itemStateChanged(ItemEvent e) {
	  			Object source = e.getSource();
	  			if(source == ckEditComm & ckEditComm.isSelected()){
	  				txtaComm.setEditable(true);
	  			}
	  			if(source == ckEditComm & !ckEditComm.isSelected()){
	  				txtaComm.setEditable(false);
	  			}
		}});

		add(ckEditComm, BorderLayout.SOUTH);

        add(scroll, BorderLayout.CENTER);
	}

}