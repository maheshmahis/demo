package mvn_driver;

import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import mvn_methods.AppDependentMethods;
import mvn_methods.AppIndependentMethods;
import mvn_methods.Datatable;
import mvn_reports.ReportsUtils;

public class DriverScript {
	public static ReportsUtils reports =null;
	public static String screenshotlocation = null;
	public static ExtentReports extent = null;
	public static AppIndependentMethods appind=null;
	public static ExtentTest test = null;
	public static Datatable datatab = null;
	public static AppDependentMethods appdep = null;
	
	@BeforeSuite
	public void loadclass() {
	 
	try {
		
		reports = new ReportsUtils();
		appind = new AppIndependentMethods();
		datatab = new Datatable();
		appdep = new AppDependentMethods();
		extent = reports.startextentreports("testsite");
		
		
		
	}
	catch (Exception e) {
		System.out.println(e);
	}
	finally {
		
	}

}
}
