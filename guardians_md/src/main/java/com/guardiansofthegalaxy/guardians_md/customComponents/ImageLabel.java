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
package com.guardiansofthegalaxy.guardians_md.customComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christina on 4/24/2015.
 */

public class ImageLabel extends JLabel {


    private Image image;
    private int width;
    private int height;

    /**
     * Default constructor
     */
    public ImageLabel(){
        this.width = 1026;
        this.height = 1026;
    }

    /**
     * Constructor that takes the width and height of the image
     * @param width
     * @param height
     */
    public ImageLabel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor that takes the image, width, and height
     * @param image
     * @param width
     * @param height
     */
    public ImageLabel(Image image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
        changeLabelImage(image);

    }


    /**
     * This method creates an image label based on the image
     * @param image
     */
    public void changeLabelImage(Image image) {
        this.setEnabled(true);

        createLabelImage(image);


    }


    /**
     * Creates the button according to the fields set by the constructor.
     */
    private void createLabelImage(Image image) {
        this.setText(null);
        this.setIcon(new ImageIcon(image));
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

}
