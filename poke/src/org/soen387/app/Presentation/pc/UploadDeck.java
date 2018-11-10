package org.soen387.app.Presentation.pc;

import java.io.IOException;
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
import org.soen387.app.Domain.Helper.CardHelper;
import org.soen387.app.Util.DeckParser;

@WebServlet("/UploadDeck")
public class UploadDeck extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UploadDeck() {
		super();
	}
    
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deck = request.getParameter("deck");
    	RequestDispatcher dis = null;
		//Parse Make sure it's okay format
    	List<CardHelper> preCards = DeckParser.parseDeck(deck);
    	try {
        	long userid = (Long)request.getSession(true).getAttribute("userid");
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	if(userFound == null || deck == null || deck.isEmpty() || preCards == null) {
        		request.setAttribute("message", "I do not recognize that user or deck.");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
		
            		
        			DeckRDG deckFound = DeckRDG.findByUserId(userFound.getId());
            		if(deckFound != null) {
            			for(CardRDG oldCard : deckFound.getCards()) {
            				oldCard.delete();
            			}
            			
            			deckFound.delete();
            		}
            		Long latestDeckID = DeckRDG.getFollowingId();
            		
            		List<CardRDG> cards = new ArrayList<CardRDG>();
            		// Convert to Cards
            		Long startingIndex = CardRDG.getFollowingId();
            		for(CardHelper helper : preCards) {
            			CardRDG createdCard = new CardRDG(
            					startingIndex,
            					0, 
            					latestDeckID, 
            					helper.getCardName(), 
            					helper.getCardType());
            			
            			startingIndex++;
            			cards.add(createdCard);
            		}

            		
            		
            		// Insert New Deck
            		DeckRDG createdDeck = new DeckRDG(latestDeckID, 0, cards, userFound.getId());
            		createdDeck.insert();
            		
            		
            		//Now that the deck exists,  insert All Card
            		for(CardRDG card: cards) {
            			card.insert();
            		}
        			request.setAttribute("message", "New deck inserted !!! ");
        			dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");    		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while uploading Deck");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	dis.forward(request, response);
	}
}
