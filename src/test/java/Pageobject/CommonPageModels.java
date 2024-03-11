package Pageobject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommonPageModels {

    //Login Page -
    public static WebElement googleLoginEmail(AndroidDriver driver, String desiredEmailAddress) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='" + desiredEmailAddress + "']"));
    }

    // Service appointment Page:-
    public static WebElement serviceDateOverView(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='Overview']/following-sibling ::android.widget.ScrollView/child::android.widget.TextView[6]"));
    }

    public static WebElement serviceTimeOverView(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='Overview']/following-sibling ::android.widget.ScrollView/child::android.widget.TextView[7]"));
    }
 // class appointment Page:-
    public static WebElement classDateOverView(AndroidDriver driver) {
    	return driver.findElement(By.xpath("//android.widget.TextView[@text='Overview']/following-sibling ::android.widget.ScrollView/child::android.widget.TextView[7]"));
    }

    public static WebElement classTimeOverView(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='Overview']/following-sibling ::android.widget.ScrollView/child::android.widget.TextView[8]"));
    }

    // Booking Page :-
    public static WebElement selectCreatedAppointment(AndroidDriver driver, String a) throws Throwable{
        return driver.findElement(By.xpath("//android.widget.TextView[@text='" + a + "']/ancestor::android.widget.LinearLayout[1]"));
    }
 
    public static WebElement editDate(AndroidDriver driver, int date) {
        return driver.findElement(By.xpath("//android.view.View[@text='" + date + "']"));
    }

    public static WebElement appointmentLabel(AndroidDriver driver, int num) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='Appointment labels']/following-sibling::android.view.View/child::android.view.View[" + num + "]"));
    }


    // Easy Share Page :-
    public static WebElement title(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.EditText[@resource-id='title']"));

    }

    public static WebElement description(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.EditText[@resource-id='description']"));

    }


    public static WebElement duration(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.EditText[@resource-id='duration']"));

    }

    public static WebElement serviceBuffer(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.EditText[@resource-id='buffer.after']"));

    }

    public static WebElement cost(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.EditText[@resource-id='price']"));

    }

    public static WebElement classSeat(AndroidDriver driver) {
        return driver.findElement(By.xpath("//android.widget.EditText[@resource-id='slotSize']"));

    }
    public static WebElement timeOffName(AndroidDriver driver, String TimeOffName) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='"+TimeOffName+"']"));
    }
}
