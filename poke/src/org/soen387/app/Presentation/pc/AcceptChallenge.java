package org.soen387.app.Presentation.pc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.ChallengeRDG;
import org.soen387.app.DataSource.UserRDG;

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
        	// Cannot accept challenge status is already refused
        	if(userFound == null || challenge == null || challenge.getChallenger() == userid 
        			|| challenge.getChallengee() != userid 
        			 || challenge.getChallengeStatus() == 2 || challenge.getChallengeStatus() == 1) {
        		request.setAttribute("message", "Error Invalid context");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
        		// We assume they both have deck since they both need deck to create a challenge
        		challenge.setChallengeStatus(3);
        		challenge.update();
        		
        		//TODO: Create Game when it's accepted...
        		
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
