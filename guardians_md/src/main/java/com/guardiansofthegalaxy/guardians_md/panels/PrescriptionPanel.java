package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.Prescription;
import com.guardiansofthegalaxy.guardians_md.db.Visit;

import javax.swing.*;
import java.awt.*;

public class PrescriptionPanel extends JPanel {

    public JPanel pnInjec, pnPO;
    //labels and text areas for the general practice section

    public JRadioButton rbIntramu, rbIntravas, rbSubcuta;
    public JLabel lblPO;
    public JTextArea txtaPO;

    public PrescriptionPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Prescriptions"));
        setBackground(Color.WHITE);

        createInjecPanel();
        createPOPanel();
        add(pnInjec, BorderLayout.WEST);
        add(pnPO, BorderLayout.CENTER);
    }

    public void createInjecPanel() {
        pnInjec = new JPanel();
        pnInjec.setBackground(Color.WHITE);
        pnInjec.setLayout(new GridLayout(3, 1));
        pnInjec.setBorder(BorderFactory.createTitledBorder("Injections"));

        rbIntramu = new JRadioButton("Intramuscular Injection (IM)");
        rbIntramu.setFont(new Font("Times New Roman", 0, 16));
        rbIntramu.setBackground(Color.WHITE);
        rbIntravas = new JRadioButton("Intravascular Injection (IV)");
        rbIntravas.setFont(new Font("Times New Roman", 0, 16));
        rbIntravas.setBackground(Color.WHITE);
        rbSubcuta = new JRadioButton("Subcutaneous Injection (SC)");
        rbSubcuta.setFont(new Font("Times New Roman", 0, 16));
        rbSubcuta.setBackground(Color.WHITE);

        pnInjec.add(rbIntramu);
        pnInjec.add(rbIntravas);
        pnInjec.add(rbSubcuta);

    }

    public void createPOPanel() {
        pnPO = new JPanel();
        pnPO.setBackground(Color.WHITE);

        pnPO.setLayout(new BorderLayout());
        pnPO.setBorder(BorderFactory.createTitledBorder("PO (Oral Medication)"));
        lblPO = new JLabel("Enter the Prescription: ");
        lblPO.setFont(new Font("Times New Roman", 0, 16));

        txtaPO = new JTextArea(50, 50);
        txtaPO.setFont(new Font("Times New Roman", 0, 16));

        JScrollPane scroll = new JScrollPane(txtaPO);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnPO.add(lblPO, BorderLayout.NORTH);
        pnPO.add(scroll, BorderLayout.CENTER);

    }


    public void loadPrescriptionsInformation() {
       Visit visit = MedicalConfigurator.getActiveVisit();
        for (Prescription prescription : visit.getPrescriptions()) {
            checkPrescriptions(prescription.getMedType(), prescription.getMedName());

        }

    }





    private void checkPrescriptions(String medType, String medName) {
        if (medType.equalsIgnoreCase("PO")) {
            txtaPO.append(medName);
        }

        if (medType.equalsIgnoreCase("injection")) {
            if (medName.equalsIgnoreCase("intramu")) {
                rbIntramu.setSelected(true);
            }
            if (medName.equalsIgnoreCase("intravas")) {
                rbIntravas.setSelected(true);
            }
            if (medName.equalsIgnoreCase("subscuta")) {
                rbSubcuta.setSelected(true);
            }
        }
    }

}