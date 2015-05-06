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

import java.io.File;
import java.awt.Image;
import org.apache.commons.io.FileUtils;
import javax.imageio.ImageIO;

public class LocalImageStorage implements ImageStorage {
	private String directory = "";

	public LocalImageStorage() {
		try {
			directory = MedicalConfigurator.LOCAL_IMAGE_DIRECTORY;
			File directoryFile = new File(directory);
			directoryFile.mkdirs();
		} catch(Exception e) {
			System.out.println("Error while making directory for image storage.");
		}
	}

	public boolean storeImage(File image, String keyName) {
		try {
			File outputFile = new File(directory + "/" + keyName);
			FileUtils.copyFile(image, outputFile);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateImage(File image, String keyName) {
		deleteImage(keyName);
		return storeImage(image, keyName);
	}

	public Image getImage(String keyName) {
		try {
			File file = new File(directory + "/" + keyName);
			return ImageIO.read(file);
		} catch(Exception ioe) {
			return null;
		}
	}

	public boolean deleteImage(String keyName) {
		try {
			File file = new File(directory + "/" + keyName);
			return file.delete();
		} catch(Exception e) {
			return false;
		}
	}

	public boolean deleteDirectoryIfEmpty() {
		try {
			File directoryFile = new File(directory);
			return (directoryFile.list().length == 0) && directoryFile.delete();
		} catch(Exception e) {
			return false;
		}
	}
}