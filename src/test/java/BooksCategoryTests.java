import categories.BooksPage;
import core.AppiumDriverSettings;
import filter.FilterItems;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainShopPage;
import sort.SortItems;

import java.util.Collections;
import java.util.List;

import static enums.BooksSorting.*;
import static enums.ItemsFilter.*;
import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class BooksCategoryTests extends AppiumDriverSettings {

    MainShopPage mainShopPage;
    BooksPage booksPage;
    SortItems sortItems;
    FilterItems filterItems;
    String booksTitle = "Books";


    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        booksPage = mainShopPage.clickOnBooksCategory();
    }

    @Test(groups = {"regression"})
    @Description("Verify the title matches the Books page")
    public void verifyBooksPageTitle() {
        String pageTitle = booksPage.getPageTitle();
        assertThat(pageTitle, equalToIgnoringCase(booksTitle));
    }

    @Test(groups = {"regression"})
    @Description("Verify sorting books by Name from A to Z")
    public void verifySortingBooksByNameAToZ() {
        sortItems = new SortItems();
        sortItems.sortElementsBy(NAME_FROM_A_TO_Z);
        List<String> itemsTitlesFirstSequence = booksPage.getItemsTitles();
        List<String> itemsTitlesSecondSequence = sortItems.getSortedList(itemsTitlesFirstSequence);
        Assert.assertEquals(itemsTitlesFirstSequence, itemsTitlesSecondSequence, "List of books isn't sorted by \"Name: A to Z\"");
    }

    @Test(groups = {"smoke","regression"})
    @Description("Verify sorting books by Name from Z to A")
    public void verifySortingBooksByNameZToA() {
        sortItems = new SortItems();
        sortItems.sortElementsBy(NAME_FROM_Z_TO_A);
        List<String> itemsTitlesFirstSequence = booksPage.getItemsTitles();
        List<String> itemsTitlesSecondSequence = sortItems.getReverseSortedList(itemsTitlesFirstSequence);
        Assert.assertEquals(itemsTitlesFirstSequence, itemsTitlesSecondSequence, "List of books isn't sorted by \"Name: Z to A\"");
    }

    @Test(groups = {"regression"})
    @Description("Verify sorting books by Price from Low to High")
    public void verifySortingBooksByPriceFromLowToHigh() {
        sortItems = new SortItems();
        sortItems.sortElementsBy(PRICE_FROM_LOW_TO_HIGH);
        List<Double> itemsTitlesFirstSequence = booksPage.getItemsPrices();
        List<Double> itemsTitlesSecondSequence = itemsTitlesFirstSequence;
        Collections.sort(itemsTitlesFirstSequence);
        Assert.assertEquals(itemsTitlesFirstSequence, itemsTitlesSecondSequence, "List of books isn't sorted by \"Price: Low to High\"");
    }

    @Test(groups = {"regression"})
    @Description("Verify sorting books by Price from High to Low")
    public void verifySortingBooksByPriceFromHighToLow() {
        sortItems = new SortItems();
        sortItems.sortElementsBy(PRICE_FROM_HIGH_TO_LOW);
        List<Double> itemsTitlesFirstSequence = booksPage.getItemsPrices();
        List<Double> itemsTitlesSecondSequence = itemsTitlesFirstSequence;
        Collections.sort(itemsTitlesFirstSequence);
        Assert.assertEquals(itemsTitlesFirstSequence, itemsTitlesSecondSequence, "List of books isn't sorted by \"Price: Low to High\"");
    }

    @Test(groups = {"regression"})
    @Description("Verify sorting books by Price Under 25")
    public void verifyFilteringBooksByPriceUnder25() throws InterruptedException {
        filterItems = new FilterItems();
        filterItems.removePriceFilter();
        sleep(2000);
        filterItems.filterElementsBy(UNDER_25);
        List<Double> itemsTitlesFirstSequence = booksPage.getItemsPrices();
        Assert.assertTrue(itemsTitlesFirstSequence.stream().allMatch(i -> i < 25));

    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify sorting books by Price From 25 To 50")
    public void verifyFilteringBooksByPriceFrom25To50() throws InterruptedException {
        filterItems = new FilterItems();
        filterItems.removePriceFilter();
        sleep(2000);
        filterItems.filterElementsBy(FROM_25_TO_50);
        List<Double> itemsTitlesFirstSequence = booksPage.getItemsPrices();
        if (itemsTitlesFirstSequence.size() > 0) {
            Assert.assertTrue(itemsTitlesFirstSequence.stream().allMatch(i -> (i > 25 && i < 50)));
        }
        filterItems.removePriceFilter();
    }

    @Test(groups = {"regression"})
    @Description("Verify sorting books by Price Over 50")
    public void verifyFilteringBooksByPriceOver50() throws InterruptedException {
        filterItems = new FilterItems();
        filterItems.removePriceFilter();
        sleep(2000);
        filterItems.filterElementsBy(OVER_50);
        List<Double> itemsTitlesFirstSequence = booksPage.getItemsPrices();
        Assert.assertTrue(itemsTitlesFirstSequence.stream().allMatch(i -> (i > 50)));
    }
}
