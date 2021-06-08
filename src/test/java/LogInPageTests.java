import core.AppiumDriverSettings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.MainShopPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class LogInPageTests extends AppiumDriverSettings {

    String errorMesIncorrectEmail = "Login was unsuccessful. Please correct the errors and try again.\n" +
            "No customer account found";
    String getErrorMesIncorrectPassword = "Login was unsuccessful. Please correct the errors and try again.\n" +
            "The credentials provided are incorrect";

    String correctEmail = "v.abarnikova2017@gmail.com";
    String incorrectEmail = "email@em.com";
    String correctPassword = "1234567";
    String incorrectPassword = "12345";

    String signInPageTitle = "Welcome, Please Sign In!";


    MainShopPage mainShopPage;
    LogInPage logInPage;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        logInPage = mainShopPage.clickOnLogIn();
    }

    @Test(groups = {"regression"})
    @Description("Verify the title matches the Login page")
    public void verifyLogInPageTitle() {
        assertThat(signInPageTitle, equalToIgnoringCase(logInPage.getSighInPAgeText()));
    }

    @Test(groups = {"regression"})
    @Description("Verify login function entering incorrect email")
    public void verifyInputIncorrectEmailToSignIn() {
        logInPage.setKeysToSignIn(incorrectEmail, correctPassword);
        logInPage.submitLigInButton();
        String errorText = logInPage.getTextOfErrorMessage();
        assertThat(errorText, equalToIgnoringCase(getErrorMesIncorrectPassword));
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify login function entering correct data")
    public void verifySuccessfulAuthorization() {
        logInPage = mainShopPage.clickOnLogIn();
        logInPage.setKeysToSignIn(correctEmail, correctPassword);
        mainShopPage = logInPage.submitLigInButton();
        String accountLink = mainShopPage.getEmailAccountText();
        assertThat(accountLink, equalToIgnoringCase(correctEmail));
        mainShopPage.clickOnLogOutButton();
    }


    @Test(groups = {"regression"})
    @Description("Verify login function entering incorrect password")
    public void verifyInputIncorrectPasswordToSignIn() {
        logInPage.clearAllFields();
        logInPage.setKeysToSignIn(correctEmail, incorrectPassword);
        logInPage.submitLigInButton();
        String errorText = logInPage.getTextOfErrorMessage();
        System.out.println(errorText);
        assertThat(errorText, equalToIgnoringCase(getErrorMesIncorrectPassword));
    }

    @Test(groups = {"regression"})
    @Description("Verify login function entering empty password and email")
    public void verifyInputEmptyEmailAndPasswordToSignIn() {
        logInPage.clearAllFields();
        logInPage.submitLigInButton();
        String errorText = logInPage.getTextOfErrorMessage();
        System.out.println(errorText);
        assertThat(errorText, equalToIgnoringCase(errorMesIncorrectEmail));
    }


    @AfterClass(alwaysRun = true)
    public void logout() {
        mainShopPage.clickOnLogOutButton();
    }
}
