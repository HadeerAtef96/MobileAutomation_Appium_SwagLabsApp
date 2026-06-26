package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends BasePage {
    //Variables
    String productName;

    //Constructor
    public CartPage(AppiumDriver driver){
        super(driver);
        super.initializeLocator();
    }

    //Locators
    private By cartPageTitle;
    private By productNameLocator;

    //Initialize Locators Based on Android or IOS
    @Override
    public void initializeLocator() {
        if ( driver instanceof AndroidDriver){
            cartPageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"YOUR CART\"]");
            productNameLocator = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]".formatted(productName));
        }

        else if (driver instanceof IOSDriver){
            cartPageTitle = AppiumBy.xpath("//XCUIElementTypeStaticText[@label=\"YOUR CART\"]");
        }

    }

    //Actions

    //Validations
    @Step
    public CartPage verifyCartPageTitleIsDisplayed(){
        Assert.assertTrue(finger.isElementDisplayed(cartPageTitle));
        return this;
    }

    @Step
    public CartPage verifyProductIsAddedToCart(String productName,String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to initialize the locators again because we make change in the locator
        initializeLocator();
        //we take validation through utils or w3c touch actions
        boolean actual = finger.isElementDisplayed(productNameLocator,direction);
        Assert.assertTrue(actual);
        return this;
    }

}
