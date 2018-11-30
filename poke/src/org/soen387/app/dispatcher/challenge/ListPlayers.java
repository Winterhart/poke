package org.soen387.app.dispatcher.challenge;

import java.io.IOException;
import javax.servlet.ServletException;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.soen387.dom.command.challenge.ListPlayersCommand;

public class ListPlayers extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ListPlayersCommand comm = new ListPlayersCommand(myHelper);
		try {
			comm.execute();
			forward("/WEB-INF/jsp/players.jsp");
		}catch(Exception ee) {
			System.out.println("Problem with executing List player: " + ee.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
		
	}

}
