package org.soen387.app.dispatcher.challenge;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.command.challenge.AcceptCommand;
import org.soen387.dom.command.challenge.ChallengePlayerCommand;
import org.soen387.dom.command.challenge.ListChallengeCommand;
import org.soen387.dom.command.challenge.RefuseCommand;
import org.soen387.dom.command.challenge.WithdrawCommand;
import org.soen387.dom.command.game.CreateGameCommand;
import org.soen387.util.UrlParser;

public class ChallengePlayer extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(myRequest.getMethod().equalsIgnoreCase("POST")) {
			
			String targetUserId = UrlParser.getIdInUR(myRequest.getPathInfo());
			myHelper.setRequestAttribute("targetId", targetUserId);
			
			String lastWord = UrlParser.getLastWord(myRequest.getPathInfo());
			lastWord = lastWord.toLowerCase();
			
			ValidatorCommand customCommand = null;
			
			// Ahahah 
			switch(lastWord) {
			
			case ("challenge"):
				customCommand = new ChallengePlayerCommand(myHelper);
				break;
			case("withdraw"):
				customCommand = new WithdrawCommand(myHelper);
				break;
			case("refuse"):
				customCommand = new RefuseCommand(myHelper);
				break;
			case("accept"):
				customCommand = new AcceptCommand(myHelper);
			}
			
			if(customCommand == null) {
				System.out.println("Operation not supported ");
				myHelper.setRequestAttribute("message", "Operation not supported");
				forward("/WEB-INF/jsp/fail.jsp");
			}
			
			try {
				customCommand.execute();
				if(lastWord.equalsIgnoreCase("accept")) {
					//We must also add a game
					CreateGameCommand gameC = new CreateGameCommand(myHelper);
					gameC.execute();
					
				}
				myHelper.setRequestAttribute("message", "Operation Success");
				forward("/WEB-INF/jsp/success.jsp");
			}catch(Exception ee) {
				System.out.println("Problem with executing ChallengePlayer player: " + ee.getMessage());
				myHelper.setRequestAttribute("message", ee.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}	
		}else {
			if(myRequest.getMethod().equalsIgnoreCase("GET")) {
				try {
					ListChallengeCommand customCommand = new ListChallengeCommand(myHelper);
					customCommand.execute();
					forward("/WEB-INF/jsp/challenges.jsp");
				}catch(Exception ee) {
					System.out.println("Problem with executing List player: " + ee.getMessage());
					myHelper.setRequestAttribute("message", ee.getMessage());
					forward("/WEB-INF/jsp/fail.jsp");
				}
				
			}
			
		}
	}
}
