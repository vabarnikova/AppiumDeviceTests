package filter;

import core.AppiumDriverWaits;
import enums.ItemsFilter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.BasePage;
import utils.Utils;

public class FilterItems extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'price=-25')]")
    private WebElement priceUnder25;

    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'price=25-50')]")
    private WebElement priceFrom25To50;

    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'price=50')]")
    private WebElement priceOver50;

    @FindBy(how = How.CSS, using = "a[class=\"remove-price-range-filter\"]")
    private WebElement removeFilter;

    @Step("Filter element By {itemsFilter.getItemsFilter()}")
    public void filterElementsBy(ItemsFilter itemsFilter) {
        AppiumDriverWaits.waitForPresentEl(priceUnder25);
        switch (itemsFilter) {
            case UNDER_25:
                priceUnder25.click();
                break;
            case FROM_25_TO_50:
                priceFrom25To50.click();
                break;
            case OVER_50:
                priceOver50.click();
                break;
        }
    }

    @Step("Remove price filter")
    public void removePriceFilter() {
        if (Utils.isElementPresent(removeFilter)) {
            removeFilter.click();
        }
    }

}
