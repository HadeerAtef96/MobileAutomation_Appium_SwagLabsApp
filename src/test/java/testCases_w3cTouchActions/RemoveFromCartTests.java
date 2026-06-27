package testCases_w3cTouchActions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_w3cTouchActions.CartPage;
import page_w3cTouchActions.LoginPage;
import page_w3cTouchActions.ProductsPage;

public class RemoveFromCartTests extends _BaseTest {

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
    public void removeOneProductFromCartByButton() {

        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
                .addProductToCartByButton(json.readTestData("products[1].name"), "Down")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .removeFromCartByButton(json.readTestData("products[0].name"), null)
                .verifyProductIsRemovedFromCart(json.readTestData("products[0].name"), "Down")
                .verifyProductIsAddedToCart(json.readTestData("products[1].name"), "Up")
                .verifyProductIsAddedToCart(json.readTestData("products[2].name"), "Up");
    }

    @Test(groups = {"Positive"})
    public void removeAllProductsFromCartByButton() {

        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
                .addProductToCartByButton(json.readTestData("products[1].name"), "Down")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .removeFromCartByButton(json.readTestData("products[0].name"), null)
                .removeFromCartByButton(json.readTestData("products[2].name"), null)
                .removeFromCartByButton(json.readTestData("products[1].name"), null)
                .verifyProductIsRemovedFromCart(json.readTestData("products[0].name"), "Down")
                .verifyProductIsRemovedFromCart(json.readTestData("products[2].name"), "Up")
                .verifyProductIsRemovedFromCart(json.readTestData("products[1].name"), "Down");
    }

    @Test(groups = {"Positive"})
    public void removeAllProductsFromCartBySwipe() {

        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
                .addProductToCartByButton(json.readTestData("products[1].name"), "Down")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .removeFromCartBySwipe(json.readTestData("products[0].name"), null)
                .removeFromCartBySwipe(json.readTestData("products[2].name"), null)
                .removeFromCartBySwipe(json.readTestData("products[1].name"), null)
                .verifyProductIsRemovedFromCart(json.readTestData("products[0].name"), "Down")
                .verifyProductIsRemovedFromCart(json.readTestData("products[2].name"), "Up")
                .verifyProductIsRemovedFromCart(json.readTestData("products[1].name"), "Down");
    }

}

