package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christina on 4/9/2015.
 */
public class MainHeaderPanel extends JPanel {

    public JLabel lblUserName;
    public JButton btnLogout;

    public MainHeaderPanel() {
        lblUserName = new JLabel(" ");
        btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(80, 20));

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(lblUserName);
        add(btnLogout);
        setUserNameAndLogout();
    }

    public void setUserNameAndLogout() {
        if (!MedicalConfigurator.isUserLoggedIn) {
            lblUserName.setVisible(false);
            btnLogout.setVisible(false);
        } else {
            lblUserName.setText(MedicalConfigurator.getUser().getFirstName());
            lblUserName.setVisible(true);
            btnLogout.setVisible(true);

        }
    }
}
