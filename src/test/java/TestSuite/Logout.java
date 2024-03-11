package TestSuite;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.lang.reflect.Method;

public class Logout extends CommonUtils {
	 @BeforeClass
	    public void setupReportTest()
	    {
		 String currentClassName = getCurrentClassName();
	         test = extent.createTest(currentClassName);
	        
	    } 
	    @BeforeMethod
	    public void setupReportNode(Method method)
	    {
	    	node = test.createNode(method.getName());
	    }
	    
	 @Test(enabled = true) 
	  public void logOut() throws Throwable {
	        System.out.println("After suite");
	        System.out.println("logout");
	        
	        logout(driver);
}
	 public void logout(AndroidDriver driver) throws Throwable {
	        //Change - new POM method
	        visibilityOfElement("home.settings", driver);
	        tapOnElement("home.settings", "bottom nav bar settings");
	        scrollOnePageUp(driver);
	        tapOnElement("login.logOutButton", "Logout button");
	        visibilityOfElement("login.confirmlogOutButton", driver);
	        tapOnElement("login.confirmlogOutButton", "Logut - pop up OK button");
	        node.pass("logged out successfully");
	    }
	 
	 
}