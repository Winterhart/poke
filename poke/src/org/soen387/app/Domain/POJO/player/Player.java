package org.soen387.app.Domain.POJO.player;

import java.util.List;

import org.soen387.app.Domain.POJO.challenge.IChallenge;
import org.soen387.app.Domain.POJO.deck.IDeck;
import org.soen387.app.Domain.POJO.game.IGame;

public class Player implements IPlayer {
	private List<IDeck> decks;
	private List<IChallenge> challenges;
	private List<IGame> games;
	
	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getHashedPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setVersion(long new_version) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
