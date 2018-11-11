package org.soen387.app.Presentation.pc;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.DataSource.TableInit.CardTableInit;
import org.soen387.app.DataSource.TableInit.ChallengeTableInit;
import org.soen387.app.DataSource.TableInit.DeckTableInit;
import org.soen387.app.DataSource.TableInit.UserTableInit;

@WebServlet("/")
public class Home extends HttpServlet {
	private boolean resetDbConfirm = true;
	public Home() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	resetDatabase();
    	request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
	}
	
	
	private void resetDatabase() {
		if(resetDbConfirm) {
			try {
				//Add all our TDG 
				//TODO: Refactor idea: use Command pattern to reset all table
				UserTableInit.deleteTable();
				CardTableInit.deleteTable();
				DeckTableInit.deleteTable();
				ChallengeTableInit.deleteTable();
				
				ChallengeTableInit.createTable();
				DeckTableInit.createTable();
				CardTableInit.createTable();
				UserTableInit.createTable();
				
				System.out.println("Database Reset");
				
			}catch(Exception ee) {
				System.out.println("Problem while reset database " + ee.getMessage());
				fail("Error while trying to reset " + ee.getMessage());
			}	
		}else {
			fail("Set the value resetDbConfirm to true");
		}
	}
}
