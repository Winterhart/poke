package org.soen387.util;

import java.util.ArrayList;
import java.util.List;

import org.soen387.dom.Helper.CardHelper;

public class DeckParser {
	public static List<CardHelper> parseDeck(String deck){
		List<CardHelper> cards = new ArrayList<CardHelper>();
		
		try {
			if(deck.isEmpty() || deck == null) {
				return null;
			}
			
			String[] splittedDeck =  deck.split("\n");
			
			
			for(String s: splittedDeck) {

				String[] splittedData = s.split(" ");
				String cardName = splittedData[1];
				String cardType = splittedData[0];
				String base = "";
				
				if(splittedData.length > 2) {
					//it contains base
					base = splittedData[2];
				}
				
				cardName = cardName.replaceAll("\"", "");
				cardType = cardType.replaceAll("\"", "");
				base = base.replaceAll("\"", "");
				
				
				boolean isTypeOkay = cardType.equals("e") || cardType.equals("t") || cardType.equals("p");
				boolean isNameOkay =(!cardName.isEmpty() && cardName != null);
				if(isTypeOkay && isNameOkay) 
				{
					CardHelper card = new CardHelper(cardName, cardType, base);
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
