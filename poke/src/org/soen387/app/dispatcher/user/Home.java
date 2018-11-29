package org.soen387.app.dispatcher.user;

import java.io.IOException;
import javax.servlet.ServletException;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

public class Home extends Dispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		forward("/WEB-INF/jsp/home.jsp");		
	}
}
