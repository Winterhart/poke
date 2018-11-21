package org.soen387.app.Domain.POJO.player;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IUser extends IDomainObject<Long> {
	
	public String getUsername();
	public String getHashedPassword();
	

}
