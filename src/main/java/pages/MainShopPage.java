package pages;

import categories.BooksPage;
import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class MainShopPage extends BasePage {

    @FindBy(how = How.CSS, using = ".topic-html-content-header")
    public WebElement mainPageTitle;

    //search
    @FindBy(how = How.CSS, using = "input[id=\"small-searchterms\"]")
    public WebElement searchInput;

    @FindBy(how = How.CSS, using = "input[value=\"Search\"]")
    public WebElement searchButton;

    private Logger log = Logger.getLogger(MainShopPage.class.getName());
    @FindBy(how = How.CSS, using = "a[class=\"ico-login\"]")
    private WebElement loginIcon;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[2]")
    private WebElement logOutIcon;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[1]")
    private WebElement registerIcon;

    // header
    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[1]")
    private WebElement accountLink;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[2]")
    private WebElement logOut;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[3]")
    private WebElement shoppingCart;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[4]")
    private WebElement wishList;

    //categories
    @FindBy(how = How.CSS, using = "div[id=\"mob-menu-button\"]")
    private WebElement menuButton;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"mob-top-menu show\"]/li/a[@href=\"/books\"]")
    private WebElement booksCategory;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"top-menu\"]/li//a[@href=\"/computers\"]")
    private WebElement computersCategory;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"top-menu\"]/li//a[@href=\"/electronics\"]")
    private WebElement electronicsCategory;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"top-menu\"]/li//a[@href=\"/apparel-shoes\"]")
    private WebElement apparelAndShoesCategory;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"top-menu\"]/li//a[@href=\"/digital-downloads\"]")
    private WebElement digitalDownloadsCategory;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"top-menu\"]/li//a[@href=\"/jewelry\"]")
    private WebElement jeweleryCategory;

    @FindBy(how = How.XPATH, using = "//ul[@class=\"top-menu\"]/li//a[@href=\"/gift-cards\"]")
    private WebElement giftCardsCategory;

    public MainShopPage() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Main shop page ------");
    }

    @Step("Click on books category")
    public BooksPage clickOnBooksCategory() {
        AppiumDriverWaits.waitForPresentEl(menuButton);
        menuButton.click();
        AppiumDriverWaits.waitForPresentEl(booksCategory);
        booksCategory.click();
        return new BooksPage();
    }

    @Step("Click oj Login button")
    public LogInPage clickOnLogIn() {
        log.info("click on LogIn button");
        AppiumDriverWaits.waitForPresentEl(loginIcon);
        loginIcon.click();
        return new LogInPage();
    }

    @Step("Get email account text")
    public String getEmailAccountText() {
        AppiumDriverWaits.waitForPresentEl(accountLink);
        return accountLink.getText();
    }

    @Step("Click on log out button")
    public void clickOnLogOutButton() {
        AppiumDriverWaits.waitForPresentEl(logOutIcon);
        logOutIcon.click();
    }

    @Step("Click on register button")
    public RegisterPage clickOnRegisterButton() {
        AppiumDriverWaits.waitForPresentEl(registerIcon);
        registerIcon.click();
        return new RegisterPage();
    }
}
