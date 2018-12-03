package org.soen387.dom.command.game;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.Mapper.game.HandInputMapper;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.IHand;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ViewHandCommand extends ValidatorCommand {

	public ViewHandCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
		Long gameId = null;
			gameId = Long.parseLong((String)helper.getRequestAttribute("gameId"));

		Long deckId = null;
		// Get the Game
		Game game = null;
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
		
		if(game.getChallengeeId() == parsedUserId) {
			deckId = game.getChallengeeDeck();
		}else {
			deckId = game.getChallengerDeck();
		}
		
		//We need to find the deck used in this game...
		List<IHand> ham = new ArrayList<IHand>();
		try {
			ham = HandInputMapper.findByGameIdAndDeckId(deckId, gameId); 
		}catch(Exception e) {
			System.out.println("You don't have hand...");
		}
		
		// Attempt to Get Data
		//From https://stackoverflow.com/questions/37091548/convert-arraylist-with-gson-to-string
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<Long> handJson = new ArrayList<Long>();
		
			for(IHand h : ham) {
				Long tmp = h.getCardId();
				handJson.add(tmp);
			}
			
			// Change back to array
			String jsonH = gson.toJson(handJson);
			System.out.println(jsonH);
			helper.setRequestAttribute("hand", jsonH);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Can't find Hand data " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
		}
		
	}

}
