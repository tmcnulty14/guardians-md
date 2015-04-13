package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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


    public JPanel pnSubmitButton;


    public MedicalMainPanel() {
        setLayout(new BorderLayout());
        buildPanels();
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.WHITE);
        //loadPatientInformation();
        //loadGeneralPracticeInformation();
        //loadLabTestInformation();
        //loadPrescriptionsInformation();
        //loadNursingComments();
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


    public void loadPatientInformation() {
        patient = MedicalConfigurator.getActivePatient();
        pnPat.txtFName.setText(patient.getFirstName());
        pnPat.txtLName.setText(patient.getLastName());
        pnPat.txtAddress1.setText(patient.getAddress());
        pnPat.txtAddress2.setText(patient.getAddress());
        pnPat.txtCity.setText(patient.getCity());
        pnPat.txtState.setText(patient.getState());
        pnPat.txtZip.setText(patient.getZipcode());
        pnPat.txtCountry.setText(patient.getCountry());
        pnPat.txtBirthDate.setText(patient.getBirthdate());
        pnPat.txtInsProv.setText(patient.getInsuranceProvider());
        pnPat.txtInsNum.setText(patient.getInsuranceNumber());

        setGender();
        try {
            setAge();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }


    public void setGender() {
        if (patient.getGender().equalsIgnoreCase("F")) {
            pnPat.ckFemale.setSelected(true);
        }
        if (patient.getGender().equalsIgnoreCase("M")) {
            pnPat.ckMale.setSelected(true);
        } else {
            //nothing is selected
        }
    }

    public void setAge() throws ParseException {

        DateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = formatter.parse(patient.getBirthdate());

        DateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(date);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int ageInt = currentYear - Integer.parseInt(year);
        String age = String.valueOf(ageInt);
        pnPat.txtAge.setText(age);
    }


    public void loadGeneralPracticeInformation() {
        visit = MedicalConfigurator.getActiveVisit();
        pnGenPract.txtDiag.setText(visit.getMdFields().get(0));
        pnGenPract.txtComp.setText(visit.getMdFields().get(1));
        pnGenPract.txtImp.setText(visit.getMdFields().get(2));
        pnGenPract.txtPhysEx.setText(visit.getMdFields().get(3));
        pnGenPract.txtPresIll.setText(visit.getMdFields().get(4));
        pnGenPract.txtPsHist.setText(visit.getMdFields().get(5));
        pnGenPract.txtRevSym.setText(visit.getMdFields().get(6));
    }

    public void loadLabTestInformation() {
        visit = MedicalConfigurator.getActiveVisit();
        LabOrder labOrder;
        for (int i = 0; i < visit.getLabOrders().size(); i++) {

            labOrder = visit.getLabOrders().get(i);
            checkLabTests(labOrder.getLabName(), labOrder.getTestName());
        }
    }

    public void loadPrescriptionsInformation() {
        visit = MedicalConfigurator.getActiveVisit();
        for (Prescription prescription : visit.getPrescriptions()) {
            checkPrescriptions(prescription.getMedType(), prescription.getMedName());

        }

    }

    public void loadNursingComments() {
        pnNursComm.txtaComm.append(MedicalConfigurator.getActiveVisit().getComments());

    }

    private void checkLabTests(String labName, String testName) {
        if (labName.equalsIgnoreCase("lab")) {
            if (testName.equalsIgnoreCase("red")) {
                pnLabTests.rbRed.setSelected(true);
            }
            if (testName.equalsIgnoreCase("white")) {
                pnLabTests.rbWhite.setSelected(true);
            }
            if (testName.equalsIgnoreCase("liver")) {
                pnLabTests.rbLiver.setSelected(true);
            }
            if (testName.equalsIgnoreCase("renal")) {
                pnLabTests.rbRenal.setSelected(true);
            }
            if (testName.equalsIgnoreCase("electrol")) {
                pnLabTests.rbEletrol.setSelected(true);
            }
            if (testName.equalsIgnoreCase("xray")) {
                pnLabTests.rbXray.setSelected(true);
            }
            if (testName.equalsIgnoreCase("comptom")) {
                pnLabTests.rbCompTom.setSelected(true);
            }
            if (testName.equalsIgnoreCase("magres")) {
                pnLabTests.rbMagRes.setSelected(true);
            }
        }
        if (labName.equalsIgnoreCase("test")) {
            if (testName.equalsIgnoreCase("urin")) {
                pnLabTests.rbUrin.setSelected(true);
            }
            if (testName.equalsIgnoreCase("stool")) {
                pnLabTests.rbStool.setSelected(true);
            }
        }
    }

    private void checkPrescriptions(String medType, String medName) {
        if (medType.equalsIgnoreCase("PO")) {
            pnPresc.txtaPO.append(medName);
        }

        if (medType.equalsIgnoreCase("injection")) {
            if (medName.equalsIgnoreCase("intramu")) {
                pnPresc.rbIntramu.setSelected(true);
            }
            if (medName.equalsIgnoreCase("intravas")) {
                pnPresc.rbIntravas.setSelected(true);
            }
            if (medName.equalsIgnoreCase("subscuta")) {
                pnPresc.rbSubcuta.setSelected(true);
            }
        }
    }

    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == btnPat) {
                cardLayout.show(cardPanel, "PatientInformationPanel");
            }
            if (source == btnGenPract) {
                cardLayout.show(cardPanel, "GeneralPracticePanel");
            }
            if (source == btnLabTests) {
                cardLayout.show(cardPanel, "LabTestsPanel");
            }
            if (source == btnPresc) {
                cardLayout.show(cardPanel, "PrescriptionsPanel");
            }
            if (source == btnNursComm) {
                cardLayout.show(cardPanel, "NursingCommentsPanel");
            }
        }
    }

}