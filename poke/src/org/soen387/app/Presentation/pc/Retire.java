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

import org.soen387.app.DataSource.BenchRDG;
import org.soen387.app.DataSource.BoardRDG;
import org.soen387.app.DataSource.DeckRDG;
import org.soen387.app.DataSource.GameRDG;
import org.soen387.app.DataSource.HandRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/Retire")
public class Retire extends HttpServlet {
	
	public Retire() {
		super();
	}
	
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
        	
        	boolean isTheUserImply = (userid == challengerId || userid == challengeeId);
        	
        	if(userFound == null || gameFound == null || !isTheUserImply ) {
        		request.setAttribute("message", "Wrong Context...");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
		    		
        			BoardRDG board = BoardRDG.findByGameId(gameId);

            		if(board != null) {
            			String status = "";
            			if(userid == challengerId) {
            				status = board.getChallengerStatus();
                			if(status.equals("playing")) {
                				board.setChallengerStatus("retired");
                				board.update();
                      			request.setAttribute("message",  "You are now retired ");
                            	dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp"); 
                				
                			}else {
                      			request.setAttribute("message",  "You already retired ");
                            	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
                			}
            			}else {
            				status = board.getChallengeeStatus();
                			if(status.equals("playing")) {
                				board.setChallengerStatus("retired");
                				board.update();
                      			request.setAttribute("message",  "You are now retired ");
                            	dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp"); 
                				
                			}else {
                      			request.setAttribute("message",  "You already retired ");
                            	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
                			}
            			}
            			

            			
            			
            		}else {
              			request.setAttribute("message",  "You don't have a board ");
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
