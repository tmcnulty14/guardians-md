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

import com.guardiansofthegalaxy.guardians_md.customComponents.ImageLabel;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.ImageStorage;
import com.guardiansofthegalaxy.guardians_md.db.S3ImageStorage;
import com.guardiansofthegalaxy.guardians_md.db.LocalImageStorage;
import com.guardiansofthegalaxy.guardians_md.tuples.LabOrder;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;

/**
 * Created by Christina on 4/24/2015.
 */
 
 // Separate from that holds images relevant to certain patients and visits 
public class ResultsFrameView extends JFrame {

    private static final String TEST_IMAGE = ConfigDirectory.getImageFileFromDirectory("Home_button.png");
    private static final String TEST_KEY = "test.png";

    //variables for the image and text files
    private ImageStorage imageStorage = null;
    private LabName labName;
    private TestName testName;
    private String[] results;
    private int imageCounter;


    //variables for the JFrame
    public CardLayout cardLayout;
    public JPanel cardPanel, imagePanel, textPanel, centerImgPanel;

    public JScrollPane jScrollPane;
    public JTextArea textArea;
    public JLabel noImage;
    public ImageLabel imageLabel;
    public JButton btnNext, btnSave, btnAddNew;
    public JFileChooser fileChooser;

    private ArrayList<LabOrder> currentLabOrders;
    private String[] supportedExts;

    public ResultsFrameView(LabName labName, TestName testName, ArrayList<LabOrder> orders) {

        //image storage initialization
        imageStorage = (MedicalConfigurator.USE_S3 ? new S3ImageStorage() : new LocalImageStorage());
        this.labName = labName;
        this.testName = testName;
        this.currentLabOrders = orders;
        this.imageCounter = 0;
        supportedExts = ImageIO.getReaderFormatNames();

        //frame component intialization
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.WHITE);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        setLocation(screenWidth/4, 0);

        setSize(new Dimension(500, 500));
        setResizable(true);

        fileChooser = new JFileChooser();

        createComponents();

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

        changeResultsView();

    }

	// generates all relevant buttons and panels 
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

        noImage = new JLabel("No images found for this test.", SwingConstants.CENTER);
        noImage.setFont(new Font("Times New Roman", 0, 16));
        noImage.setForeground(Color.RED);
        noImage.setVisible(false);
        
        imageLabel = new ImageLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        centerImgPanel = new JPanel(new BorderLayout());
        centerImgPanel.setBackground(Color.WHITE);
        centerImgPanel.add(noImage, BorderLayout.NORTH);
        centerImgPanel.add(imageLabel, BorderLayout.CENTER);

        btnNext = new JButton("Next");
        btnNext.setFont(new Font("DejaVu Serif", 0, 14));
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnNext) {

                    if (!(imageCounter < results.length)) {
                        imageCounter = 0;
                    }

                    Image currentImage = imageStorage.getImage(results[imageCounter++]).getScaledInstance(500, -1,Image.SCALE_DEFAULT);
                    imageLabel.changeLabelImage(currentImage);
                    setSize(currentImage.getWidth(null) + 20, currentImage.getHeight(null) + 100);
                }
            }
        });

        JPanel nextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextPanel.setBackground(Color.WHITE);
        nextPanel.add(btnNext);

        imagePanel.add(addPanel, BorderLayout.NORTH);
        imagePanel.add(centerImgPanel,BorderLayout.CENTER);
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
        if (testName.equals(TestName.XRAY) || testName.equals(TestName.CT) || testName.equals(TestName.MRI)) {
            
            results = MedicalConfigurator.getActiveVisitLabOrderResultsImageListByType(labName, testName);

            // Split method returns an array of size 1 when string is empty (see LabOrder.java)
            if (!results[0].equals("")) {
                noImage.setVisible(false);
                btnNext.setEnabled((results.length > 1) ? true : false);

                if (imageCounter < results.length) {
                    Image currentImage = imageStorage.getImage(results[imageCounter++]).getScaledInstance(500, -1,Image.SCALE_DEFAULT);
                    imageLabel.changeLabelImage(currentImage);
                    setSize(currentImage.getWidth(null) + 20, currentImage.getHeight(null) + 100);
                }
            }
            else {
                noImage.setVisible(true);
                btnNext.setEnabled(false);
            }

            cardLayout.show(cardPanel, "ImagePanel");
        }
        else {
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
            // New file chooser
            int status = fileChooser.showOpenDialog(null);

            if (status == JFileChooser.APPROVE_OPTION) {
                File imageFile = fileChooser.getSelectedFile();
                String fileName = imageFile.getName(), extension = "";

                // Check extension
                boolean accepted = false;
                int i = fileName.lastIndexOf('.');

                if (i > 0) {
                    extension = fileName.substring(i+1);

                    for (int j = 0, len = supportedExts.length; j < len; j++) {
                        if (extension.equalsIgnoreCase(supportedExts[j])) {
                            accepted = true;
                            break;
                        }
                    }
                }

                if (accepted) {
                    int index = 0;

                    if (!results[0].equals("")) {
                        index = results.length;
                    }

                    String imageName = MedicalConfigurator.getActiveVisit().getVisitID() + "-" + testName + "-" + index + "." + extension;

                    if (imageStorage.storeImage(imageFile, imageName)) {

                        for (LabOrder search : currentLabOrders) {
                            if (search.getTestName_enum().equals(testName)) {
                                String newResults = search.getResults() + imageName + ",";
                                search.setResults(newResults);
                                break;
                            }
                        }

                        changeResultsView();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "An error occurred; please check your network connection and try again.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "File type is not supported. Select a jpg/jpeg/bmp/gif/png/wbmp image and try again.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
