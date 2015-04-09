package com.guardiansofthegalaxy.guardians_md;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import javax.swing.*;

public class GuardiansOfTheGuiMD {

    public static void main(String[] args){

        MedicalConfigurator.createNewPatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

        MedicalDoctorFrame main = new MedicalDoctorFrame();
        main.setVisible(true);
    }


}