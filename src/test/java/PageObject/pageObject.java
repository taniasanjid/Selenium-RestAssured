package PageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class pageObject {
	static WebDriver Driver;

 
public static String getTitle(){

   return Driver.findElement(By.xpath("//h1[normalize-space()='Hello ReqRes users!']")).getText();

    }
}
