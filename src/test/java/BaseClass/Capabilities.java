package BaseClass;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Capabilities extends CommonUtils {

    static String deviceName;
    static String platformVersion;
    static String platformName;
    static String udid;
    static String appPackage;
    static String appActivity;
    static String automationName;
   
    public static String userName = "toolsanywhere"; // Add username here
           
    public static String accessKey = "s5t8PltjS8NKvxu02qPYfSKkTSSdvPeiauFUDHPkmsfgDqXKzS"; // Add accessKey here
            

   

    public static void LaunchAppInAndroid() throws MalformedURLException, Throwable {
//       System.out.println("launch app");
//       DesiredCapabilities capabilities = new DesiredCapabilities();
//       capabilities.setCapability("appium:automationName", "UiAutomator2");
//       capabilities.setCapability("appium:platformName", "android");
////     capabilities.setCapability("appium:platformVersion", platformVersion);
//       capabilities.setCapability("appium:MobileCapabilityType.DEVICE_NAME", "ZTLRC6LBBUWC5T8T");
//       capabilities.setCapability("appium:appPackage", "com.adaptavant.setmore");
//       capabilities.setCapability("appium:appActivity", "com.anywhere.container.presentation.ui.MainActivity");
//       capabilities.setCapability("noReset", true);
//       capabilities.setCapability("appium:appWaitForLaunch", true);
//       capabilities.setCapability("autoAcceptAlerts", true);
//       capabilities.setCapability("autoGrantPermissions", true);
//       driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);
//       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//       driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 100);
        
       System.out.println("launch app");
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformName", "android");
        capabilities.setCapability("deviceName", "Galaxy S21 Ultra 5G");
        capabilities.setCapability("platformVersion", "13");
        capabilities.setCapability("appium:app", "lt://APP10160281841705043691687914");
        capabilities.setCapability("isRealMobile", true);
		capabilities.setCapability("console", true);
        capabilities.setCapability("network", true);
        capabilities.setCapability("headless", false);
        capabilities.setCapability("w3c", true);
        capabilities.setCapability("appProfiling", true);
//        capabilities.setCapability("autoGrantPermissions", false);
        capabilities.setCapability("autoAcceptAlerts", false);
        capabilities.setCapability("build", "Demo - Setmore");
        capabilities.setCapability("name", "Setmore");
        capabilities.setCapability("visual", true);
        capabilities.setCapability("devicelog", true);
        System.out.println("launch app");
		driver = new AndroidDriver(
	                new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
	                capabilities);

        System.out.println("driver+++++++++++");
//        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(700));
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
//        driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 500);
//        driver.findElement(By.xpath("//android.widget.TextView[@text='Create an account.']")).click();
    }

//    static {
//        try {
//            automationName = ReadProperties("automationName", AppPropertiesFile);
//            deviceName = ReadProperties("deviceName", LocatorPropertiesFile);
//            platformVersion = ReadProperties("platformVersion", AppPropertiesFile);
//            platformName = ReadProperties("platformName", AppPropertiesFile);
//            udid = ReadProperties("udid", AppPropertiesFile);
//            appPackage = ReadProperties("appPackage", AppPropertiesFile);
//            appActivity = ReadProperties("appActivity", AppPropertiesFile);
//
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    
//    }
    public Capabilities() throws Throwable {
    }
}


