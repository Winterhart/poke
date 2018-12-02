package org.soen387.app.dispatcher.game;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.soen387.dom.command.game.DrawCardCommand;
import org.soen387.dom.command.game.EndTurnCommand;
import org.soen387.dom.command.game.ListGameCommand;
import org.soen387.dom.command.game.ViewBoardCommand;
import org.soen387.dom.command.game.ViewHandCommand;
import org.soen387.util.UrlParser;

public class GameDispatcher extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(myRequest.getMethod().equalsIgnoreCase("POST")){
			//SUPER-SWITCHER
			String lastWord = UrlParser.getLastWord(myRequest.getPathInfo());
			lastWord = lastWord.toLowerCase();
			
			ValidatorCommand cusCom = null;
			//Very beautiful code here...
			switch(lastWord) {
			case("endturn"):
				//setup game Id variable
				Long gameIdS = Long.parseLong(UrlParser.getIdInUR(myRequest.getPathInfo()));
				myHelper.setRequestAttribute("gameId", gameIdS);
				
				try {

					DrawCardCommand c = new DrawCardCommand(myHelper);
					c.execute();	
					EndTurnCommand endTurn = new EndTurnCommand(myHelper);
					endTurn.execute();
					forward("/WEB-INF/jsp/success.jsp");
				}catch(Exception ee) {
					System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
					myHelper.setRequestAttribute("message", ee.getMessage());
					forward("/WEB-INF/jsp/fail.jsp");
				}
				break;
			default:
				myHelper.setRequestAttribute("message", "Not supported Operation");
				forward("/WEB-INF/jsp/fail.jsp");
				break;
			}
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
					//setup game Id variable
					String gameS = UrlParser.getIdInUR(myRequest.getPathInfo());
					myHelper.setRequestAttribute("gameId", gameS);
					
					try {
						cusCom = new ViewHandCommand(myHelper);
						cusCom.execute();
						forward("/WEB-INF/jsp/hand.jsp");
					}catch(Exception ee) {
						System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
						myHelper.setRequestAttribute("message", ee.getMessage());
						forward("/WEB-INF/jsp/fail.jsp");
					}

					
					break;
				case("discard"):
					//setup target Id variable
					break;
				default:
					//Handle special case where last word is an ID
					if(StringUtils.isNumeric(lastWord)) {
						String targetGame = UrlParser.getIdInUR(myRequest.getPathInfo());
						myHelper.setRequestAttribute("gameId", targetGame);
						ViewBoardCommand boardC = new ViewBoardCommand(myHelper);
						try {
							boardC.execute();
							forward("/WEB-INF/jsp/game.jsp");
						}catch(Exception ee) {
							System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
							myHelper.setRequestAttribute("message", ee.getMessage());
							forward("/WEB-INF/jsp/fail.jsp");
						}
					}
					
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
