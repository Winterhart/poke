package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.CardRDG;
import org.soen387.app.DataSource.DeckRDG;
import org.soen387.app.DataSource.UserRDG;

@WebServlet("/ViewDeck")
public class ViewDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ViewDeck() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dis = null;
		//Parse Make sure it's okay format
    	try {
        	long userid = (Long)request.getSession(true).getAttribute("userid");
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	if(userFound == null) {
        		request.setAttribute("message", "I do not recognize that user.");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
		    		
        			DeckRDG deckFound = DeckRDG.findByUserId(userFound.getId());
            		if(deckFound != null) {
                		PrintWriter writer = response.getWriter();
                		writer.println("{");
                		writer.println("\"deck\": {");
                		writer.println("\"id\": " + deckFound.getId() + ", ");
                		writer.println("\"cards\": [ ");
                		for(CardRDG c : deckFound.getCards()) {
                			writer.println("{ \"t\": " + c.getType() + ", \"n\": " + c.getName() + " },");
                		}
                		writer.println("]");
                		writer.println("}");
                		writer.println("}");
                		writer.close();
            		}else {
              			request.setAttribute("message",  "You don't have deck");
                    	dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp"); 
            		}
   		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while Reading Deck");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	if(dis != null) {
        	dis.forward(request, response);
    	}

	}

}
