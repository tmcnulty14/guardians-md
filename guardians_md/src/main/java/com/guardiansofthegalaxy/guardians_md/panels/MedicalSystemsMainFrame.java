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
import com.guardiansofthegalaxy.guardians_md.tuples.User;
import com.guardiansofthegalaxy.guardians_md.tuples.Patient;
import com.guardiansofthegalaxy.guardians_md.db.DatabaseConnection;
import com.guardiansofthegalaxy.guardians_md.db.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The main frame, responsible for holding all panels through the Main Medical Panel. images related to patient visits
// are the only thing not displayed via this panel. 
public class MedicalSystemsMainFrame extends JFrame {

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
    public JMenuItem userInfoMenuItem;
    public JMenuItem patientRegMenuItem;
    public JMenuItem searchMenuItem;
    public JMenuItem createVisitMenuItem;
    public JMenuItem logoutMenuItem;
    public JMenuItem exitMenuItem;

    public DbConn database;

    public MedicalSystemsMainFrame() {
        super("Medical Doctor Software");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        MedicalConfigurator.loadProperties();
        database = new DatabaseConnection();

        buildPanels();
        buildMenuBar();
        
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildPanels() {
        cardPanel = new JPanel();

        pnUnivHeader = new UniversalHeaderPanel();
        pnUnivHeader.btnReturnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    userInfoMenuItem.setVisible(true);
                    patientRegMenuItem.setVisible(true);
                    searchMenuItem.setVisible(true);
                    logoutMenuItem.setVisible(true);
                    pnLogin.usernameField.setText("");
                    pnLogin.passwordField.setText("");
                    pnUnivHeader.setHeaderIfUserLoggedIn();
                    pnUnivHeader.btnReturnMain.setVisible(false);
                    
                    pnUserReg.loadUserInformation();
                    pnUserReg.passLabel.setVisible(false);
                    pnUserReg.passwordField.setVisible(false);
                    pnUserReg.usernameField.setEditable(false);

                    pnPatientReg.clearFields();
                    pnDoctorMedical.clearFields();
                    pnNursingMedical.clearFields();

                    if (activeUser.hasDoctorPrivileges()) {
                        pnGetStart.btnCreateVisit.setVisible(true);
                        pnGetStart.lblCreateVisit.setVisible(true);
                        createVisitMenuItem.setVisible(true);
                    }
                    else {
                        pnGetStart.btnCreateVisit.setVisible(false);
                        pnGetStart.lblCreateVisit.setVisible(false);
                        createVisitMenuItem.setVisible(false);
                    }

                    cardLayout.show(cardPanel, "GettingStartedPanel");
                    setSize(1000, 950);
                    setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Please check your username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pnLogin.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnUserReg.clearFields();
                cardLayout.show(cardPanel, "UserRegPanel");
                setSize(750, 300);
                setLocationRelativeTo(null);
            }
        });

        pnGetStart = new GettingStartedPanel(database);
        pnGetStart.btnUserAccountInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnUnivHeader.btnReturnMain.setVisible(true);
                cardLayout.show(cardPanel,"UserRegPanel");
            }
        });

        pnGetStart.btnPatientRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnUnivHeader.btnReturnMain.setVisible(true);
                pnPatientReg.clearFields();
                MedicalConfigurator.setActivePatient(new Patient("", "", "", "", "", "", "", "", "", "", "", ""));

                cardLayout.show(cardPanel,"PatientInformationPanel");
            }
        });

        pnGetStart.btnCreateVisit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pnUnivHeader.btnReturnMain.setVisible(true);
                pnSearch.reset();
                pnSearch.searchFor.setEnabled(false);
                pnSearch.delete.setVisible(false);
                cardLayout.show(cardPanel, "SearchPanel");
            }
        });

        pnGetStart.btnSearchRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnSearch.reset();
                pnUnivHeader.btnReturnMain.setVisible(true);
                pnSearch.searchFor.setEnabled(true);
                pnSearch.delete.setVisible((MedicalConfigurator.getLoginUser().hasDoctorPrivileges()) ? true : false);
                cardLayout.show(cardPanel,"SearchPanel");
            }
        });

        pnGetStart.btnViewRecent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalConfigurator.setActiveVisit(database.getVisit(pnGetStart.recentVisits.get(pnGetStart.getRecentIndex()).getVisitID()));
                MedicalConfigurator.setActivePatient(pnGetStart.dbc.getPatient(MedicalConfigurator.getActiveVisit().getPatientID()));

                pnUnivHeader.btnReturnMain.setVisible(true);

                // Show appropriate panel of most recent visit of that patient
                if (MedicalConfigurator.getLoginUser().hasDoctorPrivileges()) {
                    pnDoctorMedical.clearFields();

                    pnDoctorMedical.pnPat.loadPatientInformation();
                    pnDoctorMedical.pnGenPract.loadGeneralPracticeInformation();
                    pnDoctorMedical.pnLabTests.loadLabTestInformation();
                    pnDoctorMedical.pnPresc.loadPrescriptionsInformation();
                    pnDoctorMedical.pnNursComm.loadNursingComments();

                    cardLayout.show(cardPanel, "DoctorMedicalMain");
                }
                else {
                    pnNursingMedical.clearFields();

                    pnNursingMedical.pnPat.loadPatientInformation();
                    pnNursingMedical.pnGenPract.loadGeneralPracticeInformation();
                    pnNursingMedical.pnLabTests.loadLabTestInformation();
                    pnNursingMedical.pnPresc.loadPrescriptionsInformation();
                    pnNursingMedical.pnNursComm.loadNursingComments();

                    cardLayout.show(cardPanel, "NursingMedicalMain");
                }
            }
        });

        pnUserReg = new UserRegPanel(database);
        
        pnPatientReg = new PatientInformationPanel(database);
        pnPatientReg.editPatientInformation();
        pnPatientReg.btnSubmitPatientData.setEnabled(true);
        pnPatientReg.lblAge.setVisible(false);
        pnPatientReg.txtAge.setVisible(false);
        pnPatientReg.ckEdit.setVisible(false);

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

        userInfoMenuItem = new JMenuItem("User Information");
        userInfoMenuItem.addActionListener(new MenuListener());

        patientRegMenuItem = new JMenuItem("Patient Registration");
        patientRegMenuItem.addActionListener(new MenuListener());

        searchMenuItem = new JMenuItem("Search Records");
        searchMenuItem.addActionListener(new MenuListener());

        createVisitMenuItem = new JMenuItem("Create New Visit");
        createVisitMenuItem.addActionListener(new MenuListener());

        logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(new MenuListener());

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new MenuListener());

        mainMenu.add(loginMenuItem);
        loginMenuItem.setVisible(true);
        mainMenu.add(gettingStartedMenuItem);
        gettingStartedMenuItem.setVisible(false);
        mainMenu.add(userInfoMenuItem);
        userInfoMenuItem.setVisible(false);
        mainMenu.add(patientRegMenuItem);
        patientRegMenuItem.setVisible(false);
        mainMenu.add(searchMenuItem);
        searchMenuItem.setVisible(false);
        mainMenu.add(createVisitMenuItem);
        createVisitMenuItem.setVisible(false);
        mainMenu.add(logoutMenuItem);
        logoutMenuItem.setVisible(false);
        mainMenu.add(exitMenuItem);
    }

    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == loginMenuItem & !MedicalConfigurator.isUserLoggedIn)
            {
                setSize(500, 400);
                setLocationRelativeTo(null);
                cardLayout.show(cardPanel, "LoginPanel");
            }

            else if (source == gettingStartedMenuItem &  MedicalConfigurator.isUserLoggedIn)
            {
                pnUnivHeader.btnReturnMain.setVisible(false);
                cardLayout.show(cardPanel, "GettingStartedPanel");
            }

            else if (source == userInfoMenuItem) {
                pnUnivHeader.btnReturnMain.setVisible(true);
                cardLayout.show(cardPanel, "UserRegPanel");
            }

            else if (source == patientRegMenuItem) {
                pnUnivHeader.btnReturnMain.setVisible(true);
                pnPatientReg.clearFields();
                MedicalConfigurator.setActivePatient(new Patient("", "", "", "", "", "", "", "", "", "", "", ""));
                cardLayout.show(cardPanel,"PatientInformationPanel");
            }

            else if (source == searchMenuItem & MedicalConfigurator.isUserLoggedIn)
            {
                pnSearch.reset();
                pnUnivHeader.btnReturnMain.setVisible(true);
                pnSearch.searchFor.setEnabled(true);
                cardLayout.show(cardPanel, "SearchPanel");
            }

            else if (source == createVisitMenuItem) {
                pnUnivHeader.btnReturnMain.setVisible(true);

                pnSearch.searchFor.setEnabled(false);
                cardLayout.show(cardPanel, "SearchPanel");

            }

            else if (source == logoutMenuItem & MedicalConfigurator.isUserLoggedIn)
            {
                MedicalConfigurator.setUserLoggedIn(false);
                MedicalConfigurator.resetActiveUserToNull();
                pnUnivHeader.setHeaderIfUserLoggedIn();

                loginMenuItem.setVisible(true);
                gettingStartedMenuItem.setVisible(false);
                userInfoMenuItem.setVisible(false);
                patientRegMenuItem.setVisible(false);
                searchMenuItem.setVisible(false);
                createVisitMenuItem.setVisible(false);
                logoutMenuItem.setVisible(false);

                pnUserReg.clearFields();
                pnUserReg.passLabel.setVisible(true);
                pnUserReg.passwordField.setVisible(true);
                pnUserReg.usernameField.setEditable(true);
                
                cardLayout.show(cardPanel, "LoginPanel");
                setSize(500, 400);
                setLocationRelativeTo(null);
            }

            else if (source == exitMenuItem)
            {
                System.exit(0);
            }
        }
    }
}
