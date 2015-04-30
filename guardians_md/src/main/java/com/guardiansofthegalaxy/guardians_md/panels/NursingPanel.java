/**
 * Class: CSCI 400: Special Topic in Computer Science - GUI Programming
 * Instructor: Professor Jung
 * Date: Spring 2015
 * 
 * Group: The Guardians of the GUI
 * Group Members: Christina Reid
 *                Sara Hakkoum
 *                Thomas McNulty
 *                Trevor Gorman
 * 
 * Assignment: Project 2 - Medical Doctor software
 **/
package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NursingPanel extends JPanel {

	//labels and text areas for the general practice section
	public JTextArea txtaComm;
	public JCheckBox ckEditComm;
	public boolean commChanged;

	public NursingPanel(){
		setLayout(new BorderLayout());
        setBackground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Nursing Comments"), new EmptyBorder(10, 10, 10, 10)));

        txtaComm = new JTextArea(5,26);
        txtaComm.setEditable(false);
        txtaComm.setFont(new Font("Times New Roman", 0, 16));
        txtaComm.setLineWrap(true);
        txtaComm.setWrapStyleWord(true);
        txtaComm.getDocument().addDocumentListener(new ChangeListener());
 		
 		JScrollPane scroll = new JScrollPane(txtaComm, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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

	  	commChanged = false;

		add(ckEditComm, BorderLayout.SOUTH);
        add(scroll, BorderLayout.CENTER);
	}

    public void loadNursingComments() {
    	// setText() used to ensure previous comments of panel are replaced
        txtaComm.setText(MedicalConfigurator.getActiveVisit().getComments());

        commChanged = false;
    }

    public void clearFields() {
        txtaComm.setText("");
        ckEditComm.setSelected(false);
        commChanged = false;
    }

    private class ChangeListener implements DocumentListener {

    	public void insertUpdate(DocumentEvent e) {
    		commChanged = true;
    	}

    	public void removeUpdate(DocumentEvent e) {
    		commChanged = true;
    	}

    	public void changedUpdate(DocumentEvent e) { }
    }
}