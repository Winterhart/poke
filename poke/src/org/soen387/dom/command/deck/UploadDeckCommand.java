package org.soen387.dom.command.deck;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Helper.CardHelper;
import org.soen387.dom.POJO.deck.CardFactory;
import org.soen387.dom.POJO.deck.Deck;
import org.soen387.dom.POJO.deck.DeckFactory;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.deck.IDeck;
import org.soen387.util.DeckParser;

public class UploadDeckCommand extends ValidatorCommand  {
	
	public UploadDeckCommand(Helper helper) {
		super(helper);
	}

	@Source
	public String deck;
	
	@SetInRequestAttribute
	public IDeck deckToAdd;
	
	@SetInRequestAttribute
	public ICard cardToAdd;
	

	@Override
	public void process() throws CommandException {
		//Grab Deck in request
		List<CardHelper> rawDeck = DeckParser.parseDeck(deck);
		
		//Grab User Id
		Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
		Long parsedUserId = Long.parseLong(rawUserId.toString());
		System.out.println("User Id: " + parsedUserId.toString());
		
		Deck addedDeck = null;
		try {
			addedDeck = DeckFactory.createNew(parsedUserId);
		} catch (SQLException | MapperException e) {
			System.out.println("Error while Adding Deck");
			e.printStackTrace();
			throw new CommandException("Error while adding deck");
		}

		if(addedDeck == null) {
			//Failure to add Deck...
			throw new CommandException("Failure to add Deck");
		}
		if(rawDeck.size() != 40) {
			throw new CommandException("Deck is not in right format");
		}
		//For each card try to insert
		for(CardHelper ch : rawDeck) {
			try {
				CardFactory.createNew(ch.getCardName(), ch.getCardType(), addedDeck.getId(), "");
			} catch (SQLException | MapperException e) {
				System.out.println("Error while Adding Card");
				e.printStackTrace();
				throw new CommandException("Error while adding Card");
			}
		}
		
		String message = "Deck Created";
		helper.setRequestAttribute("message", message);
		
		
	}

}
