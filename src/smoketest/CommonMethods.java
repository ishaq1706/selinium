package smoketest;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CommonMethods {
  /*  private static WebDriver driver;
    
    
    public void openBrowser(WebDriver driver){
    	System.setProperty("webdriver.gecko.driver", "D:\\selinium\\Drivers\\geckodriver.exe");
	     driver=new FirefoxDriver();
	     System.out.println("Opening Firefox Browser");
    }
    
    public void closeBrowser(){
    	driver.quit();
    	System.out.println("Close Firefox Browser");
    }
    
    public void gotoURL(String sURL){
    	driver.get(sURL);
    	System.out.println("Opening url="+sURL);
    }
    
    public void clickLogYourSelfLink(){
    	 driver.findElement(By.xpath("//*[@id='bodyContent']/div/div[1]/a[1]/u")).click();
    	 System.out.println("Link Clicked");
    }
    
    public void Login(String UserName, String PWD){
    	
    	driver.findElement(By.name("email_address")).clear();
        driver.findElement(By.name("email_address")).sendKeys(UserName);
        System.out.println("Entering userName="+UserName);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(PWD);
        System.out.println("Entering password="+PWD);
        driver.findElement(By.id("tdb5")).click();
        System.out.println("SignIn Clicked");
    }
    
    public void VerifyHeader(String svalue){
    	System.out.println("Verifing Header="+svalue);
    	Assert.assertEquals(svalue, driver.findElement(By.cssSelector("h1")).getText());
    }
    
    public void ClickLogOff(){
    	driver.findElement(By.cssSelector("#tdb4 > span.ui-button-text")).click();
    	System.out.println("logoff Clicked");
    }*/
private WebDriver driver;
	
	public CommonMethods(WebDriver driver){
		this.driver = driver;
	}
	
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	
	public void waitForDriver(long enterSeconds){
		driver.manage().timeouts().implicitlyWait(enterSeconds, TimeUnit.SECONDS);
		System.out.println("Driver waiting for: "+ enterSeconds);
	}
	
	public WebDriver openBrowser(String sBrowserType){
		
	if (sBrowserType.equalsIgnoreCase("Chrome")){
		File chromedriver = new File("C:\\Users\\ishaq\\Documents\\chromedriver..exe");
		System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
		DesiredCapabilities Capabilities = new DesiredCapabilities();
		Capabilities = DesiredCapabilities.chrome();
		Capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
		driver = new ChromeDriver(Capabilities);
		System.out.println("Chrome Browser is opened");
	  }
	else if (sBrowserType.equalsIgnoreCase("FireFox")) {
		System.setProperty("webdriver.gecko.driver", "D:\\selinium\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// driver.manage().deleteAllCookies();
		System.out.println("FireFox Browser is opened");
	   }
	else if (sBrowserType.equalsIgnoreCase("Remote")) {
		driver= new RemoteWebDriver(DesiredCapabilities.firefox());	
		// driver.manage().deleteAllCookies();
		System.out.println("FireFox Browser is opened");
	   }
	driver.manage().window().maximize();
	System.out.println(" Browser is mazimized");
	driver.manage().deleteAllCookies();
	System.out.println("deleted all cookies");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	return driver;
  }
	
public void closeBrowser(){
	driver.quit();
	System.out.println(" Browser is closed");
}
	
public WebDriver getDriver(){
	return driver;
}

public void navigateURL(String sURL){
	driver.get(sURL);
	System.out.println("Opening the URL="+sURL);
}

public void VerifyTitle(String sTitle){
	String ActualTile=getTitle();
	System.out.println("ActualTitle="+ActualTile);

	Assert.assertEquals(ActualTile, sTitle);
	
}


public String getTitle(){
	try{
		return driver.getTitle();
	}
	catch(Exception e){
		return "Not able to capture title";
	}

}

//THis can be used for clicking any element by XPATH
public void clickElementByXPATH(String element){
	try{
		driver.findElement(By.xpath(element)).click();
		System.out.println(element+" clicked");
	}catch(Exception e){
		System.out.println(element+" not found");
	}
 }


public void clickElementByLinkText(String element){
	try{
		driver.findElement(By.linkText(element)).click();
		System.out.println(element+" clicked");
	}catch(Exception e){
		System.out.println(element+" not found");
		Assert.fail();
	}
 }

public void setValueByName(String elementName, String sValue){
	try{
		driver.findElement(By.name(elementName)).clear();
		driver.findElement(By.name(elementName)).sendKeys(sValue);
		System.out.println(sValue+" entered");
	}catch(Exception e){
		System.out.println(elementName+" not found");
	}
	
}

public void setValue(WebElement slocator, String sValue){
	try{
		slocator.clear();
		slocator.sendKeys(sValue);
		System.out.println(sValue+" entered");
	}catch(Exception e){
		System.out.println(slocator+" not found");
	}
	
}

public void verifyText(String expected){
	 try{
		 driver.findElement(By.xpath("//*[contains(text(),'"+ expected.trim() +"')]"));
		 System.out.println("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" verified");
		// return true;
	 }
	 catch(NoSuchElementException e){
		 System.out.println("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" not found");
		 Assert.fail("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" not found");
	 }
	 
}

public void click(WebElement slocator){
	try {
		
		String Element=slocator.getText();
		if ((Element.isEmpty()) || (Element==null)){
			Element=slocator.getAttribute("value");
		}
		 System.out.println(Element + " trying to click");

		slocator.click();
		 System.out.println(Element + " clicked ");
		//acceptPopup();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
	} catch (Exception ex) {
		ex.printStackTrace();
		 System.out.println(slocator + " not clicked ");
	}}


public void selectRadioButton(By locator, String value){
	List<WebElement> select = driver.findElements(locator);

	for (WebElement radio : select){
		if (radio.getAttribute("value").equalsIgnoreCase(value)){
			radio.click(); 	            	

		}}}

}
