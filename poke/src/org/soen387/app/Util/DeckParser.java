package org.soen387.app.Util;

import java.util.ArrayList;
import java.util.List;

import org.soen387.app.Domain.Helper.CardHelper;

public class DeckParser {
	public static String TEST_DECK1 =
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"p \"Meowth\"\n" +
			"e \"Fire\"\n" +
			"t \"Misty\"\n" +
			"t \"Misty\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"p \"Meowth\"\n" +
			"e \"Fire\"\n" +
			"t \"Misty\"\n" +
			"t \"Misty\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"p \"Meowth\"\n" +
			"e \"Fire\"\n" +
			"t \"Misty\"\n" +
			"t \"Misty\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"e \"Fire\"\n" +
			"p \"Charizard\"\n" +
			"e \"Fire\"\n";
	public static String TEST_DECK3_BAD =
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n";
	
	//Too Many
	public static String TEST_DECK4_BAD =
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"p \"Pikachu\"\n" +
			"p \"Meowth\"\n" +
			"e \"Lightning\"\n" +
			"t \"Tierno\"\n" +
			"t \"Tierno\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n" +
			"e \"Lightning\"\n";
	
	
	public static List<CardHelper> parseDeck(String deck){
		List<CardHelper> cards = new ArrayList<CardHelper>();
		
		try {
			if(deck.isEmpty() || deck == null) {
				return null;
			}
			
			String[] splittedDeck =  deck.split(System.getProperty("line.separator"));
			
			
			for(String s: splittedDeck) {
				String[] splittedData = s.split("\"");
				String cardName = splittedData[1];
				String cardType = splittedData[0];
				
				cardName = cardName.replaceAll("\"", "");
				cardType = cardType.replaceAll("\"", "");		
				char typeToChar = cardType.charAt(0);
				boolean isTypeOkay = (typeToChar == 'e' || typeToChar == 't' || typeToChar == 'p');
				boolean isNameOkay =(!cardName.isEmpty() && cardName != null);
				if(isTypeOkay && isNameOkay) 
				{
					CardHelper card = new CardHelper(cardName, cardType);
					cards.add(card);
				}else {
					return null;
				}
			}
			
			if(cards.size() != 40) {
				return null;
			}
			
			
		}catch(Exception ee) {
			System.out.println("Problem while parsing : " + ee.getMessage());
			return null;
		}

		
		return cards;
		
		
	}
}
