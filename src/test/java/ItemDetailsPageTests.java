import categories.BooksPage;
import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import items.ItemDetails;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ItemDetailsPage;
import pages.MainShopPage;
import utils.Utils;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class ItemDetailsPageTests extends AppiumDriverSettings {

    MainShopPage mainShopPage;
    BooksPage booksPage;
    ItemDetailsPage itemDetailsPage;
    ItemDetails itemDetails;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        itemDetailsPage = new ItemDetailsPage();
        itemDetails = new ItemDetails();
        itemDetails.setItemTitle(booksPage.getAppropriateItemName(0));
        itemDetails.setPrice(booksPage.getAppropriateItemPrice(0));
        booksPage.items.get(0).click();
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify product details matches product details on the Categories")
    public void verifyItemDetailsData() {
        String itemName = Utils.getElementText(itemDetailsPage.productName);
        Double itemPrice = Double.parseDouble(Utils.getElementText(itemDetailsPage.productPrice));
        Assert.assertEquals(itemName, itemDetails.getItemTitle());
        Assert.assertEquals(itemPrice, itemDetails.getPrice());
    }
}
