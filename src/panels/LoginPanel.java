package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import db.*;

public class LoginPanel extends JPanel {
	private DbConn dbConnection;
	private MedicalMainFrame mainFrame;

	private JButton loginButton, registerButton;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public LoginPanel(DbConn dbConnection, MedicalMainFrame mainFrame) {
		this.dbConnection = dbConnection;
		this.mainFrame = mainFrame;

		setLayout(new BorderLayout());

		add(new JLabel("Welcome to the GotG Medical Record Software."), BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		JPanel entryPanel = new JPanel();
		entryPanel.setLayout(new GridLayout(2,2));
		entryPanel.setBorder(BorderFactory.createTitledBorder("Please enter your username and password to login."));
		entryPanel.add(new JLabel("Username: "));
		usernameField = new JTextField(24);
		entryPanel.add(usernameField);
		entryPanel.add(new JLabel("Password: "));
		passwordField = new JPasswordField(24);
		entryPanel.add(passwordField);
		centerPanel.add(entryPanel, BorderLayout.CENTER);
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				boolean valid = dbConnection.validateLogin(username, password);
				if(valid) {
					User activeUser = dbConnection.getUser(username);
					mainFrame.userLoggedIn(activeUser);
				} else {
					JOptionPane.showMessageDialog(null, "Please check your username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		centerPanel.add(loginButton, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);

		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new GridLayout(2, 1));
		registerPanel.add(new JLabel("Don't have a user account?"));
		registerButton = new JButton("Register a new user");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.registerNewUser();
			}
		});
		registerPanel.add(registerButton);
		add(registerPanel, BorderLayout.SOUTH);
	}
}

