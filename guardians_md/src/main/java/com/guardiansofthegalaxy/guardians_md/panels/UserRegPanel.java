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

import com.guardiansofthegalaxy.guardians_md.db.DbConn;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.tuples.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// creates User registration panel. capable of adding or updating users in the database.
public class UserRegPanel extends JPanel {
    private JButton enter;
    private JPanel fieldsPanel, buttonsPanel;
    public JTextField fNameField, lNameField, pagerNumberField, positionField, passwordField, usernameField;
    public JLabel fnLabel, lnLabel, pagerLabel, posLabel, specialtyLabel, passLabel, usernameLabel;
    private JRadioButton nurse, doctor;
    private ButtonGroup specGroup;
    public boolean isDoctor;
    private final DbConn regConn;

    public UserRegPanel(DbConn dbc) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(2, Color.green, Color.yellow));
        setBackground(Color.WHITE);

        regConn = dbc;

        BuildFields();
        BuildButtons();

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

	// builds text fields and nurse/doctor buttons
    public void BuildFields() {
        fieldsPanel = new JPanel();
        fieldsPanel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("User Registration"), new EmptyBorder(5, 5, 5, 5)));
        fieldsPanel.setLayout(new GridLayout(4, 4, 20, 0));
        fieldsPanel.setBackground(Color.WHITE);

        fNameField = new JTextField(10);
        lNameField = new JTextField(10);
        pagerNumberField = new JTextField(10);
        positionField = new JTextField(10);
        usernameField = new JTextField(10);

        passwordField = new JPasswordField(10);

        fnLabel = new JLabel("First Name: ");
        fnLabel.setFont(new Font("Times New Roman", 0, 16));
        lnLabel = new JLabel("Last Name: ");
        lnLabel.setFont(new Font("Times New Roman", 0, 16));
        pagerLabel = new JLabel("Pager Number: ");
        pagerLabel.setFont(new Font("Times New Roman", 0, 16));
        posLabel = new JLabel("Speciality: ");
        posLabel.setFont(new Font("Times New Roman", 0, 16));
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Times New Roman", 0, 16));
        passLabel = new JLabel("Password: ");
        passLabel.setFont(new Font("Times New Roman", 0, 16));

        specialtyLabel = new JLabel("Position");
        specialtyLabel.setFont(new Font("Times New Roman", 0, 16));

        nurse = new JRadioButton("Nurse");
        nurse.setFont(new Font("DejaVu Serif", 0, 16));
        nurse.setBackground(Color.WHITE);
        doctor = new JRadioButton("Doctor");
        doctor.setFont(new Font("DejaVu Serif", 0, 16));
        doctor.setBackground(Color.WHITE);

        specGroup = new ButtonGroup();
        specGroup.add(nurse);
        specGroup.add(doctor);

        JLabel space = new JLabel(" ");

        fieldsPanel.add(fnLabel);
        fieldsPanel.add(fNameField);
        fieldsPanel.add(lnLabel);
        fieldsPanel.add(lNameField);

        fieldsPanel.add(specialtyLabel);
        fieldsPanel.add(nurse);
        fieldsPanel.add(doctor);
        fieldsPanel.add(space);

        fieldsPanel.add(pagerLabel);
        fieldsPanel.add(pagerNumberField);
        fieldsPanel.add(posLabel);
        fieldsPanel.add(positionField);

        fieldsPanel.add(usernameLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passLabel);
        fieldsPanel.add(passwordField);
    }

	// builds enter button and assigns it a button listener 
    public void BuildButtons() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);

        enter = new JButton("Enter");
        enter.setFont(new Font("DejaVu Serif", 0, 16));
        enter.addActionListener(new userRegButtonListener());

        buttonsPanel.add(enter);
    }

	// loads current logged on Users information into fields for update
    public void loadUserInformation() {
        User currentUser = MedicalConfigurator.getLoginUser();

        fNameField.setText(currentUser.getFirstName());
        lNameField.setText(currentUser.getLastName());
        pagerNumberField.setText(currentUser.getPagerNumber());
        positionField.setText(currentUser.getSpecialty());
        usernameField.setText(currentUser.getUsername());

        if (currentUser.hasDoctorPrivileges())
            doctor.setSelected(true);
        else
            nurse.setSelected(true);
    }

	// clears all fields, resets nurse / doctor
    public void clearFields() {
        fNameField.setText("");
        lNameField.setText("");
        pagerNumberField.setText("");
        positionField.setText("");
        usernameField.setText("");
        passwordField.setText("");

        specGroup.clearSelection();
    }

	// finds out whether user is a nurse or doctor based off selection
    public void getRole() {
        if (nurse.isSelected()) {
            isDoctor = false;
        } else if (doctor.isSelected()) {
            isDoctor = true;
        }
    }

	// connects to database. uses User object to pass information either for update or new User creation.
    private class userRegButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getRole();

            User newUser = new User(usernameField.getText(), fNameField.getText(), lNameField.getText(), positionField.getText(), pagerNumberField.getText(), isDoctor);
            String username = newUser.getUsername();
            String password = passwordField.getText();

            if (MedicalConfigurator.isUserLoggedIn()) {
                if (regConn.updateUser(newUser))
                    JOptionPane.showMessageDialog(null, "User has been updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Unexpected error. User was not updated. Please try again or contact a system administrator.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (regConn.checkUsernameAvailable(newUser.getUsername()) == true) {
                if (username == null || username.length() < 5) {
                    JOptionPane.showMessageDialog(null, "Usernames must be at least 5 characters", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password == null || password.length() < 6) {
                    JOptionPane.showMessageDialog(null, "Passwords must be at least 6 characters", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    regConn.registerUser(newUser, passwordField.getText());
                    if (regConn.validateLogin(newUser.getUsername(), passwordField.getText()) == true) {
                        JOptionPane.showMessageDialog(null, "User has been Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Unexpected error, User was not registered. Please try again or contact a system administrator", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username is already taken, Please try another", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
