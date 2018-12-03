package org.soen387.dom.command.game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.deck.CardInputMapper;
import org.soen387.dom.Mapper.game.BenchInputMapper;
import org.soen387.dom.Mapper.game.DiscardInputMapper;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.Mapper.game.HandInputMapper;
import org.soen387.dom.POJO.deck.CardType;
import org.soen387.dom.POJO.deck.ICard;
import org.soen387.dom.POJO.game.BenchFactory;
import org.soen387.dom.POJO.game.DiscardFactory;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.Hand;
import org.soen387.dom.POJO.game.IBench;
import org.soen387.dom.POJO.game.IDiscard;
import org.soen387.dom.POJO.game.IHand;

public class GameMoveCommand extends ValidatorCommand {

	@Source
	public String version;
	
	public GameMoveCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		// TODO:Refactor to proper way (Command Design Pattern when more time)
		Long parsedUserId = null;
		Long gameId = null;
		Long vId = null;
		Long cId = null;
		String gameMove = null;
		Long deckId = null;
		try {
			
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			gameId = (long)helper.getRequestAttribute("gameId");
			cId = (long)helper.getRequestAttribute("handId");
			vId = Long.parseLong(version);
			gameMove = (String)helper.getRequestAttribute("action");
			
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Invalid Post parameters Id in Retire Command";
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
		
		
		// Validation Game
		if(parsedUserId != game.getChallengerId() 
				&& parsedUserId != game.getChallengeeId()) {
			String message = "That's not your game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(vId != game.getVersion()) {
			String message = "Please Reload the game";
			addNotification(message);
			throw new CommandException(message);
		}
//		// It's your turn
//		if(game.getCurrentTurn() != parsedUserId) {
//			String message = "It's not your turn";
//			addNotification(message);
//			throw new CommandException(message);
//		}
		
		if(parsedUserId == game.getChallengerId()
				&& game.getChallengerStatus().equalsIgnoreCase("retire")) {
			String message = "You/other player retire from game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		
		if(parsedUserId == game.getChallengeeId() 
				&& game.getChallengeeStatus().equalsIgnoreCase("retire")) {
			String message = "You/other player retire from game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// Grab DeckId
		if(parsedUserId == game.getChallengerId()) {
			deckId = game.getChallengerDeck();
		}else {
			deckId = game.getChallengeeDeck();
		}
		
		
		// Card Id must be in your hand...
		List<IHand> userHand = new ArrayList<IHand>();
		IHand tarHandObj = null;
		try {
			userHand = HandInputMapper.findByGameIdAndDeckId(deckId, gameId);
			
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't get the Hand of user " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(userHand == null || userHand.size() == 0) {
			String message = "Your hand is empty";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Does your hand contains the Card Id
		List<Long> handCardIds = new ArrayList<Long>();
		for(IHand hObj : userHand) {
			handCardIds.add(hObj.getCardId());
		}
		
		// Card Id is in hand
		for(IHand h : userHand) {
			if(h.getCardId() == cId) {
				tarHandObj = h;
			}
		}
		if(tarHandObj == null) {
			String message = "The target card is not in your hand (gen)";
			addNotification(message);
			throw new CommandException(message);
		}
		
		
		switch(gameMove) {
		case("energy"):
			//To energy action
			doEnergy(cId, game, tarHandObj, gameId, deckId);
			break;
		case("evolve"):
			//To evolve action
			doEvolve(cId, game, tarHandObj, gameId, deckId);
			break;
		default:
			//Play Trainer or Pokemon to bench
			doBasicCase(cId, game, tarHandObj, gameId, deckId);
			break;
		}
		
		
	}
	
	private void doBasicCase(Long cId, Game game, IHand hand, 
			Long gameId, Long deckId) throws CommandException {
		ICard card = null;
		if(UoW.getCurrent() == null) {
			UoW.newCurrent();
		}
		try {
			card = CardInputMapper.find(cId);
		} catch (SQLException | MapperException e) {
			e.printStackTrace();
			String message = "The target card is not in your hand (base)";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(card.getCardType() == CardType.t) {
			// User want to play Trainer
			try {
				
				DiscardFactory.createNew(hand.getCardId(),
						gameId, deckId, (long)0);
				UoW.getCurrent().registerRemoved(hand);
				UoW.getCurrent().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				String message = "Can't update on play trainer";
				addNotification(message);
				throw new CommandException(message);
			}
			
		}else if(card.getCardType() == CardType.p) {
			// User want to play Pokemon

			try {
				
				BenchFactory.createNew(hand.getCardId(),
						gameId, deckId);
				UoW.getCurrent().registerRemoved(hand);
				UoW.getCurrent().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				String message = "Can't update on play pokemon";
				addNotification(message);
				throw new CommandException(message);
			}
			
		}else {
			String message = "Operation not recognized";
			addNotification(message);
			throw new CommandException(message);
		}
		

		
	}
	
	private void doEvolve(Long cId, Game game, IHand hand, 
			Long gameId, Long deckId) throws CommandException {
		Long targetPokemonE = null;
		try {
			targetPokemonE = Long.parseLong((String)helper.getRequestAttribute("basic"));
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't parse card for evolve";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// Target Pokemon must be in bench
		List<IBench> benchCards = new ArrayList<IBench>();
		try {
			benchCards = BenchInputMapper.findByGameIdAndDeckId(deckId, gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't get bench for evolve";
			addNotification(message);
			throw new CommandException(message);
		}
		
		ICard cardInBench = null;
		IBench bcInBench = null;
		for(IBench b : benchCards) {
			if(b.getCardId() == targetPokemonE) {
				try {
					bcInBench = b;
					cardInBench = CardInputMapper.find(b.getCardId());
				}catch(Exception e) {
					e.printStackTrace();
					String message = "Can't get detailed bench for pokemon";
					addNotification(message);
					throw new CommandException(message);
				}
			}

		}
		
		ICard cardInHand = null;
		if(UoW.getCurrent() == null) {
			UoW.newCurrent();
		}
		try {
			cardInHand = CardInputMapper.find(cId);
		} catch (Exception e) {
			e.printStackTrace();
			String message = "The target card is not in your hand (ev)";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Grab Energy previously attached to card on Bench
		List<IDiscard> energies = new ArrayList<IDiscard>();
		try {
			energies = DiscardInputMapper.findAttachedCard(deckId, gameId, cardInBench.getId());
		}catch(Exception e) {
			e.printStackTrace();
			String message = "The can't get linked energy ";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// It's a poke and bench
		if(cardInHand.getCardType() == CardType.p 
				&& cardInHand.getBase().equalsIgnoreCase(cardInBench.getName())) {
			// User want to play Trainer
			try {
				
				//Update Energy card to point to new card (update)
				for(IDiscard e : energies) {
					e.setLinkCId(cardInHand.getId());
					UoW.getCurrent().registerDirty(e);
				}
				
				// Remove from Bench
				UoW.getCurrent().registerRemoved(bcInBench);
				
				// Remove from Hand
				UoW.getCurrent().registerRemoved(hand);
				
				// Send bench card to discard
				DiscardFactory.createNew(cardInBench.getId(),
						gameId, deckId, cardInHand.getId());
				
				// Send Hand to Bench (create)
				BenchFactory.createNew(cardInHand.getId(), gameId, deckId);
				

				


				UoW.getCurrent().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				String message = "Can't update on play energy";
				addNotification(message);
				throw new CommandException(message);
			}
			
		}else {
			String message = "Card is not a pokemon";
			addNotification(message);
			throw new CommandException(message);
		}
	}
	
	private void doEnergy(Long cId, Game game, IHand hand, 
			Long gameId, Long deckId) throws CommandException {
		
		Long targetPokemonE = null;
		try {
			targetPokemonE = Long.parseLong((String)helper.getRequestAttribute("pokemon"));
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't parse card for energy";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// Target Pokemon must be in bench
		List<IBench> benchCards = new ArrayList<IBench>();
		try {
			benchCards = BenchInputMapper.findByGameIdAndDeckId(deckId, gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't get bench for energy";
			addNotification(message);
			throw new CommandException(message);
		}
		
		ICard cardInBench = null;
		for(IBench b : benchCards) {
			if(b.getCardId() == targetPokemonE) {
				try {
					cardInBench = CardInputMapper.find(b.getCardId());
				}catch(Exception e) {
					e.printStackTrace();
					String message = "Can't get detailed bench for pokemon (energy)";
					addNotification(message);
					throw new CommandException(message);
				}
			}

		}
		
		ICard cardInHand = null;
		if(UoW.getCurrent() == null) {
			UoW.newCurrent();
		}
		try {
			cardInHand = CardInputMapper.find(cId);
		} catch (SQLException | MapperException e) {
			e.printStackTrace();
			String message = "The target card is not in your hand (energy)";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// It's a poke and bench
		if(cardInHand.getCardType() == CardType.e 
				&& cardInBench.getCardType() == CardType.p) {
			// User want to attach energy
			try {
				
				//Remove from Hand
				UoW.getCurrent().registerRemoved(hand);
				// Create in Discard with link to bench pokemon
				DiscardFactory.createNew(cardInHand.getId(), gameId, deckId, cardInBench.getId());
				//Done 
				UoW.getCurrent().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				String message = "Can't update on play energy, wrong target card (energy)";
				addNotification(message);
				throw new CommandException(message);
			}
			
		}else {
			String message = "Card is not a pokemon";
			addNotification(message);
			throw new CommandException(message);
		}
		
	}

}
