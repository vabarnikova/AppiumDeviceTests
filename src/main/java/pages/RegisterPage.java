package pages;

import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;
import java.util.logging.Logger;

public class RegisterPage extends BasePage {

    //error messages
    @FindBy(how = How.CSS, using = "span[data-valmsg-for=\"FirstName\"]")
    public WebElement errorTextIncorrectFirstName;

    @FindBy(how = How.CSS, using = "span[data-valmsg-for=\"LastName\"]")
    public WebElement errorTextIncorrectLastName;

    @FindBy(how = How.CSS, using = "span[data-valmsg-for=\"Email\"]")
    public WebElement errorTextIncorrectEmail;

    @FindBy(how = How.CSS, using = "span[data-valmsg-for=\"Password\"]")
    public WebElement errorTextIncorrectPassword;

    @FindBy(how = How.CSS, using = "span[data-valmsg-for=\"ConfirmPassword\"]")
    public WebElement errorTextIncorrectPasswordConfirmation;

    //registration result
    @FindBy(how = How.CSS, using = "div[class=\"result\"]")
    public WebElement registrationResult;

    @FindBy(how = How.CSS, using = "input[value=\"Continue\"]")
    public WebElement continueButton;

    private Random rand;

    private Logger log = Logger.getLogger(RegisterPage.class.getName());

    @FindBy(how = How.CSS, using = "a[class=\"ico-register\"]")
    private WebElement registerHeaderButton;

    //data
    @FindBy(how = How.CSS, using = "input[id=\"gender-male\"]")
    private WebElement genderMale;

    @FindBy(how = How.CSS, using = "input[name=\"FirstName\"]")
    private WebElement firstName;

    @FindBy(how = How.CSS, using = "input[name=\"LastName\"]")
    private WebElement lastName;

    @FindBy(how = How.CSS, using = "input[name=\"Email\"]")
    private WebElement email;

    @FindBy(how = How.CSS, using = "input[name=\"Password\"]")
    private WebElement password;

    @FindBy(how = How.CSS, using = "input[name=\"ConfirmPassword\"]")
    private WebElement confirmPassword;

    @FindBy(how = How.CSS, using = "input[name=\"register-button\"]")
    private WebElement registerButton;

    public RegisterPage() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Register page ------");
    }

    @Step("Set keys to register user")
    public void userRegistration(String userFirstName, String userLastName, String userEmail, String userPassword) {
        AppiumDriverWaits.waitForPresentEl(genderMale);
        genderMale.click();

        firstName.sendKeys(userFirstName);
        lastName.sendKeys(userLastName);
        email.sendKeys(userEmail);
        password.sendKeys(userPassword);
        confirmPassword.sendKeys(userPassword);
    }

    @Step("Click on confirm registration button")
    public void confirmRegistration() {
        AppiumDriverWaits.waitForPresentEl(registerButton);
        registerButton.click();
    }

    @Step("Get generated first name for register")
    public String getGeneratedFirstNameForRegister() {
        rand = new Random();
        return "Alex_" + rand.nextInt(1000000);
    }

    @Step("Get generated last name for register")
    public String getGeneratedLastNameForRegister() {
        rand = new Random();
        return "Astahov" + rand.nextInt(1000000);
    }

    @Step("Get generated email for register")
    public String getGeneratedEmailForRegister() {
        rand = new Random();
        return "astahov@" + rand.nextInt(1000000) + ".com";
    }

    @Step("Get generated password for register")
    public String getGeneratedPassword() {
        rand = new Random();
        return String.valueOf(rand.nextInt(1000000000));
    }

    @Step("Get confirmation registration text")
    public String getConfirmationRegistrationText() {
        AppiumDriverWaits.waitForPresentEl(registrationResult);
        return registrationResult.getText();
    }
}
