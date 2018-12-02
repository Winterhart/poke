package org.soen387.app.dispatcher.game;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.soen387.dom.command.game.ListGameCommand;
import org.soen387.util.UrlParser;

public class GameDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(myRequest.getMethod().equalsIgnoreCase("POST")){
			
		}else {
			if(myRequest.getMethod().equalsIgnoreCase("GET")) {
				
				//SUPER-SWITCHER
				String lastWord = UrlParser.getLastWord(myRequest.getPathInfo());
				lastWord = lastWord.toLowerCase();
				
				ValidatorCommand cusCom = null;
				//Very beautiful code here...
				switch(lastWord) {
				case("game"):
					try {
						cusCom = new ListGameCommand(myHelper);
						cusCom.execute();
						forward("/WEB-INF/jsp/games.jsp");
					}catch(Exception ee) {
						System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
						myHelper.setRequestAttribute("message", ee.getMessage());
						forward("/WEB-INF/jsp/fail.jsp");
					}
					break;
				case("hand"):
					//setup target Id variable
					
					break;
				case("discard"):
					//setup target Id variable
					break;
				default:
					//Handle special case where last word is an ID
					
					break;
				}
				
				
				
				if(cusCom == null) {
					System.out.println("Operation not supported ");
					myHelper.setRequestAttribute("message", "Operation not supported");
					forward("/WEB-INF/jsp/fail.jsp");
				}
				

				
				
			}
		}
		
	}

}
