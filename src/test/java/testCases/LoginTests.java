package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.JsonReader;

public class LoginTests extends BaseTest {

    JsonReader json = new JsonReader("src/test/resources/TestData/testData.json");

    @Test (groups = {"positive"})
    public void loginWithValidUser(){

        new LoginPage(driver)
                .typeUsername(json.readTestData("validUser.username"))
                .typePassword(json.readTestData("validUser.password"))
                .clickOnLoginButton();

        new ProductsPage(driver)
                .verifyProductsPageTitleIsDisplayed();

    }
}
