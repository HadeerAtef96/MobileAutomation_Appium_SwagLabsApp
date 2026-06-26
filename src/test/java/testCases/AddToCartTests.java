package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.JsonReader;

import static utils.DataGenerator.generateRandomName;
import static utils.DataGenerator.generateRandomPassword;

public class AddToCartTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginWithValidUser(){

        new LoginPage(driver)
                .typeUsername(json.readTestData("validUser.username"))
                .typePassword(json.readTestData("validUser.password"))
                .clickOnLoginButton();

        new ProductsPage(driver)
                 .verifyProductsPageTitleIsDisplayed();

    }

    @Test (groups = {"Positive"})
    public void addProductToCartByButton(){

        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"),null)
                .addProductToCartByButton(json.readTestData("products[1].name"),"Down")
                .addProductToCartByButton(json.readTestData("products[2].name"),"Up")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .verifyProductIsAddedToCart(json.readTestData("products[0].name"),null)
                .verifyProductIsAddedToCart(json.readTestData("products[1].name"),"Down")
                .verifyProductIsAddedToCart(json.readTestData("products[2].name"),"Down");
    }


}
