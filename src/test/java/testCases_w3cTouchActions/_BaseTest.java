package testCases_w3cTouchActions;

import io.appium.java_client.AppiumDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.driverFactory.AppiumFactory;
import utils.JsonReader;

public class _BaseTest {
    //Objects and Variables
    AppiumDriver driver;
    JsonReader json = new JsonReader("src/test/resources/TestData/testData.json");

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod() {
        // Open the Application
        this.driver = AppiumFactory.openApp();

    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod(ITestResult result) {

        //Close the Application
        AppiumFactory.closeApp();
    }
}
