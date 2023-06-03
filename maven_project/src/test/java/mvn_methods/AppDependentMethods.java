package mvn_methods;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import maven_project.Loginlogoutobjmap;
import mvn_driver.DriverScript;


public class AppDependentMethods extends DriverScript implements Loginlogoutobjmap {
	
	/********************************
	 * Method      : navigateurl
	 * purpose     : URL navigation
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : WebDriver <driver>, String <url>
	 * return type : boolean
	 * 
	 ***************************/
	@Test
	public boolean navigateurl(WebDriver driver, String url) {
		String title=null;
		try {
			driver.navigate().to(url);
			
			appind.waits(driver,login_btn, "click", "", 10);
			
			title=driver.getTitle();
			Assert.assertEquals(title, "Swag Labs", "Failed to load the URL");
			return true;
			
		}catch (Exception e) {
			reports.writeresult(driver, "exception","Exception in Navigate URL method"+e);
			return false;
			
		}finally {
			
		}
	}
	
	/********************************
	 * Method      : Loginmethod
	 * purpose     : To Login
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : WebDriver <driver>, String <username>, String <password>
	 * return type : boolean
	 * 
	 ***************************/
	@Test
	public boolean loginmethod(WebDriver driver, String uname, String pass) {
		String str=null;
		try {
			driver.findElement(username_field).sendKeys(uname);
			driver.findElement(password_field).sendKeys(pass);
			driver.findElement(login_btn).click();
			
			appind.waits(driver, backpack, "click", "", 10);
			
			str = driver.findElement(backpack).getText();
			
			Assert.assertEquals(str, "Sauce Labs Backpack","Login Uncessfull");
			reports.writeresult(driver, "pass", "Login Successful");
			return true;
		}
		
				catch (Exception e) {
			System.out.println("Exception in loginmethod"+e);
			return false;
		}finally {
			str=null;
		}
	}
	
	
	/********************************
	 * Method      : Logoutmethod
	 * purpose     : To Login
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : WebDriver <driver>
	 * return type : boolean
	 * 
	 ***************************/
	@Test
	public boolean logoutmethod(WebDriver driver) {
		
		try {
			driver.findElement(three_lines).click();
			appind.waits(driver, logout_pop, "click", "", 20);
			
			driver.findElement(logout_pop).click();
			
			appind.waits(driver, login_btn, "click", "", 10);
			
			Assert.assertTrue(driver.findElement(login_btn).isDisplayed(), "Logout failed");
			reports.writeresult(driver, "pass", "logout successful");
			return true;
			
		}catch (Exception e) {
			System.out.println("Exception in the logout method"+e);
			return false;
			
		}finally {
			
		}
		
	}
}
