package TestSuite;


import Pageobject.CommonPageModels;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.lang.reflect.Method;

import static org.testng.Assert.fail;


public class EasyShare extends CommonUtils {

    static String[] newService = new String[5];
    static String[] serviceInfo = new String[5];
    static String[] editService = new String[5];

    static String[] newClass = new String[5];
    static String[] classInfo = new String[5];
    static String[] editClass = new String[5];

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

    @Test(enabled = true, priority = 1) 
    public void create_Service() throws Throwable {       
        createService();
        Thread.sleep(2000);
        System.out.println("here Passed !");
        getDetails(newService[0], "Service");
        checkInCalendar("Create", checkInCalendar(newService[0], "Service"), "Service");
        validateDetails(newService, serviceInfo);
    }
    
    @Test(enabled = true, priority = 2) 
    public void edit_Service() throws Throwable {        
        editService();
        getDetails(editService[0], "Service");
        validateDetails(editService, serviceInfo);
        checkInCalendar("Edit", checkInCalendar(editService[0], "Service"), "Service");
    }  
    
    @Test(enabled = true, priority = 3) 
    public void delete_Service() throws Throwable {          
        deleteService();
        checkInCalendar("Delete", checkInCalendar(editService[0], "Service"), "Service");

    }

    @Test(enabled = false)
    public void classCRUD() throws Throwable {
        createTest("EasyShare Class CRUD");
        createClass();
        getDetails(newClass[0], "Class");
        checkInCalendar("Create", checkInCalendar(newClass[0], "Class"), "Class");
        validateClassDetails(newClass, classInfo);
        editClass();
        getDetails(editClass[0], "Class");
        checkInCalendar("Edit", checkInCalendar(editClass[0], "Class"), "Class");
        validateClassDetails(editClass, classInfo);
        deleteClass();
        checkInCalendar("Delete", checkInCalendar(editClass[0], "Class"), "Class");

    }

    public void createClass() throws Throwable {
        createNode("Class Create");
        newClass = serviceInputGenerator("Class");
        tapOnElement("home.services", "[Services]");

        visibilityOfElement("easySharePage.easySharePlusButton", driver);
        tapOnElement("easySharePage.easySharePlusButton", "[Plus Button]");
        visibilityOfElement("easySharePage.easyShareClass", driver);
        tapOnElement("easySharePage.easyShareClass", "[Class]");
        visibilityOfElement("easySharePage.title", driver);
        type("easySharePage.title", newClass[0], "Title");
        type("easySharePage.description", newClass[1], "Description");
        ;
        type("easySharePage.duration", newClass[2], "Duration");
        type("easySharePage.classSeat", newClass[3], "Seats");
        type("easySharePage.cost", newClass[4], "Cost");
        visibilityOfElement("easySharePage.createServiceButton", driver);
        tapOnElement("easySharePage.createServiceButton", "[Confirm]");
        visibilityOfElement("home.services", driver);
        node.log(Status.INFO, codeBlock("Created Class infromation: " + "\n" + "Class Name: " + newClass[0] + "\n" + "Class Description: " +
                newClass[1] + "\n" + "Class Duration: " + newClass[2] + " minutes" + "\n" + "Class seats: " + newClass[3] + " " + "\n" + "Class Cost: " + newClass[4] + " rupees"));


    }

