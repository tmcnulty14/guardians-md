package com.guardiansofthegalaxy.guardians_md.paneltests;

import com.guardiansofthegalaxy.guardians_md.db.MedicalConfigurator;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Christina on 4/12/2015.
 */
public class SearchPanelJUnitTest {

    @Before
    public void beforeClass() {
        MedicalConfigurator.setActivePatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

    }


    @Test
    public void test_frame() {

        MedicalConfigurator.setActivePatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

        SearchPanelTest main = new SearchPanelTest("Search Records");
        main.setVisible(true);
        try{
            Thread.sleep(600000);
        }catch(InterruptedException ie){

        }
    }

}
