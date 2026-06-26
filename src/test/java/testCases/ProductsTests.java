package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class ProductsTests extends _BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginWithValidUser() {

        new LoginPage(driver)
                .typeUsername(json.readTestData("validUser.username"))
                .typePassword(json.readTestData("validUser.password"))
                .clickOnLoginButton();

        new ProductsPage(driver)
                .verifyProductsPageTitleIsDisplayed();

    }

    @Test(groups = {"Positive"})
    public void verifyAllProductDetailsAreDisplayed() {

        new ProductsPage(driver)
                .selectProduct(json.readTestData("products[0].name"), null);

        new ProductDetailsPage(driver)
                .verifyProductImageIsDisplayed()
                .verifyProductTitle(json.readTestData("products[0].name"),"Down")
                .verifyProductDescription(json.readTestData("products[0].description"),"Down")
                .verifyProductPrice(json.readTestData("products[0].price"),"Down")
                .verifyAddToCartButtonDisplayed("Down");
    }

    @Test(groups = {"Positive"})
    public void verifyZoomActionOnProductImage() {

        new ProductsPage(driver)
                .selectProduct(json.readTestData("products[0].name"), null);

        new ProductDetailsPage(driver)
                .zoomInProductImage(0.75)
                .verifyProductImageIsDisplayed()
                .zoomOutProductImage(0.75)
                .verifyProductImageIsDisplayed();
    }
}

