package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageLabel;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.S3ImageStorage;
import com.guardiansofthegalaxy.guardians_md.db.LabOrder;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Christina on 4/24/2015.
 */
public class ResultsFrameView extends JFrame {

    private static final String TEST_IMAGE = ConfigDirectory.getImageFileFromDirectory("Home_button.png");
    private static final String TEST_KEY = "test.png";

    //variables for the image and text files
    private S3ImageStorage s3Conn = null;
    private LabName labName;
    private TestName testName;
    private String[] results;
    private int imageCounter;


    //variables for the JFrame
    public CardLayout cardLayout;
    public JPanel cardPanel;

    public JScrollPane jScrollPane;
    public JTextArea textArea;
    public ImageLabel imageLabel;
    public JButton btnNext, btnSave, btnAddNew;
    private ArrayList<LabOrder> currentLabOrders;

    private JPanel imagePanel, textPanel;

    public ResultsFrameView(LabName labName, TestName testName, ArrayList<LabOrder> orders) {

        //image storage initialization
        s3Conn = new S3ImageStorage();
        this.labName = labName;
        this.testName = testName;
        this.currentLabOrders = orders;
        this.imageCounter = 0;

        //frame component intialization
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.WHITE);
        setLocationByPlatform(true);
        setLocation(840, 0);
        setSize(new Dimension(500, 500));
        setResizable(true);

        createComponents();

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

        changeResultsView();

    }

    public void createComponents() {
        imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        textPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnSave = new JButton("Save");
        btnSave.addActionListener(new SaveListener());
        btnSave.setFont(new Font("DejaVu Serif", 0, 14));

        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        savePanel.setBackground(Color.WHITE);
        savePanel.add(btnSave);

        btnAddNew = new JButton("Add New");
        btnAddNew.addActionListener(new AddListener());
        btnAddNew.setFont(new Font("DejaVu Serif", 0, 14));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addPanel.setBackground(Color.WHITE);
        addPanel.add(btnAddNew);
        
        imageLabel = new ImageLabel();

        btnNext = new JButton("Next");
        btnNext.setFont(new Font("DejaVu Serif", 0, 14));
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnNext) {
                    if (imageCounter < results.length) {
                        imageLabel.changeLabelImage(s3Conn.getImage(results[imageCounter++]));
                    } else {
                        imageCounter = 0;
                        imageLabel.changeLabelImage(s3Conn.getImage(results[imageCounter++]));
                    }
                }
            }
        });

        JPanel nextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextPanel.setBackground(Color.WHITE);
        nextPanel.add(btnNext);

        imagePanel.add(addPanel, BorderLayout.NORTH);
        imagePanel.add(imageLabel,BorderLayout.CENTER);
        imagePanel.add(nextPanel, BorderLayout.SOUTH);

        textPanel.add(jScrollPane, BorderLayout.CENTER);
        textPanel.add(savePanel, BorderLayout.SOUTH);

        imagePanel.setBackground(Color.WHITE);
        textPanel.setBackground(Color.WHITE);

        cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(imagePanel, "ImagePanel");
        cardPanel.add(textPanel, "TextPanel");
    }

    public void changeResultsView() {
        if (MedicalConfigurator.checkLabOrdersForHasResultsImage(labName, testName)) {
            results = MedicalConfigurator.getActiveVisitLabOrderResultsImageListByType(labName, testName);
            if (imageCounter < results.length) {
                imageLabel.changeLabelImage(s3Conn.getImage(results[imageCounter++]));
                cardLayout.show(cardPanel, "ImagePanel");
            }
        } else {
            results = new String[]{MedicalConfigurator.getActiveVisitLabOrderResultsByType(labName, testName)};
            textArea.setText(results[0]);
            cardLayout.show(cardPanel, "TextPanel");
        }
    }

    private class SaveListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            for (LabOrder search : currentLabOrders) {
                if (search.getTestName_enum().equals(testName)) {
                    search.setResults(textArea.getText());
                    break;
                }
            }

            dispose();
        }
    }

    private class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }
}
