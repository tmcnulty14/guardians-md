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
package com.guardiansofthegalaxy.guardians_md.db;

import java.awt.Image;
import java.io.File;

public interface ImageStorage {
	public boolean storeImage(File image, String keyName);

	public boolean updateImage(File image, String keyName);

	public Image getImage(String keyName);

	public boolean deleteImage(String keyName);
}