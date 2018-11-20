package org.soen387.app.Domain.POJO.game;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IGame extends IDomainObject<Long> {
	
	
	public IBoard getBoard();
	public void setBoard(IBoard board);
	public Long getChallengerId();
	public void setChallengerId(Long challengerId);
	public Long getChallengeeId();
	public void setChallengeeId(Long challengeeId);

}
