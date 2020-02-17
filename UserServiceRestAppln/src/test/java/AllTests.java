import java.net.URI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import service.User;

public class AllTests {

	RestTemplate restTemplate = new RestTemplate();
	String baseUrl = "http://localhost:8080/UserService/";
	
	@Test
	public void testGetAllUsers() {
		try {
			StringBuilder getAllUsers = new StringBuilder(baseUrl);
			getAllUsers.append("getAllUsers");
			URI uri = new URI(getAllUsers.toString());
			ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
			JSONArray respUsersArray = new JSONArray(result.getBody());
			Assert.assertEquals(200, result.getStatusCodeValue());
			Assert.assertEquals(true, respUsersArray.length() > 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
			
	@Test
	public void createUserTest() {
		try {
			StringBuilder createUser = new StringBuilder(baseUrl);
			createUser.append("createUser");
			URI uri = new URI(createUser.toString());
			User user = new User("User6","Sourav");
			ResponseEntity<String> result = restTemplate.postForEntity(uri, (Object) user, String.class);
			Assert.assertEquals(201, result.getStatusCodeValue());
			Assert.assertEquals(true, "User created successfully".equals(result.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}