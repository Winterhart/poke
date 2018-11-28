package org.soen387.app.dispatcher.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

public class Logout extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		myRequest.getSession(true).invalidate();
		forward("/WEB-INF/jsp/success.jsp");		
	}

}
