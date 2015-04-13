package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class SearchPanel extends JPanel {
    private CardLayout mainCardLayout, resultCardLayout;
    private JPanel mainCardPanel, searchPanel, northPanel, centerPanel, southPanel, resultCardPanel, resultPanel, backPanel;
    private PatientInformationPanel patientResultPanel;
    private DoctorMedicalMain doctorResultPanel;
    private NurseMedicalMain nurseResultPanel;

    private int index;
    private JLabel searchForLabel, searchByLabel, keywordLabel, resultsLabel, statusLabel;
    private JComboBox searchFor, searchBy;
    private JTextField searchTerm;
    private JButton submit, viewResult, back;
    private JScrollPane scrollResults;
    private JList results;

    private String[] searchForOptions = {"Patients", "Visits"}, searchByPatients, searchByVisits, patientStrings, visitStrings;
    private ArrayList<Patient> patientResults;
    private ArrayList<Visit> visitResults;

    private DbConnDummy database;

    public SearchPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Search"));
        setBackground(Color.WHITE);

        // Initialize as 0 in case user keeps default searchFor selection
        index = 0;
        database = new DbConnDummy();

        searchByPatients = database.getPatientSearchTypes();
        searchByVisits = database.getVisitSearchTypes();

        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();
        buildResultPanel();

        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);

        searchPanel.add(northPanel, BorderLayout.NORTH);
        searchPanel.add(centerPanel, BorderLayout.CENTER);
        searchPanel.add(southPanel, BorderLayout.SOUTH);

        mainCardLayout = new CardLayout();
        mainCardPanel = new JPanel(mainCardLayout);

        mainCardPanel.add(searchPanel, "searchPanel");
        mainCardPanel.add(resultPanel, "resultPanel");

        add(mainCardPanel, BorderLayout.CENTER);
    }

    private void buildNorthPanel() {
        searchForLabel = new JLabel("Search For", SwingConstants.CENTER);
        searchForLabel.setFont(new Font("Times New Roman", 0, 16));

        searchByLabel = new JLabel("Search By", SwingConstants.CENTER);
        searchByLabel.setFont(new Font("Times New Roman", 0, 16));

        keywordLabel = new JLabel("Keyword", SwingConstants.CENTER);
        keywordLabel.setFont(new Font("Times New Roman", 0, 16));

        searchFor = new JComboBox(searchForOptions);
        searchFor.addActionListener(new SearchForListener());

        // Initialize with patient search options because search for "Patients" will be selected by default
        searchBy = new JComboBox(searchByPatients);

        searchTerm = new JTextField(10);
        searchTerm.setFont(new Font("Times New Roman", 0, 16));

        submit = new JButton("Search");
        submit.setFont(new Font("DejaVu Serif", 0, 16));
        submit.addActionListener(new SubmitListener());

        northPanel = new JPanel(new GridLayout(2, 4));
        northPanel.setBackground(Color.WHITE);

        northPanel.add(searchForLabel);
        northPanel.add(searchByLabel);
        northPanel.add(keywordLabel);
        northPanel.add(new JLabel(""));
        northPanel.add(searchFor);
        northPanel.add(searchBy);
        northPanel.add(searchTerm);
        northPanel.add(submit);
    }

    private void buildCenterPanel() {
        resultsLabel = new JLabel("Results - Select a record then click View.",
            SwingConstants.CENTER);
        resultsLabel.setFont(new Font("Times New Roman", 0, 16));

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Times New Roman", 0, 16));
        statusLabel.setForeground(Color.RED);

        JPanel centerLabels = new JPanel(new GridLayout(2, 1));
        centerLabels.setBackground(Color.WHITE);

        centerLabels.add(resultsLabel);
        centerLabels.add(statusLabel);

        results = new JList(new String[0]);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        results.setVisibleRowCount(10);

        scrollResults = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        centerPanel.add(centerLabels, BorderLayout.NORTH);
        centerPanel.add(scrollResults, BorderLayout.CENTER);
    }

    private void buildSouthPanel() {
        viewResult = new JButton("View");
        viewResult.setFont(new Font("DejaVu Serif", 0, 16));
        viewResult.addActionListener(new ViewListener());

        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.WHITE);

        southPanel.add(viewResult);
    }

    private void buildResultPanel() {
        back = new JButton("Back to Search");
        back.setFont(new Font("DejaVu Serif", 0, 16));
        back.addActionListener(new BackListener());

        backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(Color.WHITE);

        backPanel.add(back);

        resultCardLayout = new CardLayout();
        resultCardPanel = new JPanel(resultCardLayout);

        patientResultPanel = new PatientInformationPanel();
        doctorResultPanel = new DoctorMedicalMain();
        nurseResultPanel = new NurseMedicalMain();

        resultCardPanel.add(doctorResultPanel, "doctorResultPanel");
        resultCardPanel.add(nurseResultPanel, "nurseResultPanel");
        resultCardPanel.add(patientResultPanel, "patientResultPanel");

        resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(Color.WHITE);

        resultPanel.add(backPanel, BorderLayout.NORTH);
        resultPanel.add(resultCardPanel, BorderLayout.CENTER);
    }

    /**
     * Displays results of search performed in the JList object.
     */
    private void showResults() {

        if (index == 0) {
            
            if (patientResults.size() == 0)
                statusLabel.setText("Search returned 0 records.");
            else {
                statusLabel.setText("");
                results.setListData(patientStrings);
            }
        }
        else if (index == 1) {

            if (visitResults.size() == 0)
                statusLabel.setText("Search returned 0 records.");
            else {
                statusLabel.setText("");
                results.setListData(visitStrings);
            }
        }

        scrollResults.setViewportView(results);
    }

    private void setPatientStringResults() {
        patientStrings = new String[patientResults.size()];

        for (int i = 0, len = patientStrings.length; i < len; i++) {
            patientStrings[i] = patientResults.get(i).toString();
        }
    }

    private void setVisitStringResults() {
        visitStrings = new String[visitResults.size()];

        for (int i = 0, len = visitStrings.length; i < len; i++) {
            visitStrings[i] = visitResults.get(i).toString();
        }
    }

    /**
     * 
     */
    private class SearchForListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            index = searchFor.getSelectedIndex();

            if (index == 0) {
                searchBy.setModel(new DefaultComboBoxModel(searchByPatients));
            }
            else if (index == 1) {
                searchBy.setModel(new DefaultComboBoxModel(searchByVisits));
            }
        }
    }

    /**
     * 
     */
    private class SubmitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                if (index == 0) {
                    patientResults = (ArrayList<Patient>) database.findPatient(searchTerm.getText(), (String) searchBy.getSelectedItem());
                }
                else if (index == 1) {
                    visitResults = (ArrayList<Visit>) database.findVisit(searchTerm.getText(), (String) searchBy.getSelectedItem());
                }
                showResults();

            }catch(NullPointerException npe){
                npe.printStackTrace();
            }

        }
    }

    /**
     * Makes either a Patient or Visit object containing all the information from the database
     * about that patient/visit.
     */
    private class ViewListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (index == 0) {
                //Patient selectedPatient = patientResults.get(results.getSelectedIndex());

                //MedicalConfigurator.setActivePatient(selectedPatient);

                // TODO remove this, for testing only
                patientResultPanel.txtFName.setText("Sara");
                patientResultPanel.txtLName.setText("Hakkoum");
                patientResultPanel.txtAddress1.setText("100 State St.");
                patientResultPanel.txtCity.setText("Framingham");
                patientResultPanel.txtState.setText("MA");
                patientResultPanel.txtZip.setText("01702");
                patientResultPanel.txtCountry.setText("USA");
                patientResultPanel.txtBirthDate.setText("02/24/1995");
                patientResultPanel.txtInsProv.setText("MassHealth");
                patientResultPanel.txtInsNum.setText("100");

                mainCardLayout.show(mainCardPanel, "resultPanel");
                // MEETING change to resultCardPanel to show what search result will look like once everything works
                resultCardLayout.show(resultCardPanel, "patientResultPanel");
            }
            else if (index == 1) {
                Visit selectedVisit = visitResults.get(results.getSelectedIndex());

                MedicalConfigurator.setActiveVisit(selectedVisit);

                // TODO search for patient using patient id in visit object
                //MedicalConfigurator.setActivePatient();

                // TODO switch to appropriate panel depending on hasDoctorPrivileges of User from getLoginUser in Configurator
            }
        }
    }

    private class BackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            mainCardLayout.show(mainCardPanel, "searchPanel");
        }
    }
}
