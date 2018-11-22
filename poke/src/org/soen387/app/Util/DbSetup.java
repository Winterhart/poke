package org.soen387.app.Util;

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
		
	}
	protected static void removeTables() {
		
	}
	protected static void startLogging() {
		
	}
	public static void setup() {
		startLogging();
	}

}
