package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicalDoctorFrame extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;

    public CardLayout cardLayout;
    public JPanel cardPanel;
    public LoginPanel pnLogin;
  //  public GettingStartedPanel pnGetStart;
    public SearchPanel pnSearch;
    public DoctorMedicalMain pnDoctorMedical;
    public NurseMedicalMain pnNursingMedical;
    public JPanel pnNorthFiller;

    public JMenu mainMenu;
    public JMenuBar menuBar;
    public JMenuItem loginMenuItem;
   // public JMenuItem gettingStartedMenuItem;
    public JMenuItem doctorMedicalMenuItem;
    public JMenuItem nursingMedicalMenuItem;
    public JMenuItem searchMenuItem;
    public JMenuItem logoutMenuItem;
    public JMenuItem exitMenuItem;

    public MedicalDoctorFrame() {

        super("Main Menu System");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanels();
       // setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        buildMenuBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void buildPanels() {


        cardPanel = new JPanel();
        pnLogin = new LoginPanel();
//        pnGetStart = new GettingStartedPanel();
        pnSearch = new SearchPanel();
        pnDoctorMedical = new DoctorMedicalMain();
        pnNursingMedical = new NurseMedicalMain();

        pnNorthFiller = new JPanel();
        pnNorthFiller.add(new JLabel("Select From the Menu"));


        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(pnLogin, "LoginPanel");
 //       cardPanel.add(pnGetStart, "GettingStartedPanel");
        cardPanel.add(pnSearch,"SearchPanel");
        cardPanel.add(pnDoctorMedical,"DoctorMedicalMain");
        cardPanel.add(pnNursingMedical,"NursingMedicalMain");

        add(cardPanel, BorderLayout.CENTER);
        add(pnNorthFiller, BorderLayout.NORTH);

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

     //   gettingStartedMenuItem = new JMenuItem("Getting Started");
       // gettingStartedMenuItem.addActionListener(new MenuListener());

        searchMenuItem = new JMenuItem("Search Records");
        searchMenuItem.addActionListener(new MenuListener());

        doctorMedicalMenuItem = new JMenuItem("Doctor Medical Main");
        doctorMedicalMenuItem.addActionListener(new MenuListener());
        nursingMedicalMenuItem = new JMenuItem("Nursing Medical Main");
        nursingMedicalMenuItem.addActionListener(new MenuListener());

        logoutMenuItem = new JMenuItem("Logout");
        loginMenuItem.addActionListener(new MenuListener());

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new MenuListener());

        mainMenu.add(loginMenuItem);
     //   mainMenu.add(gettingStartedMenuItem);
        mainMenu.add(searchMenuItem);
        mainMenu.add(doctorMedicalMenuItem);
        mainMenu.add(nursingMedicalMenuItem);

        mainMenu.add(exitMenuItem);

    }


    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == loginMenuItem) {
                cardLayout.show(cardPanel, "LoginPanel");
            }
         //   if (source == gettingStartedMenuItem) {
           //     cardLayout.show(cardPanel, "GettingStartedPanel");
           // }
            if (source == searchMenuItem) {
                cardLayout.show(cardPanel, "SearchPanel");
            }

            if (source == doctorMedicalMenuItem) {
                cardLayout.show(cardPanel, "DoctorMedicalMain");
            }
            if (source == nursingMedicalMenuItem) {
                cardLayout.show(cardPanel, "NursingMedicalMain");
            }
            if (source == logoutMenuItem) {
                cardLayout.show(cardPanel, "LoginPanel");
            }
            if (source == exitMenuItem) {
                System.exit(0);
            }
        }
    }
}
