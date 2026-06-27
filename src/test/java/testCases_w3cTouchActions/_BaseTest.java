package testCases_w3cTouchActions;

import io.appium.java_client.AppiumDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.logging_reporting.AllureReportHelper;
import utils.driverFactory.AppiumFactory;
import utils.JsonReader;
import utils.logging_reporting.Screenshots;

import java.io.File;
import java.io.IOException;

import static utils.PropertiesReader.*;

public class _BaseTest {
    //Objects and Variables
    AppiumDriver driver;
    JsonReader json = new JsonReader("src/test/resources/TestData/testData.json");

    @BeforeSuite (alwaysRun = true)
    public void beforeSuite() {
        //Load All Properties and save it into System Properties
        loadConfigurationsIntoSystemProperties();

        //Start the Appium Server
        AppiumFactory.startAppiumServer();

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
    public void afterMethod(ITestResult result) {

        //Capture Screenshot
//        if (result.getStatus() == 1){
//            Screenshots.captureSuccess(driver,result.getMethod().getMethodName());
//        }
//
//        else if (result.getStatus() == 2){
//            Screenshots.captureFailure(driver,result.getMethod().getMethodName());
//        }

        //Close the Application
//        AppiumFactory.closeApp();
    }

    @AfterSuite (alwaysRun = true)
    public void afterSuite() throws IOException {
        // Stop Appium Server
        AppiumFactory.stopAppiumServer();

        // Open Allure Report Automatically After Run
        AllureReportHelper.autoOpenAllureReport();
    }
}
