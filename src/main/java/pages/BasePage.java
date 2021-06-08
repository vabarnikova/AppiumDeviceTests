package pages;

import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BasePage {

    private final By price = By.cssSelector(".price.actual-price");
    private final By title = By.cssSelector(".product-title");

    // header
    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[1]")
    protected WebElement accountLink;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[2]")
    protected WebElement logOut;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[3]")
    public WebElement shoppingCart;

    @FindBy(how = How.XPATH, using = "//div[@class=\"header-links\"]/ul/li[4]")
    protected WebElement wishList;
    //title
    @FindBy(how = How.CSS, using = "div[class=\"page-title\"]")
    protected WebElement pageTitle;

    //item details
    @FindBy(how = How.CSS, using = "div[class=\"item-box\"]")
    public  List<WebElement> items;

    private Logger log = Logger.getLogger(MainShopPage.class.getName());

    public BasePage() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------   Open Main shop Page ------");
    }

    @Step("Click on Log out button")
    public void clickOnLogOutButton() {
        AppiumDriverWaits.waitForPresentEl(logOut);
        logOut.click();
    }

    @Step("Get page title")
    public String getPageTitle() {
        AppiumDriverWaits.waitForPresentEl(pageTitle);
        return pageTitle.getText();
    }

    @Step("Get items titles")
    public List<String> getItemsTitles() {
        List<String> titles = new ArrayList<String>();
        if (items.size() > 0) {
            for (WebElement product : items) {
                titles.add(product.findElement(title).getText());
            }
        }
        return titles;
    }

    @Step("Get items titles")
    public List<Double> getItemsPrices() {
        List<Double> prices = new ArrayList<Double>();
        if (items.size() > 0) {
            for (WebElement product : items) {
                prices.add(Double.parseDouble(product.findElement(price).getText()));
            }
        }
        return prices;
    }

    @Step("Get appropriate item name")
    public String getAppropriateItemName(int index){
       return items.get(index).findElement(title).getText();
    }

    @Step("Get appropriate item price")
    public Double getAppropriateItemPrice(int index){
        return Double.parseDouble(items.get(index).findElement(price).getText());
    }

}
