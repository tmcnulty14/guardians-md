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
import java.io.IOException;
import javax.imageio.ImageIO;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * S3ImageStorage makes a connection to an Amazon AWS S3 bucket.
 * It then uploads image files to the bucket and downloads
 * Images from the bucket, based on a String key.
 *
 * This class was built with the help of Amazon's AWS documentation for java developers.
 **/
public class S3ImageStorage implements ImageStorage {
	// AWS credentials
	private final String ACCESS_KEY_ID;
	private final String SECRET_ACCESS_KEY;
	private final String BUCKET_NAME;

	private final AmazonS3 s3Client;

	/**
	 * Constructor. Loads AWS credentials and creates a new client connection to the S3 bucket.
	 **/
	public S3ImageStorage() {
		BUCKET_NAME = MedicalConfigurator.S3_BUCKET_NAME;
		ACCESS_KEY_ID = MedicalConfigurator.S3_KEY_ID;
		SECRET_ACCESS_KEY = MedicalConfigurator.S3_SECRET_KEY;

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
		s3Client = new AmazonS3Client(awsCreds);
	}

	/**
	 * Stores an image from the specified file on the AmazonS3 bucket using the specified keyName.
	 * @param image The File where the image is stored.
	 * @param keyName The name to upload the image to S3 under.
	 **/
	public boolean storeImage(File image, String keyName) {
		try {
            s3Client.putObject(new PutObjectRequest(BUCKET_NAME, keyName, image));
            return true;
        } catch (AmazonServiceException ase) {
            System.out.println("AmazonServerException: Error on S3 Server.");
        } catch (AmazonClientException ace) {
            System.out.println("AmazonClientException: Could not reach S3 server.");
        }
        return false;
	}

	/**
	 * Stores an image from the specified file on the AmazonS3 bucket using the specified keyName.
	 * First deletes any image that already exists under that keyName.
	 * @param image The File where the image is stored.
	 * @param keyName The name to upload the image to S3 under.
	 **/
	public boolean updateImage(File image, String keyName) {
	 	deleteImage(keyName);
	 	return storeImage(image, keyName);
	}

	/**
	 * Downloads an image with the given keyName from the AmazonS3 bucket.
	 * @param keyName The key used to pull the image from S3.
	 * @return An Image object.
	 **/
	public Image getImage(String keyName) {
		try {
			S3Object object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, keyName));
			Image image = ImageIO.read(object.getObjectContent());
			return image;
		} catch(IOException ioe) {
			System.out.println("IOException while reading image from input stream.");
			return null;
		} catch (AmazonServiceException ase) {
            System.out.println("AmazonServerException: Error on S3 Server.");
        } catch (AmazonClientException ace) {
            System.out.println("AmazonClientException: Could not reach S3 server.");
        }
        return null;
	}

	/**
	 * Deletes an image with the given keyName from the AmazonS3 bucket.
	 * @param keyName The key used to delete the image from S3.
	 * @return true If deleted successfully.
	 **/
	public boolean deleteImage(String keyName) {
		try {
            s3Client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, keyName));
            return true;
        } catch (AmazonServiceException ase) {
            System.out.println("AmazonServerException: Error on S3 Server.");
        } catch (AmazonClientException ace) {
            System.out.println("AmazonClientException: Could not reach S3 server.");
        }
        return false;
	}
}