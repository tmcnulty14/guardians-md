package com.guardiansofthegalaxy.guardians_md.dbtests;

import com.guardiansofthegalaxy.guardians_md.db.*;
import com.guardiansofthegalaxy.guardians_md.environments.ConfigDirectory;

import org.junit.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class S3Test {
	private static final String TEST_IMAGE = ConfigDirectory.getImageFileFromDirectory("Home_button.png");
	private static final String TEST_KEY = "test.png";

	private static S3ImageStorage s3Conn = null;

	@BeforeClass
	public static void connectToS3() {
		s3Conn = new S3ImageStorage();
	}

	@Test
	public void testImage() {
		try {
			URL url = S3Test.class.getClassLoader().getResource(TEST_IMAGE);
			Image testImage = ImageIO.read(url); 
			Assert.assertNotNull(testImage);

			File testFile = new File(TEST_KEY);
			FileUtils.copyURLToFile(url, testFile);

			Assert.assertTrue(s3Conn.storeImage(testFile, TEST_KEY));
			Image image = s3Conn.getImage(TEST_KEY);
			Assert.assertNotNull(image);

			// Due to the way equality of BufferedImages is handled, the redownloaded image will not be considered "equal".
			// Therefore this assertion will fail.
			//Assert.assertEquals(testImage, image);

			Assert.assertTrue(s3Conn.deleteImage(TEST_KEY));
			testFile.delete();
		} catch(IOException ioe) {
			Assert.fail();
		}
	}
}