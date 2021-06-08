package sort;

import core.AppiumDriverWaits;
import enums.BooksSorting;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.BasePage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortItems extends BasePage {

    //sort
    @FindBy(how = How.CSS, using = "select[name=\"products-orderby\"]")
    private WebElement productsOrderBy;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Name: A to Z')]")
    private WebElement nameByAsc;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Name: Z to A')]")
    private WebElement nameByDesc;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Price: Low to High')]")
    private WebElement priceByAsc;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Price: High to Low')]")
    private WebElement priceByDesc;

    @Step("Sort products By {booksSorting.getBooksSorting}")
    public void sortElementsBy(BooksSorting booksSorting) {
        AppiumDriverWaits.waitForPresentEl(productsOrderBy);
        productsOrderBy.click();
        AppiumDriverWaits.waitForPresentEl(nameByAsc);
        switch (booksSorting) {
            case NAME_FROM_A_TO_Z:
                nameByAsc.click();
                break;
            case NAME_FROM_Z_TO_A:
                nameByDesc.click();
                break;
            case PRICE_FROM_LOW_TO_HIGH:
                priceByAsc.click();
                break;
            case PRICE_FROM_HIGH_TO_LOW:
                priceByDesc.click();
                break;
        }
        AppiumDriverWaits.waitForPresentEl(items);
    }

    @Step("Get sorted list")
    public List<String> getSortedList(List<String> titles) {
        return titles
                .stream()
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList());
    }

    @Step("Get reverse sorted list")
    public List<String> getReverseSortedList(List<String> titles) {
        return titles
                .stream()
                .sorted(Comparator.comparing(String::toString).reversed())
                .collect(Collectors.toList());
    }

}
