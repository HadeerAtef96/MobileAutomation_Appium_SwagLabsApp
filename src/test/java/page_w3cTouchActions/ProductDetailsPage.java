package page_w3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductDetailsPage extends _BasePage {
    //Variables

    //Constructor
    public ProductDetailsPage(AppiumDriver driver){
        super(driver);
        super.initializeLocator();
    }

    //Locators
    private By productImage;
    private By productTitleText;
    private By productDescriptionText;
    private By productPriceText;
    private By addToCartButton;

    //Initialize Locators Based on Android or IOS
    @Override
    public void initializeLocator() {
        if ( driver instanceof AndroidDriver){
            productImage = AppiumBy.accessibilityId("test-Image Container");
            productTitleText = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView[1]");
            productDescriptionText = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView[2]");
            productPriceText = AppiumBy.accessibilityId("test-Price");
            addToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");
        }

        else if (driver instanceof IOSDriver){

        }

    }

    //Actions
    @Step
    public ProductDetailsPage addProductToCart(String direction){
        finger.tap(addToCartButton,direction);
        return this;
    }

    @Step
    public ProductDetailsPage zoomInProductImage(double zoomingPercentage){
        finger.zoomIn(productImage,zoomingPercentage);
        return this;
    }

    @Step
    public ProductDetailsPage zoomOutProductImage(double zoomingPercentage){
        finger.zoomOut(productImage,zoomingPercentage);
        return this;
    }

    //Validations
    @Step
    public ProductDetailsPage verifyProductImageIsDisplayed(){
        boolean actual = finger.isElementDisplayed(productImage);
        Assert.assertTrue(actual);
        return this;
    }

    @Step
    public ProductDetailsPage verifyProductTitle(String expectedTitle,String direction){
        String actual = finger.readTextFromElement(productTitleText,direction);
        Assert.assertEquals(actual,expectedTitle);
        return this;
    }

    @Step
    public ProductDetailsPage verifyProductDescription(String expectedDescription,String direction){
        String actual = finger.readTextFromElement(productDescriptionText,direction);
        Assert.assertEquals(actual,expectedDescription);
        return this;
    }

    @Step
    public ProductDetailsPage verifyProductPrice(String expectedPrice,String direction){
        String actual = finger.readTextFromElement(productPriceText,direction);
        Assert.assertEquals(actual,expectedPrice);
        return this;
    }

    @Step
    public ProductDetailsPage verifyAddToCartButtonDisplayed(String direction){
        Assert.assertTrue(finger.isElementDisplayed(addToCartButton,direction));
        return this;
    }
}
