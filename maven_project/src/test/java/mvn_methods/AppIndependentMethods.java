package mvn_methods;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import mvn_driver.DriverScript;

public class AppIndependentMethods extends DriverScript {
	
	
	/***************************
	 * Method        : getpropdata
	 * Purpose       : To get .properties data 
	 * Author        : Mahesh S
	 * created date  : 2-Jun-2023
	 * review date   :
	 * Review By     :
	 * Approved By   :
	 * Arguments     : String<keyname>
	 * Return Type   : String
	 **********************************/
	@Test
	public String getpropdata(String keyname){
		
		FileInputStream fin = null;
		Properties prop = null;
		
		try {
			
			fin = new FileInputStream(System.getProperty("user.dir")+"\\configuration\\configdata.properties");
			prop = new Properties();
			prop.load(fin);
			
			return prop.getProperty(keyname);
			
		
			
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}finally {
			try {
				fin.close();
				fin=null;
				prop=null;
				
			}catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
	
	
	/***************************
	 * Method        : getdatetime
	 * Purpose       : To get datetime for saving screenshot
	 * Author        : Mahesh S
	 * created date  : 2-Jun-2023
	 * review date   :
	 * Review By     :
	 * Approved By   :
	 * Arguments     : String<format>
	 * Return Type   : String
	 **********************************/
	@Test
	public String getdatetime(String format) {
		Date dt = null;
		SimpleDateFormat sdf = null;
		
		try {
			dt = new Date();
			sdf= new SimpleDateFormat(format);
			return sdf.format(dt);
			
		}catch (Exception e) {
			System.out.println("Exception found in gatedatetime method"+e);
			return null;
		}finally {
			
		}
	}

	/********************************
	 * Method      : launchbrowser
	 * purpose     : To launch the browser
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : String <browsername>
	 * return type : WebDriver
	 * 
	 ***************************/
@Test
	public WebDriver launchbrowser(String browsername) {
		WebDriver driver = null;
		ChromeOptions options =null;
		try {
			switch(browsername.toLowerCase()){
				case "chrome":
					System.setProperty("Webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
					options= new ChromeOptions();
					options.addArguments("--remote-allow-origins=*");
					driver = new ChromeDriver(options);
					driver.manage().window().maximize();
					break;
					
				case "edge":
					System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe");
					driver = new EdgeDriver();
					driver.manage().window().maximize();
					break;
					
				case "firefox":
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir"+"\\drivers\\geckodriver.exe"));
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
					break;
				
				default:
					System.out.println("Invalid browser name: "+browsername);
					
			}
			
				return driver;
			
			
		
		}catch (Exception e) {
			System.out.println("Exception in launch browser method"+e);
			return null;
		}finally {
			
		}
		
	}
	
	/********************************
	 * Method      : waits
	 * purpose     : To wait for the element to visible
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : WebDriver <driver>, By <byobj>, String <eletype>, String <expectedstr>, int <timeout> 
	 * return type : boolean
	 * 
	 ***************************/
	@Test
	
	public boolean waits(WebDriver driver, By byobj, String eletype, String expectedstr, int timeout ) {
		WebDriverWait owait = null;
		try {
			owait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			switch(eletype.toLowerCase()) {
			case "click":
				owait.until(ExpectedConditions.elementToBeClickable(byobj));
				break;
				
			case "visible":
				owait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byobj));
				break;
				
			case "text":
				owait.until(ExpectedConditions.textToBePresentInElementLocated(byobj, expectedstr));
				break;
			
			default:
				reports.writeresult(driver, "fail", "Invalid selection of elements");
			}
			
			return true;
		}catch (Exception e) {
			reports.writeresult(driver, "exception", "Exception found in waits method"+e);
			return false;
		}finally {
			
		}
		
	}
	
}
