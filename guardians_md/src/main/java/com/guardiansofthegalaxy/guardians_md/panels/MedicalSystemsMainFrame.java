package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.User;

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
    public SearchPanel pnSearch;
    public DoctorMedicalMain pnDoctorMedical;
    public NurseMedicalMain pnNursingMedical;

    public JMenu mainMenu;
    public JMenuBar menuBar;
    public JMenuItem loginMenuItem;
    public JMenuItem gettingStartedMenuItem;
    public JMenuItem doctorMedicalMenuItem;
    public JMenuItem nursingMedicalMenuItem;
    public JMenuItem searchMenuItem;
    public JMenuItem logoutMenuItem;
    public JMenuItem exitMenuItem;

    public MedicalSystemsMainFrame() {

        super("Medical Doctor Main Menu System");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

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
                Object source = e.getSource();
                if(source == pnUnivHeader.btnReturnMain){
                    cardLayout.show(cardPanel,"GettingStartedPanel");
                }
            }
        });

        pnLogin = new LoginPanel();
        pnLogin.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = pnLogin.usernameField.getText();
                String password = new String(pnLogin.passwordField.getPassword());

                //TODO: this is where login check needs to happen
                //boolean valid = dbConnection.validateLogin(username, password);
                //if(valid) {
                //User activeUser = dbConnection.getLoginUser(username);
                User activeUser = new User("medicalDoctor", true);

                activeUser.setFirstName("Christina");
                MedicalConfigurator.setLoginUser(activeUser);
                MedicalConfigurator.setUserLoggedIn(true);
                pnUnivHeader.setHeaderIfUserLoggedIn();
                loginMenuItem.setVisible(false);
                gettingStartedMenuItem.setVisible(true);
                searchMenuItem.setVisible(true);
                doctorMedicalMenuItem.setVisible(true);
                nursingMedicalMenuItem.setVisible(true);
                logoutMenuItem.setVisible(true);
                pnLogin.usernameField.setText("");
                pnLogin.passwordField.setText("");
                cardLayout.show(cardPanel, "GettingStartedPanel");
                pnUnivHeader.setHeaderIfUserLoggedIn();
                pnUnivHeader.btnReturnMain.setVisible(false);



                //} else {
                //  JOptionPane.showMessageDialog(null, "Please check your username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                //	}
            }
        });
        pnGetStart = new GettingStartedPanel();
        pnGetStart.btnUserAccountInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                //TODO: call the user registration panel with fields populated
                if(source == pnGetStart.btnUserAccountInformation){
                    cardLayout.show(cardPanel,"DoctorMedicalMain");
                    pnUnivHeader.setHeaderIfUserLoggedIn();

                }
            }
        });

        pnGetStart.btnPatientRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                //TODO: call the user registration panel with fields populated
                if(source == pnGetStart.btnPatientRegistration){
                    cardLayout.show(cardPanel,"NursingMedicalMain");
                    pnUnivHeader.setHeaderIfUserLoggedIn();

                }
            }
        });

        pnGetStart.btnSearchRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                //TODO: call the user registration panel with fields populated
                if(source == pnGetStart.btnSearchRecords){
                    cardLayout.show(cardPanel,"SearchPanel");
                    pnUnivHeader.setHeaderIfUserLoggedIn();

                }
            }
        });
        pnSearch = new SearchPanel();
        pnDoctorMedical = new DoctorMedicalMain();
        pnNursingMedical = new NurseMedicalMain();


        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(pnLogin, "LoginPanel");
        cardPanel.add(pnGetStart, "GettingStartedPanel");
        cardPanel.add(pnSearch, "SearchPanel");
        cardPanel.add(pnDoctorMedical, "DoctorMedicalMain");
        cardPanel.add(pnNursingMedical, "NursingMedicalMain");

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

        doctorMedicalMenuItem = new JMenuItem("Doctor Medical Main");
        doctorMedicalMenuItem.addActionListener(new MenuListener());
        nursingMedicalMenuItem = new JMenuItem("Nursing Medical Main");
        nursingMedicalMenuItem.addActionListener(new MenuListener());

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
        mainMenu.add(doctorMedicalMenuItem);
        doctorMedicalMenuItem.setVisible(false);
        mainMenu.add(nursingMedicalMenuItem);
        nursingMedicalMenuItem.setVisible(false);
        mainMenu.add(logoutMenuItem);
        logoutMenuItem.setVisible(false);
        mainMenu.add(exitMenuItem);

    }


    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == loginMenuItem & !MedicalConfigurator.isUserLoggedIn) {
                cardLayout.show(cardPanel, "LoginPanel");
                pnUnivHeader.setHeaderIfUserLoggedIn();

            }
            if (source == gettingStartedMenuItem &  MedicalConfigurator.isUserLoggedIn) {
                cardLayout.show(cardPanel, "GettingStartedPanel");
                pnUnivHeader.setHeaderIfUserLoggedIn();
                pnUnivHeader.btnReturnMain.setVisible(false);
            }
            if (source == searchMenuItem & MedicalConfigurator.isUserLoggedIn)

            {
                cardLayout.show(cardPanel, "SearchPanel");
                pnUnivHeader.setHeaderIfUserLoggedIn();

            }

            if (source == doctorMedicalMenuItem & MedicalConfigurator.isUserLoggedIn)

            {
                cardLayout.show(cardPanel, "DoctorMedicalMain");
                pnUnivHeader.setHeaderIfUserLoggedIn();

            }

            if (source == nursingMedicalMenuItem & MedicalConfigurator.isUserLoggedIn)

            {
                cardLayout.show(cardPanel, "NursingMedicalMain");
                pnUnivHeader.setHeaderIfUserLoggedIn();

            }

            if (source == logoutMenuItem & MedicalConfigurator.isUserLoggedIn)

            {
                MedicalConfigurator.setUserLoggedIn(false);
                MedicalConfigurator.resetActiveUserToNull();
                pnUnivHeader.setHeaderIfUserLoggedIn();
                cardLayout.show(cardPanel, "LoginPanel");
            }

            if (source == exitMenuItem)

            {
                System.exit(0);
            }
        }
    }
}
