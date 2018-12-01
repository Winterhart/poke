package org.soen387.app.dispatcher.deck;

import java.io.IOException;

import javax.servlet.ServletException;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.command.deck.UploadDeckCommand;

public class ManageDeck extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(this.myRequest.getParameterMap().containsKey("deck")) {
			try {
				//User want to upload a deck
				UploadDeckCommand upload = new UploadDeckCommand(myHelper);
				upload.execute();
				UoW.getCurrent().commit();
				forward("/WEB-INF/jsp/success.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
			
		}else {
			// User want to see deck or a specific deck
			System.out.println("eeee");
		}
	}

}
