package org.soen387.dom.command.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.deck.DeckInputMapper;
import org.soen387.dom.POJO.challenge.Challenge;
import org.soen387.dom.POJO.challenge.ChallengeStatus;
import org.soen387.dom.POJO.deck.IDeck;
import org.soen387.dom.POJO.game.GameFactory;
import org.soen387.dom.command.game.CreateGameCommand;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;

public class AcceptCommand extends ValidatorCommand  {
	
	@Source
	public String deck;
	
	@Source
	public String version;

	public AcceptCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;
		Long challengeId = null;
		Long deckIdTobeUsed = null;
		Long chaVersion = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			String targetCha = (String)helper.getAttribute("targetId");
			challengeId = Long.parseLong(targetCha);
			
			String deckUse = deck;
			deckIdTobeUsed = Long.parseLong(deckUse);
			
			String versionC = version;
			chaVersion = Long.parseLong(versionC);
			
		}catch(Exception e) {
			String message = "Invalid Post parameters Id";
			addNotification(message);
			throw new CommandException(message);
		}

		
		//Ensure Parsing right
		if(challengeId == null || parsedUserId == null || 
				deckIdTobeUsed == null || chaVersion == null) {
			String message = "Can't detect user or target or deck Id";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule: Challenge must exists
		Challenge cha = null;
		try {
			cha = ChallengeInputMapper.find(challengeId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Challenge doesn't exists...";
			addNotification(message);
			throw new CommandException(message);
			
		}
		
		//Business Rule: Can you accept it...
		if(cha.getChallengeeId() != parsedUserId) {
			String message = "You can't accept this challenge";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule: Deck must exists
		try {
			IDeck deckTarget = DeckInputMapper.find(deckIdTobeUsed);
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
		
		if(cha.getStatus() != 0) {
			String message = "Challenge's status already completed";
			addNotification(message);
			throw new CommandException(message);
		}
		
		// Attempt to Update a Challenge
		try {
			cha.setVersion(chaVersion);
			cha.setStatus(3);

			UoW.getCurrent().registerDirty(cha);
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Not able to Update Challenge";
			addNotification(message);
			throw new CommandException(message);
			
		}
		
	}
	

}
