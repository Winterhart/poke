package testsDev;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJSONLibrary {

	@Test
	public void test() {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<MonsieurPatate> pat = new ArrayList<MonsieurPatate>();
			pat.add(new MonsieurPatate("Bob", 33, (long)2));
			pat.add(new MonsieurPatate("Rob", 55, (long)6));
			
			String patates = gson.toJson(pat);
			System.out.println(patates);
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}

	}
	
	private class MonsieurPatate {
		private String name;
		private int age;
		private Long wifeId;
		
		
		
		public MonsieurPatate(String name, int age, Long wifeId) {
			super();
			this.name = name;
			this.age = age;
			this.wifeId = wifeId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Long getWifeId() {
			return wifeId;
		}
		public void setWifeId(Long wifeId) {
			this.wifeId = wifeId;
		}
		
		
		
		
	}

}
