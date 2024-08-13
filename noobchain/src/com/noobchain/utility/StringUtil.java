package com.noobchain.utility;

import java.security.MessageDigest;

/**
 * Utility class to generate the encrypted hash/digital fingerprints for the
 * blocks
 */
public class StringUtil {
	// Applying SHA256 cryptographic algorithm to a string and return result.
	public static String applySHA256(String input) {
		try {
			// message digest instance
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// apply digest/SHA-256 to our input, this returns hash as a byte array
			byte[] hash = digest.digest(input.getBytes("UTF-8"));

			// to contain the hash as hexadecimal String
			StringBuffer hexString = new StringBuffer();

			// iterating over each byte in the hash, convert to hexadecimal string and
			// append to the StringBuffer, for better display, comparison and storage
			for (int i = 0; i < hash.length; i++) {
				// byte as an unsigned value
				String hex = Integer.toHexString(0xff & hash[i]);

				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);

			}
			return hexString.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	public StringUtil() {
		// TODO Auto-generated constructor stub
	}

}
