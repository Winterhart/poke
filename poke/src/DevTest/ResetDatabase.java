package DevTest;

import org.junit.jupiter.api.Test;
import org.soen387.app.Util.RestartDatabase;

class ResetDatabase {
	
	private boolean resetDbConfirm = true;
	@Test
	void InitializeDatabase() {
		RestartDatabase.RestartDB();
	}
}
