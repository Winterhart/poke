package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.RDG.UserRDG;
import org.soen387.app.Util.Hasher;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		RequestDispatcher dis = null;
		if(user!=null && !user.isEmpty() && pass!=null && !pass.isEmpty() ) {
			request.setAttribute("message", "Please enter both a username and a password.");
			dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
			String hashedPassword = Hasher.obtainHashText(user + pass);
			UserRDG userFound = null;
			try {
				userFound = UserRDG.findUserWithCredential(user, hashedPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(userFound != null) {
				request.setAttribute("message", "Successfully logged in.");
				request.getSession(true).setAttribute("userid", userFound.getId());
				dis = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");

			}else {
				request.setAttribute("message", "I do not recognize that username and password combination.");
				dis = request.getRequestDispatcher("WEB-INF/jsp/fail.jsp");
			}		
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
