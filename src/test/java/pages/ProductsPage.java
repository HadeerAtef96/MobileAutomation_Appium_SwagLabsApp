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
    String productName;

    //Constructor
    public ProductsPage(AppiumDriver driver){
        super(driver);
        super.initializeLocator();
    }

    //Locators
    private By productsPageTitle;
    private By addToCartButton;

    //Initialize Locators Based on Android or IOS
    @Override
    public void initializeLocator() {
        if ( driver instanceof AndroidDriver){
            productsPageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
            addToCartButton = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]/following-sibling::android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]".formatted(productName));
        }

        else if (driver instanceof IOSDriver){
            productsPageTitle = AppiumBy.xpath("//XCUIElementTypeStaticText[@label=\"PRODUCTS\"]");
        }

    }

    //Actions
    @Step
    public ProductsPage addProductToCartByButton(String productName,String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to initialize the locators again because we make change in the locator
        initializeLocator();
        //we take tap action through utils or w3c touch actions
        finger.tap(addToCartButton,direction);
        return this;
    }

    //Validations
    @Step
    public ProductsPage verifyProductsPageTitleIsDisplayed(){
        Assert.assertTrue(finger.isElementDisplayed(productsPageTitle));
        return this;
    }

}
