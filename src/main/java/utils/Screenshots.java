package utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;


public class Screenshots {

    public static void captureSuccess(WebDriver driver,String testName) {
        try {
            // set the file name of the screenshot
            String fileName = "Successful Screenshot for [%s]".formatted(testName);

            // take the screenshot
            var screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //upload the screenshot into allure report
            Allure.addAttachment(fileName, new ByteArrayInputStream(screenshot));

            //log the step into console
            LogHelper.logInfoStep("Capturing Screenshot for Succeeded Scenario");

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Capture Screenshot for Successes Scenario", e);
        }
    }

    public static void captureFailure(WebDriver driver,String testName) {
        try {
            String fileName = "Failed Screenshot for [%s]".formatted(testName);

            var screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(fileName, new ByteArrayInputStream(screenshot));

            LogHelper.logInfoStep("Capturing Screenshot for Failed Scenario");

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Capture Screenshot for Failed Scenario", e);
        }
    }
}
