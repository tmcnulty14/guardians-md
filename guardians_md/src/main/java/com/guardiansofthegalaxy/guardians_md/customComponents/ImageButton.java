package com.guardiansofthegalaxy.guardians_md.customComponents;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.net.URL;

public class ImageButton extends JButton {

 // The width of the button
    private int width;
    //The height of the button.

    private int height;


    /**
     * This is the constructor of the ImageButton
     * @param hasImage
     * @param fileName
     * @param width
     * @param height
     */
    public ImageButton(boolean hasImage, String fileName, int width, int height) {
        this.width = width;
        this.height = height;
        changeButtonImage(hasImage, fileName);

    }

    /**
     * This method changes the button type based on whether it has an image or text
     * @param hasImage
     * @param fileName
     */
    public void changeButtonImage(boolean hasImage, String fileName) {
        this.setEnabled(true);
        if (hasImage) {
            createButtonImage(fileName);
        } else {
            createButtonText(fileName);
        }


    }

    /**
     * This method creates the button text object
     * @param fileName
     */
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
     * @param fileName
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

    /**
     * This method returns the icon image by the class loader get resource
     * @param fileName
     * @return ImageIcon
     */
    public ImageIcon getImageIcon(String fileName) {

        System.out.println(fileName);
        URL imageUrl = ImageButton.class.getClassLoader().getResource(fileName);
        System.out.println(imageUrl);
        return new ImageIcon(imageUrl);
    }
}