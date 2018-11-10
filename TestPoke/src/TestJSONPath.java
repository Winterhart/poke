import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class TestJSONPath {

	public static void main(String[] args) {
		String s = "{\n"+
"	\"hand\": [0, 1]\n"+
"}";

		System.out.println(s);
		DocumentContext dc = JsonPath.parse(s);
		String query = "hand[?(@==0 || @==1)]";
		List<Map<String, Object>> jPathResult = dc.read(query);
		
		System.out.println(jPathResult.size());		
		
		query = "hand.length()";
		System.out.println((int)dc.read(query));
	}

}
