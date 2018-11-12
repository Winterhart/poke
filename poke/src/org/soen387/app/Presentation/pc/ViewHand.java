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
import org.soen387.app.DataSource.BoardRDG;
import org.soen387.app.DataSource.GameRDG;
import org.soen387.app.DataSource.HandRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/ViewHand")
public class ViewHand extends HttpServlet {
	
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
        	
        	boolean isTheUserChallenger = userid == challengerId;
        	boolean isTheUserImply = (userid == challengerId || userid == challengeeId);
        	

        	
        	if(userFound == null || gameFound == null || !isTheUserImply ) {
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
            			
            			
            			
                		PrintWriter writer = response.getWriter();
                		writer.println("{");
                		writer.print("\"hand\": [");
                		for(HandRDG card : handOfUser) {
                			writer.print(card.getCardId() + ", ");
                		}
                		writer.println("] ");
                		writer.println("}, ");
                		writer.close();
            		}else {
              			request.setAttribute("message",  "You don't have a board, or you are retired from it...");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while Reading hand");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	if(dis != null) {
        	dis.forward(request, response);
    	}

	}

}
