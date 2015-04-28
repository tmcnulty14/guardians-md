package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageButton;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;
import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GettingStartedPanel extends JPanel {


    public JPanel pnNorth, pnCenter, pnEast, pnOption1, pnOption2, pnOption3, pnOption4;
    public JLabel lblWelcome, lblSelectActionsOrMainMenu, lblRecentList, lblUserInfo, lblPatientReg, lblCreateVisit, lblSearchRecs;
    public ImageButton btnUserAccountInformation, btnPatientRegistration, btnCreateVisit, btnSearchRecords;
    public JList recentList;
    public JScrollPane scrollPane;
    public JButton btnRefreshRecent, btnViewRecent;

    public ArrayList<Visit> recentVisits;

    public DatabaseConnection dbc;

    public GettingStartedPanel(DatabaseConnection dbc){
        this.dbc = dbc;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        pnNorth = new JPanel(new GridLayout(2,1));
        pnNorth.setBackground(Color.WHITE);
        lblWelcome = new JLabel("Welcome to Medical Doctor", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Times New Roman", 0, 40));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblSelectActionsOrMainMenu = new JLabel("Select from the options in the Main Menu dropdown list or by the " +
                "short cut buttons on the side display.", SwingConstants.CENTER);
        lblSelectActionsOrMainMenu.setFont(new Font("Times New Roman", 0, 16));
        lblSelectActionsOrMainMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnNorth.add(lblWelcome);
        pnNorth.add(lblSelectActionsOrMainMenu);

        pnCenter = new JPanel();
        pnCenter.setBackground(Color.WHITE);
        pnCenter.setLayout(new GridLayout(4, 1));
        pnCenter.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        pnCenter.setPreferredSize(new Dimension(250, 500));

        btnUserAccountInformation = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("profileImage.jpg"),100,100);
        btnUserAccountInformation.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));

        btnPatientRegistration = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("register_button.png"),112,100);
        btnPatientRegistration.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));

        btnSearchRecords = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("blueButton.jpg"),100,100);
        btnSearchRecords.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));

        btnCreateVisit = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("edit-blue (1).png"),100,101);
        btnCreateVisit.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
        btnCreateVisit.setVisible(false);

        lblUserInfo = new JLabel("User Information", SwingConstants.CENTER);
        lblUserInfo.setFont(new Font("DejaVu Serif", 0, 18));

        lblPatientReg = new JLabel("Patient Registration", SwingConstants.CENTER);
        lblPatientReg.setFont(new Font("DejaVu Serif", 0, 18));

        lblSearchRecs = new JLabel("Search Records", SwingConstants.CENTER);
        lblSearchRecs.setFont(new Font("DejaVu Serif", 0, 18));

        lblCreateVisit = new JLabel("Create New Visit", SwingConstants.CENTER);
        lblCreateVisit.setFont(new Font("DejaVu Serif", 0, 18));
        lblCreateVisit.setVisible(false);

        pnOption1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnOption1.setBackground(Color.WHITE);
        pnOption1.setPreferredSize(new Dimension(150, 150));
        pnOption1.add(btnUserAccountInformation);
        pnOption1.add(lblUserInfo);

        pnOption2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnOption2.setBackground(Color.WHITE);
        pnOption2.setPreferredSize(new Dimension(150, 150));
        pnOption2.add(btnPatientRegistration);
        pnOption2.add(lblPatientReg);

        pnOption3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnOption3.setBackground(Color.WHITE);
        pnOption3.setPreferredSize(new Dimension(150, 150));
        pnOption3.add(btnSearchRecords);
        pnOption3.add(lblSearchRecs);

        pnOption4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnOption4.setBackground(Color.WHITE);
        pnOption4.setPreferredSize(new Dimension(150, 150));
        pnOption4.add(btnCreateVisit);
        pnOption4.add(lblCreateVisit);

        pnCenter.add(pnOption1);
        pnCenter.add(pnOption2);
        pnCenter.add(pnOption3);
        pnCenter.add(pnOption4);

        buildEastPanel();

        add(pnNorth, BorderLayout.NORTH);
        add(pnCenter, BorderLayout.CENTER);
        add(pnEast, BorderLayout.EAST);
    }

    private void buildEastPanel() {
        lblRecentList = new JLabel("Recent Visits:");
        lblRecentList.setFont(new Font("DejaVu Serif", Font.ITALIC, 14));
        lblRecentList.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        btnRefreshRecent = new JButton("Refresh");
        btnRefreshRecent.setFont(new Font("DejaVu Serif", Font.ITALIC, 12));
        btnRefreshRecent.addActionListener(new RefreshListener());

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(Color.WHITE);
        northPanel.add(lblRecentList, BorderLayout.WEST);
        northPanel.add(btnRefreshRecent, BorderLayout.EAST);

        recentList = new JList();
        recentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recentList.setVisibleRowCount(10);
        recentList.addListSelectionListener(new SelectedListener());

        scrollPane = new JScrollPane(recentList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnViewRecent = new JButton("View");
        btnViewRecent.setFont(new Font("DejaVu Serif", 0, 16));
        btnViewRecent.setEnabled(false);

        JPanel pnViewButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnViewButton.setBackground(Color.WHITE);
        pnViewButton.add(btnViewRecent);

        refreshRecentList();

        pnEast = new JPanel(new BorderLayout());
        pnEast.setBackground(Color.WHITE);
        pnEast.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnEast.setPreferredSize(new Dimension(500, 500));

        pnEast.add(northPanel, BorderLayout.NORTH);
        pnEast.add(scrollPane, BorderLayout.CENTER);
        pnEast.add(pnViewButton, BorderLayout.SOUTH);
    }

    public void refreshRecentList() {
        recentVisits = dbc.getRecentVisits();

        String[] visitStrings = new String[recentVisits.size()];

        for (int i = visitStrings.length - 1, j = 0; i >= 0; i--, j++) {
            Visit currentV = recentVisits.get(i);
            Patient currentP = dbc.getPatient(currentV.getPatientID());

            String visitStr = currentP.getFirstName() + " " + currentP.getLastName() + ", " + currentV.getDate();

            visitStrings[j] = visitStr;
        }

        recentList.setListData(visitStrings);
    }

    public int getRecentIndex() {
        return recentVisits.size() - recentList.getSelectedIndex() - 1;
    }

    private class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshRecentList();
        }
    }

    private class SelectedListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            btnViewRecent.setEnabled((recentList.isSelectionEmpty()) ? false : true);
        }
    }
}
