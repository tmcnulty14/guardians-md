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
public class S3ImageStorage {
	// AWS credentials
	private static final String ACCESS_KEY_ID2="R7RYGUY6ZQ";
	private static final String ACCESS_KEY_ID1="AKIAIJI6AN";
	private static final String ACCESS_KEY_ID=ACCESS_KEY_ID1+ACCESS_KEY_ID2;
	private static final String SECRET_ACCESS_KEY3="UM24pOpFa";
	private static final String SECRET_ACCESS_KEY2="xsdNTJEohy8w";
	private static final String SECRET_ACCESS_KEY4="K3T+AqWBsJ";
	private static final String SECRET_ACCESS_KEY1="fyS6SMYfU";
	private static final String SECRET_ACCESS_KEY= SECRET_ACCESS_KEY1+SECRET_ACCESS_KEY2+SECRET_ACCESS_KEY3+SECRET_ACCESS_KEY4;
	private static final String BUCKET_NAME="guardians-md-lab-pictures";

	private final AmazonS3 s3Client;

	/**
	 * Constructor. Loads AWS credentials and creates a new client connection to the S3 bucket.
	 **/
	public S3ImageStorage() {
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