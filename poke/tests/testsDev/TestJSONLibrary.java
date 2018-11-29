package testsDev;

import static org.junit.Assert.*;
import org.junit.Test;
import net.minidev.json.JSONObject;
public class TestJSONLibrary {

	@Test
	public void test() {
		try {
			JSONObject playersJSON = new JSONObject();
			//playersJSON.put("id", 1);
			playersJSON.put("user", "bobo");
			String mmm = "JSON:" + playersJSON.toJSONString();
		
			System.out.println("JSON:"+mmm);
		}catch(Exception ee) {
			ee.printStackTrace();
		}

	}

}
