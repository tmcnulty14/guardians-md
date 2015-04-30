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
package com.guardiansofthegalaxy.guardians_md.labtesttypes;

/**
 * Created by Christina on 4/24/2015.
 */
public enum LabName {
    /**
     * Enum for Lab Types
     */
    HEMATOLOGIC {
        @Override
        public String toString() {
            return "HEMATOLOGIC";
        }
    },

    RADIOLOGIC {
        @Override
        public String toString() {
            return "RADIOLOGIC";
        }
    },

    ADDITIONAL {
        @Override
        public String toString() {
            return "ADDITIONAL";
        }
    }


}
