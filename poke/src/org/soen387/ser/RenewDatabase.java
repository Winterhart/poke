package org.soen387.ser;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.soen387.app.PokeFC;

public class RenewDatabase {

	public static void main(String[] args) {
		PokeFC.prepareDbRegistry("");
		try {
		UserTDG.dropTable();
		} catch(Exception e){}
		
		try {
			UserTDG.dropUserRoleTable();
		} catch(Exception e){}
		
		
		try {
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
		} catch(Exception e){}

	}

}
