package DevTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.soen387.app.DataSource.DatabaseConnector;

class TestByDEV {
	


	@Test
	void testReadAllTables() {
		Connection aCon;
		int success = 0;
		aCon = DatabaseConnector.getConnection();
		ResultSet results = null;
		try {
			Statement statement = aCon.createStatement();
			results = statement.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
			System.out.println(results.toString());

			while(results.next()) {
				System.out.println(results.getString("TABLE_NAME"));
				success++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success < 3) {
			fail("Not yet implemented");
		}

		
	}
	
	@Test
	void testPasswordCreator() {
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
			
			System.out.println("Password: " + hashtext);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
