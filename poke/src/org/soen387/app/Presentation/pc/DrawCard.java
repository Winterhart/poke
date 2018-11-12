package org.soen387.app.Presentation.pc;

import java.io.IOException;
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

@WebServlet("/DrawCard")
public class DrawCard extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        	boolean isTheUserChallenger = userid == challengerId;
        	boolean isTheUserImply = (userid == challengerId || userid == challengeeId);
        	
        	if(userFound == null || gameFound == null || !isTheUserImply ) {
        		request.setAttribute("message", "Wrong Context...");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
		    		
        			BoardRDG board = BoardRDG.findByGameId(gameId);
        			String status = "";
                	if(isTheUserChallenger) {
                		status = board.getChallengerStatus();
                	}
                	else {
                		status = board.getChallengeeStatus();
                	}
            		if(board != null && !status.equals("retired")) {
            			
            			// Grab current hand
            			List<HandRDG> handCards = HandRDG.findAllByGameIdUserId(userid, gameFound.getId());
            			List<BenchRDG> benchedCards = BenchRDG.findAllByGameIdUserId(userid, gameId);
            			// Grab current deck
            			DeckRDG deck = DeckRDG.findByUserId(userid);
            			List<CardRDG> cards = deck.getCards();
            			int index = handCards.size() + benchedCards.size();
            			if(index < 40 && deck != null) {
            				
            				// Draw next card
            				CardRDG newCard = cards.get(index);
            				Long handIndex = HandRDG.getFollowingId();
            				// Insert New card
            				HandRDG newChoosenCard = new HandRDG(handIndex, 0, userid, gameFound.getId(), newCard.getId());
            				newChoosenCard.insert();
            				
            				// No need to update board since value are computed on the fly...
                  			request.setAttribute("message",  "New Card added to your hand...");
                        	dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp"); 
            				
            			}else {
                  			request.setAttribute("message",  "Problem with deck size or other...maybe your deck is empty...");
                        	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            			}
            		}else {
              			request.setAttribute("message",  "You don't have a board, or are retired from game");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while Reading Deck");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
        dis.forward(request, response);

	}

}