package org.soen387.app.Presentation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.DispatcherServlet;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.authorization.ApplicationAuthorizaton;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;
import org.soen387.app.Domain.Mapper.PlayerOutputMapper;
import org.soen387.app.Domain.POJO.user.Player;

/**
 * This Front-End Controller class and method
 * is taken from Stuart Thiel's work 
 * @author Stuart Thiel, Adapted by: Charles-Antoine Hardy (27417888)
 *
 */
public class FrontController extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	private static String defaultDispatcher = "";
	
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		// Register with default dispatcher
		// Set based path
		// Prepare DBRegistry
		// SetupUoW
		
		// (Optional) Log stuff...init logger
		super.init(conf);
		try {
			defaultDispatcher = Registry.getProperty("defaultDispatcher");
		}catch(Exception ee) {
			System.out.println("Problem with init , Front-Controller " + ee.getMessage());
		}
		
		//Setup Authorization
		ApplicationAuthorizaton.setBasePath(getServletContext().getRealPath("."));
		prepareDBRegistry();
		setupUoW();
		
		//TODO: Add logging here... 
		
		
	}
	

	private void prepareDBRegistry() {
		// TODO Auto-generated method stub
		
	}


	public static void InitializeUnitOfWork() {
		// Use MapperFactory... to init your domain obj
		//TODO: Impl. this
	}
	
	public static void prepareDatabase() {
		// prepare Database 
		//TODO: Impl
	}
	
	/**
	 * This is the main method to connect to a database...
	 * @param key
	 */
	public static void prepareDBRegistry(String key) {
		MySQLConnectionFactory facto = new MySQLConnectionFactory(null, null, null, null);
		try {
			facto.defaultInitialization();
			
		}catch(Exception ee) {
			System.out.println("Problem while creating connection to database: ");
			ee.printStackTrace();
		}
		
		DbRegistry.setConFactory(facto);
		String tablePre = "";
		try {
			tablePre = DbRegistry.getTablePrefix();
			
		}catch(Exception ee) {
			System.out.println("Problem while setting table prefix  ... ");
			//ee.printStackTrace();
			tablePre = "";
		}
		
		DbRegistry.setTablePrefix(key, tablePre);
		
		
	}
	
	@Override
	protected void processRequest(HttpServletRequest request, 
			HttpServletResponse reponse) {
		//TODO: Implement this 
	}
	

	@Override
	protected void preProcessRequest(HttpServletRequest request, 
			HttpServletResponse reponse) {
		//TODO: Implement this
	}
	

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
		
		//TODO: Create Mapper to find domain
		MapperFactory domainToMapper = new MapperFactory();
		//domainToMapper.addMapping(Player.class, PlayerOutputMapper.class);
		UoW.initMapperFactory(domainToMapper);
		
	}
}