package org.soen387.app.dispatcher.deck;

import java.io.IOException;
import javax.servlet.ServletException;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.dom.command.deck.UploadDeckCommand;

public class ManagerDeck extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(this.myRequest.getParameterMap().containsKey("deck")) {
			//User want to upload a deck
			UploadDeckCommand upload = new UploadDeckCommand(myHelper);
			this.myRequest.getParameter("deck");
			
			try {
				upload.execute();
				forward("/WEB-INF/jsp/success.jsp");
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			// User want to see deck or a specific deck
			this.myRequest.getParameterMap();
			int dd = 3;
			System.out.println("eeee");
		}
	}

}
