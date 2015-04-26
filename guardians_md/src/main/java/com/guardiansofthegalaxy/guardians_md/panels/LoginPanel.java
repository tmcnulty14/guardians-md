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
    private JLabel welcome;
    private JPanel pnUserField, pnPassField, pnLoginButton, pnRegButton;

    public LoginPanel(DbConn connection) {
        this.dbConnection = connection;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(500, 500));

        welcome = new JLabel("Welcome to Medical Doctor Software.", SwingConstants.CENTER);
        welcome.setFont(new Font("Times New Roman", 0, 20));

        add(welcome, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        centerPanel.setLayout(new BorderLayout());

        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2, 2, 5, 10));
        entryPanel.setBackground(Color.WHITE);
        entryPanel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Please enter your username and password to login."), new EmptyBorder(5, 5, 5, 5)));

        pnUserField = new JPanel(new BorderLayout());
        pnUserField.setBackground(Color.WHITE);
        usernameField = new JTextField(24);
        pnUserField.add(usernameField, BorderLayout.CENTER);

        entryPanel.add(new JLabel("Username: ", SwingConstants.CENTER));
        entryPanel.add(pnUserField);

        pnPassField = new JPanel(new BorderLayout());
        pnPassField.setBackground(Color.WHITE);
        passwordField = new JPasswordField(24);
        pnPassField.add(passwordField, BorderLayout.CENTER);

        entryPanel.add(new JLabel("Password: ", SwingConstants.CENTER));
        entryPanel.add(pnPassField);

        centerPanel.add(entryPanel, BorderLayout.CENTER);

        loginButton = new JButton("Login");

        pnLoginButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnLoginButton.setBackground(Color.WHITE);
        pnLoginButton.add(loginButton);

        centerPanel.add(pnLoginButton, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setLayout(new BorderLayout());
        registerPanel.add(new JLabel("Don't have a user account?", SwingConstants.CENTER), BorderLayout.NORTH);
        
        registerButton = new JButton("Register a new user");
        
        pnRegButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnRegButton.setBackground(Color.WHITE);
        pnRegButton.add(registerButton);

        registerPanel.add(pnRegButton, BorderLayout.SOUTH);
        add(registerPanel, BorderLayout.SOUTH);
    }
}

