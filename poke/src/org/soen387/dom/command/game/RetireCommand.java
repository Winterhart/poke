package org.soen387.dom.command.game;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.POJO.game.Game;

public class RetireCommand extends ValidatorCommand {
	
	@Source
	public String version;

	public RetireCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long parsedUserId = null;
		Long gameId = null;
		Long vId = null;
		
		try {
			
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			gameId = (long)helper.getRequestAttribute("gameId");
			
			vId = Long.parseLong(version);
			
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
		
		if(parsedUserId != game.getChallengerId() && parsedUserId != game.getChallengeeId()) {
			String message = "That's not your game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(vId != game.getVersion()) {
			String message = "Please Reload the game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(parsedUserId == game.getChallengerId() && game.getChallengerStatus().equalsIgnoreCase("retire")) {
			String message = "You retire from game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		
		if(parsedUserId == game.getChallengeeId() && game.getChallengeeStatus().equalsIgnoreCase("retire")) {
			String message = "You retire from game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		
		try {
		
			//If user was the Challenger
			if(parsedUserId == game.getChallengerId()) {
				game.setChallengerStatus("retire");
			}else {
				//If user was the Challengee
				game.setChallengeeStatus("retire");	
			}
			
			if(UoW.getCurrent() == null) {
				UoW.newCurrent();
			}
			
			UoW.getCurrent().registerDirty(game);
			UoW.getCurrent().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Problem while updating game";
			addNotification(message);
			throw new CommandException(message);
		}
		
	}

}
