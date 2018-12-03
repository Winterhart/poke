package org.soen387.dom.command.game;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.POJO.game.Game;

public class EndTurnFirstTimeCommand extends ValidatorCommand  {
	
	public EndTurnFirstTimeCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long parsedUserId = null;
		Long gameId = null;
		
		try {
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());
			
			gameId = (long)helper.getRequestAttribute("gameId");
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
		
		if(game.getChallengerId() != parsedUserId && game.getChallengeeId() != parsedUserId) {
			String message = "You are not in this game...";
			addNotification(message);
			throw new CommandException(message);
		}
		
		try {
			int numOfTurn = game.getNumberOfTurn();
			numOfTurn++;
			game.setCurrentTurn(game.getChallengerId());
			game.setNumberOfTurn(numOfTurn);	
			UoW.getCurrent().registerDirty(game);
			
		}catch(Exception ee) {
			String message = "Problem while updating EndTurn";
			addNotification(message);
			throw new CommandException(message);
		}
		
	}
}
