package org.soen387.dom.POJO.deck;


import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IDeck extends IDomainObject<Long>{
	public Long getOwnerId();
	public void setOwnerId(Long ownerId);
}
