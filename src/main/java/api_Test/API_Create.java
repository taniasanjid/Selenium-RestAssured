package api_Test;


import com.google.gson.JsonObject;

import dataProviders.ConfigFileReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;






public class API_Create {
	
	
	ConfigFileReader configFileReader= new ConfigFileReader();
	
	String Proxy = configFileReader.getProxy();
	String Port = configFileReader.getPORT();
	int PORT=Integer.parseInt(Port);  
	String User_BaseURI = configFileReader.User_BaseURI();
	String User_BasePath = configFileReader.User_BasePath();
	String Create_BasePath = configFileReader.Create_BasePath();
	JsonObject json = new JsonObject();
	JsonPath jsonResponse;
	
	public void create_User(String email, String password) {
		

		
		
		String payload = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
			
					RestAssured.baseURI=User_BaseURI;
					RestAssured.basePath=Create_BasePath;
					
		
					jsonResponse = new JsonPath(
							RestAssured
							.given()


							.contentType("application/json")
	
							.body(payload)
							.when()
							.log().method()
							.log().uri()
			
							.post()
							.asString());
							jsonResponse.prettyPrint();
	
	}
	

	public String returnId() {
		return jsonResponse.getString("id") ;
	}
	
	public String returnName() {
		return jsonResponse.getString("name") ;
	}
	
	
	
	
}
