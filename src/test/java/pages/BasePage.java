package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.elementActions.W3CTouchActions;


public class BasePage {

    //Common Variables
    AppiumDriver driver;
    W3CTouchActions finger = new W3CTouchActions(driver);

    //Constructor
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        initializeLocator();
    }

    //Header
    protected By appLogo;

    @Step
    public BasePage verifyLogoIsDisplayed(){
        boolean actualStatus = finger.isElementDisplayed(appLogo);
        Assert.assertTrue(actualStatus);
        return this;
    }


    //Initialize Locators Based on Android or IOS
    public void initializeLocator(){
        if ( driver instanceof AndroidDriver){
            appLogo = AppiumBy.xpath("//android.widget.ImageView[1]");
        }

        else if (driver instanceof IOSDriver){
            appLogo = AppiumBy.xpath("//android.widget.ImageView[1]");
        }
    }
}
