package org.soen387.app.Domain.POJO.player;

import java.util.List;

import org.soen387.app.Domain.POJO.challenge.IChallenge;
import org.soen387.app.Domain.POJO.deck.IDeck;
import org.soen387.app.Domain.POJO.game.IGame;

public class Player extends User implements IPlayer  {
	
	private List<IDeck> decks;
	private List<IChallenge> challenges;
	private List<IGame> games;
	
	public Player(Long id, long version, String user,  String pwd, 
			List<IDeck> decks,  List<IChallenge> challenge, List<IGame> games) {
		super(id, version, user, pwd);
		this.challenges = challenge;
		this.decks = decks;
		this.games = games;
	}	
	
	public List<IDeck> getDecks() {
		return decks;
	}


	public void setDecks(List<IDeck> decks) {
		this.decks = decks;
	}


	public List<IChallenge> getChallenges() {
		return challenges;
	}


	public void setChallenges(List<IChallenge> challenges) {
		this.challenges = challenges;
	}


	public List<IGame> getGames() {
		return games;
	}


	public void setGames(List<IGame> games) {
		this.games = games;
	}



	

}
