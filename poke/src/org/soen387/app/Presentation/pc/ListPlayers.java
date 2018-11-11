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
import org.soen387.app.DataSource.UserRDG;

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
        	UserRDG userFound = null;
        	userFound = UserRDG.find(userid);
        	if(userFound == null) {
        		request.setAttribute("message", "I do not recognize that user.");
        		dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
    			
        	}else {
        		
        		List<UserRDG> users = UserRDG.findAll();
        		response.setContentType("application/json");
        		PrintWriter writer = response.getWriter();
        		writer.println("{");
        		writer.println("\"players\": [");
        		for(UserRDG u : users) {
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
    	if(dis != null) {
    		dis.forward(request, response);
    	}    

	}

}
