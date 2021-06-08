package categories;

import core.AppiumDriverSettings;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.logging.Logger;

public class BooksPage extends BasePage {

    private Logger log = Logger.getLogger(BooksPage.class.getName());

    public BooksPage() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Books category ------");
    }
}
