package api_Test;


import java.io.IOException;

import com.google.gson.JsonObject;

import dataProviders.ConfigFileReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;






public class API_List {
	
	
	ConfigFileReader configFileReader= new ConfigFileReader();
	API_Create API_Create = new API_Create();
	
	String Proxy = configFileReader.getProxy();
	String Port = configFileReader.getPORT();
	int PORT=Integer.parseInt(Port);  
	String User_BaseURI = configFileReader.User_BaseURI();
	String User_BasePath = configFileReader.User_BasePath();

	
	JsonObject json = new JsonObject();
	JsonPath jsonResponse;

	
	public void api_list(String id) throws IOException {
		
		RestAssured.baseURI = User_BaseURI;
		RestAssured.basePath = User_BasePath+id;

		jsonResponse = new io.restassured.path.json.JsonPath(RestAssured.given()

				.contentType("application/json") 
				.when()
				.log().method()
				.log().uri()
				.get()
				.asString());
				jsonResponse.prettyPrint();
	}

	public String getId() {
		return jsonResponse.getString("data.id");
	}
	
	public String getEmail() {
		return jsonResponse.getString("data.email");
	}

	
	

}

