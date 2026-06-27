package testCases_w3cTouchActions;

import org.testng.annotations.Test;
import page_w3cTouchActions.LoginPage;
import page_w3cTouchActions.ProductsPage;

import static utils.DataGenerator.generateRandomName;
import static utils.DataGenerator.generateRandomPassword;

public class LoginTests extends _BaseTest {

    @Test(groups = {"Positive"})
    public void loginWithValidUser() {

        new LoginPage(driver)
                .typeUsername(json.readTestData("validUser.username"))
                .typePassword(json.readTestData("validUser.password"))
                .clickOnLoginButton();

        new ProductsPage(driver)
                .verifyProductsPageTitleIsDisplayed();

    }

    @Test(groups = {"Negative"})
    public void loginWithInvalidUser() {
        new LoginPage(driver)
                .typeUsername(generateRandomName())
                .typePassword(generateRandomPassword())
                .clickOnLoginButton()
                .verifyErrorMessageIsDisplayed(json.readTestData("errorMessages.invalidUser"));
    }

    @Test(groups = {"Negative"})
    public void loginWithLockedUser() {
        new LoginPage(driver)
                .typeUsername(json.readTestData("lockedUser.username"))
                .typePassword(json.readTestData("lockedUser.password"))
                .clickOnLoginButton()
                .verifyErrorMessageIsDisplayed(json.readTestData("errorMessages.lockedUser"));
    }

}
