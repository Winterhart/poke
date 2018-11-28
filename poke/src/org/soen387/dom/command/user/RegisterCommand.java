package org.soen387.dom.command.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.impl.GuestRole;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserFactory;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.soen387.dom.model.role.RegisteredRole;

public class RegisterCommand extends ValidatorCommand {

	@Source
	public String user;
	
	@Source
	public String pass;
	
	@SetInRequestAttribute
	public IUser currentUser;
	
	public RegisterCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			
			try{
				currentUser = UserInputMapper.find(user);
				String message = "User already exists!";
				throw new CommandException(message);
			} catch (MapperException e) {}
			
			List<IRole> roles = new ArrayList<IRole>();
			roles.add(new GuestRole());
			roles.add(new RegisteredRole());

			currentUser = UserFactory.createNew(user, pass, roles);			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}
