package org.soen387.app.Domain.POJO.player;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IUser extends IDomainObject<Long> {
	
	public abstract String getUsername();
	public abstract String getHashedPassword();
	

}
