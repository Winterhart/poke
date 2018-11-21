package org.soen387.app.DataSource.TDG;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	
	private static final String tableName = "user";
	private static final String TABLE = DbRegistry.getTablePrefix()  + tableName;
	
	private final static String CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS" + TABLE + 
			"(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
			"version int NOT NULL DEFAULT 0, " +
			"username NVARCHAR(256) NOT NULL UNIQUE, " +
			"password NVARCHAR(512) NOT NULL UNIQUE ); ";
	
}
