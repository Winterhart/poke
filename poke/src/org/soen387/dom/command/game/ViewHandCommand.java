package org.soen387.dom.command.game;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.game.HandInputMapper;
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
		//We need to find the deck used in this game...
		
		
		// Attempt to Get Data
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<IHand> ham = HandInputMapper.findByGameIdAndDeckId(deckId, gameId); 
			List<Long> handJson = new ArrayList<Long>();
		
			for(IHand h : ham) {
				handJson.add(h.getCardId());
			}
			
			// Change back to array
			Long[] handJ = new Long[handJson.size()];
			handJ = handJson.toArray(handJ);
			
			String jsonH = gson.toJson(handJ);
			helper.setRequestAttribute("hand", jsonH);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Can't find Hand data " + e.getMessage();
			addNotification(message);
		}
		
	}

}
