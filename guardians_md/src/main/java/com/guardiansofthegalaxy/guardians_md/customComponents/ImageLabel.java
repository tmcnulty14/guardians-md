package com.guardiansofthegalaxy.guardians_md.customComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christina on 4/24/2015.
 */

public class ImageLabel extends JLabel {

    /**
     * Filename of the image to be used as the button's icon.
     */
    private Image image;
    /**
     * The width of the button
     */
    private int width;
    /**
     * The height of the button.
     */
    private int height;

    public ImageLabel(){
        this.width = 1026;
        this.height = 1026;
    }

    public ImageLabel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ImageLabel(Image image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
        changeLabelImage(image);

    }


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
