package org.soen387.app.Presentation.pc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.soen387.app.Domain.DataMapper.UserDataMapper;
import org.soen387.app.Domain.pojo.User;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Logout() {
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	RequestDispatcher dis = null;
    	try {
        	long userid = (Long)request.getSession(true).getAttribute("userid");
        	User userFound = null;
        	userFound = UserDataMapper.find(userid);
        	if(userFound == null) {
        		request.setAttribute("message", "I do not recognize that user.");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
        		request.getSession(true).invalidate();
        		request.setAttribute("message", "Successfully logged out.");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
        		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while logout");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

	

}