    public void getDetails(String name, String type) throws Throwable {
        gotoEdit(name);
        if (type.equalsIgnoreCase("Class")) {

            visibilityOfElement("easySharePage.title", driver);
            classInfo[0] = getValue("easySharePage.title", " Title");
            classInfo[1] = getValue("easySharePage.description", " Description");
            classInfo[2] = getValue("easySharePage.duration", " Title");
            classInfo[3] = getValue("easySharePage.classSeat", " seat");
            classInfo[4] = getValue("easySharePage.cost", " cost");
            tapOnElement("easySharePage.backButton", "[Back button]");
            visibilityOfElement("home.services", driver);

            node.log(Status.INFO, codeBlock("Class infromation from the Summary page: " + "\n" + "Class Name: " + classInfo[0] + "\n" + "Class Description: " +
                    classInfo[1] + "\n" + "Class Duration: " + classInfo[2] + " minutes" + "\n" + "Class seats: " + classInfo[3] + " " + "\n" + "Class Cost: " + classInfo[4] + " rupees"));
        } else {
            visibilityOfElement("easySharePage.title", driver);
            serviceInfo[0] = getValue("easySharePage.title", "Service Title");
            serviceInfo[1] = getValue("easySharePage.description", "Service Description");
            serviceInfo[2] = getValue("easySharePage.duration", "Service duration");
            serviceInfo[3] = getValue("easySharePage.serviceBuffer", "Service buffer");
            serviceInfo[4] = getValue("easySharePage.cost", "Service cost");
            tapOnElement("easySharePage.backButton", "[Back button]");
            visibilityOfElement("home.services", driver);
            node.log(Status.INFO, codeBlock("Service infromation from the Summary page: " + "\n" + "Service Name: " + serviceInfo[0] + "\n" + "Service Description: " +
                    serviceInfo[1] + "\n" + "Service Duration: " + serviceInfo[2] + " minutes" + "\n" + "Service Buffer: " + serviceInfo[3] + " minutes" + "\n" + "Service Cost: " + serviceInfo[4] + " rupees"));

        }

    }

    public void checkInCalendar(String operation, boolean value, String type) {
        if (!operation.equalsIgnoreCase("Delete") && value) {
            node.log(Status.PASS, "The " + type + " is present in list");
        } else if (operation.equalsIgnoreCase("Delete") && !value) {
            node.log(Status.PASS, "The  " + type + " is not present in list.");
        } else if (!operation.equalsIgnoreCase("Delete") && !value) {
            node.log(Status.FAIL, "The  " + type + " is not present in list.");
        }
    }

    public void editClass() throws Throwable {
        createNode("Class Edit");
        editClass = serviceInputGenerator("Class");
        tapOnElement("home.services", "[Services]");
        gotoEdit(newClass[0]);
        visibilityOfElement("easySharePage.title", driver);
        clearAllField("Class");
        type("easySharePage.title", editClass[0], "Title");
        type("easySharePage.description", editClass[1], "Description");
        type("easySharePage.duration", editClass[2], "Duration");
        type("easySharePage.classSeat", editClass[3], "Seat");
        type("easySharePage.cost", editClass[4], "Cost");
        visibilityOfElement("easySharePage.updateButton", driver);
        tapOnElement("easySharePage.updateButton", "[Confirm]");
        visibilityOfElement("home.services", driver);
        node.log(Status.INFO, codeBlock("Edited Class infromation: " + "\n" + "Class Name: " + editClass[0] + "\n" + "Class Description: " +
                editClass[1] + "\n" + "Class Duration: " + editClass[2] + " minutes" + "\n" + "Class Seats: " + editClass[3] + " " + "\n" + "Class Cost: " + editClass[4] + " rupees"));

    }

