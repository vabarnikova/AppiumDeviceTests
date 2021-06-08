import categories.BooksPage;
import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Email;
import pages.ItemDetailsPage;
import pages.LogInPage;
import pages.MainShopPage;
import utils.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class MessageToFriendPageTests extends AppiumDriverSettings {

    String friendsEmail = "email@em.com";
    String correctEmail = "v.abarnikova2017@gmail.com";
    String correctPassword = "1234567";
    String content = "it's your message";
    String successfulDelivery = "Your message has been sent.";
    String errorEmailFriendText = "Enter friend's email";
    String errorEmailUserText = "Enter your email";
    String title = "Email a friend";


    MainShopPage mainShopPage;
    BooksPage booksPage;
    ItemDetailsPage itemDetailsPage;
    Email email;
    LogInPage logInPage;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        logInPage = mainShopPage.clickOnLogIn();
        //log in
        logInPage = mainShopPage.clickOnLogIn();
        logInPage.setKeysToSignIn(correctEmail, correctPassword);
        mainShopPage = logInPage.submitLigInButton();
    }

    @Test(groups = {"regression"})
    @Description("Verify the title matches the Books page")
    public void verifyBooksPageTitle() {
        //open item details
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        itemDetailsPage = new ItemDetailsPage();
        booksPage.items.get(0).click();
        email = itemDetailsPage.getEmailFriendPage();
        String pageTitle = email.getPageTitle();
        assertThat(pageTitle, equalToIgnoringCase(title));
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify friend's notification about a product by sending email")
    public void verifySendingEmailToFriendWithCorrectData() {
        //open item details
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        itemDetailsPage = new ItemDetailsPage();
        booksPage.items.get(0).click();
        email = itemDetailsPage.getEmailFriendPage();
        //create email
        email.createEmailToFriendWithCorrectData(friendsEmail, content);
        email.clickOnSendEmail();
        String result = Utils.getElementText(email.sendingResult);
        assertThat(successfulDelivery, equalToIgnoringCase(result));
        mainShopPage.clickOnBooksCategory();
    }

    @Test(groups = {"regression"})
    @Description("Verify friend's notification about a product by sending email using empty friend's email")
    public void verifySendingEmailToFriendWithEmptyFriendsEmail() {
        //open item details
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        itemDetailsPage = new ItemDetailsPage();
        booksPage.items.get(0).click();
        email = itemDetailsPage.getEmailFriendPage();

        email.clearAllFields();
        email.createEmailToFriendWithInCorrectData("", correctEmail, content);
        email.clickOnSendEmail();

        assertThat(errorEmailFriendText, equalToIgnoringCase(Utils.getElementText(email.emptyFriendsEmailError)));
        mainShopPage.clickOnBooksCategory();
    }

    @Test(groups = {"regression"})
    @Description("Verify friend's notification about a product by sending email using empty user's email")
    public void verifySendingEmailToFriendWithEmptyUsersEmail() {
        //open item details
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        itemDetailsPage = new ItemDetailsPage();
        booksPage.items.get(0).click();
        email = itemDetailsPage.getEmailFriendPage();

        email.clearAllFields();
        email.createEmailToFriendWithInCorrectData(friendsEmail, "", content);
        email.clickOnSendEmail();

        assertThat(errorEmailUserText, equalToIgnoringCase(Utils.getElementText(email.emptyUsersEmailError)));
        mainShopPage.clickOnBooksCategory();
    }

    @AfterClass(alwaysRun = true)
    public void logout() {
        mainShopPage.clickOnLogOutButton();
    }

}
