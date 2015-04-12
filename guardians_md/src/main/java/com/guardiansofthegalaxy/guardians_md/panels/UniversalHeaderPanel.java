package com.guardiansofthegalaxy.guardians_md.panels;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;

import javax.swing.*;
import java.awt.*;


public class UniversalHeaderPanel extends JPanel {

    public JPanel pnMain, pnUser;
    public JLabel lblUserName;
    public JButton btnReturnMain;

    public UniversalHeaderPanel() {

        pnMain = new JPanel();
        pnMain.setBackground(Color.WHITE);
        pnMain.setLayout(new FlowLayout(FlowLayout.LEFT));

        pnUser = new JPanel();
        pnUser.setBackground(Color.WHITE);
        pnUser.setLayout(new FlowLayout(FlowLayout.RIGHT));

        lblUserName = new JLabel(" ");
        lblUserName.setPreferredSize(new Dimension(300,50));
        lblUserName.setFont(new Font("DejaVu Serif", 0, 16));

        btnReturnMain = new JButton("Return to Main Menu");

        btnReturnMain.setFont(new Font("DejaVu Serif", 0, 16));
        btnReturnMain.setPreferredSize(new Dimension(250, 30));


        pnUser.add(lblUserName);
        setHeaderIfUserLoggedIn();

        pnMain.add(btnReturnMain);

        setLayout(new GridLayout(1,2));
        setBackground(Color.WHITE);
        add(pnMain);
        add(pnUser);
    }

    public void setHeaderIfUserLoggedIn() {
        if (!MedicalConfigurator.isUserLoggedIn) {
            lblUserName.setVisible(false);
            btnReturnMain.setVisible(false);
        } else {
            lblUserName.setText("         User logged in: " + MedicalConfigurator.getLoginUser().getFirstName());
            lblUserName.setVisible(true);
            btnReturnMain.setVisible(true);
        }
    }
}
