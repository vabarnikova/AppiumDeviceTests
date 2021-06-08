import core.AppiumDriverSettings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainShopPage;
import utils.Utils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class MainShopPageTests extends AppiumDriverSettings {

    MainShopPage mainShopPage;
    String mainPageTitle = "Welcome to our store";
    String searchText = "Diamond";

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
    }

    @Test(groups = {"regression"})
    @Description("Verify the title matches the Main shop page")
    public void verifyMainPageTitle() {
        assertThat(Utils.getElementText(mainShopPage.mainPageTitle), equalToIgnoringCase(mainPageTitle));
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify search function")
    public void verifySearchFunction() {
        mainShopPage.searchInput.sendKeys(searchText);
        mainShopPage.searchButton.click();
        List<String> titles = mainShopPage.getItemsTitles();
        Assert.assertTrue(titles.stream().allMatch(item -> item.contains(searchText)));
    }

}
