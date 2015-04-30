/**
 * Class: CSCI 400: Special Topic in Computer Science - GUI Programming
 * Instructor: Professor Jung
 * Date: Spring 2015
 * 
 * Group: The Guardians of the GUI
 * Group Members: Christina Reid
 * 				  Sara Hakkoum
 * 				  Thomas McNulty
 * 				  Trevor Gorman
 * 
 * Assignment: Project 2 - Medical Doctor software
 **/
package com.guardiansofthegalaxy.guardians_md.environments;

/**
 * Created by Christina on 4/10/2015.
 */
public class ConfigDirectory {

    public static String imageDirectory = "images";

    /**
     * This method returns the filepath for the image in the resources directory
     * @param filename
     * @return String
     */
    public static String getImageFileFromDirectory(String filename){
        return imageDirectory + "/" + filename;
    }

}