package BaseClass;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CommonUtils {
    public static String pantherName(){
        return "Mogambo";
    }
    public static void divider(){
        System.out.println("______________________________________________________________________");
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
