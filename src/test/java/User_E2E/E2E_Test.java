package User_E2E;



	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Test;
	import org.testng.Assert;

	import java.io.IOException;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;



	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;


	import org.openqa.selenium.WebDriver;

	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;


	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

import api_Test.API_Create;
import api_Test.API_List;
import PageObject.pageObject;
import dataProviders.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;



	public class E2E_Test {
		
		ConfigFileReader configFileReader= new ConfigFileReader();
	
		String email = configFileReader.getEmail();
		String password = configFileReader.getPassword();		
		String baseURL = configFileReader.baseURL();
		String Driver = configFileReader.getDriver();
	
		public WebDriver driver;
		
		public String URL;

		
		public ExtentHtmlReporter htmlReporter;
		 public ExtentReports extent;
		 public ExtentTest test;
		

		API_Create create_User = new API_Create();
		API_List api_list = new API_List();
		pageObject page = new pageObject();

		

		
		
		@BeforeTest
		public void beforeTest() throws IOException {

			  // specify location of the report
			  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Report.html");

			  htmlReporter.config().setDocumentTitle("Report"); // Tile of report
			  htmlReporter.config().setReportName("Functional Testing"); // Name of the report
			  htmlReporter.config().setTheme(Theme.DARK);
			  htmlReporter.config().enableTimeline(false);
			  
			  extent = new ExtentReports();
			  extent.attachReporter(htmlReporter);
		  
			  // Passing General information
			  extent.setSystemInfo("Environment", "Dummy Test");
			  extent.setSystemInfo("Role", "QA");
			  extent.setSystemInfo("Name", "Tania Sanjid");


		}


		
		@BeforeMethod
		 public void setup() throws IOException {
			
			
		
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+Driver);
			//WebDriverManager.chromedriver().setup();

			//ChromeOptions options = new ChromeOptions();
			//FirefoxOptions options = new FirefoxOptions();
			//options.addArguments("--headless");
			ChromeOptions capability = new ChromeOptions();
			capability.addArguments("--headless");
			capability.addArguments("--no-sandbox");
			capability.addArguments("--ignore-certificate-errors");
			capability.addArguments("--incognito");
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
	
			driver = new ChromeDriver(capability);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseURL);
		

		 }

		@Test()
		public void userCreation() throws IOException, InterruptedException {
			
			 
			 test = extent.createTest("User is created successfully");
		
			create_User.create_User(email,password);
			test.createNode("Successfully created a user - "+create_User.returnId());
		
			api_list.api_list(create_User.returnId());
			test.createNode("Successfully can view user details");
			

			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[normalize-space()='George']")));	
			
			
			

			Assert.assertEquals(pageObject.getTitle(), "Hello ReqRes users!");
			
			
	
			 
			

		
			}


		@AfterMethod
		public void tearDown(ITestResult result) throws Exception {
		 if (result.getStatus() == ITestResult.FAILURE) {
		  
		  test.log(Status.FAIL, "Test case failed"); // to add error/exception in extent report

		 
		 } else if (result.getStatus() == ITestResult.SKIP) {
		  test.log(Status.SKIP, "Test case skipped");
		 }
		 else 
		  test.log(Status.PASS, "Test case passed");
			
		 driver.close();
		}
		

	

		
		




		@AfterTest
		public void reportEnds() {
			
		    extent.flush();
		}
		}



	


