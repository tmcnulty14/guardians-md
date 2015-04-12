package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginPanel extends JPanel {
    private final DbConnDummy dbConnection;

    public JButton loginButton, registerButton;
    public JTextField usernameField;
    public JPasswordField passwordField;

    public LoginPanel() {
        this.dbConnection = new DbConnDummy();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new JLabel("Welcome to the GotG Medical Record Software."), BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout());
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2, 2));
        entryPanel.setBackground(Color.WHITE);
        entryPanel.setBorder(BorderFactory.createTitledBorder("Please enter your username and password to login."));
        entryPanel.add(new JLabel("Username: "));
        usernameField = new JTextField(24);
        entryPanel.add(usernameField);
        entryPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField(24);
        entryPanel.add(passwordField);
        centerPanel.add(entryPanel, BorderLayout.CENTER);
        loginButton = new JButton("Login");

        centerPanel.add(loginButton, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setLayout(new GridLayout(2, 1));
        registerPanel.add(new JLabel("Don't have a user account?"));
        registerButton = new JButton("Register a new user");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainFrame.registerNewUser();
            }
        });
        registerPanel.add(registerButton);
        add(registerPanel, BorderLayout.SOUTH);
    }
}

