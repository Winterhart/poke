package org.soen387.dom.command.challenge;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;

import net.minidev.json.JSONObject;

public class ListPlayersCommand extends ValidatorCommand  {

	public ListPlayersCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		try {
			JSONObject playersJSON = new JSONObject();
			playersJSON.put("id", 1);
			playersJSON.put("user", "bobo");
//			playersJSON.put("id", 2);
//			playersJSON.put("user", "coucou");
//			playersJSON.put("id", 3);
//			playersJSON.put("user", "test");
			
			String players = "Hello";
			helper.setRequestAttribute("players", players);
		}catch(Exception ee) {
			ee.printStackTrace();
			addNotification(ee.getMessage());
			helper.setRequestAttribute("message", ee.getMessage());
			throw new CommandException(ee);
		}
		
	}

}
