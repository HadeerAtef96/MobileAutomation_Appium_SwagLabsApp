package pages;

import com.github.javafaker.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    //Variables

    //Constructor
    public LoginPage (AppiumDriver driver){
        super(driver);
    }

    //Locators
    private By userNameField;
    private By passwordField ;
    private By loginButton ;

    //Initialize Locators Based on Android or IOS
    public void initializeLocator(){
        if ( driver instanceof AndroidDriver){
            userNameField = AppiumBy.accessibilityId("android locator");
        }

        else if (driver instanceof IOSDriver){
            userNameField = AppiumBy.accessibilityId("ios locator");
        }
    }

    //Actions
    @Step
    public LoginPage typeUsername(String name){
        finger.type(userNameField,name);
        return this;
    }

    @Step
    public LoginPage typePassword(String password){
        finger.type(passwordField,password);
        return this;
    }

    @Step
    public LoginPage clickOnLoginButton(){
        finger.tap(loginButton);
        return this;
    }

    //Validations

}
