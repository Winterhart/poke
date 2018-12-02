package org.soen387.dom.command.game;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;
import org.soen387.dom.POJO.challenge.Challenge;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.GameFactory;

public class CreateGameCommand extends ValidatorCommand  {

	@Source
	public String deck;
	
	public CreateGameCommand(Helper helper) {
		super(helper);
	}
	
	public Helper getHelper() {
		return this.helper;
	}

	@Override
	public void process() throws CommandException {
		Long parsedUserId = null;
		Long deckIdTobeUsed = null;
		Long challengeId = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			String targetCha = (String)helper.getAttribute("targetId");
			challengeId = Long.parseLong(targetCha);
			
			String deckUse = deck;
			deckIdTobeUsed = Long.parseLong(deckUse);
			
		}catch(Exception e) {
			String message = "Invalid Post parameters Id in Game";
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
		
		
		
		try {
			Game game = GameFactory.createNew(
					cha.getChallengerId(),
					cha.getChallengeeId(),
					cha.getChallengeeId(),
					0,
					"playing",
					"playing",
					cha.getDeckInitializer(),
					deckIdTobeUsed);
			
			helper.setRequestAttribute("gameId", game.getId());
			
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Game could not be created...";
			addNotification(message);
			throw new CommandException(message);
		}
	}	
	

}
