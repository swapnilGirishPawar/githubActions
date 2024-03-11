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

public class Appointment_CRUD extends commonFunctions {
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
	public void setupReportTest() {
		String currentClassName = getCurrentClassName();
        test = extent.createTest(currentClassName);       
    } 
	
	@BeforeMethod
	public void setupReportNode(Method method) {	    
		node = test.createNode(method.getName());
    }
	
	@Test(enabled = true, priority = 1) 
	public void create_appointment() throws Throwable {
		createAppointment(); 
		fetchAppointmentOverViewDetails("Overview", serviceOverViewList, serviceOverViewListLogs);
		validateStringLists(serviceList, serviceOverViewList, attributeNames, test);             
	}
	 
	@Test(enabled = true, priority = 2) 
	public void edit_appointment() throws Throwable {
	    editAppointment();   
	    fetchAppointmentOverViewDetails("Edit overview page", serviceEditOverViewList, serviceEditOverViewListLogs);
	    validateStringLists(serviceList, serviceEditOverViewList, attributeNames, test);
	}
	 
	@Test(enabled = true, priority = 3) 
    public void delete_appointment() throws Throwable {
		deleteAppointment(customerName);     
	}
	
    public void createAppointment() throws Throwable {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		     
        globalPlusOperation(appointment);
        appointmentName = selectService();         
        selectCurrentTime();        
        appointmentGuestName = addNewGuest();                                
        appointmentCost = serviceCost(cost);      
        appointmentDateAndTime = getValue("service.serviceDateAndTime", "date and time");
        appointmentVideoLink = getValue("booking.videoLink", "videoLink");    
        appointmentNotes = addNotes(notes());           
        appointmentLabel = addLabel();          
        getAppointmentDetails("create");
        createButton("Appointment");        
    }   
   
    public void editAppointment() throws Throwable {   	  	
    	editAppointmentButton();        
        appointmentName = editService();
        selectCurrentTime();
        appointmentCost = serviceCost(editCost);
        appointmentDateAndTime = getValue("service.serviceDateAndTime", "date and time");
        appointmentVideoLink = getValue("booking.videoLink", "videoLink");    
        appointmentGuestName = newGuest();
        appointmentNotes = addNotes(notes());   
        appointmentLabel = addLabel();           
        getAppointmentDetails("edit");
        saveButton();        
    }
    
    public void fetchAppointmentOverViewDetails(String label, List<String> list, List<String> list1) throws Throwable {
    	Thread.sleep(3000);
    	recentlyCreatedAppointment(customerName);
    	getValue("service.serviceNameOverView", "" + label + " service name", list, list1);
        getValue("service.serviceCostOverView", "" + label + " service cost", list, list1);
//				getValue(Service.serviceDurationOverView(driver), ""+label+" service duration", list,list1);
        list.add(dateAndTimeOverView());
        list1.add(label + " service date and time: " + dateAndTimeOverView());
        System.out.println("Overview service date and time: " + dateAndTimeOverView());
        node.info("Overview service date and time: " + dateAndTimeOverView());
        getValue("service.serviceGuestOverView", "" + label + " service guest", list, list1);
        getValue("booking.videoLink", "" + label + " service video link", list, list1);
        getValue("service.serviceNotesOverView", "" + label + " service notes", list, list1);
        getValue("service.serviceLabelOverView", "" + label + " service label", list, list1);
        printPageDetails(list1, "Service Details");
    }
   
    public String selectService() throws Throwable {   
	    String firstService=getValue("service.firstService", "First Servicee");
	    tapOnElement("service.firstService", "First Service");   
	    return firstService;
    }
    
    public String editService() throws Throwable {   
    	tapOnElement("service.serviceName", "Service");
    	String differentService=getValue("service.specificServiceEdit", "Specific service"); 
        tapOnElement("service.specificServiceEdit", "Specific service"); 
        return differentService;
    }
    
    public String serviceCost(String cost) throws Throwable {
	    type("service.serviceCost", cost, "cost");   
	    return cost;
    }
   
    public void getAppointmentDetails(String action) {
    	serviceList.clear();
    	serviceListLogs.clear();
    	serviceList=new ArrayList<String>(Arrays.asList(appointmentName, appointmentCost, appointmentDateAndTime, appointmentGuestName, appointmentVideoLink, appointmentNotes, appointmentLabel));
    	for (int i = 0; i < attributeNames.size(); i++) {
	        String keyValue= action +" appointment "+attributeNames.get(i)+": "+serviceList.get(i);
	        serviceListLogs.add(keyValue);
        }
        printPageDetails(serviceListLogs, "Service Details");
    }
   
   
}