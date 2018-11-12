package org.soen387.app.Domain.TransactionScript;

import java.sql.SQLException;

import org.soen387.app.DataSource.BoardRDG;
import org.soen387.app.DataSource.ChallengeRDG;
import org.soen387.app.DataSource.GameRDG;

public class AcceptChallengeTS {
	
	private ChallengeRDG challenge;
	public AcceptChallengeTS(ChallengeRDG challenge) {
		
	}
	
	public void execute() throws SQLException {
		// We assume they both have deck since they both need deck to create a challenge
		challenge.setChallengeStatus(3);
		this.challenge.update();
		
		Long newGameId = GameRDG.getFollowingId();
		GameRDG game = new GameRDG(newGameId, 0, challenge.getId(), challenge.getChallenger(), challenge.getChallengee());
		game.insert();
		
		// We also need to create a board
		Long newBoardId = BoardRDG.getFollowingId();
		BoardRDG board = new BoardRDG(newBoardId, 0, newGameId, "playing", "playing");
		board.insert();
	}
	
	

}
