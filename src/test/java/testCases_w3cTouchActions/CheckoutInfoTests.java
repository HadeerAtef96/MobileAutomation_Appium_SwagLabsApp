package testCases_w3cTouchActions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_w3cTouchActions.*;

import static utils.DataGenerator.*;

public class CheckoutInfoTests extends _BaseTest {


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
    public void fillCheckoutInfoWithValidData() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .selectToCheckoutTheCart("Down");

        new CheckoutInfoPage(driver)
                .verifyCheckoutInfoPageTitleIsDisplayed()
                .typeFirstname(generateRandomName())
                .typeLastname(generateRandomName())
                .typePostalCode(generateRandomPostalCode())
                .confirmCheckoutInfo();

        new CheckoutOverviewPage(driver)
                .verifyCheckoutCheckoutPageTitleIsDisplayed();

    }

    @Test(groups = {"Negative"})
    public void fillCheckoutInfoWithEmptyFirstName() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .selectToCheckoutTheCart("Down");

        new CheckoutInfoPage(driver)
                .verifyCheckoutInfoPageTitleIsDisplayed()
                .typeLastname(generateRandomName())
                .typePostalCode(generateRandomPostalCode())
                .confirmCheckoutInfo()
                .verifyErrorMessageIsDisplayed(json.readTestData("errorMessages.emptyFirstName"));
    }

    @Test(groups = {"Negative"})
    public void fillCheckoutInfoWithEmptyLastName() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .selectToCheckoutTheCart("Down");

        new CheckoutInfoPage(driver)
                .verifyCheckoutInfoPageTitleIsDisplayed()
                .typeFirstname(generateRandomName())
                .typePostalCode(generateRandomPostalCode())
                .confirmCheckoutInfo()
                .verifyErrorMessageIsDisplayed(json.readTestData("errorMessages.emptyLastName"));
    }

    @Test(groups = {"Negative"})
    public void fillCheckoutInfoWithEmptyPostalCode() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .selectToCheckoutTheCart("Down");

        new CheckoutInfoPage(driver)
                .verifyCheckoutInfoPageTitleIsDisplayed()
                .typeFirstname(generateRandomName())
                .typeLastname(generateRandomName())
                .confirmCheckoutInfo()
                .verifyErrorMessageIsDisplayed(json.readTestData("errorMessages.emptyPostalCode"));
    }
}
