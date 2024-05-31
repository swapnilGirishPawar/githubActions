package Suite;

import org.testng.annotations.Test;

import BaseClass.CommonUtils;

public class HelloWorld extends CommonUtils {
    public static void main(String[] args) {
        String panther = pantherName();
        System.out.println("Hello "+panther);
        
    }

    
}
