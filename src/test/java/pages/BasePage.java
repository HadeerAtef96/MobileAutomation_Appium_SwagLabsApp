package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.elementActions.W3CTouchActions;


public class BasePage {

    W3CTouchActions finger;

    public BasePage(AppiumDriver driver) {
        finger = new W3CTouchActions(driver);
    }

    //Header Locators
    protected By appLogo = AppiumBy.xpath("//android.widget.ImageView[1]");

    //Header Actions

    //Header Validation
    @Step
    public BasePage verifyLogoIsDisplayed(){
        boolean actualStatus = finger.isElementDisplayed(appLogo);
        Assert.assertTrue(actualStatus);
        return this;
    }
}
