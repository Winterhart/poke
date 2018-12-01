package org.soen387.dom.command.deck;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.deck.DeckInputMapper;
import org.soen387.dom.POJO.deck.IDeck;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ViewDecksCommand extends ValidatorCommand  {

	public ViewDecksCommand(Helper helper) {
		super(helper);
	}
	@Override
	public void process() throws CommandException {
		
		
		//Grab User Id
		Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
		Long parsedUserId = Long.parseLong(rawUserId.toString());
		System.out.println("User Id: " + parsedUserId.toString());
		
		try {
			List<IDeck> decks = DeckInputMapper.findAllForUserId(parsedUserId);
			List<Long> deckFormatted = new ArrayList<Long>();
			for(IDeck d: decks) {
				deckFormatted.add(d.getId());
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String deckIds = gson.toJson(deckFormatted);
			helper.setRequestAttribute("decks", deckIds);
			
			
		} catch (SQLException | MapperException  e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e.getMessage());
		}
		
		
	}

}
