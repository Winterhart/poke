package org.soen387.app.Presentation.pc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.soen387.app.DataSource.ChallengeRDG;
import org.soen387.app.DataSource.DeckRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/ChallengePlayer")
public class ChallengePlayer extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ChallengePlayer() {
		super();
	}
    
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String challengeeId = request.getParameter("player");
    	RequestDispatcher dis = null;
    	
    	try {
        	Long userid = (Long)request.getSession(true).getAttribute("userid");
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	
        	Long targetUserId = Long.parseLong(challengeeId);
        	UserRDG targetUser = null;
        	targetUser = UserRDG.find(targetUserId);
        	

        	
        	if(userFound == null || targetUser == null || userid == targetUserId) {
        		request.setAttribute("message", "I do not recognize the user pass as parameter");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
            	// Both user must have a deck
        		DeckRDG deckUser =  DeckRDG.findByUserId(userid);
        		DeckRDG deckTargetUser = DeckRDG.findByUserId(targetUserId);
        		if(deckUser != null && deckTargetUser != null && deckUser.getCards().size() == 40 
        				&& deckTargetUser.getCards().size() == 40) {
            		
        			Long currentId = ChallengeRDG.getFollowingId();
        			
        			ChallengeRDG chal = new ChallengeRDG(currentId, 0, userid, targetUserId, 0);
        			chal.insert();
        			
        			
        			request.setAttribute("message", "New challenge inserted !!! ");
            		dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
            		
            		
        		}else {
            		request.setAttribute("message", "One user has no deck !!! ");
            		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
        		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while creating challenge");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	dis.forward(request, response);
	}

}
