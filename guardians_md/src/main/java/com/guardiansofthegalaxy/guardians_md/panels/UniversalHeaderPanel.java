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

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageButton;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;

import javax.swing.*;
import java.awt.*;

//Universal head used through program. contains currently logged in user and home button
public class UniversalHeaderPanel extends JPanel {

    public JPanel pnMain, pnUser;
    public JLabel lblUserName;
    public ImageButton btnReturnMain;

    public UniversalHeaderPanel() {

        pnMain = new JPanel();
        pnMain.setBackground(Color.WHITE);
        pnMain.setLayout(new FlowLayout(FlowLayout.LEFT));

        pnUser = new JPanel();
        pnUser.setBackground(Color.WHITE);
        pnUser.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnUser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lblUserName = new JLabel(" ");
        lblUserName.setFont(new Font("DejaVu Serif", 0, 16));

        btnReturnMain = new ImageButton(true, ConfigDirectory.getImageFileFromDirectory("Home-button.png"),60,60);
        btnReturnMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        pnUser.add(lblUserName);
        setHeaderIfUserLoggedIn();

        pnMain.add(btnReturnMain);

        setLayout(new GridLayout(1,2));
        setBackground(Color.WHITE);
        add(pnMain);
        add(pnUser);
    }

	// Head is displayed if a user is logged in
    public void setHeaderIfUserLoggedIn() {
        if (!MedicalConfigurator.isUserLoggedIn) {
            lblUserName.setVisible(false);
            btnReturnMain.setVisible(false);
        } else {
            lblUserName.setText("         User logged in: " + MedicalConfigurator.getLoginUser().getFirstName() + " " + MedicalConfigurator.getLoginUser().getLastName());
            lblUserName.setVisible(true);
            btnReturnMain.setVisible(true);
        }
    }
}
