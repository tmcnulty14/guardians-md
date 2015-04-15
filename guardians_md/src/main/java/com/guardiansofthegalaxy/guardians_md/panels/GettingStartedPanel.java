package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageButton;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;

import javax.swing.*;
import java.awt.*;

public class GettingStartedPanel extends JPanel {


    public JPanel pnNorth, pnCenter, pnWest, pnSouth;
    public JLabel lblWelcome, lblSelectActionsOrMainMenu;
    public ImageButton btnUserAccountInformation, btnPatientRegistration, btnSearchRecords;

    public GettingStartedPanel(){

        setLayout(new BorderLayout());

        pnNorth = new JPanel(new GridLayout(2,1));
        pnNorth.setBackground(Color.WHITE);
        lblWelcome = new JLabel("Welcome to the GotG Medical Record MainFrame");
        lblWelcome.setFont(new Font("Times New Roman", 0, 40));
        lblWelcome.setPreferredSize(new Dimension(200,100));

        lblSelectActionsOrMainMenu = new JLabel("Select from the options in the Main Menu dropdown list of by the " +
                "short cut buttons on the side display.");
        lblSelectActionsOrMainMenu.setFont(new Font("Times New Roman", 0, 16));
        lblSelectActionsOrMainMenu.setPreferredSize(new Dimension(200,100));

        pnNorth.add(lblWelcome);
        pnNorth.add(lblSelectActionsOrMainMenu);

        pnWest = new JPanel();
        pnWest.setBackground(Color.WHITE);
        pnWest.setLayout(new GridLayout(3,1));

        btnUserAccountInformation = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("profileImage.jpg"),100,100);
        btnUserAccountInformation.setFont(new Font("Times New Roman", 0, 16));
        btnUserAccountInformation.setPreferredSize(new Dimension(200,100));

        btnPatientRegistration = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("register_button.png"),112,100);
        btnPatientRegistration.setFont(new Font("Times New Roman", 0, 16));
        btnUserAccountInformation.setPreferredSize(new Dimension(200,100));

        btnSearchRecords = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("blueButton.jpg"),100,100);
        btnSearchRecords.setFont(new Font("Times New Roman", 0, 16));
        btnUserAccountInformation.setPreferredSize(new Dimension(200,100));

        //TODO: quick lookup by id for patient
        pnWest.add(btnUserAccountInformation);
        pnWest.add(btnPatientRegistration);
        pnWest.add(btnSearchRecords);

        add(pnNorth, BorderLayout.NORTH);
        add(pnWest, BorderLayout.WEST);

        setBackground(Color.WHITE);

    }
}
