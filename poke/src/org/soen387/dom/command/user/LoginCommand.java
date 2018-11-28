package org.soen387.dom.command.user;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

public class LoginCommand extends ValidatorCommand {

	@Source
	public String user;
	
	@Source
	public String pass;
	
	@SetInRequestAttribute
	public IUser currentUser;
	
	public LoginCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			currentUser = UserInputMapper.find(user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}
