package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageLabel;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.S3ImageStorage;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public JButton btnNext;


    private JPanel imagePanel, textPanel;

    public ResultsFrameView(LabName labName, TestName testName) {

        //image storage initialization
        s3Conn = new S3ImageStorage();
        this.labName = labName;
        this.testName = testName;
        this.imageCounter = 0;

        //frame component intialization
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setLocationByPlatform(true);
        setLocation(840, 0);
        setSize(new Dimension(500, 500));
        setResizable(true);

        createComponents();

        add(btnNext, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

        changeResultsView();

    }

    public void createComponents() {
        imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        textPanel = new JPanel();
        textArea = new JTextArea();
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(textArea);
        imageLabel = new ImageLabel();

        imagePanel.add(imageLabel,BorderLayout.CENTER);
        textPanel.add(jScrollPane);

        imagePanel.setBackground(Color.WHITE);
        textPanel.setBackground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);

        btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnNext) {
                    if (imageCounter < results.length) {
                        imageLabel.changeLabelImage(s3Conn.getImage(results[imageCounter++]));
                    } else {
                        JOptionPane.showMessageDialog(null, "There are no more images!", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

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
                btnNext.setEnabled(true);
                btnNext.setVisible(true);
            }
        } else {
            results = new String[]{MedicalConfigurator.getActiveVisitLabOrderResultsByType(labName, testName)};
            textArea.setText(results[0]);
            cardLayout.show(cardPanel, "TextPanel");
            btnNext.setEnabled(false);
            btnNext.setVisible(false);
        }
    }

}
