import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

class TestSuite {
	private final static Logger testLogger = Logger.getLogger(TestSuite.class.getName());
	
	String URL_BASE = "http://localhost:8080/poke/";
	
	private String register(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"Register"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("user", username));
		requestSettings.getRequestParameters().add(new NameValuePair("pass", password));
		
		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	private String login(WebClient webClient, String username, String password)
			throws MalformedURLException, IOException {
		WebRequest requestSettings = new WebRequest(new URL(URL_BASE+"Login"), HttpMethod.POST);

		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(new NameValuePair("user", username));
		requestSettings.getRequestParameters().add(new NameValuePair("pass", password));

		Page page = webClient.getPage(requestSettings);
		String jsonText = page.getWebResponse().getContentAsString();
		testLogger.log(Level.INFO, jsonText);
		return jsonText;
	}
	
	@Test
	void testRegisterNoInfo() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "", "");
			DocumentContext dc = JsonPath.parse(jsonText);
			assertEquals("fail", dc.read("$['status']"));

			jsonText = register(webClient, "", "fred2");
			dc = JsonPath.parse(jsonText);
			assertEquals("fail", dc.read("$['status']"));
			
			jsonText = register(webClient, "bob2", "");
			dc = JsonPath.parse(jsonText);
			assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	void testRegisterSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob", "fred");
			DocumentContext dc = JsonPath.parse(jsonText);
			assertEquals("success", dc.read("$['status']"));
			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	void testRegisterDuplicate() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob1", "fred1");
			DocumentContext dc = JsonPath.parse(jsonText);
			assertEquals("success", dc.read("$['status']"));
			
			jsonText = register(webClient, "bob1", "fred1");
			dc = JsonPath.parse(jsonText);
			assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			
		}
	}


	
	
	@Test
	void testLoginSuccess() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = register(webClient, "bob3", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			assertEquals("success", dc.read("$['status']"));
			
			jsonText = login(webClient, "bob3", "fred3");
			dc = JsonPath.parse(jsonText);
			assertEquals("success", dc.read("$['status']"));
			
			
		} catch (IOException e) {
			
		}
	}
	
	@Test
	void testLoginFail() {
		try {
			WebClient webClient = new WebClient();
			String jsonText = login(webClient, "bob5", "fred3");
			DocumentContext dc = JsonPath.parse(jsonText);
			assertEquals("fail", dc.read("$['status']"));
			
		} catch (IOException e) {
			
		}
	}
	
}
