import categories.BooksPage;
import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CompareList;
import pages.ItemDetailsPage;
import pages.MainShopPage;
import utils.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class CompareListPageTests extends AppiumDriverSettings {

    String title = "Compare products";


    MainShopPage mainShopPage;
    BooksPage booksPage;
    ItemDetailsPage itemDetailsPage;
    CompareList compareList;
    String itemName;
    String itemPrice;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        booksPage.items.get(0).click();
        itemDetailsPage = new ItemDetailsPage();
        itemName = Utils.getElementText(itemDetailsPage.productName).trim();
        itemPrice = Utils.getElementText(itemDetailsPage.productPrice).trim();
        compareList = itemDetailsPage.getCompareListPage();
    }

    @Test(groups = {"regression"})
    @Description("Verify the title matches the Compare list page")
    public void verifyCompareListTitle() {
        String pageTitle = compareList.getPageTitle();
        assertThat(pageTitle, equalToIgnoringCase(title));
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify product data on the Categories page matches product data on the Compare list page")
    public void verifyCompareListProductData() {
        Assert.assertEquals(Utils.getElementText(compareList.productName).trim(), itemName);
        Assert.assertEquals(Utils.getElementText(compareList.productPrice).trim(), itemPrice);
    }

    @AfterClass(alwaysRun = true)
    public void clearList() {
        compareList.clearComparingList.click();
    }
}
