package org.soen387.app.dispatcher.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.command.user.RegisterCommand;

public class Register extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		RegisterCommand c = new RegisterCommand(myHelper);
		try {
			myRequest.getSession(true).invalidate();
			c.execute();
			myRequest.getSession(true).setAttribute(RequestAttributes.CURRENT_USER_ID, c.currentUser.getId());
			try {
				UoW.getCurrent().commit();
				forward("/WEB-INF/jsp/success.jsp");
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
			
		} catch (CommandException e) {
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