    public void createService() throws Throwable {
        newService = serviceInputGenerator("Service");
        tapOnElement("home.services", "[Services]");
        visibilityOfElement("easySharePage.easySharePlusButton", driver);
        tapOnElement("easySharePage.easySharePlusButton", "[Plus button]");
        visibilityOfElement("easySharePage.easyShareService", driver);
        tapOnElement("easySharePage.easyShareService", "[Service button]");
        visibilityOfElement("easySharePage.title", driver);
        type("easySharePage.title", newService[0], "Title");
        type("easySharePage.description", newService[1], "Description");
        ;
        type("easySharePage.duration", newService[2], "Duration");
        type("easySharePage.serviceBuffer", newService[3], "Buffer");
        type("easySharePage.cost", newService[4], "Cost");
        visibilityOfElement("easySharePage.createServiceButton", driver);
        tapOnElement("easySharePage.createServiceButton", "[Confirm]");
        try {
        	visibilityOfElement("easySharePage.createServiceButton", driver);
        	System.out.println("Unable to select Team members");
			node.log(Status.FAIL, "Unable to select Team members", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
			fail();
        } catch (Exception e) {
			System.out.println("Selected team member");
		}
        visibilityOfElement("home.services", driver);			
        node.log(Status.INFO, codeBlock("Created Service infromation: " + "\n" + "Service Name: " + newService[0] + "\n" + "Service Description: " +
        		newService[1] + "\n" + "Service Duration: " + newService[2] + " minutes" + "\n" + "Service Buffer: " + newService[3] + " minutes" + "\n" + "Service Cost: " + newService[4] + " rupees"));


    }

    public boolean checkInCalendar(String str, String type) throws Throwable {
        visibilityOfElement("home.calender", driver);
        //Change - new POM method
        tapOnElement("home.calender", "[Calendar]");
        tapOnElement("easySharePage.fabButton", "[Fab button]");

        if (type.equalsIgnoreCase("Service")) {
            tapOnElement("easySharePage.fabServicesTab", "[Services]");
            boolean value = isPresent(str);
            tapOnElement("easySharePage.servicePageBack", "[Back]");
            visibilityOfElement("home.calender", driver);
            return value;
        } else {

            tapOnElement("easySharePage.fabClassesTab", "[Class]");
            boolean value = isPresent(str);
            tapOnElement("easySharePage.servicePageBack", "[Back]");
            visibilityOfElement("home.calender", driver);
            return value;
        }


    }

    public void editService() throws Throwable {
        editService = serviceInputGenerator("Service");
        tapOnElement("home.services", "[Clicked on Services]");
        gotoEdit(newService[0]);
        visibilityOfElement("easySharePage.title", driver);
        clearAllField("Service");
        type("easySharePage.title", editService[0], "Service title field");
        type("easySharePage.description", editService[1], "Service description field");
        type("easySharePage.duration", editService[2], "Service duration field");
        type("easySharePage.serviceBuffer", editService[3], "Service buffer field");
        type("easySharePage.cost", editService[4], "Service cost field");
        visibilityOfElement("easySharePage.updateButton", driver);
        tapOnElement("easySharePage.updateButton", "[Confirm button selected]");
        visibilityOfElement("home.services", driver);
        node.log(Status.INFO, codeBlock("Edited Service infromation: " + "\n" + "Service Name: " + editService[0] + "\n" + "Service Description: " +
                editService[1] + "\n" + "Service Duration: " + editService[2] + " minutes" + "\n" + "Service Buffer: " + editService[3] + " minutes" + "\n" + "Service Cost: " + editService[4] + " rupees"));


    }

    public void deleteService() throws Throwable {
        tapOnElement("home.services", "[Clicked on Services]");
        visibilityOfElement("easySharePage.threeDotMenu", driver);
        tapOnElement("easySharePage.threeDotMenu", "[Three dot menu]");
        tapOnElement("easySharePage.delete", "[delete option]");
        tapOnElement("easySharePage.confirmDelete", "[delete confirmation pop-up]");
       Thread.sleep(3000);
        boolean servicePresent = isPresent(editService[0]);
        if (servicePresent) {
        	 System.out.println("Service is still showing");
             node.log(Status.FAIL, "The service" + editService[0] + " is still showing in the list", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
       
        } else {
        	 System.out.println("The service is deleted!!");
             node.log(Status.PASS, "The service" + editService[0] + " is deleted");

             }
        visibilityOfElement("home.calender", driver);
        tapOnElement("home.calender", "[Calendar]");
    }

    public void deleteClass() throws Throwable {
        createNode("Delete Class");
        tapOnElement("home.services", "[Services]");
        visibilityOfElement("easySharePage.threeDotForClass", driver);
        tapOnElement("easySharePage.threeDotForClass", "[Three dot menu]");
        tapOnElement("easySharePage.delete", "[delete option]");
        tapOnElement("easySharePage.confirmDelete", "[delete confirmation pop-up]");
       Thread.sleep(4000);
        boolean classPresent = isPresent(editClass[0]);
        if (classPresent) {
        	System.out.println("Class is still showing");
            node.log(Status.FAIL, "The " + editClass[0] + " is still showing in the list", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
      
            
        } else {
        	System.out.println("The Class is deleted!!");
            node.log(Status.PASS, "The " + editClass[0] + " is deleted");
              }
        visibilityOfElement("home.calender", driver);
        tapOnElement("home.calender", "[Calendar]");
    }

    public boolean isPresent(String str) {

        boolean visibilityOfService = false;
        try {
        	implicitlyWait(5);
            WebElement elementID = driver.findElement(By.xpath("//android.widget.TextView[@text='" + str + "']"));
            if (elementID.isDisplayed()) {
                visibilityOfService = true;
            }
        } catch (Exception ignored) {

        }
        return visibilityOfService;
    }

    public void gotoEdit(String str) {
        WebElement element =
                driver.findElement(By.xpath("//android.widget.TextView[@text='" + str + "']"));
        element.click();
    }

    public Markup codeBlock(String textblock) {
        return MarkupHelper.createCodeBlock(textblock);
    }

    public String[] serviceInputGenerator(String value) {
        Faker fake = new Faker();
        String[] informations = new String[5];
        informations[0] = fake.company().industry();
        informations[2] = randomDigitGenerator();
        informations[3] = randomDigitGenerator();
        informations[4] = randomDigitGenerator();
        if (value.equalsIgnoreCase("Service")) {
            informations[1] = "The name of the Service is: " + informations[0] + " and it's a " + informations[2] + " minutes service";
        } else {
            informations[1] = "The name of the Class is: " + informations[0] + " and it's a " + informations[2] + " minutes Class";
        }
        return informations;

    }

    public static String randomDigitGenerator() {
        int number = faker.number().numberBetween(10, 49);
        return String.valueOf(number);
    }

    public void clearAllField(String type) {
        CommonPageModels.title(driver).clear();
        CommonPageModels.description(driver).clear();
        CommonPageModels.duration(driver).clear();
        CommonPageModels.cost(driver).clear();
        if (type.equalsIgnoreCase("Service")) {
            CommonPageModels.serviceBuffer(driver).clear();
        } else {
            CommonPageModels.classSeat(driver).clear();
        }

    }

    public void validateDetails(String[] serviceDetails1, String[] serviceDetails2) {
        if (serviceDetails1[0].equalsIgnoreCase(serviceDetails2[0])) {
            node.log(Status.PASS, "The Service title is matching");
        } else {
            node.log(Status.FAIL, "Service title is not matching");
        }
        if (serviceDetails1[1].equalsIgnoreCase(serviceDetails2[1])) {
            node.log(Status.PASS, "The Service description is matching");
        } else {
            node.log(Status.FAIL, "Service description is not matching");
        }
        if (serviceDetails1[2].equalsIgnoreCase(serviceDetails2[2])) {
            node.log(Status.PASS, "The Service duration is matching");
        } else {
            node.log(Status.FAIL, "Service duration is not matching");
        }
        if (serviceDetails1[3].equalsIgnoreCase(serviceDetails2[3])) {
            node.log(Status.PASS, "The Service buffertime is matching");
        } else {
            node.log(Status.FAIL, "Service buffertime is not matching");

        }
        if (serviceDetails1[4].equalsIgnoreCase(serviceDetails2[4])) {
            node.log(Status.PASS, "The Service cost is matching");
        } else {
            node.log(Status.FAIL, "Service cost is not matching");
        }

    }

    public void validateClassDetails(String[] classDetails1, String[] classDetails2) {
        if (classDetails1[0].equalsIgnoreCase(classDetails2[0])) {
            node.log(Status.PASS, "The Class title is matching");
        } else {
            node.log(Status.FAIL, "Service title is not matching");
        }
        if (classDetails1[1].equalsIgnoreCase(classDetails2[1])) {
            node.log(Status.PASS, "The Class description is matching");
        } else {
            node.log(Status.FAIL, "Service description is not matching");
        }
        if (classDetails1[2].equalsIgnoreCase(classDetails2[2])) {
            node.log(Status.PASS, "The Class duration is matching");
        } else {
            node.log(Status.FAIL, "Service duration is not matching");
        }
        if (classDetails1[3].equalsIgnoreCase(classDetails2[3])) {
            node.log(Status.PASS, "The Class seat count is matching");
        } else {
            node.log(Status.FAIL, "Service buffertime is not matching");

        }
        if (classDetails1[4].equalsIgnoreCase(classDetails2[4])) {
            node.log(Status.PASS, "The Class cost is matching");
        } else {
            node.log(Status.FAIL, "Class cost is not matching");
        }

    }

}
