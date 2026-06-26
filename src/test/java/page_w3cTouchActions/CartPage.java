package page_w3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends _BasePage {
    //Variables
    String productName;

    //Constructor
    public CartPage(AppiumDriver driver){
        super(driver);
        super.initializeLocator();
    }

    //Locators
    private By cartPageTitle;
    private By productCard;
    private By productNameLocator;
    private By removeFromCartButton;
    private By removeFromCartRedButton;
    private By checkoutButton;

    //Initialize Locators Based on Android or IOS
    @Override
    public void initializeLocator() {
        if ( driver instanceof AndroidDriver){
            cartPageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"YOUR CART\"]");
            productCard = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup[@content-desc=\"test-Item\"]".formatted(productName));
            productNameLocator = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]".formatted(productName));
            removeFromCartButton = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup[@content-desc=\"test-Item\"]//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]".formatted(productName));
            removeFromCartRedButton = AppiumBy.accessibilityId("test-Delete");
            checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
        }

        else if (driver instanceof IOSDriver){
            cartPageTitle = AppiumBy.xpath("//XCUIElementTypeStaticText[@label=\"YOUR CART\"]");
        }

    }

    //Actions
    @Step
    public CartPage removeFromCartByButton(String productName, String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to initialize the locators again because we make change in the locator
        initializeLocator();
        //we perform remove action through utils or w3c touch actions
        finger.tap(removeFromCartButton);
        return this;
    }

    @Step
    public CartPage removeFromCartBySwipe(String productName, String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to initialize the locators again because we make change in the locator
        initializeLocator();
        //we perform remove action through utils or w3c touch actions
        finger.scrollUntilElementDisplayed(productCard,direction);
        finger.singleSwipeIntoElement("Right",productCard);
        finger.tap(removeFromCartRedButton);
        return this;
    }

    @Step
    public CartPage selectToCheckoutTheCart(String direction){
        finger.tap(checkoutButton,direction);
        return this;
    }


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

    @Step
    public CartPage verifyProductIsRemovedFromCart(String productName,String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to initialize the locators again because we make change in the locator
        initializeLocator();
        //we take validation through utils or w3c touch actions
        boolean actual = finger.isElementNotDisplayed(productNameLocator,direction);
        Assert.assertTrue(actual);
        return this;
    }

}
