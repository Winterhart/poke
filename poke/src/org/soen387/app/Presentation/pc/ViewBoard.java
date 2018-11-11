package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.BenchRDG;
import org.soen387.app.DataSource.BoardRDG;
import org.soen387.app.DataSource.CardRDG;
import org.soen387.app.DataSource.DeckRDG;
import org.soen387.app.DataSource.GameRDG;
import org.soen387.app.DataSource.HandRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/ViewBoard")
public class ViewBoard extends HttpServlet {
	
	public ViewBoard() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameIdString = request.getParameter("game");
    	RequestDispatcher dis = null;
		//Parse Make sure it's okay format
    	try {
        	Long userid = (Long)request.getSession(true).getAttribute("userid");
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	
        	Long gameId = Long.parseLong(gameIdString);
        	GameRDG gameFound = GameRDG.find(gameId);
        	Long challengerId = gameFound.getChallengerId();
        	Long challengeeId = gameFound.getChallengeeId();
        	
        	boolean isTheUserImply = (userid == challengerId || userid == challengeeId);
        	
        	if(userFound == null || gameFound == null || !isTheUserImply ) {
        		request.setAttribute("message", "Wrong Context...");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
		    		
        			BoardRDG board = BoardRDG.findByGameId(gameId);
            		if(board != null) {
            			
            			// Compute info Player 1
            			int handsizeChallenger = 0;
            			int decksizeChallenger = 0;
            			int discardsizeChallenger = 0;
            			List<BenchRDG> benchCardsChallenger = BenchRDG.findAllByGameIdUserId(gameFound.getChallengerId(), gameFound.getId());
            			List<HandRDG> handCardsChallenger = HandRDG.findAllByGameIdUserId(gameFound.getChallengerId(), gameFound.getId());
            			
            			DeckRDG deckChallenger = DeckRDG.findByUserId(gameFound.getChallengerId());
            			handsizeChallenger = handCardsChallenger.size();
            			
            			decksizeChallenger = deckChallenger.getCards().size() - 
            					(discardsizeChallenger + handsizeChallenger + benchCardsChallenger.size());
            			
            			
            			// Compute info Player 2
            			
            			int handsizeChallengee = 0;
            			int decksizeChallengee = 0;
            			int discardsizeChallengee = 0;
            			List<BenchRDG> benchCardsChallengee = BenchRDG.findAllByGameIdUserId(gameFound.getChallengeeId(), gameFound.getId());
            			List<HandRDG> handCardsChallengee = HandRDG.findAllByGameIdUserId(gameFound.getChallengeeId(), gameFound.getId());
            			
            			DeckRDG deckChallengee = DeckRDG.findByUserId(gameFound.getChallengeeId());
            			handsizeChallengee = handCardsChallengee.size();
            			decksizeChallengee = deckChallengee.getCards().size() - 
            					(discardsizeChallengee + handsizeChallengee + benchCardsChallengee.size());
            			
            			
                		PrintWriter writer = response.getWriter();
                		writer.println("{");
                		writer.println("\"board\": {");
                		writer.println("\"id\": " + board.getId() + ", ");
                		writer.println("\"players\": [" + gameFound.getChallengerId() + ", " +gameFound.getChallengeeId() + "], ");
                		writer.println("\"play\": { ");
                		// Challenger Section
                		writer.println(" \"" + gameFound.getChallengerId() + "\" : {");
                		writer.println(" \"status\": \"" + board.getChallengerStatus() + "\", ");
                		writer.println(" \"handsize\": " + handsizeChallenger + ", ");
                		writer.println(" \"decksize\": " + decksizeChallenger + ", ");
                		writer.println(" \"discardsize\": " + discardsizeChallenger + ", ");
                		writer.print(" \"bench\": [");
                		for(BenchRDG card : benchCardsChallenger) {
                			writer.print(card.getCardId() + ", ");
                		}
                		writer.println("] ");
                		writer.println("}, ");
                		
                			
                		// Challengee Section
                		
                		writer.println(" \"" + gameFound.getChallengeeId() + "\" : {");
                		writer.println(" \"status\": \"" + board.getChallengeeStatus() + "\", ");
                		writer.println(" \"handsize\": " + handsizeChallengee + ", ");
                		writer.println(" \"decksize\": " + decksizeChallengee + ", ");
                		writer.println(" \"discardsize\": " + discardsizeChallengee + ", ");
                		writer.print(" \"bench\": [");
                		for(BenchRDG card : benchCardsChallengee) {
                			writer.print(card.getCardId() + ", ");
                		}
                		writer.println("] ");
                		writer.println("}");
                		writer.println("}");
                		writer.println("}");
                		writer.println("}");
                		writer.close();
            		}else {
              			request.setAttribute("message",  "You don't have a board");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while Reading Deck");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	if(dis != null) {
        	dis.forward(request, response);
    	}

	}

}
