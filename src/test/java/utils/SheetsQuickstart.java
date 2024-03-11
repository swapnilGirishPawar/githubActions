package utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class SheetsQuickstart extends CommonUtils{
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        File credentialsFile = new File("src/test/java/Utils/credentials.json");
        InputStream in = new FileInputStream(credentialsFile);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        } 
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     * @throws IOException 
     * @throws GeneralSecurityException 
     */
//    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
    public static FileOutputStream fileOut;
    public static void triggersheet() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1qUdsEf7CEd6z9hW8Jl1264sF66VEIcSlW5Cu5h4iuM4";
        final String range = "Android Locators!A2:C";
        Properties properties = new Properties();
        
        Sheets service =
                new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s%s%s\n", row.get(0) + "=", row.get(1) + "$", row.get(2));
                String key = row.get(0)+"";
                String value = row.get(1) + "$"+ row.get(2);
                properties.setProperty(key, value);               
            }
            fileOut = new FileOutputStream(LocatorPropertiesFile);
            properties.store(fileOut, "Mobile Element Locators");
//            fileOut.close();
        }
    }
    
    
    
    
    
    public static void triggersheet2() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1qUdsEf7CEd6z9hW8Jl1264sF66VEIcSlW5Cu5h4iuM4";
        final String range = "Login Credentials!A2:B";
        Properties properties = new Properties();
        
        Sheets service =
                new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s%s\n", row.get(0) + "=", row.get(1));
                String key = row.get(0)+"";
                String value = row.get(1)+"";
                properties.setProperty(key, value);               
            }
            fileOut = new FileOutputStream(LocatorPropertiesFile3);
            properties.store(fileOut, "Login Credentials");
//            fileOut.close();
        }
    }
   
    public static List<String> executionList = new ArrayList<String>();
    public static void triggersheet4() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1qUdsEf7CEd6z9hW8Jl1264sF66VEIcSlW5Cu5h4iuM4";
        final String range = "Modules!A2:B";
        Properties properties = new Properties();
        
        Sheets service =
                new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
        for (List<Object> a : values) {
			String flag = String.valueOf(a.get(1));
			if (flag.equalsIgnoreCase("Yes")) {
				executionList.add(String.valueOf(a.get(0)));
			}
		}
        }
       

        // Create a TestNG XML suite
        XmlSuite suite = new XmlSuite();
        suite.setName("MySuite");

        // Create a TestNG XML test
        XmlTest test = new XmlTest(suite);
        test.setName("MyTest");

        // Add the test classes to the test case
        for (String className : executionList) {
        	XmlClass testClass ;
        	String hook ="Hooks";
        	if (className.equalsIgnoreCase(hook)) {
        		testClass = new XmlClass("BaseClass."+className);
			}else {
				testClass = new XmlClass("TestSuite."+className);	            
			}
        	test.getXmlClasses().add(testClass);
            System.out.println(testClass);
           
        }      
       System.out.println("done");
        // Create a TestNG object and run the suite
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(Collections.singletonList(suite));
        testNG.run();
    }
        
    
    
    
    
    
  public static void main(String[] args) throws IOException, GeneralSecurityException {
	  triggersheet4();
  }
    
    
    
    
    
    
    
    
}