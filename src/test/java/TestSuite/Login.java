package TestSuite;

import Pageobject.CommonPageModels;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.lang.reflect.Method;

public class Login extends CommonUtils {
    @BeforeClass
	public void setupReportTest() {
	    String currentClassName = getCurrentClassName();
	    test = extent.createTest(currentClassName);	        
	} 
    
	@BeforeMethod
	public void setupReportNode(Method method){
	    node = test.createNode(method.getName());
    }
	   
    @Test(enabled = true)
    public void logIn() throws Throwable {        
        emaillogin();
    }
    
    public static void emaillogin() throws Throwable {
        tapOnElement("login.emailLogin", "Continue with email button");
        Thread.sleep(2000);      
        type("login.inputEmail",credential("username"), "email field");
//        type("login.inputEmail", ReadExcel.email(), "email field");        
        type("login.inputPassword",credential("password"), "password field");
//        type("login.inputPassword", ReadExcel.password(), "password field");
       
        tapOnElement("login.logInButton", "login button");
        visibilityOfElement("home.calender", driver);
        node.pass("logged in successfully");
        try {
        	Thread.sleep(5000);
        	 driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
             driver.findElement(By.xpath("//*[@text='Add']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
       
    }
    
    Logout logout = new Logout(); 
    @Test(enabled = false)
    public void googleLogin() throws Throwable {
        System.out.println("login");
        createTest("Google Login");
        createNode("Google Login");
        tapOnElement("login.googleLogin", "[Continue with google]");
        String desiredEmailAddress = "setmoretesting1@gmail.com";
        Thread.sleep(2000);
        clickIt(CommonPageModels.googleLoginEmail(driver, desiredEmailAddress), "email : " + desiredEmailAddress);
        node.pass("logged in Successfully");
        Thread.sleep(8000);
        logout.logout(driver);
        Thread.sleep(3000);
    }

    @Test(enabled = false)
    public void facebookLogin() throws Throwable {
        createTest("Facebook Login");
        createNode("Facebook Login");
        tapOnElement("login.facebookLogin", "[Continue with Facebook]");
        visibilityOfElement("login.facebookNoneOfTheAboveButton", driver);
        try {
            tapOnElement("login.facebookNoneOfTheAboveButton", "none of the above button");
        } catch (Exception e) {
            System.out.println("None of the Above button is not there in the facebook home page");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
}








