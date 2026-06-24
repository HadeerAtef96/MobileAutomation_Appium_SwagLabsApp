package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    //Variables

    //Constructor
    public LoginPage (AppiumDriver driver){
        super(driver);
    }

    //Locators
    private By userNameField = AppiumBy.accessibilityId("test-Username");
    private By passwordField = AppiumBy.accessibilityId("test-Password");
    private By loginButton = AppiumBy.accessibilityId("test-LOGIN");

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
