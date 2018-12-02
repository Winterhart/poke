package org.soen387.dom.command.game;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.Mapper.game.BenchInputMapper;
import org.soen387.dom.Mapper.game.DiscardInputMapper;
import org.soen387.dom.Mapper.game.GameInputMapper;
import org.soen387.dom.Mapper.game.HandInputMapper;
import org.soen387.dom.POJO.game.Game;
import org.soen387.dom.POJO.game.IBench;
import org.soen387.dom.POJO.game.IDiscard;
import org.soen387.dom.POJO.game.IHand;

public class ViewBoardCommand extends ValidatorCommand {

	public ViewBoardCommand(Helper helper) {
		super(helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() throws CommandException {
		//Grab User Id
		Long parsedUserId = null;
		Long gameId = null;
		
		Object rawUserId = helper.getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
		parsedUserId = Long.parseLong(rawUserId.toString());
		System.out.println("User Id: " + parsedUserId.toString());
			
		try {
			gameId = Long.parseLong((String) helper.getRequestAttribute("gameId"));	
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Problem while parsing the view board";
			addNotification(message);
			throw new CommandException(message);
		}
		
		//Business Rule can only view your game
		Game game = null;
		try {
			game = GameInputMapper.find(gameId);
		}catch(Exception e) {
			e.printStackTrace();
			String message = "Problem getting game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		if(parsedUserId != game.getChallengeeId() && parsedUserId != game.getChallengerId()) {
			String message = "You are not in this game";
			addNotification(message);
			throw new CommandException(message);
		}
		
		List<IHand> handChallenger = new ArrayList<IHand>();
		List<IBench> benchChallenger = new ArrayList<IBench>();
		List<IDiscard> disChallenger = new ArrayList<IDiscard>();

		
		//Grab info Challenger
		try {
			handChallenger = HandInputMapper.findByGameIdAndDeckId(game.getChallengerDeck(), gameId);
			benchChallenger = BenchInputMapper.findByGameIdAndDeckId(game.getChallengerDeck(), gameId);
			disChallenger = DiscardInputMapper.findByGameIdAndDeckId(game.getChallengerDeck(), gameId);
		}catch(Exception ee) {
			String message = "Problem while getting challenger data";
			addNotification(message);
			throw new CommandException(message);
		}
		
		
		List<IHand> handChallengee = new ArrayList<IHand>();
		List<IBench> benchChallengee = new ArrayList<IBench>();
		List<IDiscard> disChallengee = new ArrayList<IDiscard>();
		
		//Grab info Challengee
		try {
			handChallengee = HandInputMapper.findByGameIdAndDeckId(game.getChallengeeDeck(), gameId);
			benchChallengee = BenchInputMapper.findByGameIdAndDeckId(game.getChallengeeDeck(), gameId);
			disChallengee = DiscardInputMapper.findByGameIdAndDeckId(game.getChallengeeDeck(), gameId);
		}catch(Exception ee) {
			String message = "Problem while getting challengee data";
			addNotification(message);
			throw new CommandException(message);
		}
		
		
		// Attempt to build board
		try {
			// Compute info Player 1
			int handsizeChallenger = handChallenger.size();
			int decksizeChallenger = 40 - (handChallenger.size() + benchChallenger.size() + disChallenger.size());
			int discardsizeChallenger = disChallenger.size();


			
			
			// Compute info Player 2
			
			int handsizeChallengee = handChallengee.size();
			int decksizeChallengee = 40 - (handChallengee.size() + benchChallengee.size() + disChallengee.size());
			int discardsizeChallengee = disChallengee.size();

			StringBuilder sB = new StringBuilder();
    		sB.append("{");
    		sB.append("\n");
    		sB.append("\"id\": " + game.getId() + ", ");
    		sB.append("\n");
    		sB.append("\"version\": " + game.getVersion() + ", ");
    		sB.append("\n");
    		sB.append("\"players\": [" + game.getChallengerId() + ", " + game.getChallengeeId() + "], ");
    		sB.append("\n");
    		sB.append("\"current\": " + game.getCurrentTurn() + ", ");
    		sB.append("\n");
    		sB.append("\"decks\": [" + game.getChallengerDeck() + ", " + game.getChallengeeDeck() + "], ");
    		sB.append("\n");
    		sB.append("\"play\": { ");
    		sB.append("\n");
    		// Challenger Section
    		sB.append(" \"" + game.getChallengerId() + "\" : {");
    		sB.append("\n");
    		sB.append(" \"status\": \"" + game.getChallengerStatus().toString() + "\", ");
    		sB.append("\n");
    		sB.append(" \"handsize\": " + handsizeChallenger + ", ");
    		sB.append("\n");
    		sB.append(" \"decksize\": " + decksizeChallenger + ", ");
    		sB.append("\n");
    		sB.append(" \"discardsize\": " + discardsizeChallenger + ", ");
    		sB.append("\n");
    		sB.append(" \"bench\": [");
    		sB.append("\n");
    		for(IBench card : benchChallenger) {
    			sB.append("{ ");
    			sB.append(" \"id\": " + card.getCardId() + "\"e\": [], \"b\": 0");
    			sB.append(" },");
        		sB.append("\n");
    		}
    		sB.append("\n");
    		sB.append("] ");
    		sB.append("\n");
    		sB.append("}, ");
    		
    			
    		// Challengee Section
    		
    		sB.append(" \"" + game.getChallengeeId() + "\" : {");
    		sB.append("\n");
    		sB.append(" \"status\": \"" + game.getChallengeeStatus() + "\", ");
    		sB.append("\n");
    		sB.append(" \"handsize\": " + handsizeChallengee + ", ");
    		sB.append("\n");
    		sB.append(" \"decksize\": " + decksizeChallengee + ", ");
    		sB.append("\n");
    		sB.append(" \"discardsize\": " + discardsizeChallengee + ", ");
    		sB.append("\n");
    		sB.append(" \"bench\": [");
    		sB.append("\n");
    		for(IBench card : benchChallengee) {
    			sB.append("{ ");
    			sB.append(" \"id\": " + card.getCardId() + "\"e\": [], \"b\": 0");
    			sB.append(" },");
        		sB.append("\n");
    		}
    		sB.append("] ");
    		sB.append("\n");
    		sB.append("}");
    		sB.append("\n");
    		sB.append("}");
    		sB.append("\n");
    		sB.append("}");
    		
    		helper.setRequestAttribute("game", sB.toString());

			
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Can't find games " + e.getMessage();
			addNotification(message);
			throw new CommandException(message);
			
		}
		
	}
	

}
