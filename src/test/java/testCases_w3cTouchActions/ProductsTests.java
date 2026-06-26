package testCases_w3cTouchActions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_w3cTouchActions.LoginPage;
import page_w3cTouchActions.ProductDetailsPage;
import page_w3cTouchActions.ProductsPage;

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
                .zoomInProductImage(1)
                .verifyProductImageIsDisplayed()
                .zoomOutProductImage(1)
                .verifyProductImageIsDisplayed();
    }
}

