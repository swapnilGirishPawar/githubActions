package utils;

import Pageobject.CommonPageModels;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class commonFunctions extends CommonUtils{
	public String event = "globalPlus.eventButton";
	public String appointment = "globalPlus.serviceButton";
	public String class_ = "globalPlus.classButton";
	public String label;
	public static String previousCustomer;
	
	  public String addNotes(String note) throws Throwable {
		  type("booking.notes", note, "Notes");
		 
		  return note;
	  }
	 
	  
	  public void createButton(String appointementType) throws Throwable {
		  tapOnElement("booking.createButton", "create button");
		  node.pass(appointementType+" created");
	  }
	  public void editAppointmentButton() throws Throwable {		
	  Thread.sleep(3000);
	  tapOnElement("booking.editEventButton", "edit button");
	  }
	  public void saveButton() throws Throwable {
		  tapOnElement("booking.saveButton", "save button");		 
	  }
	  public String addLabel() throws Throwable {
		  tapOnElement("booking.addLabel", "label option");
		    List<WebElement> CheckBoxList = driver.findElements(By.xpath("//android.widget.TextView[@text='Appointment labels']/following-sibling::android.view.View/child::android.view.View/child::android.view.View"));
			List<WebElement> labelList = driver.findElements(By.xpath("//android.widget.TextView[@text='Appointment labels']/following-sibling::android.view.View/child::android.view.View/child::android.widget.TextView"));
			Random rand = new Random();
			int randomIndex = 1 + rand.nextInt(CheckBoxList.size()-2);
			label=labelList.get(randomIndex).getText();
			System.out.println(label);
			CheckBoxList.get(randomIndex).click();
			return label;
	  }
	  
	  public void globalPlusOperation(String appointment) throws Throwable {
	    	tapOnElement("booking.globalPlus", "Global plus button");
	        Thread.sleep(2000);
	        tapOnElement(appointment, "Appointment button");	        
	  }
	  public static WebElement currentGuest;
	  public static String splitCurrentCustomerName;
	  public static String splitCustomerName;
	  
	  public static String addNewGuest() throws Throwable {
		  try {
  			  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
  			  WebElement removePreviousGuest = driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"close\"])[1]"));
  			   if (removePreviousGuest.isDisplayed()) {
  	     		 removePreviousGuest.click();
  	     	  }
  		   } catch (Exception e) {
  			 
  		   }
  		  
		  	tapOnElement("customers.addNewCustomer", "add new customer");
	        customerName = randomName();
	        type("customers.fullName", customerName, "Customer Name");
	        nextField(driver, "Create");
	        type("customers.phoneNumber", randomPhoneNumber(), "Customer Phone number");
	        nextField(driver, "Create");
	        type("customers.email", randomEmail(), "Customer email");
	        tapOnElement("customers.create", "create");
	        try {
		    	   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		    	   WebElement nextButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Next']"));
			        if (nextButton.isDisplayed()) {     
			        	try {
			  			  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
			  			  WebElement removePreviousGuest = driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"close\"])[1]"));
			  			  WebElement currentGuest = driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"close\"])[2]"));	       
			  			  WebElement currentGuestName = driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"close\"])[2]/preceding-sibling::android.widget.TextView[2]"));	
			  			 
			  			  String currentGuestNameText = currentGuestName.getText();
			  			String splitCurrentCustomerName = currentGuestNameText.substring (0,3);
			  			System.out.println(splitCurrentCustomerName);
			  			String splitCustomerName = customerName.substring (0,3);
			  			System.out.println(splitCustomerName);
			  			  if (currentGuest.isDisplayed()) {		
			  				System.out.println("1if");
			  					if (splitCurrentCustomerName== splitCustomerName) {
			  						System.out.println("equal");
				  					removePreviousGuest.click();
								}		
			  					else {
			  						System.out.println("unequal");
			  						currentGuest.click();
								
								}
							
			  					
			  	     	  }
			  		   } catch (Exception e) {
			  			 
			  		   }
			  		  
			        	nextButton.click();		
			        	previousCustomer = customerName;
					}
		       } catch (Exception e) { 
				// TODO: handle exception
		       }
	        return customerName;
	    }
	  public void recentlyCreatedAppointment(String appointmenTtitle) throws Throwable {
		  try {
			  Thread.sleep(3000);
			  clickIt(CommonPageModels.selectCreatedAppointment(driver, appointmenTtitle), "recently created appointment in the calendar page");		    
			  
		} catch (Exception e) {
			 node.log(Status.FAIL, "Unable to locate the element [ " + appointmenTtitle + " ]", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver), "Failed image").build());
        }   
		  
	  }
	  
	  public static void deleteProfile() throws Throwable{
		    tapOnElement("app.threeDotButton", "Three dots button");
			tapOnElement("profile.deleteButton", "Delete button");  
			tapOnElement("profile.yesDeleteButton", "Yes, Delete button");   
	    }
	  
	  public String newGuest() throws Throwable {   	  
		    tapOnElement("service.serviceEditGuest", "select guest");
		    return addNewGuest();
		    }
//	  public static void getPageDetails(String action, List<String> attributeNames, List<String> values) {
//	    	serviceList.clear();
//		    serviceListLogs.clear();
//	    	for (int i = 0; i < attributeNames.size(); i++) {
//	        String keyValue= action +" appointment "+attributeNames.get(i)+": "+values.get(i);
//	        serviceListLogs.add(keyValue);
//	        serviceList.add(values.(i));
//	        }
//	        printPageDetails(serviceListLogs, "Service Details");
//	    }
	  
}
