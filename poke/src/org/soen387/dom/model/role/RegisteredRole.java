package org.soen387.dom.model.role;

import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.Role;

public class RegisteredRole extends Role implements IRole {

	private static final long REGISTERED_ROLE_ID = 2l;
	private static final String ROLE_NAME = "RegisteredRole";
	
	public RegisteredRole() {
		super(REGISTERED_ROLE_ID, 2, ROLE_NAME);
	}

	@Override
	public String getName() {
		return ROLE_NAME;
	}

	@Override
	public Long getId() {
		return REGISTERED_ROLE_ID;
	}

	@Override
	public long getVersion() {
		return 1;
	}
	
}
