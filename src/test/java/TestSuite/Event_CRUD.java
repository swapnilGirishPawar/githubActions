package TestSuite;

import Pageobject.CommonPageModels;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.commonFunctions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Event_CRUD extends commonFunctions {
	public static String eventName;
	public static String eventStartTime;
	public static String eventEndTime;
	public static String eventDate;
	public static String eventVideoLink;
	public static String eventNotes;
	public static String eventGuest;
	public static String appointmentLabel;
	private List<String> attributeNames = new ArrayList<String>(Arrays.asList("Name","Date", "Start Time", "End Time", "Guest", "videoLink", "Notes"));	
   
	@BeforeClass
	public void setupReportTest() {
		String currentClassName = getCurrentClassName();
	    test = extent.createTest(currentClassName);	        
    } 
	 
	@BeforeMethod
	public void setupReportNode(Method method) {
	    node = test.createNode(method.getName());
	}
	
	@Test(enabled = true, priority = 1) 
    public void createEventAppointment() throws Throwable {       
        createEvent();
        recentlyCreatedAppointment(eventTitle);
        fetchEventDetails("Overview", overviewList, overviewListLogs);
        validateStringLists(appointmentInformationList, overviewList, attributeNames, test);             
	}  
	
	@Test(enabled = true, priority = 2)
    public void editEventAppointment() throws Throwable {         
        editEvent();
        recentlyCreatedAppointment(eventTitle);      
        fetchEventDetails("Edit overview", editOverviewPageList, editOverviewPageListLogs);
        validateStringLists(appointmentInformationList, editOverviewPageList, attributeNames, test);       
	}
	
	@Test(enabled = true, priority = 3)
    public void deleteEventAppointment() throws Throwable {         
        deleteAppointment(eventTitle);       
    }
       
    public void createEvent() throws Throwable {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);       
        globalPlusOperation(event);
        eventName = addTittle(); 
        eventGuest = newGuest();
        eventNotes = addNotes(notes());
        eventDate = getValue("booking.date", "booking date");
        eventStartTime = getValue("booking.startTime", "start time");
        eventEndTime = getValue("booking.endTime", "end time");
        eventVideoLink= addVideoLink();
        getEventDetails("create");
        createButton("Event");
    }

    public void editEvent() throws Throwable {              
        editAppointmentButton();
        eventName = addTittle();
        editDate();
        eventGuest = newGuest();
        eventNotes = addNotes(notes());
        eventDate = getValue("booking.date", "booking date");
        eventStartTime = getValue("booking.startTime", "start time");
        eventEndTime = getValue("booking.endTime", "end time");
        eventVideoLink= getValue("booking.videoLink", "videoLink");  
        getEventDetails("edit");
        saveButton();      
    }
 
    public void fetchEventDetails(String label, List<String> list, List<String> list1) throws Throwable {
    	getValue("booking.overviewTitle", "" + label + " event page title", list, list1);
        getValue("booking.overviewDate", "" + label + " event page date", list, list1);
        list.add(startTime());
        list1.add(label+" event page start time " + startTime());
        System.out.println(label+" event page start time " + startTime());
        node.info(label+" event page start time " + startTime());
        list.add(endTime());
        list1.add(label+" event page end time " + endTime());
        System.out.println(label+" event page end time " + endTime());
        node.info(label+" event page end time " + endTime());        
        getValue("service.serviceGuestOverView", "" + label + " service guest", list, list1);
        getValue("booking.videoLink", ""+label+" event page video link",list,list1);
        getValue("booking.overviewNotes", "" + label + " event page notes", list, list1);
        printPageDetails(list1, "Event Details");
    }
    
    public static String  addTittle() throws Throwable {
    	eventTitle = "Meeting " + eventTitle() + " " + time();
    	type("booking.addTitle", eventTitle, "Title field");
    	return eventTitle;
    }
    
    public String addGuest() throws Throwable {   	  
        tapOnElement("service.serviceEditGuest", "select guest");
        return addNewGuest();
    }
    
    public void getEventDetails(String action) {
    	appointmentInformationList.clear();
    	appointmentList.clear();
    	appointmentInformationList=new ArrayList<String>(Arrays.asList(eventName, eventDate, eventStartTime, eventEndTime, eventGuest, eventVideoLink, eventNotes));
    	for (int i = 0; i < attributeNames.size(); i++) {
	        String keyValue= action +" appointment "+attributeNames.get(i)+": "+appointmentInformationList.get(i);
	        appointmentList.add(keyValue);
        }
        printPageDetails(appointmentList, "Service Details");
    }
    
    public void editDate() throws Throwable {
    	tapOnElement("booking.date", "date");
        clickIt(CommonPageModels.editDate(driver, addDate()), "date+1");
        tapOnElement("booking.confirmEditEventDateButton", "date -confirm button");
    }
    
    public String addVideoLink() throws Throwable {   	  
    	tapOnElement("booking.addLocation", "Location");
        WebElement teleport = mobileElement("booking.addTeleport");
    	if (teleport.isDisplayed()) {
        	tapOnElement("booking.addTeleport", "Teleport");           
		}	
        String videoLink = getValue("booking.videoLink", "videoLink");  
        return videoLink;
    }
    
    public String startTime() {
    	String time=driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Clock Icon\"]/following-sibling::android.widget.TextView[2]")).getText();
    	return extractStartTime(time);
    }
    
    public String endTime() {
        String time=driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Clock Icon\"]/following-sibling::android.widget.TextView[2]")).getText();
        return extractEndTime(time);
    }
    
}
