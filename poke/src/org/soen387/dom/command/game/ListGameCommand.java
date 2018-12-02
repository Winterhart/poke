package org.soen387.dom.command.game;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.POJO.challenge.IChallenge;
import org.soen387.dom.POJO.game.IGame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListGameCommand extends ValidatorCommand {

	public ListGameCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;
			Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
			parsedUserId = Long.parseLong(rawUserId.toString());
			System.out.println("User Id: " + parsedUserId.toString());

		
		// Attempt to Get Challenges
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<IGame> games = 
					GameInputMapper.findAllForUser(parsedUserId);
			List<GameJsonHelper> jGame = new ArrayList<GameJsonHelper>();
		
			for(IGame g : games) {
				Long[] pls = {g.getChallengerId(), g.getChallengeeId()};
				jGame.add(new GameJsonHelper(
						g.getId(),
						g.getVersion(),
						pls));
			}
			
			String jsonGames = gson.toJson(jGame);
			helper.setRequestAttribute("games", jsonGames);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Can't find games " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
			
		}
		
	}
	
	private class GameJsonHelper{
		private Long id;
		private Long version;
		private Long[] players;
		
		public GameJsonHelper(Long id, Long version, Long[] players) {
			super();
			this.id = id;
			this.version = version;
			this.players = players;
		}
		
		
	}

}
