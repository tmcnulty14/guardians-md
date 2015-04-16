package com.guardiansofthegalaxy.guardians_md.customComponents;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.net.URL;

public class ImageButton extends JButton {

    /** Filename of the image to be used as the button's icon. */
    /**
     * The width of the button
     */
    private int width;
    /**
     * The height of the button.
     */
    private int height;


    public ImageButton(boolean hasImage, String fileName, int width, int height) {
        this.width = width;
        this.height = height;
        changeButtonImage(hasImage, fileName);

    }

    public void changeButtonImage(boolean hasImage, String fileName) {
        this.setEnabled(true);
        if (hasImage) {
            createButtonImage(fileName);
        } else {
            createButtonText(fileName);
        }


    }

    private void createButtonText(String fileName) {

        this.setIcon(null);
        this.setText(fileName);
        this.setFont(new Font("DejaVu Serif", 0, 40));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setFocusPainted(false);
        this.setRolloverEnabled(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(true);

        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE.darker(), Color.BLACK),
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
    }


    /**
     * Creates the button according to the fields set by the constructor.
     */
    private void createButtonImage(String fileName) {
        this.setText(null);
        this.setIcon(getImageIcon(fileName));
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setFocusPainted(false);
        this.setRolloverEnabled(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE.darker(), Color.BLACK),
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));

    }

    public void createWrongAnswerButton() {
        this.setText("WRONG");
        this.setFont(new Font("Times New Roman", 0, 16));
        this.setIcon(null);
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setFocusPainted(false);
        this.setRolloverEnabled(false);
        this.setOpaque(false);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK),
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED)));


        this.setEnabled(false);


    }


    public void createRightAnswerButton() {

        this.setText("CORRECT");
        this.setFont(new Font("Times New Roman", 0, 16));
        this.setBackground(Color.GREEN);
        this.setIcon(null);
        this.setContentAreaFilled(true);
        this.setBorderPainted(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));

        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK.darker(), Color.BLACK),
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));


        this.setEnabled(false);


    }

    public ImageIcon getImageIcon(String fileName) {

        System.out.println(fileName);
        URL imageUrl = ImageButton.class.getClassLoader().getResource(fileName);
        System.out.println(imageUrl);
        return new ImageIcon(imageUrl);
    }
}