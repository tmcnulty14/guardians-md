package com.guardiansofthegalaxy.guardians_md.db;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * This class is used for everything related to password security.
 * It provides methods for encrypting a password into a salt + hash,
 * and for checking/validating a password against a stored salt + hash.
 **/
public class PasswordEncryption {
	private static final SecureRandom random = new SecureRandom();

	/**
	 * Checks a given password against a given pair of salt and hash strings.
	 * The method generates a hash of the password + saltString, and checks if it
	 * matches the hashString.
	 **/
	public static boolean checkPassword(String password, String saltString, String hashString) {
		byte[] salt = DatatypeConverter.parseHexBinary(saltString);

		byte[] checkHash = generateHash(password, salt);
		String checkString = DatatypeConverter.printHexBinary(checkHash);

		return hashString.equals(checkString);
	}

	/**
	 * Encrypts the given password by assigning a salt to the password, hashing
	 * the salted password, and returning an array containing the hexadecimal
	 * string representations of the salt and the hash. The array will always
	 * have a length of 2.
	 **/
	public static String[] encryptPassword(String password) {
		byte[] salt = new byte[24];
		random.nextBytes(salt);
		String saltString = DatatypeConverter.printHexBinary(salt);

		byte[] hash = generateHash(password, salt);
		String hashString = DatatypeConverter.printHexBinary(hash);

		String[] crypto = {saltString, hashString};
		return crypto;
	}

	/**
	 * Uses the PBKDF2 hashing algorithm to generate a hash of the password + salt.
	 * This will always return the same hashcode if given the same password and salt.
	 *
	 * The entire method is basically just a single method call to 
	 * SecretKeyFactory.generateSecretKey(...), with some wrapping to format input + output.
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
}