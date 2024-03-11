package utils.tryNewSnips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.CommonUtils;

public class testing extends CommonUtils {

    @Test(enabled = true, priority = 3)
    public void CLickOnTab() throws Throwable {
        Thread.sleep(2000);
        newClickIt("home.settings", "Bottom Nav bar - Settings");
        Thread.sleep(2000);
    }

    public static void newClickIt(String element, String elementName) throws Throwable {
        String Locator = ReadProperties(element, LocatorPropertiesFile);
        WebElement value = StringToElementConverter(Locator);
        try {
            wait(value, driver).click();

        } catch (Exception e) {
            System.out.println("fail at:- " + elementName);
        }
    }

    private static WebElement StringToElementConverter(String locator) throws Exception {

        String LocatorType = locator.split("\\$")[0];
        String LocatorValue = locator.split("\\$")[1];
        System.out.println("locator Type - " + LocatorType + " And Locator Value - " + LocatorValue);

        if (LocatorType.equalsIgnoreCase("xpath"))
            return driver.findElement(By.xpath(LocatorValue));
        else if (LocatorType.equalsIgnoreCase("id"))
            return driver.findElement(By.id(LocatorValue));
        else
            throw new Exception("Unknown locator type '" + LocatorType + "'");
    }

    public testing() throws Throwable {
    }

}
