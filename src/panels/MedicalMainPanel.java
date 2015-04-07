package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;


public class  MedicalMainPanel extends JPanel{

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 500;

    public CardLayout cardLayout;
    public JPanel cardPanel;
	public PatientInformationPanel pnPat;
	public GeneralPracticePanel pnGenPract;
	public LabTestsPanel pnLabTests;
	public PrescriptionPanel pnPresc;
	public NursingPanel pnNursComm;
	public JPanel pnSelectButtons;
	public JButton btnPat, btnGenPract, btnLabTests, btnPresc, btnNursComm, btnSubmit, btnReturnMain;

	public JPanel pnNorthFiller, pnMainButtons;



	public MedicalMainPanel()
	{
		setLayout(new BorderLayout());
		buildPanels();
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}

	private void buildPanels(){


		cardPanel = new JPanel();
		pnPat = new PatientInformationPanel();
		pnPat.build();
		pnGenPract = new GeneralPracticePanel();
		pnGenPract.build();
		pnLabTests = new LabTestsPanel();
		pnLabTests.build();
		pnPresc = new PrescriptionPanel();
		pnPresc.build();
		pnNursComm = new NursingPanel();
		pnNursComm.build();

		buildSelectionButtonsPanel();
		buildMainButtonsPanel();
		pnNorthFiller = new JPanel();
		pnNorthFiller.add(new JLabel("Select From the Option to View or Edit Patient Information"));


		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.add(pnPat, "PatientInformationPanel");
		cardPanel.add(pnGenPract, "GeneralPracticePanel");
		cardPanel.add(pnLabTests, "LabTestsPanel");
		cardPanel.add(pnPresc, "PrescriptionsPanel");
		cardPanel.add(pnNursComm, "NursingCommentsPanel");

		add(pnSelectButtons, BorderLayout.WEST);
		add(cardPanel, BorderLayout.CENTER);
		add(pnNorthFiller, BorderLayout.NORTH);
		add(pnMainButtons, BorderLayout.SOUTH);

	}

	private void buildMainButtonsPanel(){

		pnMainButtons = new JPanel();
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new MenuListener());
		btnSubmit.setFont(new Font("DejaVu Serif", 0, 16));
		btnSubmit.setPreferredSize(new Dimension(200, 60));

		btnReturnMain = new JButton("Return to Main");
		btnReturnMain.addActionListener(new MenuListener());
		btnReturnMain.setFont(new Font("DejaVu Serif", 0, 16));
		btnReturnMain.setPreferredSize(new Dimension(200, 60));

		pnMainButtons.add(btnReturnMain);
		pnMainButtons.add(btnSubmit);

	}


	private void buildSelectionButtonsPanel(){

		pnSelectButtons = new JPanel();
		pnSelectButtons.setBorder(BorderFactory.createTitledBorder("Selection Options"));
		pnSelectButtons.setLayout(new GridLayout(5,1));

		btnPat = new JButton("Patient Information");
		btnPat.addActionListener(new MenuListener());
		btnPat.setFont(new Font("DejaVu Serif", 0, 16));


		btnGenPract = new JButton("General Practice");
		btnGenPract.addActionListener(new MenuListener());
		btnGenPract.setFont(new Font("DejaVu Serif", 0, 16));

		btnLabTests = new JButton("Labratory Tests");
		btnLabTests.addActionListener(new MenuListener());
		btnLabTests.setFont(new Font("DejaVu Serif", 0, 16));

		btnPresc = new JButton("Prescriptions");
		btnPresc.addActionListener(new MenuListener());
		btnPresc.setFont(new Font("DejaVu Serif", 0, 16));

		btnNursComm = new JButton("Nursing Comments");
		btnNursComm.addActionListener(new MenuListener());
		btnNursComm.setFont(new Font("DejaVu Serif", 0, 16));

		pnSelectButtons.add(btnPat);
		pnSelectButtons.add(btnGenPract);
		pnSelectButtons.add(btnLabTests);
		pnSelectButtons.add(btnPresc);
		pnSelectButtons.add(btnNursComm);

	}

	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();

			if(source == btnPat){
				cardLayout.show(cardPanel,"PatientInformationPanel");
			}
			if(source == btnGenPract){
				cardLayout.show(cardPanel,"GeneralPracticePanel");
			}
			if(source == btnLabTests){
				cardLayout.show(cardPanel,"LabTestsPanel");
			}
			if(source == btnPresc){
				cardLayout.show(cardPanel,"PrescriptionsPanel");
			}
			if(source == btnNursComm){
				cardLayout.show(cardPanel,"NursingCommentsPanel");
			}
		}
	}
}