package BaseClass;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.SheetsQuickstart;

import static BaseClass.Capabilities.LaunchAppInAndroid;

public class Hooks extends SheetsQuickstart {
    @BeforeSuite
    public void LaunchApp() throws Throwable {
//        service = startServer();
    	 triggersheet();
//         triggersheet2();
         
        LaunchAppInAndroid();
        System.out.println("App launched");
        extentReports();
       
    } 
   
    @AfterSuite()
    public void report() throws Throwable {
        System.out.println("Flush report");      
        extent.flush();  
        driver.quit();
//        fileOut.close();
//        portKiller(8888);
//        portKiller(4723);
//        service.stop();
    }

    @AfterMethod()
    public void status(ITestResult result) {
        System.out.println("Status of execution is:" + result.getStatus());
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("Test case execution status is SUCCESS");
            } else if (result.getStatus() == ITestResult.FAILURE) {

                driver.closeApp();
                driver.launchApp();

                System.out.println("Test case execution status is FAILURE");
            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("Test case execution status is SKIP");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
