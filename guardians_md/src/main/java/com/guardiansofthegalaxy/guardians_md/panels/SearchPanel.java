package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class SearchPanel extends JPanel {
    private JPanel northPanel, centerPanel, southPanel;

    private int index;
    private JLabel searchForLabel, searchByLabel, keywordLabel, resultsLabel, instructLabel;
    private JComboBox searchFor, searchBy;
    private JTextField searchTerm;
    private JButton submit, viewResult;
    private JScrollPane scrollResults;
    private JList results;

    private String[] searchForOptions = {"Patients", "Visits"}, searchByPatients, searchByVisits;
    private List patientResults;
    private List visitResults;

    private DbConnDummy database;

    public SearchPanel() {

        // Temporary size setting for testing purposes
        //setPreferredSize(new Dimension(400, 500));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Search"));
        setBackground(Color.WHITE);

        // Initialize as 0 in case user keeps default searchFor selection
        index = 0;
        database = new DbConnDummy();

        // TODO Will need to updated with the actual database connection class' name
        searchByPatients = database.getPatientSearchTypes();
        searchByVisits = database.getVisitSearchTypes();

        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void buildNorthPanel() {
        searchForLabel = new JLabel("Search For", SwingConstants.CENTER);
        searchForLabel.setFont(new Font("Times New Roman", 0, 16));

        searchByLabel = new JLabel("Search By", SwingConstants.CENTER);
        searchByLabel.setFont(new Font("Times New Roman", 0, 16));

        keywordLabel = new JLabel("Keyword", SwingConstants.CENTER);
        keywordLabel.setFont(new Font("Times New Roman", 0, 16));

      //  searchFor = new JComboBox(searchForOptions);
        searchFor = new JComboBox();
        searchFor.addActionListener(new SearchForListener());

        // Initialize with patient search options because search for "Patients" will be selected by default
        searchBy = new JComboBox();
       // searchBy = new JComboBox(searchByPatients);

        searchTerm = new JTextField(10);
        searchTerm.setFont(new Font("Times New Roman", 0, 16));

        submit = new JButton("Go");
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
        resultsLabel = new JLabel("Results", SwingConstants.CENTER);
        resultsLabel.setFont(new Font("Times New Roman", 0, 16));

        results = new JList(new String[0]);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        results.setVisibleRowCount(10);

        scrollResults = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        centerPanel.add(resultsLabel, BorderLayout.NORTH);
        centerPanel.add(scrollResults, BorderLayout.CENTER);
    }

    private void buildSouthPanel() {
        instructLabel = new JLabel("Select a result from above and click view for more details.", SwingConstants.CENTER);
        instructLabel.setFont(new Font("Times New Roman", 0, 16));

        viewResult = new JButton("View");
        viewResult.setFont(new Font("DejaVu Serif", 0, 16));
        viewResult.addActionListener(new ViewListener());
        // TODO Center this

        southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.WHITE);

        southPanel.add(instructLabel, BorderLayout.NORTH);
        southPanel.add(viewResult, BorderLayout.CENTER);
    }

    /**
     * Displays results of search performed in the JList object.
     * TODO Need to make sure that it displays them correctly, using their toString() methods.
     * And test that setViewportView() changes the ScrollPane contents.
     */
    private void showResults() {

        if (index == 0) {
            results.setListData(patientResults.getItems());
        } else if (index == 1) {
            results.setListData(visitResults.getItems());
        }

        scrollResults.setViewportView(results);
    }

    /**
     * TODO Need to test to make sure using setModel() changes the options of the searchBy combobox.
     */
    private class SearchForListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            index = searchFor.getSelectedIndex();

            // toArray() used because ComboBoxes only accept arrays for options
            if (index == 0) {
                searchBy.setModel(new DefaultComboBoxModel(searchByPatients));
            } else if (index == 1) {
                searchBy.setModel(new DefaultComboBoxModel(searchByVisits));
            }
        }
    }

    /**
     * This listener also needs to be updated as above.
     * TODO Need to test to make sure getSelectedItem() returns the correct String object.
     */
    private class SubmitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                if (index == 0) {
                    patientResults = (List) database.findPatient(searchTerm.getText(), searchBy.getSelectedItem().toString());
                } else if (index == 1) {
                    visitResults = (List) database.findVisit(searchTerm.getText(), searchBy.getSelectedItem().toString());
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
                //Patient selectedPatient = patientResults.getItem(results.getSelectedIndex());
                String selectedPatientString = patientResults.getItem(results.getSelectedIndex());

                // TODO Generate panel for patient information here and display it...
                // Should the search panel have its own cardlayout so that the main frame won't have worry about displaying results here?
            } else if (index == 1) {
               // Visit selectedVisit = visitResults.get(results.getSelectedIndex());
                String selectedVisit = visitResults.getItem(results.getSelectedIndex());

                // TODO Same as above...
                // How to create one of the panels using this info from the database?
            }
        }
    }
}
