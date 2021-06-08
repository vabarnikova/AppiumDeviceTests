import core.AppiumDriverSettings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainShopPage;
import pages.RegisterPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class RegisterPageTests extends AppiumDriverSettings {

    String errorTextName = "First name is required.";
    String errorTextSurname = "Last name is required.";
    String errorTextEmail = "Email is required.";
    String errorTextPassword = "Password is required.";
    String confirmationRegistrationText = "Your registration completed";
    String passwordLessThanSix = "12313";
    String errorTextPasswordAtLeastSixCharacters = "The password should have at least 6 characters.";

    MainShopPage mainShopPage;
    RegisterPage registerPage;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
    }

    @Test(groups = {"regression"})
    @Description("Verify registration function using empty data")
    public void verifyUserRegistrationUsingEmptyData() {
        registerPage = mainShopPage.clickOnRegisterButton();
        registerPage.confirmRegistration();
        assertThat(registerPage.errorTextIncorrectFirstName.getText(), equalToIgnoringCase(errorTextName));
        assertThat(registerPage.errorTextIncorrectLastName.getText(), equalToIgnoringCase(errorTextSurname));
        assertThat(registerPage.errorTextIncorrectEmail.getText(), equalToIgnoringCase(errorTextEmail));
        assertThat(registerPage.errorTextIncorrectPassword.getText(), equalToIgnoringCase(errorTextPassword));
        assertThat(registerPage.errorTextIncorrectPasswordConfirmation.getText(), equalToIgnoringCase(errorTextPassword));
        driver.navigate().refresh();
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify registration function using correct data")
    public void verifyUserRegistrationUsingCorrectData() {
        registerPage = mainShopPage.clickOnRegisterButton();
        String userFirstName = registerPage.getGeneratedFirstNameForRegister();
        String userLastName = registerPage.getGeneratedLastNameForRegister();
        String userEmail = registerPage.getGeneratedEmailForRegister();
        String userPassword = registerPage.getGeneratedPassword();
        registerPage.userRegistration(userFirstName, userLastName, userEmail, userPassword);
        registerPage.confirmRegistration();
        String confirmationText = registerPage.getConfirmationRegistrationText();
        assertThat(confirmationText, equalToIgnoringCase(confirmationRegistrationText));
        registerPage.continueButton.click();
        registerPage.clickOnLogOutButton();
    }

    @Test(groups = {"regression"})
    @Description("Verify registration function using password less than six characters")
    public void verifyUserRegistrationUsingPasswordLessThanSixCharacters() {
        registerPage = mainShopPage.clickOnRegisterButton();
        String userFirstName = registerPage.getGeneratedFirstNameForRegister();
        String userLastName = registerPage.getGeneratedLastNameForRegister();
        String userEmail = registerPage.getGeneratedEmailForRegister();
        registerPage.userRegistration(userFirstName, userLastName, userEmail, passwordLessThanSix);
        registerPage.confirmRegistration();
        assertThat(registerPage.errorTextIncorrectPassword.getText(), equalToIgnoringCase(errorTextPasswordAtLeastSixCharacters));
        driver.navigate().refresh();
    }

    @Test(groups = {"regression"})
    @Description("Verify registration function using empty first name")
    public void verifyUserRegistrationUsingEmptyFirstName() {
        registerPage = mainShopPage.clickOnRegisterButton();
        String userLastName = registerPage.getGeneratedLastNameForRegister();
        String userEmail = registerPage.getGeneratedEmailForRegister();
        String userPassword = registerPage.getGeneratedPassword();
        registerPage.userRegistration("", userLastName, userEmail, userPassword);
        registerPage.confirmRegistration();
        assertThat(registerPage.errorTextIncorrectFirstName.getText(), equalToIgnoringCase(errorTextName));
        driver.navigate().refresh();
    }

    @Test(groups = {"regression"})
    @Description("Verify registration function using empty last name")
    public void verifyUserRegistrationUsingEmptyLastName() {
        registerPage = mainShopPage.clickOnRegisterButton();
        String userFirstName = registerPage.getGeneratedFirstNameForRegister();
        String userEmail = registerPage.getGeneratedEmailForRegister();
        String userPassword = registerPage.getGeneratedPassword();
        registerPage.userRegistration(userFirstName, "", userEmail, userPassword);
        registerPage.confirmRegistration();
        assertThat(registerPage.errorTextIncorrectLastName.getText(), equalToIgnoringCase(errorTextSurname));
        driver.navigate().refresh();
    }
}
