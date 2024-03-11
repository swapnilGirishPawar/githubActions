package TestSuite;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.commonFunctions;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Customer_CRUD extends commonFunctions {
    public String customerFullName;
    public String companyName;
    public String customerPhoneNumber;
    public String customerEmail;
    public String customerCompany;
    public String customerAddress;
    public String customerCountry;
    public String customerCity;
    public String customerPostalCode;
    public String customerState;
    public List<String> customerList = new ArrayList<String>();
    public List<String> customerListLogs = new ArrayList<String>();
    public List<String> overviewCustomerList = new ArrayList<String>();
    public List<String> overviewCustomerListLogs = new ArrayList<String>();
    private List<String> attributeNames = new ArrayList<String>(Arrays.asList("customerFullName", "customerPhoneNumber", "customerEmail", "customerCompany", "customerAddress"));
    private String value;
    
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
    public void createCustomer() throws Throwable {
       newCustomer();
       validateCustomerDetails("Create");           
    }
    
    @Test(enabled = true, priority = 2)
    public void editCustomer() throws Throwable {
    	editCustomerFlow();  
    	back();
    	validateCustomerDetails("Edit");       
    }
    
    @Test(enabled = true, priority = 3)
    public void deleteCustomer() throws Throwable {
    	deleteCustomerFlow();
    	searchCustomer("Delete");
    	tapOnElement("app.backIcon", "back");
    }   
    
    public void newCustomer() throws Throwable {
	    addCustomerTab();
	    inputCustomerFields("Create");
    }
        
    public void editCustomerFlow() throws Throwable {
	    tapOnElement("customers.editCustomers", "edit button");	 
	    inputCustomerFields("Edit");
    }
    
    public void deleteCustomerFlow() throws Throwable {
	    deleteProfile();
		tapOnElement("customers.confirmDeleteButton", "Confirm delete button");   
    }
	
	public void inputCustomerFields(String type) throws Throwable {
	    customerFullName = inputName(randomName());
	    nextField(driver, type);
	    customerPhoneNumber=inputPhoneNumber(randomPhoneNumber());
	    nextField(driver, type);
	    customerEmail= inputEmail(randomEmail());        
	    nextField(driver, type);
	    customerCompany= inputCompany(randomCompany());         
	    nextField(driver, type);
	    customerAddress=inputAddress(randomAddress());
	    customerCountry=inputCountry();
	    try {
	    	implicitlyWait(3);
	    	customerCity=inputCity(randomCity());               
	 	    customerPostalCode=inputPostalCode(randomPostalCode());      
	 	    customerState=inputState();
	 	    getCustomerDetails(type);
	 	    tapOnElement("customers.create", "Create button");  
		} catch (Exception e) {
			scrollOnePageUp(driver);
			customerCity=inputCity(randomCity());               
		    customerPostalCode=inputPostalCode(randomPostalCode());      
		    customerState=inputState();
		    getCustomerDetails(type);
		    tapOnElement("customers.create", "Create button");  
		}
    }
    
    public void validateCustomerDetails(String type) throws Throwable {
    	System.out.println("search1");
    	searchCustomer(type);
    	Thread.sleep(2000);
    	tapOnElement("customers.searchSelectCustomer", "recently created customer");	
    	fetchCustomerDetails("overview", overviewCustomerList, overviewCustomerListLogs);
    	validateStringLists(customerList, overviewCustomerList, attributeNames, test);
    }
    
    public void searchCustomer(String type) throws Throwable {
    	System.out.println("search2");
    	By search = By.xpath("//android.widget.TextView[@text='Customers']");
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
         wait.until(ExpectedConditions.visibilityOfElementLocated(search));        
    	tapOnElement("customers.searchCustomer", "search");
    	type("customers.searchText", customerFullName, "on the search");  
    	isDisplying("customers.searchSelectCustomer", "customer name in the list", type);
    }
    
    public void fetchCustomerDetails(String label, List<String> list, List<String> list1) throws Throwable {     
        getValue("customers.overviewName", "" + label + " customer name", list, list1);
        getValue("customers.overviewPhoneNumber", "" + label + " customer phone number", list, list1);
        getValue("customers.overviewEmail", "" + label + " customer email", list, list1);
        getValue("customers.overviewCompanyName", "" + label + " customer company name", list, list1);     
        getValue("customers.overviewAddress", "" + label + " customer country name", list, list1);
        printPageDetails(list1, "Customer Details");
    }
    
    public void addCustomerTab() throws Throwable{
    	tapOnElement("home.customers", "Bottom Navbar Customer Tab");
        tapOnElement("customers.addNewCustomer", "Add new customer");
        tapOnElement("profile.addManually", "add manually");
    }
    
    public String inputName(String name) throws Throwable {    
	    type("profile.fullName", name, "Customer Name");
	    return name;
    }
    
    public String inputPhoneNumber(String phoneNumber) throws Throwable {
	    type("customers.phoneNumber", phoneNumber, "Customer Phone number");
	    String countryCode = getValue("customers.countryCode", "country code");
		String wholeNumber = countryCode +" "+phoneNumber;
	    return wholeNumber;	
    }
    
    public String inputEmail(String email) throws Throwable {
        type("profile.email", email, "Customer email");
    	return email;
    }
    
    public String inputCompany(String company) throws Throwable {
    	type("customers.companyName", company, "Company name");
    	return company;
    }
    
    public String inputAddress(String address) throws Throwable {
    	type("customers.address", address, "Address");
    	return address;
    }
    
    public String inputCountry() throws Throwable {
    	tapOnElement("customers.country", "Country dropdown");
    	tapOnElement("customers.countryName", "Country");
    	String country= "Afghanistan";
    	return country;
    	 
    }
    
    public String inputCity(String city) throws Throwable {
    	type("customers.city", city, "City");
    	return city;
    }
    
    public String inputPostalCode(String postalCode) throws Throwable {
    	type("customers.postalCode", postalCode, "Postal Code");
    	return postalCode;
    }
    
    public String inputState() throws Throwable {   	
    	return addState();
    }
    
    public void back() throws Throwable {
    	tapOnElement("easySharePage.servicePageBack", "back");
    }
    
    public void getCustomerDetails(String action) {
    	System.out.println("customer details");
    	customerList.clear();
    	customerListLogs.clear();
    	String customerWholeAddress = customerAddress+", "+customerCity+", "+customerState+", "+customerCountry+" - "+customerPostalCode;
    	customerList=new ArrayList<String>(Arrays.asList(customerFullName, customerPhoneNumber, customerEmail, customerCompany, customerWholeAddress));
    	
    	for (int i = 0; i < attributeNames.size(); i++) {
        String keyValue= action +" "+attributeNames.get(i)+": "+customerList.get(i);
        System.out.println(keyValue);
        customerListLogs.add(keyValue);
        }
        printPageDetails(customerListLogs, "Customer Details");
    }
    
    public String addCountry() throws Throwable {		  
	    List<WebElement> CheckBoxList = driver.findElements(By.xpath("//android.view.View[@content-desc='search']/../../following-sibling::android.view.View/child::android.view.View/child::android.view.View[2]"));
		List<WebElement> labelList = driver.findElements(By.xpath("//android.view.View[@content-desc='search']/../../following-sibling::android.view.View/child::android.view.View/child::android.widget.TextView"));
		Random rand = new Random();
		int randomIndex = 1 + rand.nextInt(CheckBoxList.size()-2);
		value=labelList.get(randomIndex).getText();
	    node.log(Status.INFO, "Typed [" + value + "] on the Country");
		System.out.println(value);
		CheckBoxList.get(randomIndex).click();
		return value;
	}
    
    public String addState() throws Throwable {		
    	try {
    		tapOnElement("customers.stateDropdown", "State dropdown");  	
    		List<WebElement> CheckBoxList = driver.findElements(By.xpath("//android.view.View[@content-desc='search']/../../following-sibling::android.view.View//child::android.view.View/child::android.view.View"));
    		List<WebElement> labelList = driver.findElements(By.xpath("//android.view.View[@content-desc='search']/../../following-sibling::android.view.View//child::android.view.View/child::android.widget.TextView"));
    		Random rand = new Random();
    		int randomIndex = 1 + rand.nextInt(CheckBoxList.size()-2);
    		value=labelList.get(randomIndex).getText();
    		node.log(Status.INFO, "Typed [" + value + "] on the State");
    		System.out.println(value);
    		CheckBoxList.get(randomIndex).click();  		
		} catch (Exception e) {
			value=randomState();
			type("customers.state", value, "Postal Code");
		}
		return value;	    
    }
    
}
