package org.soen387.app.Domain.pojo;

public class Board {
	private Long id;
	private int version;
	private User[] players;
	private Deck challengeDeck;
	private Deck challengerDeck;
	private Play[] turns;
	
	
}
