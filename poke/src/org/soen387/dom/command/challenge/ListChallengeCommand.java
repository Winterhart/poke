package org.soen387.dom.command.challenge;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.challenge.ChallengeInputMapper;
import org.soen387.dom.POJO.challenge.IChallenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListChallengeCommand extends ValidatorCommand {

	public ListChallengeCommand(Helper helper) {
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
			List<IChallenge> challenges = ChallengeInputMapper.findAll();
			List<ChallengeJsonHelper> chH = new ArrayList<ChallengeJsonHelper>();
			
			//TODO: Refactor to use IdentityMap 
			// I had a really strange bug with the IdentityMap and Proxy where the 
			// .getFromMapper was running in loop
			for(IChallenge c : challenges) {
				chH.add(new ChallengeJsonHelper(c.getId(), c.getVersion(), c.getChallengerId(),
						c.getChallengeeId(), c.getStatus(), c.getDeckInitializer()));
			}
			
			String jsonChallenges = gson.toJson(chH);
			helper.setRequestAttribute("challenges", jsonChallenges);
			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "To find challenges";
			addNotification(message);
			throw new CommandException(message);
			
		}
		
	}
	
	private class ChallengeJsonHelper{
		private Long id;
		private Long version;
		private Long challenger;
		private Long challengee;
		private int status;
		private Long deck;
		
		
		public ChallengeJsonHelper(Long id, Long version, Long challenger, Long challengee, int status, Long deck) {
			super();
			this.id = id;
			this.version = version;
			this.challenger = challenger;
			this.challengee = challengee;
			this.status = status;
			this.deck = deck;
		}
	}

}
