package testCases_w3cTouchActions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_w3cTouchActions.CartPage;
import page_w3cTouchActions.LoginPage;
import page_w3cTouchActions.ProductsPage;

public class AddToCartTests extends _BaseTest {

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
    public void addProductToCartByButton() {

        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[1].name"), "Down")
                .addProductToCartByButton(json.readTestData("products[2].name"), "Up")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .verifyProductIsAddedToCart(json.readTestData("products[0].name"), null)
                .verifyProductIsAddedToCart(json.readTestData("products[1].name"), "Down")
                .verifyProductIsAddedToCart(json.readTestData("products[2].name"), "Down");
    }

    @Test(groups = {"Positive"})
    public void addProductToCartByDragDrop() {

        new ProductsPage(driver)
                .addProductToCartByDragDrop(json.readTestData("products[0].name"), null)
                .addProductToCartByDragDrop(json.readTestData("products[1].name"), "Down")
                .addProductToCartByDragDrop(json.readTestData("products[2].name"), "Up")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .verifyProductIsAddedToCart(json.readTestData("products[0].name"), null)
                .verifyProductIsAddedToCart(json.readTestData("products[1].name"), "Down")
                .verifyProductIsAddedToCart(json.readTestData("products[2].name"), "Down");
    }

    @Test(groups = {"Negative"})
    public void addProductToCartTwice() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByDragDrop(json.readTestData("products[2].name"), null)
                .verifyAddToCartButtonIsRemovedFromPage(json.readTestData("products[0].name"), "Down")
                .verifyAddToCartButtonIsRemovedFromPage(json.readTestData("products[2].name"), "Up");
    }

}

