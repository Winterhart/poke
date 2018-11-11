package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.CardRDG;
import org.soen387.app.DataSource.ChallengeRDG;
import org.soen387.app.DataSource.DeckRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/ListChallenges")
public class ListChallenges extends HttpServlet {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public ListChallenges() {
			super();
		}
	    
	    
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	RequestDispatcher dis = null;
	    	
	    	try {
	        	Long userid = (Long)request.getSession(true).getAttribute("userid");
	        	UserRDG userFound = null;
	        	userFound = UserRDG.find(userid);

	        	if(userFound == null) {
	        		request.setAttribute("message", "I do not recognize the user");
	        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
	    			
	        	}else {
	        		
		    		
        			List<ChallengeRDG> allChallenges = ChallengeRDG.findAll();
            		if(allChallenges != null) {
                		PrintWriter writer = response.getWriter();
                		writer.println("{");
                		writer.println("\"challenges\": [ ");
                		for(ChallengeRDG cha : allChallenges) {
                			writer.println("{ \"id\": " + cha.getId() + ", \"challenger\": " 
                		+ cha.getChallenger() + ", \"challengee\":  " + cha.getChallengee() + 
                		", \"status\": " + cha.getChallengeStatus() + "},");
                		}
                		writer.println("]");
                		writer.println("}");
                		writer.close();
            		}else {
              			request.setAttribute("message",  "No challenge are created");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            		}
	   		
	        	}
	    	}catch(Exception ee) {
	    		ee.printStackTrace();
				request.setAttribute("message", "Problem while creating challenge");
				dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
	    	}
	    	
	    	if(dis != null) {
		    	dis.forward(request, response);
	    	}

		}
}
