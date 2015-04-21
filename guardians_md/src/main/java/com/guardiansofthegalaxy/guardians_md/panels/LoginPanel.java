package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

// Login Button and Register button action listeners is in MedicalSystemsMainFrame. 
// Also need to work on design of login panel (make it pretty!)

public class LoginPanel extends JPanel {
    private final DbConn dbConnection;

    public JButton loginButton, registerButton;
    public JTextField usernameField;
    public JPasswordField passwordField;

    public LoginPanel(DbConn connection) {
        this.dbConnection = connection;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new JLabel("Welcome to the GotG Medical Record Software."), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(200, 200, 200, 200), new EtchedBorder()));
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
        registerPanel.setLayout(new BorderLayout());
        registerPanel.add(new JLabel("Don't have a user account?"), BorderLayout.NORTH);
        registerButton = new JButton("Register a new user");
        
        registerPanel.add(registerButton, BorderLayout.SOUTH);
        add(registerPanel, BorderLayout.SOUTH);
    }
}

