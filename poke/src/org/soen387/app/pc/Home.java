package org.soen387.app.Presentation.pc;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.soen387.app.Util.RestartDatabase;

@WebServlet("/")
public class Home extends HttpServlet {
	private boolean resetDbConfirm = true;
	public Home() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//RestartDatabase.RestartDB();
    	request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
	}
}
