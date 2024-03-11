package TestSuite;

import Pageobject.CommonPageModels;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.lang.reflect.Method;

public class TimeOff_CRUD extends CommonUtils {
    public String timeOffTitle;
    @BeforeClass
    public void setupReportTest(){
        String currentClassName = getCurrentClassName();
        test = extent.createTest(currentClassName);
    }
    @BeforeMethod
    public void setupReportNode(Method method) {
        node = test.createNode(method.getName());
    }

    //Tests
    // Time Off Iteration 1
    @Test(enabled = true, priority = 1)
    public void create_timeoff() throws Throwable {
        navigateToTimeOffFromCalendarPage();
        createTimeOff();
    }


    //Methods
    private void createTimeOff() throws Throwable {
        String titleOfTimeOff = createSimpleTimeOff(); // iteration 1 :- to be refactored in next iterations
        validateTimeOff(titleOfTimeOff);
    }

    public void validateTimeOff(String Title) throws Throwable {
        validateTimeOFfOnTimeOffPage(Title);
        validateTimeOFfOnCalendarPage(Title);
    }

    // composed methods specific to timeOff flows:-

    public void validateTimeOFfOnTimeOffPage(String Title) throws Throwable {
        Thread.sleep(2000);
        isDisplying(CommonPageModels.timeOffName(driver, Title), Title);
    }
    public void validateTimeOFfOnCalendarPage(String Title) throws Throwable {
        navigateToCalendarPageFromTimeOff();
        checkTimeOffOnCalendarPage(Title);
    }
    public void navigateToTimeOffFromCalendarPage() throws Throwable {
        tapOnElement("BottomNavBar.settings", "bottom nav bar settings");
        selectMyTeam();
        selectStaff();
        selectTimeOff();
        validateEmptyTimeOffPage();
    }
    public void navigateToCalendarPageFromTimeOff() throws Throwable {
        Thread.sleep(3000);
        System.out.println("here");
        tapOnElement("TimeOff.backButton", "time off back button");
        Thread.sleep(3000);
        tapOnElement("staffProfile.backButton", " staff profile back button");
        tapOnElement("myTeam.backIcon", "your Team back button");
        tapOnElement("BottomNavBar.calendar", "Calendar tab");
    }

    public void validateEmptyTimeOffPage() throws Throwable {
        isDisplying("timeOff.addNewButton", "Add New button");
    }

    public void checkTimeOffOnCalendarPage(String Title) throws Throwable {
//        scrollUp(driver);
        isDisplying(CommonPageModels.timeOffName(driver, Title), Title);
    }

    public String createSimpleTimeOff() throws Throwable {
        tapOnElement("timeOff.addNewButton", "Add New button");
        timeOffTitle = inputTimeOffName(randomTitle());
        System.out.println("this is title of timeoff :- "+timeOffTitle);
        tapOnElement("addTimeOff.createButton", "Create button");
        return timeOffTitle;
    }

    // simple methods can be moved into common functions.
    public void editTimeOff() throws Throwable {
        navigateToTimeOffFromCalendarPage();
    }
    public void deleteTimeOff() throws Throwable {
        navigateToTimeOffFromCalendarPage();
    }
    public void selectMyTeam() throws Throwable {
        tapOnElement("settings.myTeam", "Settings -> my team selected");
    }
    public void selectStaff() throws Throwable {
        tapOnElement("staffProfile.youName","Owner staff");
    }
    public void selectTimeOff() throws Throwable {
        Thread.sleep(2000);
        isDisplying("staffProfile.timeOff", "TimeOff displayed");
        tapOnElement("staffProfile.timeOff", "Time Off on staff profile page");
    }
    private String inputTimeOffName(String name) throws Throwable {
        type("addTimeOff.inputTitle", name, "TimeOff Title");
        return name;
    }
}

//
//    // Time Off Iteration 2
//    @Test(enabled = false, priority = 2)
//    public void edit_timeoff() throws Throwable {
//        editTimeOff();
//    }
//
//    // Time Off Iteration 3
//    @Test(enabled = false, priority = 3)
//    public void delete_timeoff() throws Throwable {
//        deleteTimeOff();
//    }