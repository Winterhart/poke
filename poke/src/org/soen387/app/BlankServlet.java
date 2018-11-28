package org.soen387.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 
 * This is a ridiculous solution to making the Filters do what I want, but I'll let it be because I don't want to waste time on it.
 * 
 */
@WebServlet("/Poke/*")
public class BlankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BlankServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
