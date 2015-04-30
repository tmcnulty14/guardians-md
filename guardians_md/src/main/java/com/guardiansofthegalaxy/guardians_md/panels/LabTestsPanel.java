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

import com.guardiansofthegalaxy.guardians_md.tuples.LabOrder;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.tuples.Visit;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LabTestsPanel extends JPanel {

    public JPanel pnHemLab, pnRadLab, pnTests;
    //labels and text areas for the general practice section

    //labels for the subsections
    public JCheckBox ckRed, ckWhite, ckLiver, ckRenal, ckEletrol;
    public JCheckBox ckXray, ckCompTom, ckMagRes;
    public JCheckBox ckUrin, ckStool;
    public JButton resultRed, resultWhite, resultLiver, resultRenal, resultElectrol;
    public JButton resultXray, resultCompTom, resultMagRes;
    public JButton resultUrin, resultStool;

    private ArrayList<LabOrder> labOrders;

    public LabTestsPanel() {
        setLayout(new GridLayout(1, 3));
        setBorder(BorderFactory.createTitledBorder("Labratory Tests"));
        createHemLabPanel();
        createRadLabPanel();
        createTestsPanel();

        setBackground(Color.WHITE);

        add(pnHemLab);
        add(pnRadLab);
        add(pnTests);
    }

    public void createHemLabPanel() {
        pnHemLab = new JPanel();
        pnHemLab.setBackground(Color.WHITE);
        pnHemLab.setLayout(new GridLayout(5, 1, 0, 20));
        pnHemLab.setBorder(BorderFactory.createTitledBorder("Hematologic Laboratory"));

        ckRed = new JCheckBox("Red Blood Cell Test");
        ckRed.setFont(new Font("Times New Roman", 0, 16));
        ckRed.setBackground(Color.WHITE);
        ckRed.addActionListener(new HemBoxListener());

        resultRed = new JButton("Results");
        resultRed.setFont(new Font("DejaVu Serif", 0, 14));
        resultRed.addActionListener(new ViewResultListener());
        resultRed.setVisible(false);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.setBackground(Color.WHITE);
        panel2.add(resultRed);

        panel1.add(ckRed, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);

        ckWhite = new JCheckBox("White Blood Cell Test");
        ckWhite.setFont(new Font("Times New Roman", 0, 16));
        ckWhite.setBackground(Color.WHITE);
        ckWhite.addActionListener(new HemBoxListener());

        resultWhite = new JButton("Results");
        resultWhite.setFont(new Font("DejaVu Serif", 0, 14));
        resultWhite.addActionListener(new ViewResultListener());
        resultWhite.setVisible(false);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.WHITE);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.setBackground(Color.WHITE);
        panel4.add(resultWhite);

        panel3.add(ckWhite, BorderLayout.NORTH);
        panel3.add(panel4, BorderLayout.CENTER);

        ckLiver = new JCheckBox("Liver Function Test");
        ckLiver.setFont(new Font("Times New Roman", 0, 16));
        ckLiver.setBackground(Color.WHITE);
        ckLiver.addActionListener(new HemBoxListener());

        resultLiver = new JButton("Results");
        resultLiver.setFont(new Font("DejaVu Serif", 0, 14));
        resultLiver.addActionListener(new ViewResultListener());
        resultLiver.setVisible(false);

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setBackground(Color.WHITE);

        JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel6.setBackground(Color.WHITE);
        panel6.add(resultLiver);

        panel5.add(ckLiver, BorderLayout.NORTH);
        panel5.add(panel6, BorderLayout.CENTER);

        ckRenal = new JCheckBox("Renal Function Test");
        ckRenal.setFont(new Font("Times New Roman", 0, 16));
        ckRenal.setBackground(Color.WHITE);
        ckRenal.addActionListener(new HemBoxListener());

        resultRenal = new JButton("Results");
        resultRenal.setFont(new Font("DejaVu Serif", 0, 14));
        resultRenal.addActionListener(new ViewResultListener());
        resultRenal.setVisible(false);

        JPanel panel7 = new JPanel(new BorderLayout());
        panel7.setBackground(Color.WHITE);

        JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel8.setBackground(Color.WHITE);
        panel8.add(resultRenal);

        panel7.add(ckRenal, BorderLayout.NORTH);
        panel7.add(panel8, BorderLayout.CENTER);

        ckEletrol = new JCheckBox("Electrol Test");
        ckEletrol.setFont(new Font("Times New Roman", 0, 16));
        ckEletrol.setBackground(Color.WHITE);
        ckEletrol.addActionListener(new HemBoxListener());

        resultElectrol = new JButton("Results");
        resultElectrol.setFont(new Font("DejaVu Serif", 0, 14));
        resultElectrol.addActionListener(new ViewResultListener());
        resultElectrol.setVisible(false);

        JPanel panel9 = new JPanel(new BorderLayout());
        panel9.setBackground(Color.WHITE);

        JPanel panel10 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel10.setBackground(Color.WHITE);
        panel10.add(resultElectrol);

        panel9.add(ckEletrol, BorderLayout.NORTH);
        panel9.add(panel10, BorderLayout.CENTER);

        pnHemLab.add(panel1);
        pnHemLab.add(panel3);
        pnHemLab.add(panel5);
        pnHemLab.add(panel7);
        pnHemLab.add(panel9);
    }

    public void createRadLabPanel() {
        pnRadLab = new JPanel();
        pnRadLab.setBackground(Color.WHITE);
        pnRadLab.setLayout(new GridLayout(3, 1, 0, 20));
        pnRadLab.setBorder(BorderFactory.createTitledBorder("Radiologic Laboratory"));

        ckXray = new JCheckBox("X-Ray");
        ckXray.setFont(new Font("Times New Roman", 0, 16));
        ckXray.setBackground(Color.WHITE);
        ckXray.addActionListener(new RadBoxListener());

        resultXray = new JButton("Results");
        resultXray.setFont(new Font("DejaVu Serif", 0, 14));
        resultXray.addActionListener(new ViewResultListener());
        resultXray.setVisible(false);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.setBackground(Color.WHITE);
        panel2.add(resultXray);

        panel1.add(ckXray, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);

        ckCompTom = new JCheckBox("Computed Tomography (CT)");
        ckCompTom.setFont(new Font("Times New Roman", 0, 16));
        ckCompTom.setBackground(Color.WHITE);
        ckCompTom.addActionListener(new RadBoxListener());

        resultCompTom = new JButton("Results");
        resultCompTom.setFont(new Font("DejaVu Serif", 0, 14));
        resultCompTom.addActionListener(new ViewResultListener());
        resultCompTom.setVisible(false);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.WHITE);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.setBackground(Color.WHITE);
        panel4.add(resultCompTom);

        panel3.add(ckCompTom, BorderLayout.NORTH);
        panel3.add(panel4, BorderLayout.CENTER);

        ckMagRes = new JCheckBox("Magnetic Resonance Image (MRI)");
        ckMagRes.setFont(new Font("Times New Roman", 0, 16));
        ckMagRes.setBackground(Color.WHITE);
        ckMagRes.addActionListener(new RadBoxListener());

        resultMagRes = new JButton("Results");
        resultMagRes.setFont(new Font("DejaVu Serif", 0, 14));
        resultMagRes.addActionListener(new ViewResultListener());
        resultMagRes.setVisible(false);

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setBackground(Color.WHITE);

        JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel6.setBackground(Color.WHITE);
        panel6.add(resultMagRes);

        panel5.add(ckMagRes, BorderLayout.NORTH);
        panel5.add(panel6, BorderLayout.CENTER);

        pnRadLab.add(panel1);
        pnRadLab.add(panel3);
        pnRadLab.add(panel5);
    }

    public void createTestsPanel() {
        pnTests = new JPanel();
        pnTests.setBackground(Color.WHITE);
        pnTests.setLayout(new GridLayout(2, 1, 0, 20));
        pnTests.setBorder(BorderFactory.createTitledBorder("Additional Tests"));

        ckUrin = new JCheckBox("Urinary Test");
        ckUrin.setFont(new Font("Times New Roman", 0, 16));
        ckUrin.setBackground(Color.WHITE);
        ckUrin.addActionListener(new TestBoxListener());

        resultUrin = new JButton("Results");
        resultUrin.setFont(new Font("DejaVu Serif", 0, 14));
        resultUrin.addActionListener(new ViewResultListener());
        resultUrin.setVisible(false);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.setBackground(Color.WHITE);
        panel2.add(resultUrin);

        panel1.add(ckUrin, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);

        ckStool = new JCheckBox("Stool Test");
        ckStool.setFont(new Font("Times New Roman", 0, 16));
        ckStool.setBackground(Color.WHITE);
        ckStool.addActionListener(new TestBoxListener());

        resultStool = new JButton("Results");
        resultStool.setFont(new Font("DejaVu Serif", 0, 14));
        resultStool.addActionListener(new ViewResultListener());
        resultStool.setVisible(false);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.WHITE);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.setBackground(Color.WHITE);
        panel4.add(resultStool);

        panel3.add(ckStool, BorderLayout.NORTH);
        panel3.add(panel4, BorderLayout.CENTER);

        pnTests.add(panel1);
        pnTests.add(panel3);
    }

    public void loadLabTestInformation() {
        Visit visit = MedicalConfigurator.getActiveVisit();
        LabOrder labOrder;

        if (!visit.getLabOrders().isEmpty()) {
            for (int i = 0, len = visit.getLabOrders().size(); i < len; i++) {

                labOrder = visit.getLabOrders().get(i);
                checkLabTests(labOrder.getTestName_enum());
            }
        } else {
            clearFields();
        }

        setCurrentLabOrders();
    }

    private void checkLabTests(TestName testName) {
        if (testName.equals(TestName.RED)) {
            ckRed.setSelected(true);
            resultRed.setVisible(true);
        } else if (testName.equals(TestName.WHITE)) {
            ckWhite.setSelected(true);
            resultWhite.setVisible(true);
        } else if (testName.equals(TestName.LIVER)) {
            ckLiver.setSelected(true);
            resultLiver.setVisible(true);
        } else if (testName.equals(TestName.RENAL)) {
            ckRenal.setSelected(true);
            resultRenal.setVisible(true);
        } else if (testName.equals(TestName.ELECTROL)) {
            ckEletrol.setSelected(true);
            resultElectrol.setVisible(true);
        } else if (testName.equals(TestName.XRAY)) {
            ckXray.setSelected(true);
            resultXray.setVisible(true);
        } else if (testName.equals(TestName.CT)) {
            ckCompTom.setSelected(true);
            resultCompTom.setVisible(true);
        } else if (testName.equals(TestName.MRI)) {
            ckMagRes.setSelected(true);
            resultMagRes.setVisible(true);
        } else if (testName.equals(TestName.URINARY)) {
            ckUrin.setSelected(true);
            resultUrin.setVisible(true);
        } else if (testName.equals(TestName.STOOL)) {
            ckStool.setSelected(true);
            resultStool.setVisible(true);
        }
    }

    public void clearFields() {
        ckRed.setSelected(false);
        ckWhite.setSelected(false);
        ckLiver.setSelected(false);
        ckRenal.setSelected(false);
        ckEletrol.setSelected(false);
        ckXray.setSelected(false);
        ckCompTom.setSelected(false);
        ckMagRes.setSelected(false);
        ckUrin.setSelected(false);
        ckStool.setSelected(false);
    }

    public ArrayList<LabOrder> getNewLabOrders() {
        return labOrders;
    }

    public void setCurrentLabOrders() {
        labOrders = MedicalConfigurator.getActiveVisit().getLabOrders();
    }

    private void removeLabOrder(TestName testName) {
        
        for (LabOrder search : labOrders) {
            if (search.getTestName_enum().equals(testName)) {
                labOrders.remove(search);
                break;
            }
        }
    }

    private class ViewResultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == resultRed) {
                new ResultsFrameView(LabName.HEMATOLOGIC, TestName.RED, labOrders).setVisible(true);
            }
            else if (source == resultWhite) {
                new ResultsFrameView(LabName.HEMATOLOGIC, TestName.WHITE, labOrders).setVisible(true);
            }
            else if (source == resultLiver) {
                new ResultsFrameView(LabName.HEMATOLOGIC, TestName.LIVER, labOrders).setVisible(true);
            }

            else if (source == resultRenal) {
                new ResultsFrameView(LabName.HEMATOLOGIC, TestName.RENAL, labOrders).setVisible(true);
            }

            else if (source == resultElectrol) {
                new ResultsFrameView(LabName.HEMATOLOGIC, TestName.ELECTROL, labOrders).setVisible(true);
            }

            else if (source == resultXray) {
                new ResultsFrameView(LabName.RADIOLOGIC, TestName.XRAY, labOrders).setVisible(true);
            }

            else if (source == resultCompTom) {
                new ResultsFrameView(LabName.RADIOLOGIC, TestName.CT, labOrders).setVisible(true);
            }

            else if (source == resultMagRes) {
                new ResultsFrameView(LabName.RADIOLOGIC, TestName.MRI, labOrders).setVisible(true);
            }

            else if (source == resultUrin) {
                new ResultsFrameView(LabName.ADDITIONAL, TestName.URINARY, labOrders).setVisible(true);
            }

            else if (source == resultStool) {
                new ResultsFrameView(LabName.ADDITIONAL, TestName.STOOL, labOrders).setVisible(true);
            }
        }
    }

    private class HemBoxListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == ckRed) {
                if (ckRed.isSelected()) {
                    labOrders.add(new LabOrder(LabName.HEMATOLOGIC, TestName.RED));
                    resultRed.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.RED);
                    resultRed.setVisible(false);
                }
            } else if (obj == ckWhite) {
                if (ckWhite.isSelected()) {
                    labOrders.add(new LabOrder(LabName.HEMATOLOGIC, TestName.WHITE));
                    resultWhite.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.WHITE);
                    resultWhite.setVisible(false);
                }
            } else if (obj == ckLiver) {
                if (ckLiver.isSelected()) {
                    labOrders.add(new LabOrder(LabName.HEMATOLOGIC, TestName.LIVER));
                    resultLiver.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.LIVER);
                    resultLiver.setVisible(false);
                }
            } else if (obj == ckRenal) {
                if (ckRenal.isSelected()) {
                    labOrders.add(new LabOrder(LabName.HEMATOLOGIC, TestName.RENAL));
                    resultRenal.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.RENAL);
                    resultRenal.setVisible(false);
                }
            } else if (obj == ckEletrol) {
                if (ckEletrol.isSelected()) {
                    labOrders.add(new LabOrder(LabName.HEMATOLOGIC, TestName.ELECTROL));
                    resultElectrol.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.ELECTROL);
                    resultElectrol.setVisible(false);
                }
            }
        }
    }

    private class RadBoxListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == ckXray) {
                if (ckXray.isSelected()) {
                    labOrders.add(new LabOrder(LabName.RADIOLOGIC, TestName.XRAY));
                    resultXray.setVisible(true);
                } else {
                    removeLabOrder(TestName.XRAY);
                    resultXray.setVisible(false);
                }
            } else if (obj == ckCompTom) {
                if (ckCompTom.isSelected()) {
                    labOrders.add(new LabOrder(LabName.RADIOLOGIC, TestName.CT));
                    resultCompTom.setVisible(true);
                } else {
                    removeLabOrder(TestName.CT);
                    resultCompTom.setVisible(false);
                }
            } else if (obj == ckMagRes) {
                if (ckMagRes.isSelected()) {
                    labOrders.add(new LabOrder(LabName.RADIOLOGIC, TestName.MRI));
                    resultMagRes.setVisible(true);
                } else {
                    removeLabOrder(TestName.MRI);
                    resultMagRes.setVisible(false);
                }
            }
        }
    }

    private class TestBoxListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == ckUrin) {
                if (ckUrin.isSelected()) {
                    labOrders.add(new LabOrder(LabName.ADDITIONAL, TestName.URINARY));
                    resultUrin.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.URINARY);
                    resultUrin.setVisible(false);
                }
            } else if (obj == ckStool) {
                if (ckStool.isSelected()) {
                    labOrders.add(new LabOrder(LabName.ADDITIONAL, TestName.STOOL));
                    resultStool.setVisible(true);
                }
                else {
                    removeLabOrder(TestName.STOOL);
                    resultStool.setVisible(false);
                }
            }
        }
    }
}