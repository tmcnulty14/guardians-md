package com.guardiansofthegalaxy.guardians_md.dbtests;

import com.guardiansofthegalaxy.guardians_md.db.S3ImageStorage;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * A Junit test class. This class systematically tests every method of the S3ImageStorage class.
 **/
public class S3Test {
	private static final String TEST_IMAGE = ConfigDirectory.getImageFileFromDirectory("Home_button.png");
	private static final String TEST_KEY = "test.png";
	private static final String TEST_KEY_PREFIX = "test";
	private static final String TEST_KEY_SUFFIX = "png";

	private static S3ImageStorage s3Conn = null;

	@BeforeClass
	/**
	 * Runs before all the tests run. Makes a new S3ImageStorage connection.
	 **/
	public static void connectToS3() {
		s3Conn = new S3ImageStorage();
	}

	@Test
	/**
	 * Tests image storage and retrieval on the S3 bucket.
	 **/
	public void testImage() {
		try {
			// Load a simple test image from our resources.
			URL url = S3Test.class.getClassLoader().getResource(TEST_IMAGE);
			Image testImage = ImageIO.read(url); 
			Assert.assertNotNull(testImage);

			// Copy the image to a temp file with a known location.
			File testFile = File.createTempFile(TEST_KEY_PREFIX, TEST_KEY_SUFFIX);
			FileUtils.copyURLToFile(url, testFile);

			// Upload the image to S3.
			Assert.assertTrue(s3Conn.storeImage(testFile, TEST_KEY));

			// Download the image from S3.
			Image image = s3Conn.getImage(TEST_KEY);
			Assert.assertNotNull(image);

			// Due to the way equality of BufferedImages is handled, the redownloaded image will not be considered "equal".
			// Therefore this assertion will fail, and we can't test for this without examining the data of the BufferedImage on a closer level.
			//Assert.assertEquals(testImage, image);

			// Delete the image from S3 and from the local temp folder.
			Assert.assertTrue(s3Conn.deleteImage(TEST_KEY));
			Assert.assertTrue(testFile.delete());
		} catch(IOException ioe) {
			// Fail the test if there is any sort of exception.
			Assert.fail();
		}
	}
}