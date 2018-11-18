package org.soen387.app.Presentation.pc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.RDG.UserRDG;
import org.soen387.app.Util.Hasher;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		RequestDispatcher dispatcher = null;
		if(user==null || user.isEmpty() || pass==null || pass.isEmpty() ) {
			request.setAttribute("message", "Please enter both a username and a password.");
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
		}else {
			//Convert to lower cases
			UserRDG similarUser = null;
			try {
				similarUser = UserRDG.findByUsername(user);
			}catch(Exception ee) {
				System.out.println(ee.getMessage());
			}
			// Very simple hash function to not store user password directly
			String hashedPassword = user + pass;
			hashedPassword = Hasher.obtainHashText(hashedPassword);
			
			if(similarUser == null) {
				try {
					long id = 0;
					id = UserRDG.getFollowingId();
					UserRDG createdUser = new UserRDG(id, 0, user, hashedPassword);
					createdUser.insert();
					request.getSession(true).setAttribute("userid", id);
					request.setAttribute("message", "That user has been successfully registered.");
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
					
				}catch(Exception ee) {
					ee.printStackTrace();
					request.setAttribute("message", "Problem while registring");
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
				}		
			}else {
				request.setAttribute("message", "That user has already registered.");
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
			}
		}
		
		dispatcher.forward(request, response);

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
