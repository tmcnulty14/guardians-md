package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MedicalMainPanel extends JPanel {

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;

    public CardLayout cardLayout;
    public JPanel cardPanel;
    public PatientInformationPanel pnPat;
    public GeneralPracticePanel pnGenPract;
    public LabTestsPanel pnLabTests;
    public PrescriptionPanel pnPresc;
    public NursingPanel pnNursComm;
    public JPanel pnSelectButtons;
    public JButton btnPat, btnGenPract, btnLabTests, btnPresc, btnNursComm, btnSubmit;
    public Patient patient = null;
    public Visit visit = null;
    public DatabaseConnection database;

    public JPanel pnSubmitButton;


    public MedicalMainPanel(DatabaseConnection database) {
        this.database = database;
        setLayout(new BorderLayout());
        buildPanels();
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void buildPanels() {


        cardPanel = new JPanel();
        pnPat = new PatientInformationPanel();
        pnGenPract = new GeneralPracticePanel();
        pnLabTests = new LabTestsPanel();
        pnPresc = new PrescriptionPanel();
        pnNursComm = new NursingPanel();

        buildSelectionButtonsPanel();
        buildButtonSubmitPanel();

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(pnPat, "PatientInformationPanel");
        cardPanel.add(pnGenPract, "GeneralPracticePanel");
        cardPanel.add(pnLabTests, "LabTestsPanel");
        cardPanel.add(pnPresc, "PrescriptionsPanel");
        cardPanel.add(pnNursComm, "NursingCommentsPanel");

        add(pnSelectButtons, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
        add(pnSubmitButton, BorderLayout.SOUTH);

        pnPat.btnSubmitPatientData.setVisible(false);

    }

    private void buildButtonSubmitPanel() {

        pnSubmitButton = new JPanel();
        pnSubmitButton.setBackground(Color.WHITE);
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new MenuListener());
        btnSubmit.setFont(new Font("DejaVu Serif", 0, 16));
        btnSubmit.setPreferredSize(new Dimension(200, 60));

        pnSubmitButton.add(btnSubmit);

    }


    private void buildSelectionButtonsPanel() {

        pnSelectButtons = new JPanel();
        pnSelectButtons.setBackground(Color.WHITE);
        pnSelectButtons.setBorder(BorderFactory.createTitledBorder("Selection Options"));
        pnSelectButtons.setLayout(new GridLayout(5, 1));

        btnPat = new JButton("Patient Information");
        btnPat.addActionListener(new MenuListener());
        btnPat.setFont(new Font("DejaVu Serif", 0, 16));


        btnGenPract = new JButton("General Practice");
        btnGenPract.addActionListener(new MenuListener());
        btnGenPract.setFont(new Font("DejaVu Serif", 0, 16));

        btnLabTests = new JButton("Labratory Tests");
        btnLabTests.addActionListener(new MenuListener());
        btnLabTests.setFont(new Font("DejaVu Serif", 0, 16));

        btnPresc = new JButton("Prescriptions");
        btnPresc.addActionListener(new MenuListener());
        btnPresc.setFont(new Font("DejaVu Serif", 0, 16));

        btnNursComm = new JButton("Nursing Comments");
        btnNursComm.addActionListener(new MenuListener());
        btnNursComm.setFont(new Font("DejaVu Serif", 0, 16));

        pnSelectButtons.add(btnPat);
        pnSelectButtons.add(btnGenPract);
        pnSelectButtons.add(btnLabTests);
        pnSelectButtons.add(btnPresc);
        pnSelectButtons.add(btnNursComm);

    }

    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == btnPat) {
                cardLayout.show(cardPanel, "PatientInformationPanel");
            }
            else if (source == btnGenPract) {
                cardLayout.show(cardPanel, "GeneralPracticePanel");
            }
            else if (source == btnLabTests) {
                cardLayout.show(cardPanel, "LabTestsPanel");
            }
            else if (source == btnPresc) {
                cardLayout.show(cardPanel, "PrescriptionsPanel");
            }
            else if (source == btnNursComm) {
                cardLayout.show(cardPanel, "NursingCommentsPanel");
            }
            else if (source == btnSubmit) {
                DatabaseConnection dbc = new DatabaseConnection();

                String gender = "";

                if (pnPat.rbMale.isSelected()) {
                    gender = "M";
                }
                else if (pnPat.rbFemale.isSelected()) {
                    gender = "F";
                }

                Patient updatedPatient = new Patient(MedicalConfigurator.getActivePatient().getPatientID(),
                        pnPat.txtFName.getText(), pnPat.txtLName.getText(),
                        pnPat.txtBirthDate.getText(), gender,
                        pnPat.txtAddress1.getText(), pnPat.txtAddress2.getText(),
                        pnPat.txtCity.getText(), pnPat.txtState.getText(),
                        pnPat.txtZip.getText(), pnPat.txtCountry.getText(),
                        pnPat.txtInsProv.getText(), pnPat.txtInsNum.getText());

                if (!dbc.updatePatient(updatedPatient)) {
                    JOptionPane.showMessageDialog(null, "Could not update patient. Please check that all fields are valid and try again.",
                        "Update failed", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    MedicalConfigurator.setActivePatient(updatedPatient);

                    // New visits have an ID of -1
                    // TODO make sure that when a visit is being created, that a new visit with ID -1 is set in med config
                    if (MedicalConfigurator.isNewVisit()) {
                        Date dt = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String date = sdf.format(dt);

                        ArrayList<String> mdFields = new ArrayList<>();
                        mdFields.add(pnGenPract.txtComp.getText());
                        mdFields.add(pnGenPract.txtPresIll.getText());
                        mdFields.add(pnGenPract.txtPsHist.getText());
                        mdFields.add(pnGenPract.txtRevSym.getText());
                        mdFields.add(pnGenPract.txtPhysEx.getText());
                        mdFields.add(pnGenPract.txtImp.getText());
                        mdFields.add(pnGenPract.txtDiag.getText());

                        Visit newVisit = new Visit(MedicalConfigurator.getActivePatient().getPatientID(),
                            MedicalConfigurator.getLoginUser().getUsername(), date, mdFields);

                        // Add lab orders to new visit

                        if (pnLabTests.ckRed.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "red"));
                        }
                        if (pnLabTests.ckWhite.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "white"));
                        }
                        if (pnLabTests.ckLiver.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "liver"));
                        }
                        if (pnLabTests.ckRenal.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "renal"));
                        }
                        if (pnLabTests.ckEletrol.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "electrol"));
                        }
                        if (pnLabTests.ckXray.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "xray"));
                        }
                        if (pnLabTests.ckCompTom.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "comptom"));
                        }
                        if (pnLabTests.ckMagRes.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("lab", "magres"));
                        }
                        if (pnLabTests.ckUrin.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("test", "urin"));
                        }
                        if (pnLabTests.ckStool.isSelected()) {
                            newVisit.addLabOrder(new LabOrder("test", "stool"));
                        }

                        // Add prescriptions to new visit

                        if (pnPresc.ckIntramu.isSelected()) {
                            newVisit.addPrescription(new Prescription("intramu", pnPresc.txtIntramu.getText()));
                        }
                        if (pnPresc.ckIntravas.isSelected()) {
                            newVisit.addPrescription(new Prescription("intravas", pnPresc.txtIntravas.getText()));
                        }
                        if (pnPresc.ckSubcuta.isSelected()) {
                            newVisit.addPrescription(new Prescription("subcuta", pnPresc.txtSubcuta.getText()));
                        }
                        if (pnPresc.ckOral.isSelected()) {
                            newVisit.addPrescription(new Prescription("oral", pnPresc.txtOral.getText()));
                        }

                        if (!dbc.createVisit(newVisit)) {
                            JOptionPane.showMessageDialog(null, "Could not create visit. Please check that all fields are valid and try again.",
                                "Visit creation failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Successfully created new visit for patient " + pnPat.txtFName.getText() +
                                " " + pnPat.txtLName.getText() + ".", "Successful", JOptionPane.PLAIN_MESSAGE);

                            MedicalConfigurator.setActiveVisit(dbc.getVisit(dbc.getMaxVisitId()));
                        }
                    }

                    // Update existing visit
                    else {
                        Visit updatedVisit = MedicalConfigurator.getActiveVisit();

                        // Set general practice fields of visit

                        ArrayList<String> mdFields = new ArrayList<>();
                        mdFields.add(pnGenPract.txtComp.getText());
                        mdFields.add(pnGenPract.txtPresIll.getText());
                        mdFields.add(pnGenPract.txtPsHist.getText());
                        mdFields.add(pnGenPract.txtRevSym.getText());
                        mdFields.add(pnGenPract.txtPhysEx.getText());
                        mdFields.add(pnGenPract.txtImp.getText());
                        mdFields.add(pnGenPract.txtDiag.getText());

                        updatedVisit.setMdFields(mdFields);

                        // Set lab orders of visit

                        ArrayList<LabOrder> labOrders = new ArrayList<>();

                        if (pnLabTests.ckRed.isSelected()) {
                            labOrders.add(new LabOrder("lab", "red"));
                        }
                        if (pnLabTests.ckWhite.isSelected()) {
                            labOrders.add(new LabOrder("lab", "white"));
                        }
                        if (pnLabTests.ckLiver.isSelected()) {
                            labOrders.add(new LabOrder("lab", "liver"));
                        }
                        if (pnLabTests.ckRenal.isSelected()) {
                            labOrders.add(new LabOrder("lab", "renal"));
                        }
                        if (pnLabTests.ckEletrol.isSelected()) {
                            labOrders.add(new LabOrder("lab", "electrol"));
                        }
                        if (pnLabTests.ckXray.isSelected()) {
                            labOrders.add(new LabOrder("lab", "xray"));
                        }
                        if (pnLabTests.ckCompTom.isSelected()) {
                            labOrders.add(new LabOrder("lab", "comptom"));
                        }
                        if (pnLabTests.ckMagRes.isSelected()) {
                            labOrders.add(new LabOrder("lab", "magres"));
                        }
                        if (pnLabTests.ckUrin.isSelected()) {
                            labOrders.add(new LabOrder("test", "urin"));
                        }
                        if (pnLabTests.ckStool.isSelected()) {
                            labOrders.add(new LabOrder("test", "stool"));
                        }

                        updatedVisit.setLabOrders(labOrders);

                        // Set prescriptions of visit
                        
                        ArrayList<Prescription> prescriptions = new ArrayList<>();

                        if (pnPresc.ckIntramu.isSelected()) {
                            prescriptions.add(new Prescription("intramu", pnPresc.txtIntramu.getText()));
                        }
                        if (pnPresc.ckIntravas.isSelected()) {
                            prescriptions.add(new Prescription("intravas", pnPresc.txtIntravas.getText()));
                        }
                        if (pnPresc.ckSubcuta.isSelected()) {
                            prescriptions.add(new Prescription("subcuta", pnPresc.txtSubcuta.getText()));
                        }
                        if (pnPresc.ckOral.isSelected()) {
                            prescriptions.add(new Prescription("oral", pnPresc.txtOral.getText()));
                        }

                        updatedVisit.setPrescriptions(prescriptions);

                        // Set nursing comments of visit
                        if (pnNursComm.commChanged) {
                            User currentUser = MedicalConfigurator.getLoginUser();

                            Date dt = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String date = sdf.format(dt);

                            String comments = pnNursComm.txtaComm.getText();
                            comments += "\n\n---Nurse: " + currentUser.getFirstName() + " " + currentUser.getLastName() + ", " + date;

                            updatedVisit.setComments(comments);
                        }

                        if (!dbc.updateVisit(updatedVisit)) {
                            JOptionPane.showMessageDialog(null, "Could not update visit. Please check that all fields are valid and try again.",
                                "Visit update failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Successfully updated visit for patient " + pnPat.txtFName.getText() +
                                " " + pnPat.txtLName.getText() + ".", "Successful", JOptionPane.PLAIN_MESSAGE);

                            MedicalConfigurator.setActiveVisit(updatedVisit);
                        }
                    }
                }
            }
        }
    }
}