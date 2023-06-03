package mvn_testScripts;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import mvn_driver.DriverScript;

public class UserModuleScripts extends DriverScript{
	
	
	@Test
	public void logincall() {
		
		Map<String, String> mapobj = null;
		WebDriver obrowser = null;
		
		try {
			
			test= extent.startTest("login");
		mapobj=datatab.readfromexcel("edata", "TC_01");
		
		obrowser =appind.launchbrowser(mapobj.get("browsername"));
		Assert.assertTrue(appdep.navigateurl(obrowser, mapobj.get("url")),"Failed to load the url");
		reports.writeresult(obrowser, "screenshot", "URL loaded successful");
	
		Assert.assertTrue(appdep.loginmethod(obrowser, mapobj.get("username"), mapobj.get("password")),"Login Failed");
		reports.writeresult(obrowser, "screenshot", "Login successful");
		
		Assert.assertTrue(appdep.logoutmethod(obrowser),"Logout unsucessful");
		reports.writeresult(obrowser, "screenshot", "Logout Successfull");
			
			
		}catch (Exception e) {
			System.out.println("Exception in logincall method"+e);
		}finally {
			reports.endextentreport(test);
		}
	}

}
