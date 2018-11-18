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

import org.soen387.app.DataSource.RDG.ChallengeRDG;
import org.soen387.app.DataSource.RDG.GameRDG;
import org.soen387.app.DataSource.RDG.UserRDG;

@WebServlet("/ListGames")
public class ListGames extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListGames() {
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
        		
	    		
    			List<GameRDG>  games = GameRDG.findAll();
        		if(games != null) {
            		PrintWriter writer = response.getWriter();
            		writer.println("{");
            		writer.println("\"games\": [ ");
            		for(GameRDG g : games) {
            			writer.println("{\"id\": " + g.getId() + ", \"players\": ["
            			+ g.getChallengerId() + ", " + g.getChallengeeId()	+ "]},");
            		}
            		writer.println("]");
            		writer.println("}");
            		writer.close();
        		}else {
          			request.setAttribute("message",  "No games are created");
                	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
        		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while getting the games");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	if(dis != null) {
	    	dis.forward(request, response);
    	}

	}

}
