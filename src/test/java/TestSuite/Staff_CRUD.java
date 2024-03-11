package TestSuite;

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
import java.util.Random;

public class Staff_CRUD extends commonFunctions {
	public String staffFullName;
    public String staffPhoneNumber;
    public String staffEmail;
    public String staffPermissionType;
    
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
    public void createStaff() throws Throwable {
       newStaff();         
    }
    
    public void newStaff() throws Throwable {
    	addStaff();	   
    }
    
    public void navigateToAddStaff() throws Throwable{
    	tapOnElement("home.settings", "Bottom Navbar Settings Tab");
        tapOnElement("settings.myTeam", "My Team");        
    }
    
    public void addStaff() throws Throwable{
    	navigateToAddStaff();
    	List<WebElement> listofStaffs = driver.findElements(By.id("com.adaptavant.setmore:id/first_and_last_name"));
	   	int staffListSize = listofStaffs.size();
	   	System.out.println(staffListSize);
	   	if (staffListSize==4) {
	   		listofStaffs.get(1).click();
	   		deleteProfile();
	   	}	   
	   	addTeamMember();
        }
    
    public String inputName(String name) throws Throwable {    
	    type("profile.fullName", name, "Staff Name");
	    return name;
    }
    
    public void addTeamMember() throws Throwable {  
	    tapOnElement("myTeam.addNewTeamMember", "add new team member");
	    tapOnElement("profile.addManually", "add manually");
		staffFullName=inputName(randomName());
		staffEmail=inputEmail(randomEmail());	   	    
		staffPermissionType = permissionLabel();
		System.out.println(staffFullName+" " +staffEmail+" "+staffPermissionType);
		tapOnElement("staffProfile.add", "add button");
    }
    
    public String inputPhoneNumber(String phoneNumber) throws Throwable {
	    type("customers.phoneNumber", phoneNumber, "Customer Phone number");
	    String countryCode = getValue("customers.countryCode", "country code");
		String wholeNumber = countryCode +" "+phoneNumber;
	    return wholeNumber;	
    }
    
    public String inputEmail(String email) throws Throwable {
        type("profile.email", email, "Staff email");
    	return email;
    }
    
    public void fetchStaffDetails(String label, List<String> list, List<String> list1) throws Throwable {
		getValue("Customers.fullName", "" + label + " staff name", list, list1);
		getValue("Customers.email", "" + label + " staff email", list, list1);
		 groupContent(list1, "Staff Details"); 
	 }
    
    public String permissionLabel() throws Throwable {
    	tapOnElement("staffProfile.staffAccess", "staff permission dropdown");
		Thread.sleep(2000);
	    List<String> permissionList = new ArrayList<String>(Arrays.asList("Standard","Enhanced","Admin"));
		Random rand = new Random();	
		int randomIndex = rand.nextInt(permissionList.size());
		System.out.println(randomIndex);
		String permission=permissionList.get(randomIndex);
		System.out.println(permission);			
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+permission+"']")).click();
		return permission;
    }
    
    

	public void fetchStaffOverviewDetails(String label, List<String> list, List<String> list1) throws Throwable {
		getValue("General.staffNameOverviewPage", "" + label + " staff name", list, list1);
		 Thread.sleep(3000);
		getValue("General.staffEmailOverviewPage", "" + label + " staff email", list, list1);
		 groupContent(list1, "Staff Details"); 
	 }
    
}
