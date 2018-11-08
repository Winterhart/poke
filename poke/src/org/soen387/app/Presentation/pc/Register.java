package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static Map<String, String>registeredMap = new HashMap<String, String>(); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		if(user==null || user.isEmpty() || pass==null || pass.isEmpty() ) {
			request.setAttribute("message", "Please enter both a username and a password.");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		} else if(registeredMap.containsKey(user)) {
			request.setAttribute("message", "That user has already registered.");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		} else {
			registeredMap.put(user, pass);
			request.setAttribute("message", "That user has been successfully registered.");
			request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
		}
		
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
