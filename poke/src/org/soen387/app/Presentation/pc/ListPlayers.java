package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.soen387.app.Domain.DataMapper.UserDataMapper;
import org.soen387.app.Domain.pojo.User;

@WebServlet("/ListPlayers")
public class ListPlayers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ListPlayers() {
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
        		
        		List<User> users = UserDataMapper.findAll();
        		response.setContentType("application/json");
        		PrintWriter writer = response.getWriter();
        		writer.println("{");
        		writer.println("\"players\": [");
        		for(User u : users) {
        			writer.println("{ \"id\": " + u.getId() + ", \"user\": " + u.getUsername() + " },");
        		}
        		writer.println("]");
        		writer.println("}");
        		writer.close();
        		
        	}
    	}catch(Exception ee) {
    		ee.printStackTrace();
			request.setAttribute("message", "Problem while getting players");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    	}
    	
    	dis.forward(request, response);
	}

}
