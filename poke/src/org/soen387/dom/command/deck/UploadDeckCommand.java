package org.soen387.dom.command.deck;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Helper.CardHelper;
import org.soen387.dom.Mapper.deck.DeckOutputMapper;
import org.soen387.dom.POJO.deck.CardFactory;
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
		List<CardHelper> rawDeck = DeckParser.parseDeck(deck);
		
		helper.getSessionAttribute("");
		
		String message = "Deck Created";
		helper.setRequestAttribute("message", message);
		
		
	}

}
