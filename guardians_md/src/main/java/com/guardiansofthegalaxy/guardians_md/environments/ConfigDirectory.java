package com.guardiansofthegalaxy.guardians_md.environments;

/**
 * Created by Christina on 4/10/2015.
 */
public class ConfigDirectory {

    public static String imageDirectory = "images";

    public static String getImageFileFromDirectory(String filename){
        return imageDirectory + "/" + filename;
    }

}