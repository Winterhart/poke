package org.soen387.app.Domain.POJO.player;

import org.dsrg.soenea.domain.DomainObject;;

public class User extends DomainObject<Long> implements IUser {
	private String username;
	private String hashedPassword;
	
	public User(Long id, long version, String user, String pwd) {
		super(id, version);
		this.username = user;
		this.hashedPassword = pwd;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public String getHashedPassword() {
		return this.hashedPassword;
	}
	
	

	
	

}
