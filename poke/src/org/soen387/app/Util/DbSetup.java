package org.soen387.app.Util;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.app.Presentation.FrontController;
/**
 * Inspired by Stuart Thiel's Thesis : 
 * 'Enterprise Application Design Patterns: Improved and Applied'
 */
public abstract class DbSetup {
	
	public static void main(String[] args) {
		startLogging();
		FrontController.prepareDBRegsitry("");
		FrontController.setupUoW();
		removeTables();
		createDatabase();
		
	}
	protected static void createDatabase() {
		
		try {
			
			UserTDG.createTable();
			//TODO: Add other table
			
		}catch(Exception ee) {
			System.out.println(" ERROR WHILE CREATING DATABASE ");
			ee.printStackTrace();
		}
		
	}
	protected static void removeTables() {
		try {
			UserTDG.dropTable();
			//TODO: add Drop to other table
		}catch(Exception ee) {
			System.out.println(" ERROR WHILE DELETING DATABASE");
		}
	}
	protected static void startLogging() {
		//TODO: Add logging implementation
	}
	protected static void startTransaction() {
		
	}
	
	public static void setup() {
		startLogging();
		FrontController.prepareDBRegsitry("");
		FrontController.setupUoW();
		try {
			
			startTransaction();
			createDatabase();
			
		}catch(Exception ee) {
			ee.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void tearDown() {
		try {
			startTransaction();
			removeTables();
			finishTransaction();
			DbRegistry.closeDbConnectionIfNeeded();
		}catch(Exception ee) {
			ee.printStackTrace();
			
		}
	}
	
	public static void finishTransaction() {
		
	}
	


}
