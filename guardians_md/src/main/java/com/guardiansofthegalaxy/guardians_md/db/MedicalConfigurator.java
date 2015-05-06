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
package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.labtesttypes.LabName;
import com.guardiansofthegalaxy.guardians_md.labtesttypes.TestName;
import com.guardiansofthegalaxy.guardians_md.tuples.*;

import java.util.ArrayList;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MedicalConfigurator {

    public static Patient patient = null;
    public static Visit visit = null;
    public static User user = null;
    public static boolean isUserLoggedIn = false;

    // Default values
    public static String DB_URL = "jdbc:mysql://localhost:3306";
    public static String DB_USER = "root";
    public static String DB_PASS = "";
    public static boolean USE_S3 = false;
    public static String S3_KEY_ID = "";
    public static String S3_SECRET_KEY = "";
    public static String S3_BUCKET_NAME = "gotg-md-pictures";
    public static String LOCAL_IMAGE_DIRECTORY = "gotg-md-pictures";

    public static void setLoginUser(String username, String firstName, String lastName, boolean doctorPrivileges) {

        user = new User(username, firstName, lastName, doctorPrivileges);

    }

    public static void setLoginUser(User newUser) {
        user = newUser;
    }

    public static User getLoginUser() {
        return user;

    }

    public static boolean isUserLoggedIn() {
        return isUserLoggedIn;

    }

    public static void resetActiveUserToNull() {
        user = null;
    }


    /**
     * This method creates a new static patient object that can be accessed anywhere in the program
     */
    public static void setActivePatient(String firstName, String lastName, String birthdate, String gender,
                                        String address1, String address2, String city, String state, String zipcode, String country,
                                        String insuranceProvider, String insuranceNumber) {


        patient = new Patient(firstName, lastName, birthdate, gender, address1, address2, city, state, zipcode, country,
                insuranceProvider, insuranceNumber);
    }

    /**
     * This method creates a new static patient object that can be accessed anywhere in the program
     */
    public static void setActivePatient(Patient newPatient) {
        patient = newPatient;
    }

    /**
     * This method returns the static patient object that can be accessed anywhere in the program
     */
    public static Patient getActivePatient() {
        return patient;
    }

    /**
     * This method creates the static visit object that can be accessed anywhere in the program
     */

    public static void setActiveVisit(int patientID, String doctorID, String date, ArrayList<String> mdFields,
                                      ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions, String comments) {

        visit = new Visit(patientID, doctorID, date, mdFields, labOrders, prescriptions, comments);

    }

    /**
     * This method creates the static visit object that can be accessed anywhere in the program
     */
    public static void setActiveVisit(Visit newVisit) {

        visit = newVisit;
    }


    /**
     * This method returns the static visit object that can be accessed anywhere in the program
     */
    public static Visit getActiveVisit() {
        return visit;
    }

    public static void setUserLoggedIn(boolean loggedIn) {
        isUserLoggedIn = loggedIn;
    }

    public static boolean isNewVisit() {
        return getActiveVisit().getVisitID() == -1;
    }

    public static boolean isNewPatient() {
        return getActivePatient().getPatientID() == -1;
    }



    public static String[] getActiveVisitLabOrderResultsImageListByType(LabName labName, TestName testName) {
        ArrayList<LabOrder> resultList = MedicalConfigurator.getActiveVisit().getLabOrders();
        for (LabOrder labOrder : resultList) {
            if (labOrder.getLabName_enum().equals(labName) & labOrder.getTestName_enum().equals(testName)) {
                return labOrder.getResultsImageList();
            }
        }
        return null;
    }

    public static String getActiveVisitLabOrderResultsByType(LabName labName, TestName testName) {
        ArrayList<LabOrder> resultList = MedicalConfigurator.getActiveVisit().getLabOrders();
        for (LabOrder labOrder : resultList) {
            if (labOrder.getLabName_enum().equals(labName) & labOrder.getTestName_enum().equals(testName)) {
                return labOrder.getResults();
            }
        }

        return null;
    }

    public static boolean checkLabOrdersForHasResultsImage(LabName labName, TestName testName){
        ArrayList<LabOrder> resultList = MedicalConfigurator.getActiveVisit().getLabOrders();
        for (LabOrder labOrder : resultList) {
            if (labOrder.getLabName_enum().equals(labName) & labOrder.getTestName_enum().equals(testName)) {
                if(labOrder.getHasResultsImage()){
                    return true;
                }
            }
        }

        return false;

    }


    public static boolean isResultSetEmpty(LabName labName, TestName testName) {
        ArrayList<LabOrder> resultList = MedicalConfigurator.getActiveVisit().getLabOrders();
        for (LabOrder lab : resultList) {
            if (lab.getLabName_enum().equals(labName) & lab.getTestName_enum().equals(testName)) {
                String results = lab.getResults();
                if (results.length() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void loadProperties() {
        boolean useLocalProperties = true;
        String[] input = new String[3];
        Scanner propScanner;
        try {
            propScanner = new Scanner(new File("db.properties"));
        } catch(FileNotFoundException fnfe) {
            try {
                propScanner = new Scanner(MedicalConfigurator.class.getClassLoader().getResource("db.properties").openStream());
            } catch(IOException ioe) {
                return;
            }
        }
        while(propScanner.hasNextLine()) {
            String inputLine = propScanner.nextLine();
            String[] inputLineArray = inputLine.split("=", -1);
            if(inputLineArray.length > 1) {
                if("DB_URL".equals(inputLineArray[0])) {
                    DB_URL = inputLineArray[inputLineArray.length - 1];
                } else if("DB_USER".equals(inputLineArray[0])) {
                    DB_USER = inputLineArray[inputLineArray.length - 1];
                } else if("DB_PASS".equals(inputLineArray[0])) {
                    DB_PASS = inputLineArray[inputLineArray.length - 1];
                } else if("USE_S3".equals(inputLineArray[0])) {
                    USE_S3 = "true".equals(inputLineArray[inputLineArray.length - 1]);
                } else if("S3_KEY_ID".equals(inputLineArray[0])) {
                    S3_KEY_ID = inputLineArray[inputLineArray.length - 1];
                } else if("S3_SECRET_KEY".equals(inputLineArray[0])) {
                    S3_SECRET_KEY = inputLineArray[inputLineArray.length - 1];
                } else if("S3_BUCKET_NAME".equals(inputLineArray[0])) {
                    S3_BUCKET_NAME = inputLineArray[inputLineArray.length - 1];
                } else if("LOCAL_IMAGE_DIRECTORY".equals(inputLineArray[0])) {
                    LOCAL_IMAGE_DIRECTORY = inputLineArray[inputLineArray.length - 1];
                }
            }
        }
    }
}