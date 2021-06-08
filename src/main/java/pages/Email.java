package pages;

import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class Email extends BasePage {

    @FindBy(how = How.CSS, using = "div[class=\"result\"]")
    public WebElement sendingResult;

    @FindBy(how = How.CSS, using = "span[for=\"FriendEmail\"]")
    public WebElement emptyFriendsEmailError;

    @FindBy(how = How.CSS, using = "span[for=\"YourEmailAddress\"]")
    public WebElement emptyUsersEmailError;

    private Logger log = Logger.getLogger(Email.class.getName());

    @FindBy(how = How.CSS, using = "input[id=\"FriendEmail\"]")
    private WebElement friendsEmail;

    @FindBy(how = How.CSS, using = "input[id=\"YourEmailAddress\"]")
    private WebElement usersEmail;

    @FindBy(how = How.CSS, using = "textarea[id=\"PersonalMessage\"]")
    private WebElement message;

    @FindBy(how = How.CSS, using = "input[value=\"Send email\"]")
    private WebElement sendEmail;

    public Email() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Email page ------");
    }

    @Step("Create email to friend with correct data")
    public void createEmailToFriendWithCorrectData(String friendsAddress, String content) {
        AppiumDriverWaits.waitForPresentEl(friendsEmail);
        friendsEmail.sendKeys(friendsAddress);
        message.sendKeys(content);
    }

    @Step("Create email to friend with incorrect data")
    public void createEmailToFriendWithInCorrectData(String friendsAddress, String usersAddress, String content) {
        AppiumDriverWaits.waitForPresentEl(friendsEmail);
        friendsEmail.click();
        friendsEmail.sendKeys(friendsAddress);
        usersEmail.click();
        usersEmail.sendKeys(usersAddress);
        message.click();
        message.sendKeys(content);
    }

    @Step("Click on send email button")
    public void clickOnSendEmail() {
        AppiumDriverWaits.waitForPresentEl(sendEmail);
        sendEmail.click();
    }

    @Step("Clear all fields")
    public void clearAllFields() {
        AppiumDriverWaits.waitForPresentEl(friendsEmail);
        friendsEmail.clear();
        usersEmail.clear();
        message.clear();
    }


}
