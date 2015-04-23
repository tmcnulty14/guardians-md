package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.User;
import com.guardiansofthegalaxy.guardians_md.db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicalSystemsMainFrame extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;

    public CardLayout cardLayout;
    public JPanel cardPanel;
    public UniversalHeaderPanel pnUnivHeader;
    public LoginPanel pnLogin;
    public GettingStartedPanel pnGetStart;
    public UserRegPanel pnUserReg;
    public DoctorMedicalMain pnDoctorMedical;
    public NurseMedicalMain pnNursingMedical;
    public PatientInformationPanel pnPatientReg;
    public SearchPanel pnSearch;

    public JMenu mainMenu;
    public JMenuBar menuBar;
    public JMenuItem loginMenuItem;
    public JMenuItem gettingStartedMenuItem;
    public JMenuItem searchMenuItem;
    public JMenuItem logoutMenuItem;
    public JMenuItem exitMenuItem;

    // TODO add menu items for patient reg, user info, and creating a visit

    public DatabaseConnection database;

    public MedicalSystemsMainFrame() {

        super("Medical Doctor Main Menu System");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        database = new DatabaseConnection();

        buildPanels();
        // setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        buildMenuBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void buildPanels() {
        cardPanel = new JPanel();

        pnUnivHeader = new UniversalHeaderPanel();
        pnUnivHeader.btnReturnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnGetStart.refreshRecentList();
                pnUnivHeader.btnReturnMain.setVisible(false);
                cardLayout.show(cardPanel,"GettingStartedPanel");
            }
        });

        pnLogin = new LoginPanel(database);
        pnLogin.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = pnLogin.usernameField.getText();
                String password = new String(pnLogin.passwordField.getPassword());

                boolean valid = database.validateLogin(username, password);

                if(valid) {
                    User activeUser = database.getUser(username); 
                    MedicalConfigurator.setLoginUser(activeUser);
                    MedicalConfigurator.setUserLoggedIn(true);
                    loginMenuItem.setVisible(false);
                    gettingStartedMenuItem.setVisible(true);
                    searchMenuItem.setVisible(true);
                    logoutMenuItem.setVisible(true);
                    pnLogin.usernameField.setText("");
                    pnLogin.passwordField.setText("");
                    pnUnivHeader.setHeaderIfUserLoggedIn();
                    pnUnivHeader.btnReturnMain.setVisible(false);

                    if (activeUser.hasDoctorPrivileges()) {
                        pnGetStart.btnCreateVisit.setVisible(true);
                        pnGetStart.lblCreateVisit.setVisible(true);
                    }
                    else {
                        pnGetStart.btnCreateVisit.setVisible(false);
                        pnGetStart.lblCreateVisit.setVisible(false);
                    }

                    cardLayout.show(cardPanel, "GettingStartedPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "Please check your username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pnLogin.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "UserRegPanel");
            }
        });

        pnGetStart = new GettingStartedPanel();
        pnGetStart.btnUserAccountInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: call the user registration panel with fields populated
                pnUnivHeader.btnReturnMain.setVisible(true);
                cardLayout.show(cardPanel,"UserRegPanel");
            }
        });

        pnGetStart.btnPatientRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnUnivHeader.btnReturnMain.setVisible(true);
                cardLayout.show(cardPanel,"PatientInformationPanel");
            }
        });

        pnGetStart.btnCreateVisit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnUnivHeader.btnReturnMain.setVisible(true);

                if (MedicalConfigurator.getLoginUser().hasDoctorPrivileges())
                    cardLayout.show(cardPanel, "DoctorMedicalMain");
                else
                    cardLayout.show(cardPanel, "NurseMedicalMain");
            }
        });

        pnGetStart.btnSearchRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnSearch.reset();
                pnUnivHeader.btnReturnMain.setVisible(true);
                cardLayout.show(cardPanel,"SearchPanel");
            }
        });

        pnGetStart.btnViewRecent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalConfigurator.setActiveVisit(pnGetStart.recentVisits.get(pnGetStart.recentList.getSelectedIndex()));
                MedicalConfigurator.setActivePatient(pnGetStart.dbc.getPatient(MedicalConfigurator.getActiveVisit().getPatientID()));

                pnUnivHeader.btnReturnMain.setVisible(true);

                // Show appropriate panel of most recent visit of that patient
                if (MedicalConfigurator.getLoginUser().hasDoctorPrivileges()) {
                    pnDoctorMedical.pnPat.loadPatientInformation();
                    pnDoctorMedical.pnGenPract.loadGeneralPracticeInformation();
                    pnDoctorMedical.pnLabTests.loadLabTestInformation();
                    pnDoctorMedical.pnPresc.loadPrescriptionsInformation();
                    pnDoctorMedical.pnNursComm.loadNursingComments();

                    cardLayout.show(cardPanel, "DoctorMedicalMain");
                }
                else {
                    pnNursingMedical.pnPat.loadPatientInformation();
                    pnNursingMedical.pnGenPract.loadGeneralPracticeInformation();
                    pnNursingMedical.pnLabTests.loadLabTestInformation();
                    pnNursingMedical.pnPresc.loadPrescriptionsInformation();
                    pnNursingMedical.pnNursComm.loadNursingComments();

                    cardLayout.show(cardPanel, "NursingMedicalMain");
                }
            }
        });

        pnUserReg = new UserRegPanel();
        pnPatientReg = new PatientInformationPanel();
        pnPatientReg.btnSubmitPatientData.setVisible(true);
        pnSearch = new SearchPanel(database);
        pnDoctorMedical = new DoctorMedicalMain(database);
        pnNursingMedical = new NurseMedicalMain(database);

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(pnLogin, "LoginPanel");
        cardPanel.add(pnGetStart, "GettingStartedPanel");
        cardPanel.add(pnUserReg, "UserRegPanel");
        cardPanel.add(pnDoctorMedical, "DoctorMedicalMain");
        cardPanel.add(pnNursingMedical, "NursingMedicalMain");
        cardPanel.add(pnPatientReg,"PatientInformationPanel");
        cardPanel.add(pnSearch, "SearchPanel");

        add(pnUnivHeader,BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    private void buildMenuBar() {
        menuBar = new JMenuBar();

        mainMenu = new JMenu("Main Menu");

        buildMenu();

        menuBar.add(mainMenu);

        setJMenuBar(menuBar);
    }

    private void buildMenu() {
        loginMenuItem = new JMenuItem("Login");
        loginMenuItem.addActionListener(new MenuListener());

        gettingStartedMenuItem = new JMenuItem("Getting Started");
        gettingStartedMenuItem.addActionListener(new MenuListener());

        searchMenuItem = new JMenuItem("Search Records");
        searchMenuItem.addActionListener(new MenuListener());

        logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(new MenuListener());

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new MenuListener());

        mainMenu.add(loginMenuItem);
        loginMenuItem.setVisible(true);
        mainMenu.add(gettingStartedMenuItem);
        gettingStartedMenuItem.setVisible(false);
        mainMenu.add(searchMenuItem);
        searchMenuItem.setVisible(false);
        mainMenu.add(logoutMenuItem);
        logoutMenuItem.setVisible(false);
        mainMenu.add(exitMenuItem);
    }

    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == loginMenuItem & !MedicalConfigurator.isUserLoggedIn)
            {
                cardLayout.show(cardPanel, "LoginPanel");
            }

            else if (source == gettingStartedMenuItem &  MedicalConfigurator.isUserLoggedIn)
            {
                pnUnivHeader.btnReturnMain.setVisible(false);
                pnGetStart.refreshRecentList();
                cardLayout.show(cardPanel, "GettingStartedPanel");
            }

            else if (source == searchMenuItem & MedicalConfigurator.isUserLoggedIn)
            {
                pnSearch.reset();
                pnUnivHeader.btnReturnMain.setVisible(true);
                cardLayout.show(cardPanel, "SearchPanel");
            }

            else if (source == logoutMenuItem & MedicalConfigurator.isUserLoggedIn)
            {
                MedicalConfigurator.setUserLoggedIn(false);
                MedicalConfigurator.resetActiveUserToNull();
                pnUnivHeader.setHeaderIfUserLoggedIn();

                loginMenuItem.setVisible(true);
                gettingStartedMenuItem.setVisible(false);
                searchMenuItem.setVisible(false);
                logoutMenuItem.setVisible(false);
                
                cardLayout.show(cardPanel, "LoginPanel");
            }

            else if (source == exitMenuItem)
            {
                System.exit(0);
            }
        }
    }
}
