package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductsPage extends BasePage {
    //Variables

    //Constructor
    public ProductsPage(AppiumDriver driver){
        super(driver);
    }

    //Locators
    private By productsPageTitle;

    //Initialize Locators Based on Android or IOS
    @Override
    public void initializeLocator() {
        if ( driver instanceof AndroidDriver){
            productsPageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
        }

        else if (driver instanceof IOSDriver){
            productsPageTitle = AppiumBy.xpath("//XCUIElementTypeStaticText[@label=\"PRODUCTS\"]");
        }

    }

    //Actions

    //Validations
    @Step
    public ProductsPage verifyProductsPageTitleIsDisplayed(){
        Assert.assertTrue(finger.isElementDisplayed(productsPageTitle));
        return this;
    }

}
