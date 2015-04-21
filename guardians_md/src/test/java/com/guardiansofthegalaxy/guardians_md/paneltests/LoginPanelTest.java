package com.guardiansofthegalaxy.guardians_md.paneltests;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.DatabaseConnection;
import com.guardiansofthegalaxy.guardians_md.panels.LoginPanel;

import javax.swing.*;

/**
 * Created by Christina on 4/9/2015.
 */
public class LoginPanelTest extends JFrame {

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;
    public LoginPanel loginPanel;

    public LoginPanelTest(String frameTitle)
    {
        super(frameTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanels();

        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(false);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildPanels(){

        loginPanel = new LoginPanel(new DatabaseConnection());
        add(loginPanel);

    }

    public static void main(String[] args){

        MedicalConfigurator.setActivePatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

        LoginPanelTest main = new LoginPanelTest("Login");
        main.setVisible(true);

    }
}
