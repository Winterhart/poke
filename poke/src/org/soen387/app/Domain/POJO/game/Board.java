package org.soen387.app.Domain.POJO.game;

import java.util.List;

import org.soen387.app.Domain.POJO.deck.IDeck;

public class Board implements IBoard {
	
	private boardStatus challengerStatus;
	private boardStatus challengeeStatus;
	
	private IDeck challengerDeck;
	private IDeck challengeeDeck;
	
	private List<ITurn> turns;
	

	
	
	

}
