package testsDev;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestHasher {
	@Test
	public void HashSomething() {
		String plaintext = "your text here, wow this is an awesome computer bla bla bla";
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("SHA-256");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			
			assertEquals(hashtext, "8b37fbca8d64b2fb8decbe5f1e5c0d6fd48b8427832ef2557f18ea0a19a67926");
			
			System.out.println("Password: " + hashtext);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
