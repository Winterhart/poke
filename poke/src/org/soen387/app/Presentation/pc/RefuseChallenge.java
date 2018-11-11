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


@WebServlet("/RefuseChallenge")
public class RefuseChallenge extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RefuseChallenge() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String challengeIdS= request.getParameter("challenge");
    	RequestDispatcher dis = null;
    	
    	try {
        	Long userid = (Long)request.getSession(true).getAttribute("userid");
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	
        	Long challengeId = Long.parseLong(challengeIdS);
        	ChallengeRDG challenge = ChallengeRDG.find(challengeId);
        	boolean isUserImply = (challenge.getChallengee() == userid || challenge.getChallengee() == userid);
        	if(userFound == null || challenge == null || !isUserImply ) {
        		request.setAttribute("message", "Invalid Challenge context");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
        		int status = 0;
        		// We know it must be from challengee or challenger
        		if(challenge.getChallengee() == userid) {
        			status = 1;
        			
        		}else {
        			status = 2;
        		}  		
        		challenge.setChallengeStatus(status);
        		challenge.update();
    			request.setAttribute("message", "Challenge Refused");
    			dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
        		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while refusing challenge");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	dis.forward(request, response);
	}

}
