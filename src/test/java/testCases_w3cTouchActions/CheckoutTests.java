package testCases_w3cTouchActions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_w3cTouchActions.*;

import static utils.DataGenerator.generateRandomName;
import static utils.DataGenerator.generateRandomPostalCode;

public class CheckoutTests extends _BaseTest {

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
    public void verifyCheckoutOverviewDetails() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
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
                .verifyCheckoutCheckoutPageTitleIsDisplayed()
                .verifyProductIsAddedToCart(json.readTestData("products[0].name"), "Down")
                .verifyProductDescription(json.readTestData("products[0].name"), "Down", json.readTestData("products[0].description"))
                .verifyProductPrice(json.readTestData("products[0].name"), "Down", json.readTestData("products[0].price"))
                .verifyProductQuantity(json.readTestData("products[0].name"), "Down", json.readTestData("products[0].quantity"))

                .verifyProductIsAddedToCart(json.readTestData("products[2].name"), "Down")
                .verifyProductDescription(json.readTestData("products[2].name"), "Down", json.readTestData("products[2].description"))
                .verifyProductPrice(json.readTestData("products[2].name"), "Down", json.readTestData("products[2].price"))
                .verifyProductQuantity(json.readTestData("products[2].name"), "Down", json.readTestData("products[2].quantity"))

                .verifyPaymentMethod(json.readTestData("paymentMethod"), "Down")
                .verifyShippingMethod(json.readTestData("shippingMethod"), "Down")
                .verifyTotalPriceOfProducts(
                        Double.parseDouble(json.readTestData("products[0].price")) + Double.parseDouble(json.readTestData("products[2].price"))
                        , "Down");

    }

    @Test(groups = {"Positive"})
    public void verifySuccessfulCheckoutCart() {
        new ProductsPage(driver)
                .addProductToCartByDragDrop(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
                .selectProduct(json.readTestData("products[1].name"), "Down");

        new ProductDetailsPage(driver)
                .verifyProductImageIsDisplayed()
                .zoomInProductImage(0.75)
                .zoomOutProductImage(0.75)
                .addProductToCart("Down")
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .removeFromCartBySwipe(json.readTestData("products[0].name"), null)
                .selectToCheckoutTheCart("Down");

        new CheckoutInfoPage(driver)
                .verifyCheckoutInfoPageTitleIsDisplayed()
                .typeFirstname(generateRandomName())
                .typeLastname(generateRandomName())
                .typePostalCode(generateRandomPostalCode())
                .confirmCheckoutInfo();

        new CheckoutOverviewPage(driver)
                .verifyProductIsAddedToCart(json.readTestData("products[2].name"), "Down")
                .verifyProductIsAddedToCart(json.readTestData("products[1].name"), "Down")
                .verifyTotalPriceOfProducts(
                        Double.parseDouble(json.readTestData("products[2].price")) + Double.parseDouble(json.readTestData("products[1].price")), "Down")
                .finishCartCheckOut("Down");

        new CheckoutSuccessPage(driver)
                .verifySuccessImageIsDisplayed()
                .verifySuccessMessageIsDisplayed(json.readTestData("successMessages.checkout"));

    }

    @Test(groups = {"Negative"})
    public void verifyCheckoutAfterEmptyTheCart() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
                .navigateToCart();

        new CartPage(driver)
                .verifyCartPageTitleIsDisplayed()
                .removeFromCartByButton(json.readTestData("products[0].name"), null)
                .removeFromCartByButton(json.readTestData("products[2].name"), null)
                .verifyCheckoutButtonIsRemovedFromPage("Down");

    }

    @Test(groups = {"Negative"})
    public void verifyTotalPriceIsUpdatedInCheckoutAfterRemovingProducts() {
        new ProductsPage(driver)
                .addProductToCartByButton(json.readTestData("products[0].name"), null)
                .addProductToCartByButton(json.readTestData("products[2].name"), null)
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
                .verifyProductIsAddedToCart(json.readTestData("products[0].name"), "Down")
                .verifyProductIsAddedToCart(json.readTestData("products[2].name"), "Down")
                .verifyTotalPriceOfProducts(
                        Double.parseDouble(json.readTestData("products[0].price")) + Double.parseDouble(json.readTestData("products[2].price"))
                        , "Down");

        new CheckoutOverviewPage(driver)
                .removeFromCartBySwipe(json.readTestData("products[0].name"), "Up")
                .verifyProductIsRemovedFromCart(json.readTestData("products[0].name"), "Down")
                .verifyTotalPriceOfProducts(
                        Double.parseDouble(json.readTestData("products[2].price"))
                        , "Up");
    }

}
