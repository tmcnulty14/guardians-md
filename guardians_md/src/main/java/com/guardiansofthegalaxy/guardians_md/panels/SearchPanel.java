package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.DbConn;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.tuples.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SearchPanel extends JPanel {
    private CardLayout mainCardLayout, resultCardLayout;
    private JPanel mainCardPanel, searchPanel, northPanel, centerPanel, southPanel, resultCardPanel, resultPanel, backPanel;
    private PatientInformationPanel patientResultPanel;
    private DoctorMedicalMain doctorResultPanel;
    private NurseMedicalMain nurseResultPanel;

    private int index;
    private JLabel searchForLabel, searchByLabel, keywordLabel, resultsLabel, failedLabel;
    public JComboBox searchFor, searchBy;
    private JTextField searchTerm;
    private JButton submit, back, newVisit, viewResult;
    public JButton delete;
    private JScrollPane scrollResults;
    private JList results;

    private String[] searchForOptions = {"Patients", "Visits"}, patientStrings, visitStrings;
    private ArrayList<String> searchByPatients, searchByVisits;
    private ArrayList<Patient> patientResults;
    private ArrayList<Visit> visitResults;

    private DbConn database;

    public SearchPanel(DbConn database) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Search")));
        setBackground(Color.WHITE);

        // Initialize as 0 in case user keeps default search for patients selection
        index = 0;
        this.database = database;
        patientResults = new ArrayList<>();
        visitResults = new ArrayList<>();

        searchByPatients = new ArrayList<>(Arrays.asList(database.getPatientSearchTypes()));
        searchByVisits = new ArrayList<>(Arrays.asList(database.getVisitSearchTypes()));

        // Allow visits to be searched by patient info
        searchByVisits.addAll(searchByPatients);

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
        searchBy = new JComboBox(searchByPatients.toArray());

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

        failedLabel = new JLabel("Search returned 0 records.", SwingConstants.CENTER);
        failedLabel.setFont(new Font("Times New Roman", 0, 16));
        failedLabel.setForeground(Color.RED);
        failedLabel.setVisible(false);

        JPanel centerLabels = new JPanel(new GridLayout(2, 1));
        centerLabels.setBackground(Color.WHITE);

        centerLabels.add(resultsLabel);
        centerLabels.add(failedLabel);

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

    /**
    * 
    */
    private void buildSouthPanel() {
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.WHITE);

        newVisit = new JButton("New Visit");
        newVisit.setFont(new Font("DejaVu Serif", 0, 16));
        newVisit.addActionListener(new NewVisitListener());
        newVisit.setVisible(false);
        newVisit.setEnabled(false);

        viewResult = new JButton("View");
        viewResult.setFont(new Font("DejaVu Serif", 0, 16));
        viewResult.addActionListener(new ViewListener());
        viewResult.setEnabled(false);

        delete = new JButton("Delete");
        delete.setFont(new Font("DejaVu Serif", 0, 16));
        delete.addActionListener(new DeleteListener());
        delete.setVisible(false);
        delete.setEnabled(false);

        southPanel.add(newVisit);
        southPanel.add(viewResult);
        southPanel.add(delete);
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

        patientResultPanel = new PatientInformationPanel(database);
        patientResultPanel.btnSubmitPatientData.setVisible(true);
        doctorResultPanel = new DoctorMedicalMain(database);
        nurseResultPanel = new NurseMedicalMain(database);

        resultCardPanel.add(doctorResultPanel, "doctorResultPanel");
        resultCardPanel.add(nurseResultPanel, "nurseResultPanel");
        resultCardPanel.add(patientResultPanel, "patientResultPanel");

        resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(Color.WHITE);

        resultPanel.add(backPanel, BorderLayout.NORTH);
        resultPanel.add(resultCardPanel, BorderLayout.CENTER);
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
     * Sets the search-by list of options in the search panel based on what the user is searching for.
     */
    private class SearchForListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            index = searchFor.getSelectedIndex();

            if (index == 0) {
                searchBy.setModel(new DefaultComboBoxModel(searchByPatients.toArray()));
            }
            else if (index == 1) {
                searchBy.setModel(new DefaultComboBoxModel(searchByVisits.toArray()));
            }
        }
    }

    /**
     * Sets the search results to the correct array list of results based on what is being searched for,
     * patients or visits. Displays results of search performed in the JList object.
     */
    private class SubmitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                String currentSearchBy = searchBy.getSelectedItem().toString();

                if (index == 0) {
                    patientResults = database.findPatient(searchTerm.getText(), currentSearchBy);

                    if (patientResults.size() == 0) {
                        failedLabel.setVisible(true);
                        viewResult.setEnabled(false);
                        newVisit.setEnabled(false);
                        delete.setEnabled(false);
                    }
                    else {
                        failedLabel.setVisible(false);
                        viewResult.setEnabled(true);

                        if (MedicalConfigurator.getLoginUser().hasDoctorPrivileges()) {
                            newVisit.setVisible(true);
                            newVisit.setEnabled(true);
                            delete.setEnabled(true);
                        }
                    }

                    setPatientStringResults();
                    results.setListData(patientStrings);
                }
                else if (index == 1) {

                    // Check if search by is a patient attribute, and use it to search for patients, then their visits
                    if (searchByPatients.contains(currentSearchBy)) {
                        patientResults = database.findPatient(searchTerm.getText(), currentSearchBy);

                        visitResults.clear();

                        for (int i = 0, len = patientResults.size(); i < len; i++) {
                            visitResults.addAll(database.findVisit(Integer.toString(patientResults.get(i).getPatientID()), "patient_id"));
                        }
                    }
                    else {
                        visitResults = database.findVisit(searchTerm.getText(), currentSearchBy);
                    }

                    newVisit.setVisible(false);

                    if (visitResults.size() == 0) {
                        failedLabel.setVisible(true);
                        viewResult.setEnabled(false);
                        delete.setEnabled(false);
                    }
                    else {
                        failedLabel.setVisible(false);
                        viewResult.setEnabled(true);

                        if (MedicalConfigurator.getLoginUser().hasDoctorPrivileges()) {
                            delete.setEnabled(true);
                        }
                    }

                    setVisitStringResults();
                    results.setListData(visitStrings);
                }

                // Select first search result by default if list is not empty
                if (results.getModel().getSize() > 0)
                    results.setSelectedIndex(0);
                scrollResults.setViewportView(results);
            }
            catch(NullPointerException npe){
                npe.printStackTrace();
            }
        }
    }

    private class NewVisitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Patient selectedPatient = patientResults.get(results.getSelectedIndex());
            MedicalConfigurator.setActivePatient(selectedPatient);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            MedicalConfigurator.setActiveVisit(new Visit(
                selectedPatient.getPatientID(),
                MedicalConfigurator.getLoginUser().getUsername(),
                dateFormat.format(new Date()),
                new ArrayList<String>()));

            mainCardLayout.show(mainCardPanel, "resultPanel");

            doctorResultPanel.pnPat.loadPatientInformation();
            doctorResultPanel.pnGenPract.loadGeneralPracticeInformation();
            doctorResultPanel.pnLabTests.loadLabTestInformation();
            doctorResultPanel.pnPresc.loadPrescriptionsInformation();
            doctorResultPanel.pnNursComm.loadNursingComments();
            doctorResultPanel.pnLabTests.setCurrentLabOrders();

            resultCardLayout.show(resultCardPanel, "doctorResultPanel");
        }
    }

    /**
     * Makes either a Patient or Visit object containing all the information from the database
     * about that patient/visit.
     */
    private class ViewListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (index == 0) {
                Patient selectedPatient = patientResults.get(results.getSelectedIndex());
                MedicalConfigurator.setActivePatient(selectedPatient);

                patientResultPanel.loadPatientInformation();

                mainCardLayout.show(mainCardPanel, "resultPanel");
                resultCardLayout.show(resultCardPanel, "patientResultPanel");
            }
            else if (index == 1) {
                Visit selectedVisit = visitResults.get(results.getSelectedIndex());
                MedicalConfigurator.setActiveVisit(selectedVisit);

                mainCardLayout.show(mainCardPanel, "resultPanel");

                if (MedicalConfigurator.getLoginUser().hasDoctorPrivileges()) {
                    doctorResultPanel.pnPat.loadPatientInformation();
                    doctorResultPanel.pnGenPract.loadGeneralPracticeInformation();
                    doctorResultPanel.pnLabTests.loadLabTestInformation();
                    doctorResultPanel.pnPresc.loadPrescriptionsInformation();
                    doctorResultPanel.pnNursComm.loadNursingComments();
                    doctorResultPanel.pnLabTests.setCurrentLabOrders();

                    resultCardLayout.show(resultCardPanel, "doctorResultPanel");
                }
                else {
                    nurseResultPanel.pnPat.loadPatientInformation();
                    nurseResultPanel.pnGenPract.loadGeneralPracticeInformation();
                    nurseResultPanel.pnLabTests.loadLabTestInformation();
                    nurseResultPanel.pnPresc.loadPrescriptionsInformation();
                    nurseResultPanel.pnNursComm.loadNursingComments();

                    resultCardLayout.show(resultCardPanel, "nurseResultPanel");
                }
            }
        }
    }

    private class DeleteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            if (JOptionPane.showConfirmDialog(null, "Are you sure? Deleting this record CANNOT be undone.", "Confirm deletion",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                boolean success = false;;

                if (index == 0) {
                    success = database.deletePatient(patientResults.get(results.getSelectedIndex()).getPatientID());
                }
                else {
                    success = database.deleteVisit(visitResults.get(results.getSelectedIndex()).getVisitID());
                }

                if (!success) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Please check your network connection and try again.",
                        "Unsuccessful record deletion", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    reset();
                }
            }
        }
    }

    private class BackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            mainCardLayout.show(mainCardPanel, "searchPanel");
        }
    }

    public void reset() {
        mainCardLayout.show(mainCardPanel, "searchPanel");
        searchFor.setSelectedIndex(0);
        index = 0;
        searchBy.setModel(new DefaultComboBoxModel(searchByPatients.toArray()));
        searchTerm.setText("");
        failedLabel.setVisible(false);
        results.setListData(new String[0]);
        viewResult.setEnabled(false);
        newVisit.setVisible(false);
        delete.setEnabled(false);
    }
}
