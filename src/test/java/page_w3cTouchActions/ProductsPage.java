package page_w3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductsPage extends _BasePage {
    //Variables
    String productName;

    //Constructor
    public ProductsPage(AppiumDriver driver){
        super(driver);
        super.initializeLocator();
    }

    //Locators
    private By productsPageTitle;
    private By productNameLocator;
    private By addToCartButton;
    private By dragButton;
    private By dropButton;

    //Initialize Locators Based on Android or IOS
    @Override
    public void initializeLocator() {
        if ( driver instanceof AndroidDriver){
            productsPageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
            productNameLocator = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]".formatted(productName));
            addToCartButton = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]/following-sibling::android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]".formatted(productName));
            dragButton = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]/following-sibling::android.view.ViewGroup[@content-desc=\"test-Drag Handle\"]//android.widget.TextView".formatted(productName));
            dropButton = AppiumBy.accessibilityId("test-Cart drop zone");
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

    @Step
    public ProductsPage addProductToCartByDragDrop(String productName,String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to initialize the locators again because we make change in the locator
        initializeLocator();
        //we take tap action through utils or w3c touch actions
        finger.dragAndDrop(dragButton,dropButton,direction);
        return this;
    }

    @Step
    public ProductsPage selectProduct(String productName, String direction){
        //we pass productName from test case, then we convert it from local variable to global variable
        this.productName = productName;
        //we need to re-initialize the locators again because we make change in the locator
        initializeLocator();
        //we take tap action through utils or w3c touch actions
        finger.tap(productNameLocator,direction);
        return this;
    }

    //Validations
    @Step
    public ProductsPage verifyProductsPageTitleIsDisplayed(){
        Assert.assertTrue(finger.isElementDisplayed(productsPageTitle));
        return this;
    }

}
