package org.soen387.app.Presentation.pc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.RDG.BoardRDG;
import org.soen387.app.DataSource.RDG.ChallengeRDG;
import org.soen387.app.DataSource.RDG.GameRDG;
import org.soen387.app.DataSource.RDG.UserRDG;

@WebServlet("/AcceptChallenge")
public class AcceptChallenge extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AcceptChallenge() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String challengeIdS = request.getParameter("challenge");
    	RequestDispatcher dis = null;
    	
    	try {
        	Long userid = (Long)request.getSession(true).getAttribute("userid");
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	
        	Long challengeId = Long.parseLong(challengeIdS);
        	ChallengeRDG challenge = ChallengeRDG.find(challengeId);
        	
        	// Cannot accept challenge if you are the challenger
        	// Can only accept challenge which are status => 0
        	if(userFound == null || challenge == null || challenge.getChallenger() == userid 
        			|| challenge.getChallengee() != userid 
        			 || challenge.getChallengeStatus() != 0) {
        		request.setAttribute("message", "Error Invalid context");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
        		// We assume they both have deck since they both need deck to create a challenge
        		challenge.setChallengeStatus(3);
        		challenge.update();
        		
        		Long newGameId = GameRDG.getFollowingId();
        		GameRDG game = new GameRDG(newGameId, 0, challenge.getId(), challenge.getChallenger(), challenge.getChallengee());
        		game.insert();
        		
        		// We also need to create a board
        		Long newBoardId = BoardRDG.getFollowingId();
        		BoardRDG board = new BoardRDG(newBoardId, 0, newGameId, "playing", "playing");
        		board.insert();
        				
    			request.setAttribute("message", "Challenge Accepted");
    			dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while accepting challenge");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	dis.forward(request, response);
	}

}
