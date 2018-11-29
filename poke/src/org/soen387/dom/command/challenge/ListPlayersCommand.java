package org.soen387.dom.command.challenge;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;

public class ListPlayersCommand extends ValidatorCommand  {

	public ListPlayersCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		try {
			
		}catch(Exception ee) {
			ee.printStackTrace();
			addNotification(ee.getMessage());
			throw new CommandException(ee);
		}
		
	}

}
