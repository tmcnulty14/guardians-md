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
import com.guardiansofthegalaxy.guardians_md.tuples.Visit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

// general practice form for a patient visit
public class GeneralPracticePanel extends JPanel {

    //labels and text areas for the general practice section
    public JTextField txtDate, txtDoc;
    public JPanel pnDate, pnDoc;
    public JLabel lblComp, lblPresIll, lblPsHist, lblRevSym, lblPhysEx, lblImp, lblDiag, lblLabTest, lblPresc;
    public JTextArea txtComp, txtPresIll, txtPsHist, txtRevSym, txtPhysEx, txtImp, txtDiag;
    public JScrollPane compScroll, presIllScroll, psHistScroll, revSymScroll, physExScroll, impScroll, diagScroll;

    public GeneralPracticePanel() {
        setLayout(new GridLayout(8, 2, 0, 10));
        setBorder(BorderFactory.createCompoundBorder(new TitledBorder("General Practice"), new EmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);

        pnDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnDate.setBackground(Color.WHITE);
        txtDate = new JTextField(10);
        txtDate.setEditable(false);

        pnDoc = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnDoc.setBackground(Color.WHITE);
        txtDoc = new JTextField(20);
        txtDoc.setEditable(false);

        lblComp = new JLabel("Chief Complaint");
        lblComp.setFont(new Font("Times New Roman", 0, 16));

        txtComp = new JTextArea();
        txtComp.setFont(new Font("Times New Roman", 0, 16));
        txtComp.setLineWrap(true);
        txtComp.setWrapStyleWord(true);

        compScroll = new JScrollPane(txtComp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblPresIll = new JLabel("Present Illness");
        lblPresIll.setFont(new Font("Times New Roman", 0, 16));

        txtPresIll = new JTextArea();
        txtPresIll.setFont(new Font("Times New Roman", 0, 16));
        txtPresIll.setLineWrap(true);
        txtPresIll.setWrapStyleWord(true);

        presIllScroll = new JScrollPane(txtPresIll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblPsHist = new JLabel("Past History");
        lblPsHist.setFont(new Font("Times New Roman", 0, 16));

        txtPsHist = new JTextArea();
        txtPsHist.setFont(new Font("Times New Roman", 0, 16));
        txtPsHist.setLineWrap(true);
        txtPsHist.setWrapStyleWord(true);

        psHistScroll = new JScrollPane(txtPsHist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblRevSym = new JLabel("Review of Symptoms");
        lblRevSym.setFont(new Font("Times New Roman", 0, 16));

        txtRevSym = new JTextArea();
        txtRevSym.setFont(new Font("Times New Roman", 0, 16));
        txtRevSym.setLineWrap(true);
        txtRevSym.setWrapStyleWord(true);

        revSymScroll = new JScrollPane(txtRevSym, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblPhysEx = new JLabel("Physical Exam");
        lblPhysEx.setFont(new Font("Times New Roman", 0, 16));

        txtPhysEx = new JTextArea();
        txtPhysEx.setFont(new Font("Times New Roman", 0, 16));
        txtPhysEx.setLineWrap(true);
        txtPhysEx.setWrapStyleWord(true);

        physExScroll = new JScrollPane(txtPhysEx, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblImp = new JLabel("Impression");
        lblImp.setFont(new Font("Times New Roman", 0, 16));

        txtImp = new JTextArea();
        txtImp.setFont(new Font("Times New Roman", 0, 16));
        txtImp.setLineWrap(true);
        txtImp.setWrapStyleWord(true);

        impScroll = new JScrollPane(txtImp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblDiag = new JLabel("Diagnosis");
        lblDiag.setFont(new Font("Times New Roman", 0, 16));

        txtDiag = new JTextArea();
        txtDiag.setFont(new Font("Times New Roman", 0, 16));
        txtDiag.setLineWrap(true);
        txtDiag.setWrapStyleWord(true);

        diagScroll = new JScrollPane(txtDiag, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        pnDate.add(txtDate);
        pnDoc.add(txtDoc);

        add(pnDate);
        add(pnDoc);
        add(lblComp);
        add(compScroll);
        add(lblPresIll);
        add(presIllScroll);
        add(lblPsHist);
        add(psHistScroll);
        add(lblRevSym);
        add(revSymScroll);
        add(lblPhysEx);
        add(physExScroll);
        add(lblImp);
        add(impScroll);
        add(lblDiag);
        add(diagScroll);
    }


    public void loadGeneralPracticeInformation() {
        Visit visit = MedicalConfigurator.getActiveVisit();

        if (!visit.getMdFields().isEmpty()) {
            txtComp.setText(visit.getMdFields().get(0));
            txtPresIll.setText(visit.getMdFields().get(1));
            txtPsHist.setText(visit.getMdFields().get(2));
            txtRevSym.setText(visit.getMdFields().get(3));
            txtPhysEx.setText(visit.getMdFields().get(4));
            txtImp.setText(visit.getMdFields().get(5));
            txtDiag.setText(visit.getMdFields().get(6));
            txtDate.setText(visit.getDate());
            txtDoc.setText("Doctor Id: " + visit.getDoctorID());
        } else {
            clearFields();
        }
    }

    public void clearFields() {
        txtDiag.setText("");
        txtComp.setText("");
        txtImp.setText("");
        txtPhysEx.setText("");
        txtPresIll.setText("");
        txtPsHist.setText("");
        txtRevSym.setText("");
        txtDate.setText("");
        txtDoc.setText("");
    }

    public void readOnlyGeneralPractice() {
        txtComp.setEditable(false);
        txtPresIll.setEditable(false);
        txtPsHist.setEditable(false);
        txtRevSym.setEditable(false);
        txtPhysEx.setEditable(true);
        txtImp.setEditable(false);
        txtDiag.setEditable(false);
    }


    public void editGeneralPractice() {
        txtComp.setEditable(true);
        txtPresIll.setEditable(true);
        txtPsHist.setEditable(true);
        txtRevSym.setEditable(true);
        txtPhysEx.setEditable(true);
        txtImp.setEditable(true);
        txtDiag.setEditable(true);


    }


}