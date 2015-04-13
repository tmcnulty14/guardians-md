package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import java.util.ArrayList;


public class MedicalConfigurator {

    public static Patient patient = null;
    public static Visit visit = null;
    public static User user  = null;
    public static boolean isUserLoggedIn = false;

    public static void setLoginUser(String username, String firstName, String lastName, boolean doctorPrivileges) {

        user = new User(username, firstName, lastName, doctorPrivileges);

    }

    public static void setLoginUser(User newUser){
        user = newUser;
    }

    public static User getLoginUser(){
        return user;

    }

    public static boolean isUserLoggedIn(){
        return isUserLoggedIn;

    }

    public static void resetActiveUserToNull(){
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
                                      ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions,String comments) {

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
}