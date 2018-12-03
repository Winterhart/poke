package org.soen387.app.dispatcher.game;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.soen387.dom.command.game.DrawCardCommand;
import org.soen387.dom.command.game.EndTurnCommand;
import org.soen387.dom.command.game.GameMoveCommand;
import org.soen387.dom.command.game.ListGameCommand;
import org.soen387.dom.command.game.RetireCommand;
import org.soen387.dom.command.game.ViewBoardCommand;
import org.soen387.dom.command.game.ViewDiscardCommand;
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
						EndTurnCommand endTurn = new EndTurnCommand(myHelper);
						endTurn.execute();
						DrawCardCommand c = new DrawCardCommand(myHelper);
						c.execute();	
						forward("/WEB-INF/jsp/success.jsp");
					}catch(Exception ee) {
						System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
						myHelper.setRequestAttribute("message", ee.getMessage());
						forward("/WEB-INF/jsp/fail.jsp");
					}
			break;
			case("play"):
					//Must Handle : playTrainer, attachEnergy, 
					//evolvePokemon (based on card Type do action)
					
					List<Long> multipleIdURL = UrlParser.getThoseId(myRequest.getPathInfo());
					
					if(myRequest.getParameterMap().containsKey("pokemon")) {
						myHelper.setRequestAttribute("action", "energy");
						myHelper.setRequestAttribute("pokemon", myRequest.getParameter("pokemon"));
						
					}else if(myRequest.getParameterMap().containsKey("basic")) {
						myHelper.setRequestAttribute("action", "evolve");
						myHelper.setRequestAttribute("basic", myRequest.getParameter("basic"));
						
					}else {
						myHelper.setRequestAttribute("action", "normal");
					}
					
					try {	
						myHelper.setRequestAttribute("gameId", multipleIdURL.get(0));
						myHelper.setRequestAttribute("handId", multipleIdURL.get(1));
						GameMoveCommand move = new GameMoveCommand(myHelper);
						move.execute();
						
						EndTurnCommand end = new EndTurnCommand(myHelper);
						end.execute();
						
						DrawCardCommand draw = new DrawCardCommand(myHelper);
						draw.execute();
						
						forward("/WEB-INF/jsp/success.jsp");
					}catch(Exception ee) {
						System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
						myHelper.setRequestAttribute("message", ee.getMessage());
						forward("/WEB-INF/jsp/fail.jsp");
					}
			break;
			case("retire"):
				Long gameId = Long.parseLong((String)UrlParser.getIdInUR(myRequest.getPathInfo()));
				myHelper.setRequestAttribute("gameId", gameId);
				try {
					RetireCommand r = new RetireCommand(myHelper);
					r.execute();
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
				
				//Very beautiful code here...
				switch(lastWord) {
				case("game"):

					try {
						ListGameCommand cusCom = new ListGameCommand(myHelper);
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
						ViewHandCommand cusCom = new ViewHandCommand(myHelper);
						cusCom.execute();
						forward("/WEB-INF/jsp/hand.jsp");
					}catch(Exception ee) {
						System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
						myHelper.setRequestAttribute("message", ee.getMessage());
						forward("/WEB-INF/jsp/fail.jsp");
					}

					
					break;

				case("discard"):
					//setup game Id variable
					String gameStr = UrlParser.getIdInUR(myRequest.getPathInfo());
					myHelper.setRequestAttribute("gameId", gameStr);
					

					
					try {
						List<Long> multipleIdURL = UrlParser.getThoseId(myRequest.getPathInfo());
						myHelper.setRequestAttribute("gameId", multipleIdURL.get(0));
						myHelper.setRequestAttribute("player", multipleIdURL.get(1));
						ViewDiscardCommand vDis = new ViewDiscardCommand(myHelper);
						vDis.execute();
						forward("/WEB-INF/jsp/discard.jsp");
					}catch(Exception ee) {
						System.out.println("Problem with executing GameDispatcher: " + ee.getMessage());
						myHelper.setRequestAttribute("message", ee.getMessage());
						forward("/WEB-INF/jsp/fail.jsp");
					}

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
			}
		}
		
	}

}
