package com.guardiansofthegalaxy.guardians_md.db;

import com.guardiansofthegalaxy.guardians_md.panels.*;
import com.guardiansofthegalaxy.guardians_md.db.*;

import java.util.ArrayList;


public class MedicalConfigurator {

    public static Patient patient;
    public static Visit visit;
    public static User user;
    public static boolean isUserLoggedIn = false;

    public static void createNewUser(String username, String firstName, String lastName, boolean doctorPrivileges) {

        user = new User(username, firstName, lastName, doctorPrivileges);

    }

    public static void createNewUser(User newUser){
        user = newUser;
    }

    public static User getUser(){
        return user;

    }

    public static boolean isUserLoggedIn(){
        return isUserLoggedIn;

    }

    /**
     * This method creates a new static patient object that can be accessed anywhere in the program
     */
    public static void createNewPatient(String firstName, String lastName, String birthdate, String gender,
                                        String address, String city, String state, String zipcode, String country,
                                        String insuranceProvider, String insuranceNumber) {


        patient = new Patient(firstName, lastName, birthdate, gender, address, city, state, zipcode, country,
                insuranceProvider, insuranceNumber);
    }

    /**
     * This method creates a new static patient object that can be accessed anywhere in the program
     */
    public static void createNewPatient(Patient newPatient) {
        patient = newPatient;
    }

    /**
     * This method returns the static patient object that can be accessed anywhere in the program
     */
    public static Patient getPatient() {
        return patient;
    }

    /**
     * This method creates the static visit object that can be accessed anywhere in the program
     */

    public static void createNewVisit(int patientID, String doctorID, String date, ArrayList<String> mdFields,
                                      ArrayList<LabOrder> labOrders, ArrayList<Prescription> prescriptions) {

        visit = new Visit(patientID, doctorID, date, mdFields, labOrders, prescriptions);

    }

    /**
     * This method creates the static visit object that can be accessed anywhere in the program
     */
    public static void createNewVisit(Visit newVisit) {

        visit = newVisit;
    }


    /**
     * This method returns the static visit object that can be accessed anywhere in the program
     */
    public static Visit getVisit() {
        return visit;
    }

    public static void setUserLoggedIn(boolean loggedIn) {
        isUserLoggedIn = loggedIn;
    }
}