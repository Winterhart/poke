package org.soen387.dom.POJO.user;

import java.util.List;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.soen387.dom.POJO.challenge.IChallenge;
import org.soen387.dom.POJO.deck.IDeck;
import org.soen387.dom.POJO.game.IGame;

public interface IPlayer  extends IDomainObject<Long> {
	
	public List<IDeck> getDecks();

	public void setDecks(List<IDeck> decks);

	public List<IChallenge> getChallenges();

	public void setChallenges(List<IChallenge> challenges);

	public List<IGame> getGames();

	public void setGames(List<IGame> games);
	
	public String getUsername();
	
	public String getHashedPassword();
	
}
