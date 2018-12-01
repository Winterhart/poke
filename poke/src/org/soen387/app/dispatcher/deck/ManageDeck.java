package org.soen387.app.dispatcher.deck;

import java.io.IOException;

import javax.servlet.ServletException;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.command.deck.UploadDeckCommand;
import org.soen387.dom.command.deck.ViewDecksCommand;
import org.soen387.dom.command.deck.ViewDetailDeckCommand;
import org.soen387.util.UrlParser;

public class ManageDeck extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(myRequest.getMethod().equalsIgnoreCase("POST") &&
				this.myRequest.getParameterMap().containsKey("deck")) {
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
			
		}else if(myRequest.getMethod().equalsIgnoreCase("GET")) {
			

			String url = myRequest.getPathInfo();
			String idIntUrl = UrlParser.getIdInUR(url);
			
			if(idIntUrl != null) {
				
				ViewDetailDeckCommand viewDetails = new ViewDetailDeckCommand(myHelper);
				try {
					myHelper.setRequestAttribute("id", idIntUrl);
					viewDetails.execute();
					forward("/WEB-INF/jsp/cards.jsp");
				} catch (CommandException e) {
					e.printStackTrace();
					myHelper.setRequestAttribute("message", e.getMessage());
					forward("/WEB-INF/jsp/fail.jsp");
				}
				
			}else {
				ViewDecksCommand viewer = new ViewDecksCommand(myHelper);
				try {
					viewer.execute();
					forward("/WEB-INF/jsp/decks.jsp");
				} catch (CommandException e) {
					e.printStackTrace();
					myHelper.setRequestAttribute("message", e.getMessage());
					forward("/WEB-INF/jsp/fail.jsp");
				}
				
			}		
		}
	}

}
