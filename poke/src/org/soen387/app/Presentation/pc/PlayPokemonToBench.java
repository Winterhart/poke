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
import org.soen387.app.DataSource.GameRDG;
import org.soen387.app.DataSource.HandRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/PlayPokemonToBench")
public class PlayPokemonToBench extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameIdString = request.getParameter("game");
		String cardPositionString = request.getParameter("card");
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
        	
        	// Verify card is in his hand
        	int cardIndex = Integer.parseInt(cardPositionString);
        	HandRDG foundCard = null;
        	List<HandRDG> samCard = HandRDG.findAllByGameIdUserId(userid, gameId);
        	if(cardIndex < samCard.size()) {
        		foundCard = samCard.get(cardIndex);
        	}
        	boolean isTheUserChallenger = userid == challengerId;
        	boolean isTheUserImply = (userid == challengerId || userid == challengeeId);
        	
        	if(userFound == null || gameFound == null || !isTheUserImply || foundCard == null ) {
        		request.setAttribute("message", "Wrong Context...");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
		    		
        			BoardRDG board = BoardRDG.findByGameId(gameId);
        			List<HandRDG> handOfUser = HandRDG.findAllByGameIdUserId(userid, gameFound.getId());
        			String status = "";
                	if(isTheUserChallenger) {
                		status = board.getChallengerStatus();
                	}
                	else {
                		status = board.getChallengeeStatus();
                	}
            		if(board != null && !status.equals("retired")) {
            			
            			Long idForBench = BenchRDG.getFollowingId();
            			BenchRDG cardToBench = new BenchRDG(idForBench, 0, userid, gameFound.getId(), foundCard.getCardId());
            			cardToBench.insert();
            			// Remove from Hand
            			foundCard.delete();
            			
              			request.setAttribute("message",  "Card sent to bench");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp"); 
            			
            		}else {
              			request.setAttribute("message",  "You don't have a board");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while Sending to bench Deck");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
        dis.forward(request, response);

	}
}
