package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
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
    private By productsPageTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");

    //Actions

    //Validations
    @Step
    public ProductsPage verifyProductsPageTitleIsDisplayed(){
        Assert.assertTrue(finger.isElementDisplayed(productsPageTitle));
        return this;
    }

}
