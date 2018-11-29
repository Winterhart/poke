package org.soen387.dom.command.challenge;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserProxy;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ListPlayersCommand extends ValidatorCommand  {

	public ListPlayersCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<UserJsonFormat> userJson = convertToFormat( UserInputMapper.findAll());
			String players = gson.toJson(userJson);
			helper.setRequestAttribute("players", players);
		}catch(Exception ee) {
			ee.printStackTrace();
			addNotification(ee.getMessage());
			helper.setRequestAttribute("message", ee.getMessage());
			throw new CommandException(ee);
		}
		
	}
	
	private List<UserJsonFormat> convertToFormat(List<IUser> users){
		List<UserJsonFormat> usersJ = new ArrayList<UserJsonFormat>();
		for(IUser user : users) {
			UserJsonFormat u = new UserJsonFormat(user.getUsername(), user.getId());
			usersJ.add(u);
		}
		
		return usersJ;

	}
	
	protected class UserJsonFormat{
		private String user;
		private Long id;
		UserJsonFormat(String user, Long id){
			this.user = user;
			this.id = id;
		}
	}

}
