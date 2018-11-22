package org.soen387.app.Presentation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.DispatcherServlet;

/**
 * This Front-End Controller class and method
 * is taken from Stuart Thiel's work 
 * @author Stuart Thiel, Adapted by: Charles-Antoine Hardy (27417888)
 *
 */
public class FrontController extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Original method from Stuart Thiel
	 */
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		// Register with default dispatcher
		// Set based path
		// Prepare DBRegistry
		// SetupUoW
		
		// (Optional) Log stuff...init logger
	}
	
	/**
	 *  Original method from Stuart Thiel
	 */
	public static void InitializeUnitOfWork() {
		// Use MapperFactory... to init your domain obj
		//TODO: Impl. this
	}
	
	/**
	 *  Original method from Stuart Thiel
	 */
	public static void prepareDatabase() {
		// prepare Database 
		//TODO: Impl
	}
	
	/**
	 *  Original method from Stuart Thiel
	 * @param key
	 */
	public static void prepareDBRegsitry(String key) {
		//TODO: Impl
	}
	
	/**
	 *  Original method from Stuart Thiel
	 */
	@Override
	protected void processRequest(HttpServletRequest request, 
			HttpServletResponse reponse) {
		//TODO: Implement this 
	}
	
	/**
	 *  Original method from Stuart Thiel
	 */
	@Override
	protected void preProcessRequest(HttpServletRequest request, 
			HttpServletResponse reponse) {
		//TODO: Implement this
	}
	
	/**
	 *  Original method from Stuart Thiel
	 */
	@Override
	protected void postProcessRequest(HttpServletRequest request, 
			HttpServletResponse reponse) {
		//TODO: Implment this
	}
	
	protected String getCommandName(HttpServletRequest request, 
			HttpServletResponse reponse) {
		String cmdName = "";
		//TODO: Implement this
		return cmdName;
	}

	public static void setupUoW() {
		// TODO Auto-generated method stub
		
	}
}