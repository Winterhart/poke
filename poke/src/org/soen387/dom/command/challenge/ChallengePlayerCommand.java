package org.soen387.dom.command.challenge;


import java.sql.SQLException;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.deck.DeckInputMapper;
import org.soen387.dom.POJO.challenge.ChallengeFactory;
import org.soen387.dom.POJO.deck.IDeck;

public class ChallengePlayerCommand extends ValidatorCommand  {

	@Source
	public String deck;
	
	public ChallengePlayerCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;
		Long targetUserId = null;
		Long initDeckId = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			String targetUser = (String)helper.getAttribute("targetId");
			targetUserId = Long.parseLong(targetUser);
			
			String initialDeck = deck;
			initDeckId = Long.parseLong(initialDeck);
			
		}catch(Exception e) {
			String message = "Invalid Deck/User/TargetUser Id";
			addNotification(message);
			throw new CommandException(message);
		}

		
		//Ensure Parsing right
		if(targetUserId == null || parsedUserId == null || initDeckId == null) {
			String message = "Can't detect user or target or deck Id";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule: Deck must exists
		try {
			IDeck deckTarget = DeckInputMapper.find(initDeckId);
			if(deckTarget.getOwnerId() != parsedUserId) {
				String message = "You don't own this deck";
				addNotification(message);
				throw new CommandException(message);
			}
		} catch (SQLException | MapperException e1) {
			e1.printStackTrace();
			String message = "Deck doesn't exists...";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule: Can't Challenge yourself
		if(targetUserId == parsedUserId) {
			String message = "You can't challenge yourself";
			addNotification(message);
			throw new CommandException(message);
		}
		//Target User Must exists
		try {
			UserInputMapper.find(targetUserId);
		} catch (Exception e1) {
			e1.printStackTrace();
			String message = "Target user id doesn't exists";
			addNotification(message);
			throw new CommandException(message);
		}
		// Attempt to create a Challenge
		try {
			ChallengeFactory.createNew(parsedUserId, targetUserId, initDeckId, 0);
			UoW.getCurrent().commit();
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Not able to create the Challenge";
			addNotification(message);
			throw new CommandException(message);
			
		}
		
	}

}
