package org.soen387.dom.command.game;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.deck.CardInputMapper;
import org.soen387.dom.Mapper.game.BenchInputMapper;
import org.soen387.dom.Mapper.game.DiscardInputMapper;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.Mapper.game.HandInputMapper;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.HandFactory;
import org.soen387.dom.POJO.game.IBench;
import org.soen387.dom.POJO.game.IDiscard;
import org.soen387.dom.POJO.game.IHand;

public class DrawCardCommand extends ValidatorCommand {

	public DrawCardCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long parsedUserId = null;
		Long gameId = null;
		String status = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			gameId = (long)helper.getRequestAttribute("gameId");
			
			status= (String)helper.getRequestAttribute("stat");
			
			
		}catch(Exception e) {
			String message = "Invalid Post parameters Id in Draw Card";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule: Game must exists
		Game game = null;
		try {
			game = GameInputMapper.find(gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Game doesn't exists...";
			addNotification(message);
			throw new CommandException(message);
			
		}
		if(game.getNumberOfTurn() == 0 && status.equals("fresh") ) {
			// allowing to draw card
			
		}else {
			//Must be current turn for User
			if(game.getCurrentTurn() != parsedUserId) {
				String message = "It's not your turn or game...";
				addNotification(message);
				throw new CommandException(message);
			}
		}

		// Which deck must play
		Long deckId = null;
		if(game.getCurrentTurn() == game.getChallengerId()) {
			deckId = game.getChallengerDeck();
		}else {
			deckId = game.getChallengeeDeck();
		}
		
		// Deck must not be empty
		List<ICard> deck = new ArrayList<ICard>();
		List<IHand> hand = new ArrayList<IHand>();
		List<IBench> bench = new ArrayList<IBench>();
		List<IDiscard> dis = new ArrayList<IDiscard>();
		
		//Grab cards
		try {
			deck = CardInputMapper.findAllByDeckId(deckId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't find cards...";
			addNotification(message);
			throw new CommandException(message);
		}
		try {
			hand = HandInputMapper.findByGameIdAndDeckId(deckId, gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't find hand...";
			addNotification(message);
			throw new CommandException(message);
		}
		try {
			bench = BenchInputMapper.findByGameIdAndDeckId(deckId, gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't find bench...";
			addNotification(message);
			throw new CommandException(message);
		}
		try {
			dis = DiscardInputMapper.findByGameIdAndDeckId(deckId, gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't find dicard...";
			addNotification(message);
			throw new CommandException(message);
		}
		


		
		//Which card must be draw next
		Long cardIdToPick = null;
		int count = hand.size() + bench.size() + dis.size();
		if(count == deck.size()) {
			String message = "You have no card left";
			addNotification(message);
			throw new CommandException(message);
		}else {
			cardIdToPick = deck.get(count).getId();
		}
		
		if(hand.size() == 7) {
			String message = "Your hand is full";
			addNotification(message);
			throw new CommandException(message);
		}
		
		try {
			HandFactory.createNew(cardIdToPick, gameId, deckId);
			UoW.getCurrent().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Game could not be created...";
			addNotification(message);
			throw new CommandException(message);
		}
		
	}

}
