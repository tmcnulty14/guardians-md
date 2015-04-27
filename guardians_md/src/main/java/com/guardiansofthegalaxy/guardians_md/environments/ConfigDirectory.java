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