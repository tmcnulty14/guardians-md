package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.DatabaseConnection;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class PatientInformationPanel extends JPanel {

    private Patient patient;

    //labels and textfields for the patient personal information
    public JLabel lblFName, lblLName, lblAddress1, lblAddress2, lblCity, lblState, lblZip, lblCountry, lblBirthDate, lblAge, lblInsProv, lblInsNum, lblGender;
    public JTextField txtFName, txtLName, txtAddress1, txtAddress2, txtCity, txtState, txtZip, txtCountry, txtBirthDate, txtAge, txtInsProv, txtInsNum;
    public JCheckBox ckEdit;
    public JRadioButton rbMale, rbFemale;
    public ButtonGroup buttonGroup;
    public JButton btnSubmitPatientData;

    public JPanel pnName, pnAddress, pnGender, pnEdit;

    public DatabaseConnection dbc;

    public PatientInformationPanel(DatabaseConnection dbc) {
        this.dbc = dbc;
        setLayout(new GridLayout(4, 1));
        setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Patient Information"), new EmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);

        pnName = new JPanel();
        pnName.setBackground(Color.WHITE);
        pnName.setLayout(new GridLayout(2, 4, 10, 10));
        pnAddress = new JPanel();
        pnAddress.setBackground(Color.WHITE);
        pnAddress.setLayout(new GridLayout(4, 4, 10, 10));
        pnGender = new JPanel();
        pnGender.setBackground(Color.WHITE);
        pnGender.setLayout(new GridLayout(1, 4));
        pnEdit = new JPanel(new BorderLayout());
        pnEdit.setBackground(Color.WHITE);

        buildComponents();

        pnName.add(lblFName);
        pnName.add(txtFName);
        pnName.add(lblLName);
        pnName.add(txtLName);

        pnName.add(lblBirthDate);
        pnName.add(txtBirthDate);
        pnName.add(lblAge);
        pnName.add(txtAge);

        pnGender.add(lblGender);
        pnGender.add(rbMale);
        pnGender.add(rbFemale);
        pnGender.add(new JLabel(""));

        pnAddress.add(lblAddress1);
        pnAddress.add(txtAddress1);
        pnAddress.add(lblAddress2);
        pnAddress.add(txtAddress2);

        pnAddress.add(lblCity);
        pnAddress.add(txtCity);
        pnAddress.add(lblState);
        pnAddress.add(txtState);

        pnAddress.add(lblZip);
        pnAddress.add(txtZip);
        pnAddress.add(lblCountry);
        pnAddress.add(txtCountry);

        pnAddress.add(lblInsProv);
        pnAddress.add(txtInsProv);
        pnAddress.add(lblInsNum);
        pnAddress.add(txtInsNum);

        JPanel fix1 = new JPanel(new BorderLayout()), fix2 = new JPanel(new BorderLayout());
        fix1.setBackground(Color.WHITE);
        fix2.setBackground(Color.WHITE);
        fix1.add(ckEdit, BorderLayout.SOUTH);
        fix2.add(btnSubmitPatientData, BorderLayout.SOUTH);

        pnEdit.add(fix1, BorderLayout.WEST);
        pnEdit.add(fix2, BorderLayout.EAST);

        add(pnName);
        add(pnGender);
        add(pnAddress);
        add(pnEdit);
    }

    public void buildComponents() {
        lblFName = new JLabel("First Name");
        lblFName.setFont(new Font("Times New Roman", 0, 16));
        txtFName = new JTextField(10);
        txtFName.setBackground(Color.WHITE);
        txtFName.setEditable(false);
        txtFName.setFont(new Font("Times New Roman", 0, 16));


        lblLName = new JLabel("Last Name");
        lblLName.setFont(new Font("Times New Roman", 0, 16));
        txtLName = new JTextField(10);
        txtLName.setEditable(false);
        txtLName.setFont(new Font("Times New Roman", 0, 16));
        txtLName.setBackground(Color.WHITE);

        lblAddress1 = new JLabel("Street Address 1");
        lblAddress1.setFont(new Font("Times New Roman", 0, 16));
        txtAddress1 = new JTextField(10);
        txtAddress1.setEditable(false);
        txtAddress1.setFont(new Font("Times New Roman", 0, 16));
        txtAddress1.setBackground(Color.WHITE);

        lblAddress2 = new JLabel("Street Address 2");
        lblAddress2.setFont(new Font("Times New Roman", 0, 16));
        txtAddress2 = new JTextField(10);
        txtAddress2.setEditable(false);
        txtAddress2.setFont(new Font("Times New Roman", 0, 16));
        txtAddress2.setBackground(Color.WHITE);

        lblCity = new JLabel("City");
        lblCity.setFont(new Font("Times New Roman", 0, 16));
        txtCity = new JTextField(10);
        txtCity.setEditable(false);
        txtCity.setFont(new Font("Times New Roman", 0, 16));
        txtCity.setBackground(Color.WHITE);

        lblState = new JLabel("State");
        lblState.setFont(new Font("Times New Roman", 0, 16));
        txtState = new JTextField(10);
        txtState.setEditable(false);
        txtState.setFont(new Font("Times New Roman", 0, 16));
        txtState.setBackground(Color.WHITE);

        lblZip = new JLabel("Zip Code");
        lblZip.setFont(new Font("Times New Roman", 0, 16));
        txtZip = new JTextField(10);
        txtZip.setEditable(false);
        txtZip.setFont(new Font("Times New Roman", 0, 16));
        txtZip.setBackground(Color.WHITE);

        lblCountry = new JLabel("Country");
        lblCountry.setFont(new Font("Times New Roman", 0, 16));
        txtCountry = new JTextField(10);
        txtCountry.setEditable(false);
        txtCountry.setFont(new Font("Times New Roman", 0, 16));
        txtCountry.setBackground(Color.WHITE);

        lblBirthDate = new JLabel("Birth Date (yyyy-mm-dd)");
        lblBirthDate.setFont(new Font("Times New Roman", 0, 16));
        txtBirthDate = new JTextField(10);
        txtBirthDate.setEditable(false);
        txtBirthDate.setFont(new Font("Times New Roman", 0, 16));
        txtBirthDate.setBackground(Color.WHITE);

        lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Times New Roman", 0, 16));
        txtAge = new JTextField(10);
        txtAge.setEditable(false);
        txtAge.setFont(new Font("Times New Roman", 0, 16));
        txtAge.setBackground(Color.WHITE);

        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Times New Roman", 0, 16));
        rbMale = new JRadioButton("Male");
        rbMale.setEnabled(false);
        rbMale.setFont(new Font("Times New Roman", 0, 16));
        rbMale.setBackground(Color.WHITE);
        rbFemale = new JRadioButton("Female");
        rbFemale.setEnabled(false);
        rbFemale.setFont(new Font("Times New Roman", 0, 16));
        rbFemale.setBackground(Color.WHITE);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rbMale);
        buttonGroup.add(rbFemale);

        lblInsProv = new JLabel("Insurance Provider");
        lblInsProv.setFont(new Font("Times New Roman", 0, 16));
        txtInsProv = new JTextField(10);
        txtInsProv.setEditable(false);
        txtInsProv.setFont(new Font("Times New Roman", 0, 16));
        txtInsProv.setBackground(Color.WHITE);

        lblInsNum = new JLabel("Insurance Policy Number");
        lblInsNum.setFont(new Font("Times New Roman", 0, 16));
        txtInsNum = new JTextField(10);
        txtInsNum.setEditable(false);
        txtInsNum.setFont(new Font("Times New Roman", 0, 16));
        txtInsNum.setBackground(Color.WHITE);

        ckEdit = new JCheckBox("Edit Patient Information");
        ckEdit.setFont(new Font("Times New Roman", 0, 16));
        ckEdit.setBackground(Color.WHITE);
        ckEdit.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getSource();
                if (source == ckEdit & ckEdit.isSelected()) {
                    editPatientInformation();
                    btnSubmitPatientData.setEnabled(true);
                }
                if (source == ckEdit & !ckEdit.isSelected()) {
                    readOnlyPatientInformation();
                    btnSubmitPatientData.setEnabled(false);
                }
            }
        });


        btnSubmitPatientData = new JButton("Submit");
        btnSubmitPatientData.setFont(new Font("DejaVu Serif", 0, 16));
        btnSubmitPatientData.setEnabled(false);
        btnSubmitPatientData.addActionListener(new SubmitPatientListener());
    }


    public void loadPatientInformation() {
        patient = MedicalConfigurator.getActivePatient();
        txtFName.setText(patient.getFirstName());
        txtLName.setText(patient.getLastName());
        txtAddress1.setText(patient.getAddress1());
        txtAddress2.setText(patient.getAddress2());
        txtCity.setText(patient.getCity());
        txtState.setText(patient.getState());
        txtZip.setText(patient.getZipcode());
        txtCountry.setText(patient.getCountry());
        txtBirthDate.setText(patient.getBirthdate());
        txtInsProv.setText(patient.getInsuranceProvider());
        txtInsNum.setText(patient.getInsuranceNumber());

        setGender();
        try {
            setAge();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

    }


    public void setGender() {
        if (patient.getGender().equalsIgnoreCase("Female")) {
            rbFemale.setSelected(true);
        }
        else if (patient.getGender().equalsIgnoreCase("Male")) {
            rbMale.setSelected(true);
        }
        else {
            buttonGroup.clearSelection();
        }
    }

    public void setAge() throws ParseException {

        String year = patient.getBirthdate().substring(0,4);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int ageInt = currentYear - Integer.parseInt(year);
        String age = String.valueOf(ageInt);
        txtAge.setText(age);
    }


    public void editPatientInformation() {
        txtFName.setEditable(true);
        txtLName.setEditable(true);
        txtAddress1.setEditable(true);
        txtAddress2.setEditable(true);
        txtCity.setEditable(true);
        txtState.setEditable(true);
        txtZip.setEditable(true);
        txtCountry.setEditable(true);
        txtBirthDate.setEditable(true);

        txtAge.setEditable(false);
        rbFemale.setEnabled(true);
        rbMale.setEnabled(true);
        txtInsProv.setEditable(true);
        txtInsNum.setEditable(true);
    }

    public void readOnlyPatientInformation() {
        txtFName.setEditable(false);
        txtLName.setEditable(false);
        txtAddress1.setEditable(false);
        txtAddress2.setEditable(false);
        txtCity.setEditable(false);
        txtState.setEditable(false);
        txtZip.setEditable(false);
        txtCountry.setEditable(false);
        txtBirthDate.setEditable(false);
        txtAge.setEditable(false);
        txtInsProv.setEditable(false);
        txtInsNum.setEditable(false);
        rbFemale.setEnabled(false);
        rbMale.setEnabled(false);

    }

    public boolean submitPatient(boolean showMessage) {
        boolean success = true;

        String gender = "";

        if (rbMale.isSelected()) {
            gender = "Male";
        } else if (rbFemale.isSelected()) {
            gender = "Female";
        }

        String fixedBirth;

        try {
            String birth = txtBirthDate.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date result = sdf.parse(birth);
            fixedBirth = sdf.format(result);
        }
        catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Please fix birthdate format.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // New patients have an ID of -1
        if (MedicalConfigurator.isNewPatient()) {

            if (!dbc.registerPatient(new Patient(
                    txtFName.getText(), txtLName.getText(),
                    fixedBirth, gender,
                    txtAddress1.getText(), txtAddress2.getText(),
                    txtCity.getText(), txtState.getText(),
                    txtZip.getText(), txtCountry.getText(),
                    txtInsProv.getText(), txtInsNum.getText()
            ))) {

                JOptionPane.showMessageDialog(null, "Could not create patient. Please check that all fields are valid and try again.",
                        "Registration failed", JOptionPane.ERROR_MESSAGE);

                success = false;
            } else {
                if (showMessage)
                JOptionPane.showMessageDialog(null, "Successfully registered patient " + txtFName.getText() +
                        " " + txtLName.getText() + ".", "Successful", JOptionPane.PLAIN_MESSAGE);

                MedicalConfigurator.setActivePatient(dbc.getPatient(dbc.getMaxPatientId()));                
            }
        }

        // Update existing patient
        else {
            Patient updatedPatient = new Patient(MedicalConfigurator.getActivePatient().getPatientID(),
                    txtFName.getText(), txtLName.getText(),
                    txtBirthDate.getText(), gender,
                    txtAddress1.getText(), txtAddress2.getText(),
                    txtCity.getText(), txtState.getText(),
                    txtZip.getText(), txtCountry.getText(),
                    txtInsProv.getText(), txtInsNum.getText());

            if (!dbc.updatePatient(updatedPatient)) {
                JOptionPane.showMessageDialog(null, "Could not update patient. Please check that all fields are valid and try again.",
                        "Update failed", JOptionPane.ERROR_MESSAGE);

                success = false;
            } else {
                if (showMessage)
                JOptionPane.showMessageDialog(null, "Successfully updated patient " + txtFName.getText() +
                        " " + txtLName.getText() + ".", "Successful", JOptionPane.PLAIN_MESSAGE);

                MedicalConfigurator.setActivePatient(updatedPatient);
            }
        }

        return success;
    }

    public void clearFields() {
        txtFName.setText("");
        txtLName.setText("");
        txtAddress1.setText("");
        txtAddress2.setText("");
        txtCity.setText("");
        txtState.setText("");
        txtZip.setText("");
        txtCountry.setText("");
        txtBirthDate.setText("");
        txtInsProv.setText("");
        txtInsNum.setText("");
        buttonGroup.clearSelection();
        ckEdit.setSelected(false);
        txtAge.setText("");
    }

    private class SubmitPatientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            submitPatient(true);
        }
    }
}