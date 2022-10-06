package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	//Driver
	public String getDriver(){
		String Driver = properties.getProperty("Driver");
		
			if(Driver!= null) return Driver;
			else throw new RuntimeException("Driver not specified in the testProperty.properties file.");		
		}
	
	//Proxy
	public String getProxy(){
	String Proxy = properties.getProperty("PROXY");
	
		if(Proxy!= null) return Proxy;
		else throw new RuntimeException("Proxy not specified in the testProperty.properties file.");		
	}
	public String getPORT(){
		String Port = properties.getProperty("PORT");
		
			if(Port!= null) return Port;
			else throw new RuntimeException("Port not specified in the testProperty.properties file.");		
		}
	
	public String getProxyPort(){
		String ProxyPort = properties.getProperty("ProxyPort");
		
			if(ProxyPort!= null) return ProxyPort;
			else throw new RuntimeException("ProxyPort not specified in the testProperty.properties file.");		
		}
	

	

	
	public String getEmail(){
	String getEmail = properties.getProperty("getEmail");
	
		if(getEmail!= null) return getEmail;
		else throw new RuntimeException("getEmail not specified in the testProperty.properties file.");		
	}
	
	public String getPassword(){
	String getPassword = properties.getProperty("getPassword");
	
		if(getPassword!= null) return getPassword;
		else throw new RuntimeException("getPassword not specified in the testProperty.properties file.");		
	}
	

	public String User_BaseURI(){
		String User_BaseURI = properties.getProperty("User_BaseURI");
		
			if(User_BaseURI!= null) return User_BaseURI;
			else throw new RuntimeException("User_BaseURI not specified in the testProperty.properties file.");		
		}
	public String User_BasePath(){
		String User_BasePath = properties.getProperty("User_BasePath");
		
			if(User_BasePath!= null) return User_BasePath;
			else throw new RuntimeException("User_BasePath not specified in the testProperty.properties file.");		
		}
	public String Create_BasePath(){
		String Create_BasePath = properties.getProperty("Create_BasePath");
		
			if(Create_BasePath!= null) return Create_BasePath;
			else throw new RuntimeException("Create_BasePath not specified in the testProperty.properties file.");		
		}
	
	public String baseURL(){
		String baseURL = properties.getProperty("baseURL");
		
			if(baseURL!= null) return baseURL;
			else throw new RuntimeException("baseURL not specified in the testProperty.properties file.");		
		}
	




}