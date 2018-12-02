package org.soen387.dom.command.game;


import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.POJO.game.Game;

public class EndTurnCommand extends ValidatorCommand {

	@Source
	public String version;
	
	public EndTurnCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long parsedUserId = null;
		Long gameId = null;
		Long versionL = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			gameId = (long)helper.getRequestAttribute("gameId");
			
			versionL = Long.parseLong(version);
			
		}catch(Exception e) {
			String message = "Invalid Post parameters Id in EndTurn";
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
		
		if(versionL != game.getVersion()) {
			String message = "Reload your game is not updated...";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(game.getChallengerId() != parsedUserId && game.getChallengeeDeck() != parsedUserId) {
			String message = "You are not in this game...";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(game.getCurrentTurn() != parsedUserId) {
			String message = "It's not your turn...";
			addNotification(message);
			throw new CommandException(message);
		}
		
		Long newUserTurn = null;
		if(game.getCurrentTurn() == game.getChallengerId()) {
			newUserTurn = game.getChallengeeId();
		}else {
			newUserTurn = game.getChallengerId();
		}
		
		try {
			int numOfTurn = game.getNumberOfTurn();
			numOfTurn++;
			game.setCurrentTurn(newUserTurn);
			game.setNumberOfTurn(numOfTurn);
			//game.setVersion(versionL++);
			if(UoW.getCurrent() == null) {
				UoW.newCurrent();
			}
			UoW.getCurrent().registerDirty(game);
			UoW.getCurrent().commit();
			
			helper.setRequestAttribute("message", "Turn ended");
			
		}catch(Exception ee) {
			String message = "Problem while updating EndTurn";
			addNotification(message);
			throw new CommandException(message);
		}
		
	}

}
