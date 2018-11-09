package org.soen387.app.Presentation.pc;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.Domain.DataMapper.UserDataMapper;
import org.soen387.app.Domain.pojo.User;
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
		if(user==null || user.isEmpty() || pass==null || pass.isEmpty() ) {
			request.setAttribute("message", "Please enter both a username and a password.");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		}  
		//Convert to lower cases
		User similarUser = null;
		try {
			similarUser = UserDataMapper.findByUsername(user);
		}catch(Exception ee) {
			System.out.println(ee.getMessage());
		}

		if(similarUser != null) {
			request.setAttribute("message", "That user has already registered.");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		}
		
		
		// Very simple hash function to not store user password directly
		String hashedPassword = user + pass;
		hashedPassword = Hasher.obtainHashText(hashedPassword);
		
		if(hashedPassword == null) {
			request.setAttribute("message", "Problem while creating password");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		}
		long id = 0;
		int creationStatus = 0;
		try {
			id = UserDataMapper.getFollowingId();
			User createdUser = new User(id, 0, user, hashedPassword);
			creationStatus = UserDataMapper.insertUser(createdUser);
		}catch(Exception ee) {
			ee.printStackTrace();
			request.setAttribute("message", "Problem while creating user");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		}
		
		if(creationStatus != 0) {
			request.setAttribute("message", "That user has been successfully registered.");
			request.getSession(true).setAttribute("userid", id);
			request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Problem with user registration");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
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
