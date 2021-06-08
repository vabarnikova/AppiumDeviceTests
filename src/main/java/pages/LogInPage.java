package pages;

import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class LogInPage extends BasePage {
    private Logger log = Logger.getLogger(LogInPage.class.getName());

    @FindBy(how = How.CSS, using = "a[class=\"ico-login\"]")
    private WebElement loginIcon;

    @FindBy(how = How.CSS, using = "input[name=\"Email\"]")
    private WebElement userEmail;

    @FindBy(how = How.CSS, using = "input[name=\"Password\"]")
    private WebElement userPassword;

    @FindBy(how = How.CSS, using = "input[value=\"Log in\"]")
    private WebElement submitLogInButton;

    @FindBy(how = How.CSS, using = "div[class=\"validation-summary-errors\"]")
    private WebElement errorMessage;

    @FindBy(how = How.CSS, using = "div[class=\"page-title\"]")
    private WebElement signInText;


    public LogInPage() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Log In page ------");
    }

    @Step("Submit login button")
    public MainShopPage submitLigInButton() {
        AppiumDriverWaits.waitForPresentEl(submitLogInButton);
        submitLogInButton.click();
        return new MainShopPage();
    }

    @Step("Set keys to sign in")
    public void setKeysToSignIn(String emailText, String passwordText) {
        AppiumDriverWaits.waitForPresentEl(userEmail);
        userEmail.click();
        log.info("Input email: " + emailText);
        userEmail.sendKeys(emailText);
        userPassword.click();
        log.info("Input password: " + passwordText);
        userPassword.sendKeys(passwordText);
    }

    @Step("Get text of error message")
    public String getTextOfErrorMessage() {
        AppiumDriverWaits.waitForPresentEl(errorMessage);
        return errorMessage.getText();
    }

    @Step("Clear all fields")
    public void clearAllFields() {
        AppiumDriverWaits.waitForPresentEl(userEmail);
        userEmail.clear();
        userPassword.clear();
    }

    @Step("Get sign in page text")
    public String getSighInPAgeText() {
        AppiumDriverWaits.waitForPresentEl(signInText);
        return signInText.getText();
    }


}
