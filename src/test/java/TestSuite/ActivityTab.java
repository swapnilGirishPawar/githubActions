package TestSuite;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.commonFunctions;

import java.lang.reflect.Method;
import java.time.Duration;

public class ActivityTab extends commonFunctions {

	
	@BeforeClass
	public void setupReportTest(){
    	String currentClassName = getCurrentClassName();
        test = extent.createTest(currentClassName);        
    } 
	    
	@BeforeMethod
	public void setupReportNode(Method method) {
		node = test.createNode(method.getName());    	
	}
	
	@Test(enabled = true, priority = 1) 
	public void activity() throws Throwable {
	   tapOnElement("home.settings","settings");      
	   tapOnElement("activity.activityButton", "Activity");
	   By search = By.id("spinner-lg");
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));      
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(search));  
	   node.log(Status.PASS,"Activity tab loaded successfully");
	   tapOnElement("premiumPage.close", event);
	  
	}
	
}
