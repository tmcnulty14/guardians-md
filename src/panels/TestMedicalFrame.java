package panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import panels.*;

public class TestMedicalFrame extends JFrame  {


  //values for the size of the window
    private final int WINDOW_HEIGHT = 750;
    private final int WINDOW_WIDTH = 750;
    private MedicalPanel pnMedical;


    public TestMedicalFrame() {
        super("Medical Frame");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        setResizable(false);

        //center the frame based on the screen's dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        setLocation((d.width - WINDOW_WIDTH) / 2, (d.height - WINDOW_HEIGHT) / 2);

        //setResizable(false);

        build();

        //pack();
    }


 //this method builds the main panel for the frame
    private void build() {

		pnMedical = new MedicalPanel();
		pnMedical.build();
       	add(pnMedical);
    }



    public static void main(String[] args){

		TestMedicalFrame main = new TestMedicalFrame();
		main.setVisible(true);
	}


}