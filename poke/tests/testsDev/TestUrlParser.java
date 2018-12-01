package testsDev;

import static org.junit.Assert.*;

import org.junit.Test;
import org.soen387.util.UrlParser;

public class TestUrlParser {

	@Test
	public void testId() {
		String url = "/Robert/23";
		String url2 = "/Rbobew/fieemm/44/esosmm";
		
		int urlNumber = Integer.parseInt(UrlParser.getIdInUR(url));
		int urlNumber2 = Integer.parseInt(UrlParser.getIdInUR(url2));
		
		assertTrue(urlNumber == 23);
		assertTrue(urlNumber2 == 44);
		
		
	}
	
	
	@Test
	public void testLastWord() {
		String url = "/Robert/23/Dmeimg/Coucou";
		
		String word = UrlParser.getLastWord(url);
		assertTrue(word.equalsIgnoreCase("coucou"));
		
		
	}
	
	

}
