package BaseClass;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static BaseClass.SheetsQuickstart.*;

public class CommonUtils {
    public static String pantherName(){
        return "Mogambo";
    }
    public static void divider(){
        System.out.println("______________________________________________________________________");
    }

    @BeforeTest
    public void BT() throws GeneralSecurityException, IOException {
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        fetchAppConfigs();
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        fetchAllLocatorsFromSheet();
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        fetchLoginCredentials();
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
    }
    @Test
    public void testing(){
        System.out.println(pantherName());
        Date date = new Date();
        divider();
        System.out.println("Current Date :- "+LocalDate.now());
        System.out.println("Current Time :- "+LocalTime.now());
        divider();
    }
}
