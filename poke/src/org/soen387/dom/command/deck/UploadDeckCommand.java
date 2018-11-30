package org.soen387.dom.command.deck;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.Util.DeckParser;
import org.soen387.dom.Helper.CardHelper;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.deck.IDeck;

public class UploadDeckCommand extends ValidatorCommand  {

	@Source
	public String deck;
	
	@SetInRequestAttribute
	public IDeck deckToAdd;
	
	@SetInRequestAttribute
	public ICard cardToAdd;
	
	public UploadDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		List<CardHelper> rawDeck = DeckParser.parseDeck(deck);
		
		
		
	}

}
