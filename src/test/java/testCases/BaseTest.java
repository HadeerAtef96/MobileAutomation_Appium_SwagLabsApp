package testCases;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.AllureReportHelper;
import utils.AppiumFactory;

import java.io.File;
import java.io.IOException;

import static utils.AppiumFactory.startAppiumServerOnWindows;
import static utils.AppiumFactory.stopAppiumServer;
import static utils.PropertiesReader.*;

public class BaseTest {
    //Objects and Variables
    AppiumDriver driver;

    @BeforeSuite (alwaysRun = true)
    public void beforeSuite() {
        //Load All Properties and save it into System
        loadConfigurationsIntoSystemProperties();

        //Start the Appium Server
        AppiumFactory.startAppiumServerOnWindows();

        //Clear Old Allure Results before Every Run
        File file = new File("target/allure-results");
        AllureReportHelper.deleteOldFiles(file);
    }

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod() {
        // Open the Application
        this.driver = AppiumFactory.openApp();

    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {
        //Close the Application
        AppiumFactory.closeApp();
    }

    @AfterSuite (alwaysRun = true)
    public void afterSuite() throws IOException {
        // Stop Appium Server
        AppiumFactory.stopAppiumServer();

        // Open Allure Report Automatically After Run
        Runtime.getRuntime().exec(System.getProperty("user.dir")+"/Open_Allure_Report.bat");
    }
}
