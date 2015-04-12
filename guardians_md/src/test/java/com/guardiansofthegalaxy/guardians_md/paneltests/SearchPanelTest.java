package com.guardiansofthegalaxy.guardians_md.paneltests;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import com.guardiansofthegalaxy.guardians_md.panels.SearchPanel;

import javax.swing.*;

/**
 * Created by Christina on 4/9/2015.
 */
public class SearchPanelTest extends JFrame {

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;
    public SearchPanel searchPanel;

    public SearchPanelTest(String frameTitle)
    {
        super(frameTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanels();

        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(false);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildPanels(){

        searchPanel = new SearchPanel();
        add(searchPanel);

    }

    public static void main(String[] args){

        MedicalConfigurator.setActivePatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

        SearchPanelTest main = new SearchPanelTest("Search Records");
        main.setVisible(true);

    }
}
