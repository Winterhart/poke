package org.soen387.app.Domain.DataMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.soen387.app.Domain.pojo.User;
import org.soen387.app.DataSource.UserTDG;

public class UserDataMapper {

	
	public static User findByUsername(String username) throws SQLException {
		ResultSet userFound = UserTDG.findByUsername(username);
		while(userFound.next()) {
			long foundId = userFound.getLong("id");
			int version = userFound.getInt("version");
			String password = userFound.getString("password");
			//TODO: add this user to identityMap 
			return new User(foundId, version, username, password);
		}
		
		return null;
	}
	
	public static User find(long id) throws SQLException {
		ResultSet userFound = UserTDG.find(id);
		while(userFound.next()) {
			long foundId = userFound.getLong("id");
			int version = userFound.getInt("version");
			String username = userFound.getString("username");
			String password = userFound.getString("password");
			//TODO: add this user to identityMap 
			return new User(foundId, version, username, password);
		}
		return null;
	}
	
	public static User findUserWithCredential(String user, String pass) throws SQLException {
		ResultSet userFound = UserTDG.findUserWithCredential(user, pass);
		while(userFound.next()) {
			long foundId = userFound.getLong("id");
			int version = userFound.getInt("version");
			String username = userFound.getString("username");
			String password = userFound.getString("password");
			//TODO: add this user to identityMap 
			return new User(foundId, version, username, password);
		}
		
		return null;
	
	}
	
	public static int insertUser(User addedUser) throws SQLException  {
		
		return UserTDG.insert(addedUser.getId(),
				addedUser.getVersion(),
				addedUser.getUsername(),
				addedUser.getPassword());
	}
	
	public static long getFollowingId() throws SQLException {
		return UserTDG.getFollowingId();
	}
}
