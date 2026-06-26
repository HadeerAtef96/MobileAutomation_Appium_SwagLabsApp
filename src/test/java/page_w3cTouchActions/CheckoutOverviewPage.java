package page_w3cTouchActions;

import com.github.javafaker.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutOverviewPage extends _BasePage {
    //Variables
    String productName;

    //Constructor
    public CheckoutOverviewPage(AppiumDriver driver){
        super(driver);
        super.initializeLocator();
    }

    //Locators
    private By pageTitleText;
    private By productNameText;
    private By productPriceText;
    private By productDescriptionText;
    private By productQuantityText;
    private By paymentMethodText ;
    private By shippingMethodText;
    private By totalPriceText;
    private By finishButton;

    //Initialize Locators Based on Android or IOS
    public void initializeLocator(){
        if ( driver instanceof AndroidDriver){
            pageTitleText = AppiumBy.xpath("//android.widget.TextView[@text=\"CHECKOUT: OVERVIEW\"]");
            productNameText = AppiumBy.xpath("//android.widget.TextView[@text=\"%s\"]".formatted(productName));
            productPriceText =

        }
        else if (driver instanceof IOSDriver){

        }
    }

    //Actions
    @Step("Finish Cart Checkout")
    public CartCheckOutCompletePage finishCartCheckOut()
    {
        action.tap(finishButton);
        return new CartCheckOutCompletePage(driver);
    }


    //Validations
    @Step
    public CheckoutOverviewPage verifyCheckoutCheckoutPageTitleIsDisplayed(){
        boolean flag = finger.isElementDisplayed(pageTitleText);
        Assert.assertTrue(flag);
        return this;
    }

    @Step("Assert Product Is Added To Cart")
    public CartCheckOutOverviewPage assertProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(productItem,DOWN));
        return this;
    }

    @Step("Assert Product Is Removed From Cart")
    public CartCheckOutOverviewPage assertProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(productItem));
        return this;
    }

    @Step("Verify Product Info")
    public CartCheckOutOverviewPage verifyProductInfo(String productName,String price,String quantity,String description)
    {
        verifyProductPrice(productName,price)
                .verifyProductQuantity(productName,quantity)
                .verifyProductDescription(productName,description);
        return this;
    }

    @Step("Verify Product Price")
    private CartCheckOutOverviewPage verifyProductPrice(String productName,String price) {
        defineLocatorsByProductName(productName);
        String actualPrice = action.readText(productPriceLocator).split("\\$",2)[1];
        CustomSoftAssert.assertEquals(actualPrice,price);
        return this;
    }

    @Step("Verify Product Quantity")
    private CartCheckOutOverviewPage verifyProductQuantity(String productName,String quantity) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action.readText(productQuantityLocator),quantity);
        return this;
    }

    @Step("Verify Product Description")
    private CartCheckOutOverviewPage verifyProductDescription(String productName,String description) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action.readText(productDescriptionLocator),description);
        return this;
    }

    @Step("Verify Total Price of Products")
    public CartCheckOutOverviewPage verifyTotalPriceOfProducts(double totalPrice) {
        double actualTotalPrice = Double.parseDouble(action.readText(totalPriceLocator).split("\\$",2)[1]);
        CustomSoftAssert.assertEquals(actualTotalPrice,totalPrice);
        return this;
    }

    @Step("Verify the Payment Info")
    public CartCheckOutOverviewPage verifyPaymentInfo(String paymentInfo){
        CustomSoftAssert.assertEquals(action.readText(paymentInfoLocator),paymentInfo);
        return this;
    }

    @Step("Verify the Shipping Method")
    public CartCheckOutOverviewPage verifyShippingMethod(String shippingMethod) {
        CustomSoftAssert.assertEquals(action.readText(shippingInfoLocator),shippingMethod);
        return this;
    }

}
