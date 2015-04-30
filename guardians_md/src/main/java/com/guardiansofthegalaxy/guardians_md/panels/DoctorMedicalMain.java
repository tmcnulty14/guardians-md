/**
 * Class: CSCI 400: Special Topic in Computer Science - GUI Programming
 * Instructor: Professor Jung
 * Date: Spring 2015
 * 
 * Group: The Guardians of the GUI
 * Group Members: Christina Reid
 * 				  Sara Hakkoum
 * 				  Thomas McNulty
 * 				  Trevor Gorman
 * 
 * Assignment: Project 2 - Medical Doctor software
 **/
package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.DbConn;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Date.*;
import java.text.*;

// general doctor main panel, allows for more access then nurses
public class DoctorMedicalMain extends MedicalMainPanel {
	public DoctorMedicalMain(DbConn database) {
		super(database);
		disableNursingCheckBox();
	}
	// as stated Nurse check box is disabled
	public void disableNursingCheckBox(){
		pnNursComm.ckEditComm.setEnabled(false);
	}
}