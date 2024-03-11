package utils;

import Pageobject.CommonPageModels;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableMap;
import freemarker.core.ParseException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.fail;
public class CommonUtils{
    //  Variables :-
    public static AppiumDriverLocalService service;
    public static String reportFolderPath = "reports";
    public static AndroidDriver driver;
    public static ExtentSparkReporter spark;
    public static ExtentTest test;
    public static ExtentTest node;
    public static ExtentReports extent = new ExtentReports();
    public static String customerName;
    public static String inputTitle;
    public static String eventTitle;
    public  List<String> attributeNames = new ArrayList<String>();
    public List<String> overviewListLogs = new ArrayList<String>();
    public List<String> createListLogs = new ArrayList<String>();
    public List<String> editOverviewPageListLogs = new ArrayList<String>();
    public List<String> editPageListLogs = new ArrayList<String>();
    public List<String> createList = new ArrayList<String>();
    public List<String> overviewList = new ArrayList<String>();
    public List<String> editPageList = new ArrayList<String>();
    public List<String> editOverviewPageList = new ArrayList<String>();
    public List<String> addNewCustomerListLogs = new ArrayList<String>();
    public List<String> addNewCustomerList = new ArrayList<String>();
    public List<String> serviceListLogs = new ArrayList<String>();
    public List<String> serviceEditListLogs = new ArrayList<String>();
    public List<String> serviceList = new ArrayList<String>();
    public List<String> appointmentInformationList = new ArrayList<String>();
    public List<String> appointmentList = new ArrayList<String>();
    public List<String> serviceEditList = new ArrayList<String>();
    public List<String> serviceOverViewListLogs = new ArrayList<String>();
    public List<String> serviceEditOverViewListLogs = new ArrayList<String>();
    public List<String> serviceOverViewList = new ArrayList<String>();
    public List<String> serviceEditOverViewList = new ArrayList<String>();
    public static Faker faker = new Faker();
    public static Properties Prop;
    public static File FileLocation;
    public static String LocatorPropertiesFile3 = "./src/test/resources/Locators3.properties";
    public static String LocatorPropertiesFile = "./src/test/resources/Locators.properties";
    public static String AppPropertiesFile = "./src/test/resources/App.properties";


