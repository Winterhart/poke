package org.soen387.app.dispatcher.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

public class Home extends Dispatcher  {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			forward("/WEB-INF/jsp/home.jsp");
		}catch(Exception ee) {
			System.out.println("Exception...while going to home page " + ee.getMessage());
			myHelper.setRequestAttribute("message", ee.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}
}
