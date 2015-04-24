package com.guardiansofthegalaxy.guardians_md;

import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;
import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import java.util.ArrayList;

public class GuardiansOfTheGuiMD {

    public static void main(String[] args){

        MedicalConfigurator.setActivePatient("Charles", "Henderson", "July 18, 2010", "M",
                "13 Hill Lane", "", "Natick", "MA", "01744", "United States",
                "Fallon Health", "1231561515");

        ArrayList<String> md = new ArrayList();
        md.add("blaf");
        md.add("fdfd");
        md.add("sdsdsds");
        md.add("Sdsds");
        md.add("sdsdsd0");
        md.add("SDsdsd");
        md.add("dsdsds");

        ArrayList<LabOrder> labOrders = new ArrayList<>();
        labOrders.add(new LabOrder(LabName.HEMATOLOGIC, TestName.RED));
        labOrders.add(new LabOrder(LabName.HEMATOLOGIC,TestName.WHITE));
        labOrders.add(new LabOrder(LabName.HEMATOLOGIC,TestName.LIVER));
        labOrders.add(new LabOrder(LabName.ADDITIONAL, TestName.STOOL));

        ArrayList<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(new Prescription("PO", "Prescription for Morphine"));
        prescriptions.add(new Prescription("Injection","intramu"));

        MedicalConfigurator.setActiveVisit(
                1, "doctT", "January 25,2015", md, labOrders, prescriptions, "This is my nursing comment");


        MedicalSystemsMainFrame main = new MedicalSystemsMainFrame();
        main.setVisible(true);
    }


}