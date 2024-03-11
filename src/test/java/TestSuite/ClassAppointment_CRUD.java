package TestSuite;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.commonFunctions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

	public class ClassAppointment_CRUD extends commonFunctions {
	public static String appointmentName;
	public static String appointmentCost;
	public static String appointmentDateAndTime;
	public static String appointmentGuestName;
	public static String appointmentVideoLink;
	public static String appointmentNotes;
	public static String appointmentLabel;
	private List<String> attributeNames = new ArrayList<String>(Arrays.asList("Name","Cost","Date and Time", "Guest", "Video Link", "Notes", "Label"));
	
	String cost = "20";
	String editCost = "30";
	
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
    public void createClassAppointment() throws Throwable {
        createAppointment(); 
        fetchAppointmentOverViewDetails("Overview", serviceOverViewList, serviceOverViewListLogs);
        validateStringLists(serviceList, serviceOverViewList, attributeNames, test);              
	}
	
    @Test(enabled = true, priority = 2) 
    public void editClassAppointment() throws Throwable {
        editAppointment();          
        fetchAppointmentOverViewDetails("Edit overview page", serviceEditOverViewList, serviceEditOverViewListLogs);
        validateStringLists(serviceList, serviceEditOverViewList, attributeNames, test);
    }
    
    @Test(enabled = true, priority = 3) 
    public void deleteClassAppointment() throws Throwable {
        deleteAppointment(customerName);     
    }
      
    public void createAppointment() throws Throwable {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		     
        globalPlusOperation(class_);
        appointmentName = selectAppointment();         
        selectCurrentTime();        
        appointmentGuestName = addNewGuest();                                
        appointmentCost = appointmentCost(cost);      
        appointmentDateAndTime = getValue("service.serviceDateAndTime", "date and time");
        appointmentVideoLink = addVideoLink();
        appointmentNotes = addNotes(notes());           
        appointmentLabel = addLabel();          
        getAppointmentDetails("create");
        createButton("Appointment");        
    }   
   
    public void editAppointment() throws Throwable {   	  	
    	editAppointmentButton();        
        appointmentName = editClass();
        selectCurrentTime();
        appointmentCost = appointmentCost(editCost);
        appointmentDateAndTime = getValue("service.serviceDateAndTime", "date and time");
        appointmentVideoLink = getValue("booking.videoLink", "videoLink");    
        appointmentGuestName = newGuest();
        appointmentNotes = addNotes(notes());   
        appointmentLabel = addLabel();           
        getAppointmentDetails("edit");
        saveButton();        
    }
    
    public void fetchAppointmentOverViewDetails(String label, List<String> list, List<String> list1) throws Throwable {
    	Thread.sleep(4000);
    	recentlyCreatedAppointment(customerName);
    	getValue("service.serviceNameOverView", "" + label + " class name", list, list1);
        getValue("service.serviceCostOverView", "" + label + " class cost", list, list1);
//				getValue(Service.serviceDurationOverView(driver), ""+label+" service duration", list,list1);
        list.add(dateAndTimeClassAppointmentOverView());
        list1.add(label + " class date and time: " + dateAndTimeClassAppointmentOverView());
        System.out.println("Overview class date and time: " + dateAndTimeClassAppointmentOverView());
        node.info("Overview class date and time: " + dateAndTimeClassAppointmentOverView());
        getValue("service.serviceGuestOverView", "" + label + " class guest", list, list1);
        getValue("booking.videoLink", "" + label + " class video link", list, list1);
        getValue("service.serviceNotesOverView", "" + label + " class notes", list, list1);
        getValue("service.serviceLabelOverView", "" + label + " class label", list, list1);
        printPageDetails(list1, "Class Details");
    }
   
    public String selectAppointment() throws Throwable {   
	    String firstService=getValue("service.firstService", "First Servicee");
	    tapOnElement("service.firstService", "First Service");   
	    return firstService;
    }
    
    public String editClass() throws Throwable {   
    	tapOnElement("service.serviceName", "Service");
    	String differentService=getValue("service.specificServiceEdit", "Specific service"); 
        tapOnElement("service.specificServiceEdit", "Specific service"); 
        return differentService;
    }
    
    public String appointmentCost(String cost) throws Throwable {
	    type("service.serviceCost", cost, "cost");   
	    return cost;
    }
    
    public String addVideoLink() throws Throwable {   	  
    	tapOnElement("booking.addLocation", "Location");
    	implicitlyWait(6);
        String videoLink = getValue("booking.videoLink", "videoLink");  
        return videoLink;
    }
    
    public void getAppointmentDetails(String action) {
    	serviceList.clear();
    	serviceListLogs.clear();
    	serviceList=new ArrayList<String>(Arrays.asList(appointmentName, appointmentCost, appointmentDateAndTime, appointmentGuestName, appointmentVideoLink, appointmentNotes, appointmentLabel));
    	for (int i = 0; i < attributeNames.size(); i++) {
        String keyValue= action +" appointment "+attributeNames.get(i)+": "+serviceList.get(i);
        serviceListLogs.add(keyValue);
        }
        printPageDetails(serviceListLogs, "Class Details");
    }
   
   
}