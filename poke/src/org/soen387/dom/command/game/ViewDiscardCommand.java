package org.soen387.dom.command.game;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.game.DiscardInputMapper;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.IDiscard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ViewDiscardCommand extends ValidatorCommand {

	public ViewDiscardCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;		
		Long gameId = null;
		Long deckId = null;
		Game game = null;
		Long tPly = null;
		
		try {
			gameId = (long)helper.getRequestAttribute("gameId");
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			tPly = (long)helper.getRequestAttribute("player");
			System.out.println("User Id: " + parsedUserId.toString());
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't parse data in View Dicard " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
		}

		try {
			game = GameInputMapper.find(gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Can't find the Game " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule can only view your hand
		if(game.getChallengerId() != parsedUserId && game.getChallengeeId() != parsedUserId) {
			String message = "That's not your game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule Target Player must also be in game
		if(game.getChallengerId() != tPly && game.getChallengeeId() != tPly) {
			String message = "Target player is not in your game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// Get Deck Id of target player
		if(game.getChallengeeId() == tPly) {
			deckId = game.getChallengeeDeck();
		}else {
			deckId = game.getChallengerDeck();
		}
		
		//We need to find the deck used in this game...
		List<IDiscard> dis = new ArrayList<IDiscard>();
		try {
			dis = DiscardInputMapper.findByGameIdAndDeckId(deckId, gameId); 
		}catch(Exception e) {
			System.out.println("You don't have hand...");
		}
		
		// Attempt to Get Data
		//From https://stackoverflow.com/questions/37091548/convert-arraylist-with-gson-to-string
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<Long> handJson = new ArrayList<Long>();
		
			for(IDiscard d : dis) {
				Long tmp = d.getCardId();
				handJson.add(tmp);
			}
			
			// Change back to array
			String jsonH = gson.toJson(handJson);
			System.out.println(jsonH);
			helper.setRequestAttribute("discard", jsonH);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Can't find discard data " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
		}
		
	}

}
