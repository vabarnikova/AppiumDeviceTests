import categories.BooksPage;
import core.AppiumDriverScroll;
import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ItemDetailsPage;
import pages.LogInPage;
import pages.MainShopPage;
import utils.Utils;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class ShoppingCartTests extends AppiumDriverSettings {

    String title = "Shopping cart";
    String correctEmail = "v.abarnikova2017@gmail.com";
    String correctPassword = "1234567";
    MainShopPage mainShopPage;
    BooksPage booksPage;
    ItemDetailsPage itemDetailsPage;
    CartPage cartPage;
    String itemName;
    double itemPrice;
    LogInPage logInPage;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        logInPage = mainShopPage.clickOnLogIn();
        logInPage.setKeysToSignIn(correctEmail, correctPassword);
        mainShopPage = logInPage.submitLigInButton();
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        booksPage.items.get(0).click();
        itemDetailsPage = new ItemDetailsPage();
        itemName = Utils.getElementText(itemDetailsPage.productName).trim();
        itemPrice = Double.parseDouble(Utils.getElementText(itemDetailsPage.productPrice).trim());
        cartPage = itemDetailsPage.getCartPage();
        AppiumDriverScroll.scrollToElement(driver, cartPage.shoppingCart);
        AppiumDriverWaits.waitForPresentEl(cartPage.shoppingCart);
        cartPage.shoppingCart.click();
    }

    @Test(groups = {"regression"})
    @Description("Verify the title matches the Shopping cart page")
    public void verifyShoppingCartPageTitle() {
        String pageTitle = cartPage.getPageTitle();
        assertThat(pageTitle, equalToIgnoringCase(title));
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify product data on the Shopping cart page matches product data on the Product Details page")
    public void verifyShoppingCartPageData() {
        String productName = cartPage.getProductsTitle(0);
        double productPrice = cartPage.getPricePerUnitOfItem(0);
        assertThat(itemName, equalToIgnoringCase(productName));
        Assert.assertEquals(itemPrice, productPrice);
    }

    @Test(groups = {"regression"})
    @Description("Verify changing number of products to checkout")
    public void verifyChangingNumberOfItems() throws InterruptedException {
        //change product quantity
        sleep(1000);
        Double priceForOneProduct = cartPage.getPricePerUnitOfItem(0);
        cartPage.setQuantityInput(0, "3");
        sleep(1000);
        Double priceForThreeProduct = cartPage.getTotalPriceOfItem(0);
        Double priceForThreeProductSecondSequence = priceForOneProduct * 3.0;
        Assert.assertEquals(priceForThreeProduct, priceForThreeProductSecondSequence);
    }

    @AfterClass(alwaysRun = true)
    public void logout() {
        mainShopPage.clickOnLogOutButton();
    }
}
