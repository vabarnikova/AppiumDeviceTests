package pages;

import core.AppiumDriverSettings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class CompareList extends BasePage {

    @FindBy(how = How.CSS, using = "tr[class=\"product-name\"]")
    public WebElement productName;

    @FindBy(how = How.XPATH, using = "//tr[@class=\"product-price\"]/td[@class=\"a-center\"]")
    public WebElement productPrice;

    @FindBy(how = How.CSS, using = "a[class=\"clear-list\"]")
    public WebElement clearComparingList;

    private Logger log = Logger.getLogger(CompareList.class.getName());

    public CompareList() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Compare List page ------");
    }
}
