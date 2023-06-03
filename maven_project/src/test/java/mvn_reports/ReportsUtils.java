package mvn_reports;


import java.io.File;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import mvn_driver.DriverScript;
@Test
public class ReportsUtils extends DriverScript {
	
	/********************************
	 * Method      : startextentreport
	 * purpose     : To start the report writing
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : resultfilename
	 * return type : ExtentReports
	 * 
	 ***************************/

	public ExtentReports startextentreports(String reoprtfilename) {
		String reportpath = null;
		File objresultfilepath = null;
		File objscreenshotpath = null;
		try {
			reportpath = System.getProperty("user.dir")+"\\target\\reports";
			
			objresultfilepath = new File(reportpath);
			if(!objresultfilepath.exists()) {
				objresultfilepath.mkdirs();
			}
			
			screenshotlocation = reportpath+"\\screenshot";
			
			objscreenshotpath = new File(screenshotlocation);
			if(!objscreenshotpath.exists()) {
				objscreenshotpath.mkdirs();
			}
			
			extent = new ExtentReports(reportpath +"\\"+reoprtfilename+".html", false);
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			//extent.addSystemInfo("Environment", appind.getpropdata("enviornment"));
			extent.addSystemInfo("username", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;
			
			}
			
		catch (Exception e) {
			System.out.println("Exception in startextentreport"+e);
			return null;
		}
		finally {

		}
	}
	
	/********************************
	 * Method      : endextentreport
	 * purpose     : To end report writing
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : Extenttest <test>
	 * return type : void
	 * 
	 ***************************/
	@Test
	public void endextentreport(ExtentTest test) {
		
		try {
			extent.endTest(test);
			extent.flush();
				
		}catch (Exception e) {
			System.out.println("Exception in EndExtentReport"+e);
		}finally {
			
		}
	}
	
	/********************************
	 * Method      : takescreenshot
	 * purpose     : To take screenshot
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : Webdriver <obrowser>
	 * return type : String
	 * 
	 ***************************/
	@Test
	public String takescreenshot(WebDriver obrowser) {
		String strscreenpath = null;
		File objsource = null;
		File objdestination = null;
		
		try {
			
			strscreenpath = System.getProperty("user.dir")+"\\target\\reports\\screenshot_"+appind.getdatetime("hhmmss")+".png";
			TakesScreenshot tk = (TakesScreenshot) obrowser;
			objsource = tk.getScreenshotAs(OutputType.FILE);
			objdestination = new File(strscreenpath);
			FileHandler.copy(objsource, objdestination);
			return strscreenpath;
			
		}catch (Exception e) {
			System.out.println("Exception in takescreenshot method"+e);
			return null;
		}finally {
			strscreenpath = null;
			objsource = null;
			objdestination = null;
		}
	}
	
	/********************************
	 * Method      : writeresult
	 * purpose     : To Write the result
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : Webdriver <obrowser>, String status, String expectedresult
	 * return type : void
	 * 
	 ***************************/
	@Test
	public void writeresult(WebDriver obrowser, String status, String resultdesc) {
		
		try {
			switch(status.toLowerCase()) {
			case "pass":
				test.log(LogStatus.PASS, resultdesc);
				break;
				
			case "fail":
				if(obrowser!=null) {
					test.log(LogStatus.FAIL, resultdesc +":"+ test.addScreenCapture(takescreenshot(obrowser)));
				}else {
					test.log(LogStatus.FAIL, resultdesc);
				}
				break;
			case "info":
				test.log(LogStatus.INFO, resultdesc);
				break;
				
			case "warning":
				test.log(LogStatus.WARNING, resultdesc);
				break;
				
			case "exception":
				if(obrowser!=null) {
					test.log(LogStatus.FATAL, resultdesc+ ":"+ test.addScreenCapture(takescreenshot(obrowser)));
				}else {
					test.log(LogStatus.FATAL, resultdesc);
				}
				break;
			case "screenshot":
				if(obrowser!=null) {
					test.log(LogStatus.PASS, resultdesc+ ":"+ test.addScreenCapture(takescreenshot(obrowser)));
				}else {
					test.log(LogStatus.PASS, resultdesc);
				}
				break;
				
				default:
					System.out.println("Invalid status"+status);
			}
			
				
			
			
		}catch (Exception e) {
			System.out.println("Exception in write result method"+e);
		}finally {
			
		}
	}
	
	
}
