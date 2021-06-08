package utils;

import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class Utils {

    @Step("Get element's text")
    public static String getElementText(WebElement element) {
        AppiumDriverWaits.waitForPresentEl(element);
        return element.getText();
    }

    @Step("Verify is element present")
    public static boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