    // Appium Server snippets :-
    public static AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerRunning(4723);
        if (!flag) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .usingDriverExecutable(new File("/usr/local/bin/node"))
                    .usingPort(4723)
                    .withArgument(GeneralServerFlag.USE_PLUGINS, "element-wait")
                    .withLogFile(new File("test-output/AppiumLogs/" + "AppiumLog.txt"));
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }
        return service;
    }
    public static boolean validateStringLists(List<String> list1, List<String> list2, List<String> list3, ExtentTest test) throws Exception {
	      System.out.println(list1.size());
	      System.out.println(list2.size());
     	if (list1.size() != list2.size()) {
 	           System.out.println("not equal");
 	           node.fail("list size of both are not equal");
 	           fail("list size of both are not equal");
 	           return false;    			 
 	        }
     	
     	 boolean listsAreEqual = true;

         for (int i = 0; i < list1.size(); i++) {
             if (!list1.get(i).equals(list2.get(i))) {
                 listsAreEqual = false;
                 String errorMessage = list3.get(i)+" : not matched";
//                 String errorMessage = "Before creation: " + list1.get(i) + " After creation: " + list2.get(i);
                 node.log(Status.FAIL, errorMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
             }
             else if (list2.get(i).equals("not present")) {
            	 String errorMessage = list3.get(i)+" : not Present in the overview page";
            	 node.log(Status.FAIL, errorMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
             }	 
   			else {
				String passMessage = list3.get(i)+" : matched";
                node.pass(passMessage);
			}
         }
        
         list2.clear();
         return listsAreEqual;
     }
    public static boolean checkIfServerRunning(int portNumber) {
        boolean isServerRunning = false;
        ServerSocket serversocket;
        try {
            serversocket = new ServerSocket(portNumber);
            serversocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }
        return isServerRunning;
    }

    // readProps snippets:-
    public static String ReadProperties(String Property, String Location) throws Throwable {
        Prop = new Properties();
        File FileLocation = new File(Location);
        FileReader ReadFile = new FileReader(FileLocation);
        Prop.load(ReadFile);
        return Prop.getProperty(Property);
    }


    // Unknown snipets :-
    public Sheet input() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/sethuraman/eclipse-workspace/SetMore/excel/data.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        return workbook.getSheetAt(0);
    }

    public String inputEmail() throws IOException {
        Row row = input().getRow(1);
        Cell emailCell = row.getCell(0);
        return emailCell.toString();
    }

    public String inputPassword() throws IOException {
        Row row = input().getRow(1);
        Cell passwordCell = row.getCell(1);
        return passwordCell.toString();
    }
    public static void createTest(String FlowTitle) throws IOException {   	  
    test = extent.createTest(FlowTitle);
    }
    public static void createNode(String nodeTitle) throws IOException {   	  
    	node = test.createNode(nodeTitle);
        }
    
    
    // Common Functions:-=
  
    public static String getCurrentClassName() {
        // Get the current stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // The class name is the second element in the stack trace
        // (index 0 is Thread, index 1 is the method that called getStackTrace)
        String fullClassName = stackTrace[2].getClassName();

        // Extract only the class name without the package
        int lastDotIndex = fullClassName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fullClassName.substring(lastDotIndex + 1);
        } else {
            return fullClassName;
        }
    }


    // action functions :-
    public static WebElement wait(WebElement element, AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public static WebElement visibilityOfElement(WebElement element, AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static void visibilityOfElement(String element, AndroidDriver driver) throws Throwable {
        String Locator = ReadProperties(element, LocatorPropertiesFile);
        WebElement value = StringToElementConverter(Locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(value));
    }
    public static void clickIt(String element, String elementName) throws Throwable {
        WebElement value = StringToElementConverter(ReadProperties(element, LocatorPropertiesFile));
        try {
            wait(value, driver);
            value.click();
            print("Clicked on [" + elementName + "]");
        } catch (Exception e) {
            node.log(Status.FAIL, "Unable to locate the element [ " + elementName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        }
    }

    public static void clickIt(WebElement element, String elementName) {
        try {
            wait(element, driver);
            element.click();
            print("Clicked on [" + elementName + "]");
        } catch (Exception e) {
        	System.out.println("Unable to locate the element [ " + elementName + " ]");
            node.log(Status.FAIL, "Unable to locate the element [ " + elementName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        } 
    }
    
    public static WebElement mobileElement(String element) throws Throwable {
    	 String Locator = ReadProperties(element, LocatorPropertiesFile);
         WebElement value = StringToElementConverter(Locator);
        return value;
    }
    
    public static void tapOnElement(String element, String elementName) throws Throwable {
        String Locator = ReadProperties(element, LocatorPropertiesFile);
        WebElement value = StringToElementConverter(Locator);
        try {
            wait(value, driver);
            value.click();
            print("Clicked on [" + elementName + "]");
        } catch (Exception e) {
        	System.out.println("Unable to locate the element [ " + elementName + " ]");
            node.log(Status.FAIL, "Unable to locate the element [ " + elementName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
     
        }
    }
    
    public static void isDisplying(String element, String elementName, String type) throws Throwable {  
    	String Locator = ReadProperties(element, LocatorPropertiesFile);
        WebElement value = StringToElementConverter(Locator);
        System.out.println(value);
        try {
        	By search = By.xpath("//android.widget.TextView[@resource-id='com.adaptavant.setmore:id/first_and_last_name']/..");
       	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            if (type=="Delete") {
            	wait.until(ExpectedConditions.invisibilityOfElementLocated(search));        
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(search));        
			}  	    
            value.isDisplayed();
            System.out.println("[ " + elementName + " ] is displayed");
            if (type=="Delete") {
            	node.log(Status.FAIL, "[ " + elementName + " ] is still displaying", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());			     
			} else {
				node.log(Status.PASS,"[" + elementName + "] is displayed");				 
			}           
        } catch (Exception e) {
        	System.out.println("[ " + elementName + " ] is not displayed");
        	if (type=="Delete") {
        		node.log(Status.PASS,"[" + elementName + "] got deleted");
			} else {
				node.log(Status.FAIL, "[ " + elementName + " ] is not displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());        	     				
			}
            
        }
    }

    private static WebElement StringToElementConverter(String locator) throws Exception {
        String LocatorType = locator.split("\\$")[0];
        String LocatorValue = locator.split("\\$")[1];
//        System.out.println("locator type:- "+LocatorType+" locator Value:- "+LocatorValue);
        if (LocatorType.equalsIgnoreCase("xpath"))
            return driver.findElement(By.xpath(LocatorValue));
        else if (LocatorType.equalsIgnoreCase("id"))
            return driver.findElement(By.id(LocatorValue));
        else if (LocatorType.equalsIgnoreCase("accessibilityid"))
            return driver.findElement(MobileBy.accessibilityId(LocatorValue));
        else
            throw new Exception("Unknown locator type '" + LocatorType + "'");
    }

    public static void type(String element, String input, String elementName) throws Throwable {
        String Locator = ReadProperties(element, LocatorPropertiesFile);
        WebElement value = StringToElementConverter(Locator);
        try {
            visibilityOfElement(value, driver).clear();
            //			element.click();
            value.sendKeys(input);
            print("Typed [" + input + "] on the " + elementName);
        } catch (Exception e) {
            System.out.println("failed");
            node.log(Status.FAIL, "Unable to locate the element [ " + elementName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        }
    }
    public static String credential(String credentialType) throws Throwable {
        String credentialValue = ReadProperties(credentialType, LocatorPropertiesFile3);
        return credentialValue;
       
    }

    // overload - type
    public static void type(WebElement element, String input, String elementName) throws Throwable {
        try {
            visibilityOfElement(element, driver).clear();
            //			element.click();
            element.sendKeys(input);
            print("Typed [" + input + "] on the " + elementName);
        } catch (Exception e) {
            System.out.println("failed");
            node.log(Status.FAIL, "Unable to locate the element [ " + elementName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        }
    }

    public static void print(String action) {
        node.log(Status.INFO, action);
        System.out.println(action);
    }

    public static String getBase64(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static void validateStringLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            System.out.println("not equal");
            node.fail("list size of both are not equal");
            Assert.fail("list size of both are not equal");
            return;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                String listener;
                if (i == 0) {
                    listener = "Date";
                } else if (i == 1) {
                    listener = "Cost";
                } else {
                    listener = "VideoLink";
                }
                node.fail("Before creation " + listener + " " + list1.get(i) + " After creation " + listener + " " + list2.get(i), MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
                return;
            }
        }
        node.pass("After Creation: validated successfully");
    }

    public void implicitlyWait(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public static void nextField(AndroidDriver driver, String type) {
       if (type=="Create") {
    	   driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "done"));
       }
    	
    }

    public static void scrollOnePageUp(AndroidDriver driver) {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
    }

    public static void scrollOnePageDown(AndroidDriver driver) {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"));
    }

    public void scroll(String text) {
        WebElement element =
                driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains('" + text + "').instance(0))"));
        element.click();
    }

    public static void scroll(String s, AndroidDriver driver) {
        WebElement element =
                driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + s + "\"))"));
        element.isDisplayed();
    }


    // getter and setters :-

    public String getElementText(String element) throws Exception {
        return MobileElement(element).getText();
    }

    public WebElement MobileElement(String element) throws Exception {
        return StringToElementConverter(element);
    }

    public WebElement getMobileElement(String Locator, String locatorfile) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Thread.sleep(3000);
        WebElement element = getElement(Locator, locatorfile);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.isDisplayed();
        return element;
    }

    public WebElement getElement(String Property, String Location) throws Throwable {
        String LocatorType, LocatorValue;
        Properties Prop = new Properties();
        FileLocation = new File(Location);
        FileReader File = new FileReader(FileLocation);
        Prop.load(File);
        LocatorType = Prop.getProperty(Property).split(":")[0];
        LocatorValue = Prop.getProperty(Property).split(":")[1];
        if (LocatorType.equalsIgnoreCase("accessibility"))
            return driver.findElement(AppiumBy.accessibilityId(LocatorValue));
        else if (LocatorType.equalsIgnoreCase("xpath"))
            return driver.findElement(AppiumBy.xpath(LocatorValue));
        else if (LocatorType.equalsIgnoreCase("iOSClassChain"))
            return driver.findElement(AppiumBy.iOSClassChain(LocatorValue));
        else if (LocatorType.equalsIgnoreCase("id"))
            return driver.findElement(AppiumBy.id(LocatorValue));
        else
            throw new Exception("Unknown locator type '" + LocatorType + "'");

    }

    public void getValue(WebElement element, String fieldName, List<String> list, List<String> list1) {
        String value = "";
        try {
            value = element.getText();
            //	    node.info(fieldName + ": " + value);
            String a = fieldName + ": " + value;
            list.add(value);
            list1.add(a);
            System.out.println(fieldName + ": " + value);
        } catch (Exception e) {
            node.log(Status.FAIL, "Unable to locate the element [ " + fieldName + " ]");
        }
    }

    // overload - getValue
    public void getValue(String element, String fieldName, List<String> list, List<String> list1) throws Throwable {
        WebElement LocatorValue = StringToElementConverter(ReadProperties(element, LocatorPropertiesFile));
        String value = "";
        try {
            value = LocatorValue.getText();
            String a = fieldName + ": " + value;
            list.add(value);
            list1.add(a);
            System.out.println(fieldName + ": " + value);
        } catch (Exception e) {
        	System.out.println(fieldName+" not present");
        	list.add("not present");
            
        }
    }

    public static String getValue(String element, String fieldName) throws Throwable {
        WebElement LocatorValue = StringToElementConverter(ReadProperties(element, LocatorPropertiesFile));
        String value = "";
        try {
            value = wait(LocatorValue, driver).getText();
            System.out.println(value);
        } catch (Exception e) {
        	 System.out.println("failed to get the value of "+fieldName);
            node.log(Status.FAIL, "Unable to locate the element [ " + fieldName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        }
        return value;
    }

    public static String getValue(WebElement element, String fieldName) {
        String value = "";
        try {
            value = wait(element, driver).getText();
        } catch (Exception e) {
            node.log(Status.FAIL, "Unable to locate the element [ " + fieldName + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        }
        return value;
    }

    public static String getLocalDirectory() {
        return System.getProperty("user.dir");
    }

public boolean scrollup=false;
    // Common Date & time functions :-
    public int addDate() {
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        if (day == 31 || day == 30) {
//            day--;
            scrollup=true;
        } else {
            day++;
        }
        return day;
    }

    public String selectCurrentTime2() throws ParseException {
        String time = new SimpleDateFormat("hh:mm a").format(System.currentTimeMillis());
        System.out.println("Device time :" + time);
        return gettime(time);
    } 

    public static String selectCurrentTime() throws Exception {
        String time = new SimpleDateFormat("hh:mm a").format(System.currentTimeMillis());
        System.out.println("Device time :" + time);
        String currenttime = gettime(time);       
//        currenttime.toUpperCase();
        System.out.println("The trimmed time :" + currenttime);
        try {
            clickIt(driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + currenttime + "')]")), "slot");
        } catch (Exception e) {
            //				Byclick("selecting Slot", By.xpath(
            //						"//XCUIElementTypeStaticText[contains(@value,'am')][1] | //XCUIElementTypeStaticText[contains(@value,'pm')][1]"));
            System.out.println("not clicked on calender");
        }
        return currenttime;
    }
    public String endTimeSplit(String time) {
		String[] abc1 = time.split("→", time.length() - 1);
		time = abc1[1].trim();
		return time;
	}
    public String[] splitTime(String time) {
		String[] Time = new String[] { time.substring(0, time.indexOf('→')).trim(),
				time.substring(time.indexOf('→') + 1, time.length()).trim() };
		return Time;

	}
    public static String gettime2(String currenttime) throws ParseException {
        String lRes = "";
        try {
            System.out.println("Time of device : " + currenttime);
            String lTime = currenttime;
            int lHr = Integer.parseInt(lTime.substring(0, lTime.indexOf(':')));
            int lMin = Integer.parseInt(lTime.substring(lTime.indexOf(':') + 1, lTime.indexOf(' ')));
            String lAMPM = lTime.substring(lTime.indexOf(' ') + 1, lTime.length());
            // int lDiff = 30 - (lMin % 30);
            int lDiff = 60 - (lMin % 60);
            if (lDiff != 60) {
                lMin = lMin + lDiff;
                if (lMin >= 60) {
                    if (lHr == 12) {
                        lHr = 1;
                    } else {
                        lHr = lHr + 1;
                    }
                    lMin = lMin % 60;
                    if (lHr == 12 && lAMPM.equals("AM")) {
                        lAMPM = "PM";
                    }
                }
            }
            lRes = String.valueOf(lHr) + ":" + String.format("%02d", lMin) + " " + lAMPM;
            lRes = lRes.toLowerCase();
        } catch (Exception e) {
            System.out.println("exc :: +" + e.getMessage());
            e.printStackTrace();
        }
        return lRes;
    }

    public static String gettime(String currenttime) throws ParseException {
        String lRes = "";
        try {
            System.out.println("Time of device : " + currenttime);
            String lTime = currenttime;
            int lHr = Integer.parseInt(lTime.substring(0, lTime.indexOf(':')));
            int lMin = Integer.parseInt(lTime.substring(lTime.indexOf(':') + 1, lTime.indexOf(' ')));
            String lAMPM = lTime.substring(lTime.indexOf(' ') + 1, lTime.length());
            // int lDiff = 30 - (lMin % 30);
            int lDiff = 60 - (lMin % 60);
            if (lDiff != 60) {
                lMin = lMin + lDiff;
                if (lMin >= 60) {
                    if (lHr == 12) {
                        lHr = 1;
                    } else {
                        lHr = lHr + 1;
                    }
                    lMin = lMin % 60;
                    if (lHr == 12) {
                        if (lAMPM.equals("am")) {
                            lAMPM = "PM";
                        } else if (lAMPM.equals("pm")) {
                            lAMPM = "AM";
                        }
                    }
                }
            }
            lRes = String.valueOf(lHr) + ":" + String.format("%02d", lMin) + " " + lAMPM;
            lRes = lRes.toUpperCase();
        } catch (Exception e) {
            System.out.println("exc :: +" + e.getMessage());
            e.printStackTrace();
        }
        return lRes;
    }

    public static String time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId indianTimeZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime indianTime = localDateTime.atZone(indianTimeZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedIndianTime = indianTime.format(formatter);
        System.out.println("Current Indian Time (IST): " + formattedIndianTime);
        return formattedIndianTime;
    }
    public static String randomTamilName() {
   	 Random random = new Random();
   	List<String> tamilNames = Arrays.asList(
               "உங்களைப் பார்த்து நிறைய நாட்கள் ஆகிவிட்டது",
               "கூடுதல் விவாதங்களுக்கு கலந்து பார்க்கலாம்.",
               "நேரடியாக முன்னிட்டு விவாதிக்க போவோம்.",
               "வணக்கம்! எங்கும் உங்களுக்கு உதவ இருக்கிறோம்.");
       int index = random.nextInt(tamilNames.size());
       String randomTamilName = tamilNames.get(index);
  	 return randomTamilName;
   }
    public static String randomEmoji() {
      	 Random random = new Random();
      	List<String> emojiList = Arrays.asList(
      		  "\uD83D\uDE00", // 😀
              "\uD83D\uDE03", // 😃
              "\uD83D\uDE04", // 😄
              "\uD83D\uDE01", // 😁
              "\uD83D\uDE06", // 😆
              "\uD83D\uDE05", // 😅
              "\uD83D\uDE02", // 😂
              "\uD83E\uDD23"  );// 🤣
          int index = random.nextInt(emojiList.size());
          String randomEmoji = emojiList.get(index);
     	 return randomEmoji;
      }
    public static String randomTitle(){
        String title;
        return title = faker.dune().title();
    }
  
    public static String notes() {
        String note = "";
        long number = randomNumber(1, 4);
        if (number == 1) {
            note = "meeting with " + randomPhoneNumber();
        } else if (number == 2) {
            note = randomTamilName() +" "+randomEmoji();
        } else if (number == 3) {
            note = randomCharacter();
        } else {
        	String newlineNote = randomName() + "\n" +randomName();
            note = newlineNote;
        }
        return note;
    }

    protected static String extractStartTime(String timeRange) {
        String[] parts = timeRange.split("-");
        String startTime = parts[0].trim();     
        return startTime;
    }
    protected static String extractEndTime(String timeRange) {
        String[] parts = timeRange.split("-");
        String endTime = parts[1].trim();     
        return endTime;
    }
	public static String eventTitle() {
        String adjective = faker.color().name();
        String noun = faker.gameOfThrones().character();
        String eventType = faker.company().buzzword();
        return adjective + " " + eventType;
    }

    public String dateAndTimeOverView() throws Exception {
        String date = CommonPageModels.serviceDateOverView(driver).getText();
        String time = CommonPageModels.serviceTimeOverView(driver).getText();
        return date + time;
    }

    public String dateAndTimeClassAppointmentOverView() throws Exception {
        String date = CommonPageModels.classDateOverView(driver).getText();
        String time = CommonPageModels.classTimeOverView(driver).getText();
        return date + time;
    }
    // Extent report snippets :-
    public static void extentReports() {
        spark = new ExtentSparkReporter(reportFolderPath+"/Android_Setmore_"+getCurrentDateTime()+ ".html");
        //	spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Event Creation");
        spark.config().setReportName("Semore Android Report");
        extent.attachReporter(spark);
    }
    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom date and time format if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");

        // Format the current date and time using the formatter
        return currentDateTime.format(formatter);
    }  
    
    public static void failure() {
        test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failure").build());
    }


    //Faker Funcrtions :-
    public static String randomName() {
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String name = firstname + " " + lastname;
        name = name.replace("\'", "");
        return name;
    }

    public static String randomPhoneNumber() {
        return "9" + faker.number().numberBetween(100000000, 999999999);
    }
    public static long randomNumber(long from, long to) {
        return faker.number().numberBetween(from, to);
    }
    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomCompany() {
        return faker.company().industry();
    }

    public static String randomAddress() {
        return faker.address().fullAddress();
    }

    public static String randomCity() {
        return faker.address().city();
    }

    public static String randomState() {
        return faker.address().state();
    }

    public static String randomPostalCode() {
        return faker.address().zipCode();
    }
    public static String randomCharacter() {
        String randomLetter = faker.lorem().characters(1);
        return String.valueOf(randomLetter.charAt(0));
    }
    // Flow funtions :-
    public void appointmentLabel() {
        clickIt(CommonPageModels.appointmentLabel(driver, 2), "label");
    }

    public void editAppointmentLabel() {
        clickIt(CommonPageModels.appointmentLabel(driver, 3), "label");
    }

   
    public void deleteAppointment(String name) throws Throwable {
    	Thread.sleep(2000);
//    	createNode("Delete " + appointment);
        tapOnElement("booking.threeDotsEventButton", "Three dots in the overview page");
        tapOnElement("booking.deleteEventButton", "Delete button");
        tapOnElement("booking.confirmDeleteEventButton", "Delete -confirmation popup button");
        try {
            Thread.sleep(3000);
            CommonPageModels.selectCreatedAppointment(driver, name).isDisplayed();
            node.log(Status.FAIL, "The deleted event is still diplaying", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Faied image").build());

        } catch (Exception e) {
            node.log(Status.PASS, "The deleted event is not diplaying");
        }
    }

    

  

    

   

  

    public void printPageDetails(List<String> list1, String groupHeading) {
        String s = list1.toString();
        s = s.substring(1, s.length() - 1);
        String[] items = s.split(", ");
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(groupHeading + "\n");
        for (String item : items) {
            contentBuilder.append(item).append("\n");
        }
        String content = contentBuilder.toString();
        Markup m = MarkupHelper.createCodeBlock(content);
        node.info(m);
        list1.clear();
    }
    public static void portKiller(int port) throws IOException {        
        ProcessBuilder lsofProcessBuilder = new ProcessBuilder("lsof", "-i", ":" + port);
        Process lsofProcess = lsofProcessBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(lsofProcess.getInputStream()));
        String line;
        int pid = -1;
        Pattern pattern = Pattern.compile("node\\s+(\\d+)");
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                pid = Integer.parseInt(matcher.group(1));
                break;
            }
        }
        if (pid != -1) {
            System.out.println("Found PID: " + pid);
        } else {
            System.out.println("No process found running on port " + port);
        }
        PIDKiller(pid);
    }
    public static void PIDKiller(int pid){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("kill", String.valueOf(pid));
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Process with PID " + pid + " has been successfully killed.");
            } else {
                System.err.println("Failed to kill process with PID " + pid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void isDisplying(String element, String elementName) throws Throwable {
        String Locator = ReadProperties(element, LocatorPropertiesFile);
        WebElement value = StringToElementConverter(Locator);
        try {
            wait(value, driver);
            value.isDisplayed();
            System.out.println("[ " + elementName + " ] is displayed");
            node.log(Status.PASS,"[" + elementName + "] is displayed");
        } catch (Exception e) {
            System.out.println("[ " + elementName + " ] is not displayed");
            node.log(Status.FAIL, "[ " + elementName + " ] is not displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());

        }
    }
    public static void isDisplying(WebElement element, String elementName) throws Throwable {
        try {
            wait(element, driver);
            element.isDisplayed();
            System.out.println("[ " + elementName + " ] is displayed");
            node.log(Status.PASS,"[" + elementName + "] is displayed");
        } catch (Exception e) {
            System.out.println("[ " + elementName + " ] is not displayed");
            node.log(Status.FAIL, "[ " + elementName + " ] is not displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());

        }
    }
    public static void scrollup(String element, AndroidDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        HashMap<String, Object> scrollObject = new HashMap<String, Object>();

        scrollObject.put("direction", "up");
        scrollObject.put("name", element);
        js.executeScript("mobile:scroll", scrollObject);

    }

    public static void scrolldown(String element, AndroidDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        HashMap<String, Object> scrollObject = new HashMap<String, Object>();

        scrollObject.put("direction", "down");
        scrollObject.put("name", element);
        js.executeScript("mobile:scroll", scrollObject);

    }
    public static void scrollDown(AndroidDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");

        js.executeScript("mobile: scroll", scrollObject);
    }

    public static void scrollUp(AndroidDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "up");

        js.executeScript("mobile: scroll", scrollObject);
    }
    public void groupContent(List<String> list1, String groupHeading) {
        String s = list1.toString();
        s = s.substring(1, s.length() - 1);
        String[] items = s.split(", ");
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(groupHeading + "\n");
        for (String item : items) {
            contentBuilder.append(item).append("\n");
        }
        String content = contentBuilder.toString();
        Markup m = MarkupHelper.createCodeBlock(content);
        node.info(m);
    }
}



