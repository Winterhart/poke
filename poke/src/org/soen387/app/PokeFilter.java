package org.soen387.app;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.dsrg.soenea.application.filter.PermalinkFilter;

@WebFilter( 
		urlPatterns="/Poke/*",
		dispatcherTypes={DispatcherType.REQUEST}
)
public class PokeFilter extends PermalinkFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		super.doFilter(request, response, chain);
		request.getServletContext().getRequestDispatcher("/PokeServlet").include(request, response);
	}
}
