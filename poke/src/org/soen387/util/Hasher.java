package org.soen387.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

	/**
	 * Snippet from https://www.baeldung.com/sha-256-hashing-java
	 * @param textIn
	 * @return
	 */
    public static String obtainHashText(String textIn) {
		String hashedText = null;
    	try {
			MessageDigest mess = MessageDigest.getInstance("SHA-256");
			mess.reset();
			mess.update(textIn.getBytes());
			byte[] digest = mess.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashedText = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashedText.length() < 32 ){
			  hashedText = "0"+ hashedText;
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return hashedText;
    }
}
