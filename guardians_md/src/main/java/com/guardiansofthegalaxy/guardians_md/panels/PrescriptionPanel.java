package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.Prescription;
import com.guardiansofthegalaxy.guardians_md.db.Visit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PrescriptionPanel extends JPanel {

    public JPanel pnPrescribe;
    public JCheckBox ckIntramu, ckIntravas, ckSubcuta, ckOral;
    public JTextArea txtIntramu, txtIntravas, txtSubcuta, txtOral;
    public JScrollPane scroll1, scroll2, scroll3, scroll4;

    public PrescriptionPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Prescriptions"));
        setBackground(Color.WHITE);

        createPanel();

        add(pnPrescribe, BorderLayout.CENTER);
    }

    public void createPanel() {
        pnPrescribe = new JPanel();
        pnPrescribe.setBackground(Color.WHITE);
        pnPrescribe.setLayout(new GridLayout(4, 1, 0, 10));
        pnPrescribe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ckIntramu = new JCheckBox("Intramuscular Injection (IM)");
        ckIntramu.setFont(new Font("Times New Roman", 0, 16));
        ckIntramu.addItemListener(new CheckBoxListener());

        txtIntramu = new JTextArea();
        txtIntramu.setFont(new Font("Times New Roman", 0, 16));
        txtIntramu.setLineWrap(true);
        txtIntramu.setWrapStyleWord(true);
        txtIntramu.setEnabled(false);
        scroll1 = new JScrollPane(txtIntramu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll1.setEnabled(false);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel1.add(ckIntramu, BorderLayout.NORTH);
        panel1.add(scroll1, BorderLayout.CENTER);

        ckIntravas = new JCheckBox("Intravascular Injection (IV)");
        ckIntravas.setFont(new Font("Times New Roman", 0, 16));
        ckIntravas.addItemListener(new CheckBoxListener());

        txtIntravas = new JTextArea();
        txtIntravas.setFont(new Font("Times New Roman", 0, 16));
        txtIntravas.setLineWrap(true);
        txtIntravas.setWrapStyleWord(true);
        txtIntravas.setEnabled(false);
        scroll2 = new JScrollPane(txtIntravas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll2.setEnabled(false);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(Color.WHITE);
        panel2.add(ckIntravas, BorderLayout.NORTH);
        panel2.add(scroll2, BorderLayout.CENTER);
        
        ckSubcuta = new JCheckBox("Subcutaneous Injection (SC)");
        ckSubcuta.setFont(new Font("Times New Roman", 0, 16));
        ckSubcuta.addItemListener(new CheckBoxListener());

        txtSubcuta = new JTextArea();
        txtSubcuta.setFont(new Font("Times New Roman", 0, 16));
        txtSubcuta.setLineWrap(true);
        txtSubcuta.setWrapStyleWord(true);
        txtSubcuta.setEnabled(false);
        scroll3 = new JScrollPane(txtSubcuta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll3.setEnabled(false);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.WHITE);
        panel3.add(ckSubcuta, BorderLayout.NORTH);
        panel3.add(scroll3, BorderLayout.CENTER);

        ckOral = new JCheckBox("Oral Medication (PO)");
        ckOral.setFont(new Font("Times New Roman", 0, 16));
        ckOral.addItemListener(new CheckBoxListener());

        txtOral = new JTextArea();
        txtOral.setFont(new Font("Times New Roman", 0, 16));
        txtOral.setLineWrap(true);
        txtOral.setWrapStyleWord(true);
        txtOral.setEnabled(false);
        scroll4 = new JScrollPane(txtOral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll4.setEnabled(false);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.setBackground(Color.WHITE);
        panel4.add(ckOral, BorderLayout.NORTH);
        panel4.add(scroll4, BorderLayout.CENTER);

        pnPrescribe.add(panel1);
        pnPrescribe.add(panel2);
        pnPrescribe.add(panel3);
        pnPrescribe.add(panel4);
    }

    public void loadPrescriptionsInformation() {
        Visit visit = MedicalConfigurator.getActiveVisit();

        if (!visit.getPrescriptions().isEmpty()) {
            for (Prescription prescription : visit.getPrescriptions()) {
                checkPrescriptions(prescription.getMedType(), prescription.getMedName());
            }
        }
        else {
            ckIntramu.setSelected(false);
            ckIntravas.setSelected(false);
            ckSubcuta.setSelected(false);
            ckOral.setSelected(false);

            txtIntramu.setText("");
            txtIntravas.setText("");
            txtSubcuta.setText("");
            txtOral.setText("");
        }
    }

    private void checkPrescriptions(String medType, String medName) {
        switch (medType) {
            case "intramu": {
                ckIntramu.setSelected(true);
                txtIntramu.setText(medName);
                break;
            }

            case "intravas": {
                ckIntravas.setSelected(true);
                txtIntravas.setText(medName);
                break;
            }

            case "subcuta": {
                ckSubcuta.setSelected(true);
                txtSubcuta.setText(medName);
                break;
            }

            case "oral": {
                ckOral.setSelected(true);
                txtOral.setText(medName);
                break;
            }
        }
    }

    private class CheckBoxListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object obj = e.getSource();

            if (obj == ckIntramu) {
                if (ckIntramu.isSelected()) {
                    txtIntramu.setEnabled(true);
                    scroll1.setEnabled(true);
                }
                else {
                    txtIntramu.setEnabled(false);
                    scroll1.setEnabled(false);
                }
            }
            else if (obj == ckIntravas) {
                if (ckIntravas.isSelected()) {
                    txtIntravas.setEnabled(true);
                    scroll2.setEnabled(true);
                }
                else {
                    txtIntravas.setEnabled(false);
                    scroll2.setEnabled(false);
                }
            }
            else if (obj == ckSubcuta) {
                if (ckSubcuta.isSelected()) {
                    txtSubcuta.setEnabled(true);
                    scroll3.setEnabled(true);
                }
                else {
                    txtSubcuta.setEnabled(false);
                    scroll3.setEnabled(false);
                }
            }
            else if (obj == ckOral) {
                if (ckOral.isSelected()) {
                    txtOral.setEnabled(true);
                    scroll4.setEnabled(true);
                }
                else {
                    txtOral.setEnabled(false);
                    scroll4.setEnabled(false);
                }
            }
        }
    }
}