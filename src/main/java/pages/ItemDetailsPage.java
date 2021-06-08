package pages;

import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ItemDetailsPage extends BasePage {

    @FindBy(how = How.CSS, using = "h1[itemprop=\"name\"]")
    public WebElement productName;

    @FindBy(how = How.CSS, using = "span[itemprop=\"price\"]")
    public WebElement productPrice;

    @FindBy(how = How.CSS, using = "input[value=\"Add to cart\"]")
    private WebElement addToCart;

    @FindBy(how = How.CSS, using = "input[id=\"add-to-wishlist-button-22\"]")
    private WebElement addToWishList;

    @FindBy(how = How.CSS, using = "input[value=\"Add to compare list\"]")
    private WebElement addToCompareList;

    @FindBy(how = How.CSS, using = "input[value=\"Email a friend\"]")
    private WebElement emailAFriend;

    @Step("Open email friend page")
    public Email getEmailFriendPage(){
        AppiumDriverWaits.waitForPresentEl(emailAFriend);
        emailAFriend.click();
        return new Email();
    }

    @Step("Open cart page")
    public CartPage getCartPage(){
        AppiumDriverWaits.waitForPresentEl(addToCart);
        addToCart.click();
        return new CartPage();
    }

    @Step("Open compare list page")
    public CompareList getCompareListPage(){
        AppiumDriverWaits.waitForPresentEl(addToCompareList);
        addToCompareList.click();
        return new CompareList();
    }

}
