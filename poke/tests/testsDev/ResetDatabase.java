package testsDev;

import org.junit.Test;
import org.soen387.app.Util.RestartDatabase;

public class ResetDatabase {
	
	@Test
	public void ResetDB() {
		RestartDatabase.RestartDB();
	}

}
