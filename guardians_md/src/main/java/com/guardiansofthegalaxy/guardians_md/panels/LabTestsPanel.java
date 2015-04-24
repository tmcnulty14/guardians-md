package com.guardiansofthegalaxy.guardians_md.panels;

import com.guardiansofthegalaxy.guardians_md.db.LabOrder;
import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.db.Visit;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LabTestsPanel extends JPanel {

    public JPanel pnHemLab, pnRadLab, pnTests;
    //labels and text areas for the general practice section

    //labels for the subsections
    public JCheckBox ckRed, ckWhite, ckLiver, ckRenal, ckEletrol;
    public JCheckBox ckXray, ckCompTom, ckMagRes;
    public JCheckBox ckUrin, ckStool;
    public JButton resultRed, resultWhite, resultLiver, resultRenal, resultElectrol;
    public JButton newXray, resultXray, newCompTom, resultCompTom, newMagRes, resultMagRes;
    public JButton resultUrin, resultStool;
    public TestResultFrame resultFrame;

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
        ckRed.addItemListener(new HemBoxListener());

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
        ckWhite.addItemListener(new HemBoxListener());

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
        ckLiver.addItemListener(new HemBoxListener());

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
        ckRenal.addItemListener(new HemBoxListener());

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
        ckEletrol.addItemListener(new HemBoxListener());

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
        ckXray.addItemListener(new RadBoxListener());

        newXray = new JButton("Add New");
        newXray.setFont(new Font("DejaVu Serif", 0, 14));
        newXray.addActionListener(new NewResultListener());
        newXray.setVisible(false);

        resultXray = new JButton("Results");
        resultXray.setFont(new Font("DejaVu Serif", 0, 14));
        resultXray.addActionListener(new ViewResultListener());
        resultXray.setVisible(false);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel2.setBackground(Color.WHITE);
        panel2.add(newXray);
        panel2.add(resultXray);

        panel1.add(ckXray, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);

        ckCompTom = new JCheckBox("Computed Tomography (CT)");
        ckCompTom.setFont(new Font("Times New Roman", 0, 16));
        ckCompTom.setBackground(Color.WHITE);
        ckCompTom.addItemListener(new RadBoxListener());

        newCompTom = new JButton("Add New");
        newCompTom.setFont(new Font("DejaVu Serif", 0, 14));
        newCompTom.addActionListener(new NewResultListener());
        newCompTom.setVisible(false);

        resultCompTom = new JButton("Results");
        resultCompTom.setFont(new Font("DejaVu Serif", 0, 14));
        resultCompTom.addActionListener(new ViewResultListener());
        resultCompTom.setVisible(false);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Color.WHITE);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.setBackground(Color.WHITE);
        panel4.add(newCompTom);
        panel4.add(resultCompTom);

        panel3.add(ckCompTom, BorderLayout.NORTH);
        panel3.add(panel4, BorderLayout.CENTER);

        ckMagRes = new JCheckBox("Magnetic Resonance Image (MRI)");
        ckMagRes.setFont(new Font("Times New Roman", 0, 16));
        ckMagRes.setBackground(Color.WHITE);
        ckMagRes.addItemListener(new RadBoxListener());

        newMagRes = new JButton("Add New");
        newMagRes.setFont(new Font("DejaVu Serif", 0, 14));
        newMagRes.addActionListener(new NewResultListener());
        newMagRes.setVisible(false);

        resultMagRes = new JButton("Results");
        resultMagRes.setFont(new Font("DejaVu Serif", 0, 14));
        resultMagRes.addActionListener(new ViewResultListener());
        resultMagRes.setVisible(false);

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setBackground(Color.WHITE);

        JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel6.setBackground(Color.WHITE);
        panel6.add(newMagRes);
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
        ckUrin.addItemListener(new TestBoxListener());

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
        ckStool.addItemListener(new TestBoxListener());

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
    }

    private void checkLabTests(TestName testName) {
        if (testName.equals(TestName.RED)) {
            ckRed.setSelected(true);
        } else if (testName.equals(TestName.WHITE)) {
            ckWhite.setSelected(true);
        } else if (testName.equals(TestName.LIVER)) {
            ckLiver.setSelected(true);
        } else if (testName.equals(TestName.RENAL)) {
            ckRenal.setSelected(true);
        } else if (testName.equals(TestName.ELECTROL)) {
            ckEletrol.setSelected(true);
        } else if (testName.equals(TestName.XRAY)) {
            ckXray.setSelected(true);
        } else if (testName.equals(TestName.CT)) {
            ckCompTom.setSelected(true);
        } else if (testName.equals(TestName.MRI)) {
            ckMagRes.setSelected(true);
        } else if (testName.equals(TestName.URINARY)) {
            ckUrin.setSelected(true);
        } else if (testName.equals(TestName.STOOL)) {
            ckStool.setSelected(true);
        }
    }


    private class ViewResultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == resultRed) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.HEMATOLOGIC, TestName.RED)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.HEMATOLOGIC, TestName.RED).setVisible(true);
                }
            }
            if (source == resultWhite) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.HEMATOLOGIC, TestName.WHITE)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.HEMATOLOGIC, TestName.WHITE).setVisible(true);
                }
            }
            if (source == resultLiver) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.HEMATOLOGIC, TestName.LIVER)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.HEMATOLOGIC, TestName.WHITE).setVisible(true);
                }
            }

            if (source == resultRenal) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.HEMATOLOGIC, TestName.RENAL)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.HEMATOLOGIC, TestName.RENAL).setVisible(true);
                }
            }

            if (source == resultElectrol) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.HEMATOLOGIC, TestName.ELECTROL)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.HEMATOLOGIC, TestName.ELECTROL).setVisible(true);
                }
            }

            if (source == resultXray) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.RADIOLOGIC, TestName.XRAY)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.RADIOLOGIC, TestName.XRAY).setVisible(true);
                }
            }

            if (source == resultCompTom) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.RADIOLOGIC, TestName.CT)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.RADIOLOGIC, TestName.CT).setVisible(true);
                }
            }

            if (source == resultMagRes) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.RADIOLOGIC, TestName.MRI)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.RADIOLOGIC, TestName.MRI).setVisible(true);
                }
            }

            if (source == resultUrin) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.ADDITIONAL, TestName.URINARY)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.ADDITIONAL, TestName.URINARY).setVisible(true);
                }
            }

            if (source == resultStool) {
                if (MedicalConfigurator.isResultSetEmpty(LabName.ADDITIONAL, TestName.STOOL)) {
                    JOptionPane.showMessageDialog(null, "There are no active results for this Patient");
                } else {
                    new ResultsFrameView(LabName.ADDITIONAL, TestName.STOOL).setVisible(true);
                }
            }

        }
    }

    private class NewResultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class HemBoxListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object obj = e.getSource();

            if (obj == ckRed) {
                if (ckRed.isSelected())
                    resultRed.setVisible(true);
                else
                    resultRed.setVisible(false);
            } else if (obj == ckWhite) {
                if (ckWhite.isSelected())
                    resultWhite.setVisible(true);
                else
                    resultWhite.setVisible(false);
            } else if (obj == ckLiver) {
                if (ckLiver.isSelected())
                    resultLiver.setVisible(true);
                else
                    resultLiver.setVisible(false);
            } else if (obj == ckRenal) {
                if (ckRenal.isSelected())
                    resultRenal.setVisible(true);
                else
                    resultRenal.setVisible(false);
            } else if (obj == ckRenal) {
                if (ckRenal.isSelected())
                    resultRenal.setVisible(true);
                else
                    resultRenal.setVisible(false);
            }
        }
    }

    private class RadBoxListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object obj = e.getSource();

            if (obj == ckXray) {
                if (ckXray.isSelected()) {
                    newXray.setVisible(true);
                    resultXray.setVisible(true);
                } else {
                    newXray.setVisible(false);
                    resultXray.setVisible(false);
                }
            } else if (obj == ckCompTom) {
                if (ckCompTom.isSelected()) {
                    newCompTom.setVisible(true);
                    resultCompTom.setVisible(true);
                } else {
                    newCompTom.setVisible(false);
                    resultCompTom.setVisible(false);
                }
            } else if (obj == ckMagRes) {
                if (ckMagRes.isSelected()) {
                    newMagRes.setVisible(true);
                    resultMagRes.setVisible(true);
                } else {
                    newMagRes.setVisible(false);
                    resultMagRes.setVisible(false);
                }
            }
        }
    }

    private class TestBoxListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object obj = e.getSource();

            if (obj == ckUrin) {
                if (ckUrin.isSelected())
                    resultUrin.setVisible(true);
                else
                    resultUrin.setVisible(false);
            } else if (obj == ckStool) {
                if (ckStool.isSelected())
                    resultStool.setVisible(true);
                else
                    resultStool.setVisible(false);
            }
        }
    }
}