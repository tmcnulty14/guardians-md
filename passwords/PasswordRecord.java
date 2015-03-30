package passwords;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordRecord {
	private static final SecureRandom random = new SecureRandom();
	private final String saltString;
	private final String hashString;

	/**
	 * Creates a password record by assigning a salt to the password,
	 * and then generating a hash of the password + salt.
	 **/
	public PasswordRecord(String password) {
		byte[] salt = new byte[24];
		random.nextBytes(salt);
		saltString = DatatypeConverter.printHexBinary(salt);

		byte[] hash = generateHash(password, salt);
		hashString = DatatypeConverter.printHexBinary(hash);
	}

	public PasswordRecord(String hash, String salt) {
		saltString = salt;
		hashString = hash;
	}

	/**
	 * Checks a password using this PasswordRecord's hash and salt.
	 **/
	public boolean checkLogin(String password) {
		return checkLogin(password, hashString, saltString);
	}

	@Override
	public String toString() {
		return saltString + ":" + hashString;
	}

	/**
	 * Getter for hashString
	 **/
	public String getHash() {
		return hashString;
	}
	/**
	 * Getter for saltString
	 **/
	public String getSalt() {
		return saltString;
	}

	/**
	 * Uses the PBKDF2 hashing algorithm to generate a hash of the password + salt.
	 **/
	private static byte[] generateHash(String password, byte[] salt) {
		byte[] hash;
		try {
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 24 * 8);
    		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    		hash = skf.generateSecret(spec).getEncoded();
    	} catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
    		hash = new byte[0];
    		System.out.println("Error: " + e.getClass());
    	}
		return hash;
	}	

	/**
	 * Checks a given login against a given hash string and salt string.
	 **/
	public static boolean checkLogin(String password, String hashString, String saltString) {
		byte[] salt = DatatypeConverter.parseHexBinary(saltString);
		byte[] checkHash = generateHash(password, salt);
		String checkString = DatatypeConverter.printHexBinary(checkHash);
		return hashString.equals(checkString);
	}
}