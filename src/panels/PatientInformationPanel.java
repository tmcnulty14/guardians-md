package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class PatientInformationPanel extends JPanel  {

	//labels and textfields for the patient personal information
	public JLabel lblFName, lblLName, lblAddress1, lblAddress2, lblCity, lblState, lblZip, lblCountry, lblBirthDate, lblAge, lblInsProv, lblInsNum;
	public JTextField txtFName, txtLName, txtAddress1, txtAddress2, txtCity, txtState, txtZip, txtCountry, txtBirthDate, txtAge, txtInsProv, txtInsNum;
	public JCheckBox ckEdit;

	public void build(){

		setLayout(new GridLayout(7,4));
		setBorder(BorderFactory.createTitledBorder("Patient Information"));

		lblFName = new JLabel("First Name");
		lblFName.setFont(new Font("Times New Roman", 0, 16));
		txtFName = new JTextField(10);
		txtFName.setEditable(false);
		txtFName.setFont(new Font("Times New Roman", 0, 16));

		lblLName = new JLabel("Last Name");
		lblLName.setFont(new Font("Times New Roman", 0, 16));
		txtLName = new JTextField(10);
		txtLName.setEditable(false);
		txtLName.setFont(new Font("Times New Roman", 0, 16));

		lblAddress1 = new JLabel("Street Address 1");
		lblAddress1.setFont(new Font("Times New Roman", 0, 16));
		txtAddress1 = new JTextField(10);
		txtAddress1.setEditable(false);
		txtAddress1.setFont(new Font("Times New Roman", 0, 16));

		lblAddress2 = new JLabel("Street Address 2");
		lblAddress2.setFont(new Font("Times New Roman", 0, 16));
		txtAddress2 = new JTextField(10);
		txtAddress2.setEditable(false);
		txtAddress2.setFont(new Font("Times New Roman", 0, 16));

		lblCity = new JLabel("City");
		lblCity.setFont(new Font("Times New Roman", 0, 16));
		txtCity = new JTextField(10);
		txtCity.setEditable(false);
		txtCity.setFont(new Font("Times New Roman", 0, 16));

		lblState = new JLabel("State");
		lblState.setFont(new Font("Times New Roman", 0, 16));
		txtState = new JTextField(10);
		txtState.setEditable(false);
		txtState.setFont(new Font("Times New Roman", 0, 16));

		lblZip = new JLabel("Zip Code");
		lblZip.setFont(new Font("Times New Roman", 0, 16));
		txtZip = new JTextField(10);
		txtZip.setEditable(false);
		txtZip.setFont(new Font("Times New Roman", 0, 16));

		lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Times New Roman", 0, 16));
		txtCountry = new JTextField(10);
		txtCountry.setEditable(false);
		txtCountry.setFont(new Font("Times New Roman", 0, 16));

		lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setFont(new Font("Times New Roman", 0, 16));
		txtBirthDate = new JTextField(10);
		txtBirthDate.setEditable(false);
		txtBirthDate.setFont(new Font("Times New Roman", 0, 16));

		lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Times New Roman", 0, 16));
		txtAge = new JTextField(10);
		txtAge.setEditable(false);
		txtAge.setFont(new Font("Times New Roman", 0, 16));

		lblInsProv = new JLabel("Insurance Provider");
		lblInsProv.setFont(new Font("Times New Roman", 0, 16));
		txtInsProv = new JTextField(10);
		txtInsProv.setEditable(false);
		txtInsProv.setFont(new Font("Times New Roman", 0, 16));

		lblInsNum = new JLabel("Insurance Policy Number");
		lblInsNum.setFont(new Font("Times New Roman", 0, 16));
		txtInsNum = new JTextField(10);
		txtInsNum.setEditable(false);
		txtInsNum.setFont(new Font("Times New Roman", 0, 16));

		ckEdit = new JCheckBox("Edit Patient Information");
		ckEdit.setFont(new Font("Times New Roman", 0, 16));
		ckEdit.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object source = e.getSource();
				if(source == ckEdit & ckEdit.isSelected()){
					editPatientInformation();
				}
				if(source == ckEdit & !ckEdit.isSelected()){
					readOnlyPatientInformation();
				}
		}});

		add(lblFName);
		add(txtFName);
		add(lblLName);
		add(txtLName);

		add(lblAddress1);
		add(txtAddress1);
		add(lblAddress2);
		add(txtAddress2);

		add(lblCity);
		add(txtCity);
		add(lblState);
		add(txtState);

		add(lblZip);
		add(txtZip);
		add(lblCountry);
		add(txtCountry);

		add(lblBirthDate);
		add(txtBirthDate);
		add(lblAge);
		add(txtAge);

		add(lblInsProv);
		add(txtInsProv);
		add(lblInsNum);
		add(txtInsNum);

		add(ckEdit);

		}

		public void editPatientInformation(){
			txtFName.setEditable(true);
			txtLName.setEditable(true);
			txtAddress1.setEditable(true);
			txtAddress2.setEditable(true);
			txtCity.setEditable(true);
			txtState.setEditable(true);
			txtZip.setEditable(true);
			txtCountry.setEditable(true);
			txtBirthDate.setEditable(true);
			txtAge.setEditable(true);
			txtInsProv.setEditable(true);
			txtInsNum.setEditable(true);
		}

		public void readOnlyPatientInformation(){
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

		}

}