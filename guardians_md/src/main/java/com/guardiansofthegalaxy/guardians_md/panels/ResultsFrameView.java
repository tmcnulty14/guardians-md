package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageLabel;
import com.guardiansofthegalaxy.guardians_md.db.S3ImageStorage;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christina on 4/24/2015.
 */
public class ResultsFrameView extends JFrame {

    private final String TEST_IMAGE = ConfigDirectory.getImageFileFromDirectory("Home_button.png");
    private final String TEST_KEY = "test.png";

    private S3ImageStorage s3Conn = null;
    private LabName labName;
    private TestName testName;
    public CardLayout cardLayout;
    public JPanel cardPanel;

    public JScrollPane jScrollPane;
    public JTextArea textArea;
    public ImageLabel imageLabel;


    private JPanel imagePanel, textPanel;

    public ResultsFrameView(LabName labName, TestName testName) {
        s3Conn = new S3ImageStorage();
        this.labName = labName;
        this.testName = testName;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        imagePanel = new JPanel();
        textPanel = new JPanel();
        textArea = new JTextArea();
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(textArea);
        imageLabel = new ImageLabel();

        imagePanel.add(imageLabel);
        textPanel.add(jScrollPane);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(imagePanel, "ImagePanel");
        cardPanel.add(textPanel, "TextPanel");


        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args){
        new ResultsFrameView(LabName.HEMATOLOGIC,TestName.RED).setVisible(true);
    }

}
