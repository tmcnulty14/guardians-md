package com.guardiansofthegalaxy.guardians_md.panels;

import javax.swing.*;
import java.awt.*;

public class GettingStartedPanel extends JPanel {

    public JButton btnUserAccountInformation, btnPatientRegistration, btnSearchRecords;

    public GettingStartedPanel(){

        btnUserAccountInformation = new JButton("User Account Information");
        btnUserAccountInformation.setFont(new Font("Times New Roman", 0, 16));
        btnUserAccountInformation.setPreferredSize(new Dimension(200,100));

        btnPatientRegistration = new JButton("Patient Registration");
        btnPatientRegistration.setFont(new Font("Times New Roman", 0, 16));
        btnUserAccountInformation.setPreferredSize(new Dimension(200,100));

        btnSearchRecords = new JButton("Search Records");
        btnSearchRecords.setFont(new Font("Times New Roman", 0, 16));
        btnUserAccountInformation.setPreferredSize(new Dimension(200,100));

        add(btnUserAccountInformation);
        add(btnPatientRegistration);
        add(btnSearchRecords);
        setBackground(Color.WHITE);

    }
}
