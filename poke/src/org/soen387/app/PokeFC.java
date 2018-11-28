package org.soen387.app;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dsrg.soenea.application.servlet.Servlet;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.dispatcher.HttpServletHelper;
import org.dsrg.soenea.application.servlet.dispatcher.MultipartHttpServletHelper;
import org.dsrg.soenea.application.servlet.impl.AuthenticationException;
import org.dsrg.soenea.application.servlet.impl.AuthorizationException;
import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.application.servlet.impl.SmartDispatcherServlet;
import org.dsrg.soenea.application.servlet.service.DispatcherFactory;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.GuestUser;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.dsrg.soenea.domain.user.mapper.UserOutputMapper;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.authorization.ApplicationAuthorizaton;
import org.dsrg.soenea.service.fileupload.FileUploadFactory;
import org.dsrg.soenea.service.logging.Logging;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;

@WebServlet("/PokeServlet")
public class PokeFC extends Servlet {

	private static final Log log = LogFactory.getLog(SmartDispatcherServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ThreadLocal<Helper> helper = new ThreadLocal<Helper>();
	
	private static String defaultCommand;
	
	
	protected String getDefaultCommand() {
		return "org.dsrg.soenea.application.servlet.dispatcher.impl.DefaultCommand";
	}
	
	public static void prepareDbRegistry(final String db_id) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		final MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization(db_id);
		} catch (final SQLException e2) {
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(db_id, f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty(db_id+"mySqlTablePrefix");
		} catch (final Exception e1) {
			e1.printStackTrace();
			tablePrefix = "";
		}
		if(tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(db_id, tablePrefix);
	}
	
	@Override
	public void init(final ServletConfig config) throws ServletException {
	    super.init(config);

	    ApplicationAuthorizaton.setBasePath(getServletContext().getRealPath("/."));
	    prepareDbRegistry("");
	    
	    
	    getServletContext().setAttribute("sp", " ");
	    getServletContext().setAttribute("br", "\n");
	    
	    //With protocol and host
	    String url = "";
	    try {
	        url = Registry.getProperty("BaseAbsoluteURL");
	        if(url == null || url.equals("")) {
	        	System.out.println("BaseAbsoluteURL must be defined in MyResources.properties.");
	        	return;
	        }
	        getServletContext().setAttribute("BaseAbsoluteURL", url);
	    } catch (final Exception e) {
	    	System.out.println("BaseAbsoluteURL must be defined in MyResources.properties.");
	    	return;
	    }
	    
	    //with just the path, probably /
	    try {
	        url = Registry.getProperty("BaseURL");
	        if(url == null || url.equals("")) {
	        	System.out.println("BaseURL must be defined in MyResources.properties.");
	        	return;
	        }
	        getServletContext().setAttribute("BaseURL", url);
	    } catch (final Exception e) {
	    	System.out.println("BaseURL must be defined in MyResources.properties.");
	    	return;
	    }

	    try {
	    	defaultCommand = getDefaultCommand();
		    defaultCommand = Registry.getProperty("DefaultCommand");
	    } catch (final Exception e) {
	    	e.printStackTrace();
	    }

		final File fileUploadDir = new File(Registry.getString("file.uploadDir"));
		if (!fileUploadDir.exists()) {
			if (!fileUploadDir.mkdirs())
				throw new ServletException("Could not create file upload directory: " + fileUploadDir.getAbsolutePath());
		}
		getServletContext().setAttribute("fileUploadDirectory", fileUploadDir);
		
		FileUploadFactory.setRepo(new File(getServletContext().getRealPath("WEB-INF/files/temp")));
		
		setupUoW();
	}
	
	 public static void setupUoW() {
		 MapperFactory myDomain2MapperMapper = new MapperFactory();
		 myDomain2MapperMapper.addMapping(User.class, UserOutputMapper.class);
		 UoW.initMapperFactory(myDomain2MapperMapper);
	 } 
	

	/** 
	 * Processes requests for both HTTP <code>GET</code> and 
	 * <code>POST</code> methods.
	 * 
	 * All this does is attempt to create a FrontCommand and execute it. 
	 * The only time this method does anything itself besides initiating 
	 * the above actions is when there is a problem, at which point it will
	 * forward to the Error JSP page.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void processRequest(
		final HttpServletRequest request,
		final HttpServletResponse response)
		throws ServletException, java.io.IOException //
	{
		preProcessRequest(request, response);
		
		if(FileUploadFactory.isMultipartContent(request)) {
			helper.set(new MultipartHttpServletHelper(request));
		} else {
			helper.set(new HttpServletHelper(request));
		}	
		
		helper.get().logParameters();
		try {
			Logging.logDebug("RequestURI: " + request.getRequestURI());
			
			request.setCharacterEncoding("UTF-8");

			helper.get().setRequestAttribute("currentTimeMillis", System.currentTimeMillis());		
			helper.get().setRequestAttribute("ip", 0); // TODO: put a real ip address in here
			helper.get().setRequestAttribute("method", request.getMethod());
			String commandName = getCommandName();
			
			Logging.log("Command: " + commandName);
			
			processAuthentication();
			
			IUser currentUser = (IUser)helper.get().getAttribute(org.dsrg.soenea.application.servlet.impl.RequestAttributes.CURRENT_USER);
				
			processAuthorization(request, commandName, currentUser);
			
			processDispatcher(request, response, commandName);

		} catch (final AuthorizationException e) {
			log.debug("", e);
			List<String> notifications = (List<String>)request.getAttribute("notifications");
			if(notifications == null) notifications = new Vector<String>();
			request.setAttribute("notifications", notifications);
			
			List<String> notify_fields = (List<String>)request.getAttribute("notify_fields");
			if(notify_fields == null) notify_fields = new Vector<String>();
			request.setAttribute("notify_fields", notify_fields);

			notify_fields.add("notifications");
			notifications.add(e.getMessage());		

			
			if ("XML".equalsIgnoreCase(request.getParameter("mode"))) {
				request.getRequestDispatcher(getXMLErrorTemplate()).forward(request, response);
			} else if ("JSON".equalsIgnoreCase(request.getParameter("mode"))) {
				request.getRequestDispatcher(getJSONErrorTemplate()).forward(request, response);
			} else {
				final String template = getErrorTemplate();
				request.getRequestDispatcher(template).forward(request, response);
			}
		} catch (final Exception exception) {
			Throwable e = exception;
			while (e.getCause() != null) e = e.getCause();
			Logging.logError(getClass().getName() + "processRequest");
			Logging.logError(exception);
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("exception", e);
			final String template = getErrorTemplate();
			request.getRequestDispatcher(template).include(request, response);
			
		}
		postProcessRequest(request, response);
	}


	protected String getXMLErrorTemplate() {return "/WEB-INF/jsp/fail.jsp";}
	
	protected String getJSONErrorTemplate() {return "/WEB-INF/jsp/fail.jsp";}


	protected String getErrorTemplate() {return "/WEB-INF/jsp/fail.jsp";}


	protected String getMessageTemplate() {return "/WEB-INF/jsp/success.jsp";}


	protected String getMainTemplate() {return "/WEB-INF/jsp/success.jsp";}


	private void processDispatcher(final HttpServletRequest request,
			final HttpServletResponse response, String commandName)
			throws Exception, ServletException, IOException {
		final Dispatcher d = DispatcherFactory.getInstance(commandName);
		d.init(request, response);
		d.execute();
	}


	protected void processAuthorization(final HttpServletRequest request,
			String commandName, IUser currentUser)
			throws AuthorizationException {
		if (!ApplicationAuthorizaton.hasAuthority(commandName, currentUser.getRoles(), ApplicationAuthorizaton.RequestMethod.valueOf(request.getMethod()))) {
			throw new AuthorizationException("Access Denied to " + commandName + " for user " + currentUser.getUsername() + " using method " + request.getMethod());
		}
	}


	protected void processAuthentication() throws AuthenticationException {
		IUser currentUser = new GuestUser();
		final Long userId = (Long)helper.get().getSessionAttribute(RequestAttributes.CURRENT_USER_ID);
		try {
			if (userId != null) {
				currentUser = UserInputMapper.find(userId);
			}
		} catch (final Exception e) {
			throw new AuthenticationException(e);
		} finally {
			helper.get().setRequestAttribute(RequestAttributes.CURRENT_USER, currentUser);
		}
	}


	@Override
	protected void preProcessRequest(final HttpServletRequest request, final HttpServletResponse response) {
		super.preProcessRequest(request, response);
		UoW.newCurrent();
		try {
			startDatabaseTransactions();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void postProcessRequest(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			endDatabaseTransactions();
		} catch (final Exception e) {
			//It's ok to throw this away... that's the point. If something is really
			//going wrong, we'll catch it elsewhere. I guess that's the theory.
			// 20070621 dhr: Scary...
		}
		performRequestCleanup();
	}

	protected void performRequestCleanup() {
		ThreadLocalTracker.purgeThreadLocal();
		helper.remove();
	}

	/**
	 * The default implementation sets autocommit false on the default database connection and starts a transaction
	 * 
	 * Transactions and connection setup should be done here.
	 * 
	 * @throws SQLException
	 */
	protected void startDatabaseTransactions() throws SQLException {
		DbRegistry.getDbConnection().setAutoCommit(false);
		DbRegistry.getDbConnection().createStatement().execute("START TRANSACTION;");
	}

	
	/**
	 * The default implementation rolls back the default connection, undoing any uncommited changes.
	 * 
	 * @throws SQLException
	 */
	protected void endDatabaseTransactions() throws SQLException {
		DbRegistry.getDbConnection().createStatement().execute("ROLLBACK;");
		DbRegistry.closeDbConnectionIfNeeded();
	}

	protected String getCommandName() throws Exception{
		String commandName = helper.get().getString("command");
		if(commandName == null || commandName.isEmpty()) commandName = (String)helper.get().getAttribute("command");
		if (commandName == null || commandName.equals("")) {
			commandName=defaultCommand;
		}
		return commandName.trim();
	}

}
