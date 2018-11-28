package org.soen387.dom.POJO.user;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.dom.POJO.challenge.IChallenge;
import org.soen387.dom.POJO.deck.IDeck;
import org.soen387.dom.POJO.game.IGame;

public class Player extends DomainObject<Long> implements IPlayer  {
	
	private List<IDeck> decks;
	private List<IChallenge> challenges;
	private List<IGame> games;
	private String username;
	private String hashedPassword;
	
	public Player(Long id, long version, String user,  String pwd, 
			List<IDeck> decks,  List<IChallenge> challenge, List<IGame> games, String username, String hashedPwd) {
		super(id, version);
		this.challenges = challenge;
		this.decks = decks;
		this.games = games;
		this.username = username;
		this.hashedPassword = hashedPwd;
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

	public String getUsername() {
		return username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}



	

}
