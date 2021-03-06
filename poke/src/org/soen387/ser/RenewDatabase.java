package org.soen387.ser;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.soen387.app.PokeFC;
import org.soen387.ser.TDG.BenchTDG;
import org.soen387.ser.TDG.CardTDG;
import org.soen387.ser.TDG.ChallengeTDG;
import org.soen387.ser.TDG.DeckTDG;
import org.soen387.ser.TDG.DiscardTDG;
import org.soen387.ser.TDG.GameTDG;
import org.soen387.ser.TDG.HandTDG;

public class RenewDatabase {

	public static void main(String[] args) {
		PokeFC.prepareDbRegistry("");			
		try {			
			HandTDG.dropTable();
			BenchTDG.dropTable();
			DiscardTDG.dropTable();
			CardTDG.dropTable();
			GameTDG.dropTable();
			ChallengeTDG.dropTable();
			DeckTDG.dropTable();
			UserTDG.dropTable();
			UserTDG.dropUserRoleTable();
		} catch(Exception e){
			System.out.println("Problem while droping table " + e.getMessage());
		}
		try {			
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
			DeckTDG.createTable();
			ChallengeTDG.createTable();
			GameTDG.createTable();
			CardTDG.createTable();
			DiscardTDG.createTable();
			BenchTDG.createTable();
			HandTDG.createTable();
		} catch(Exception e){
			System.out.println("Problem while creating table: " + e.getMessage());
		}
		

	}

}
