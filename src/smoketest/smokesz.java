package smoketest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class smokesz {
	
	public static WebDriver driver;
	public static CommonMethods Cm;
	


	 
	@Test(groups="Smoke Test")
	 
	 public void test(){
		 Cm=new CommonMethods(driver);
		 driver =Cm.openBrowser("Firefox");
		 Cm.navigateURL("http://10.1.10.93:9090/CounterWebApp/");
		 Cm.verifyText("Maven Spring MVC Web Project Example");
		 //Cm.VerifyTitle("Maven Spring MVC Web Project Example");
		// assertEquals("Maven Spring MVC Web Project Example", driver.findElement(By.xpath("/html/body/h1")).getText());
		 System.out.println("Maven Spring MVC Web Project Example");
		 Cm.closeBrowser();
		
		 
		 
		
		
		
		
		
		
	}

}
