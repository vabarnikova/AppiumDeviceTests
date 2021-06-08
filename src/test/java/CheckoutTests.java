import categories.BooksPage;
import core.AppiumDriverScroll;
import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import filter.ShippingSettings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ItemDetailsPage;
import pages.LogInPage;
import pages.MainShopPage;
import utils.Utils;

import static enums.PaymentMethod.*;
import static enums.ShippingMethod.NEXT_DAY_AIR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@Epic("UI Tests")
@Feature("Testing demowebshop application")
public class CheckoutTests extends AppiumDriverSettings {

    String errorApplyingMessage = "The coupon code you entered couldn't be applied to your order";
    String incorrectCoupon = "123456";
    String incorrectGiftCard = "987654";
    String zipCode = "AD500";

    String cardNumber = "4111 1111 4555 1142";
    String cardHolderName = "Vitaliy Astahov";
    String cardCode = "737";
    String correctEmail = "v.abarnikova2017@gmail.com";
    String correctPassword = "1234567";

    String shippingFirstName = "Vladimir";
    String shippingLastName = "Ulianov";
    String shippingEmail = "ulianov2021@gmail.com";
    String shippingCity = "Miami";
    String shippingAddress = "Gray, FL";
    String shippingCode = "74354";
    String shippingPhone = "561-364-5318";


    String billingFirstName = "Alex";
    String billingLastName = "Astahov";
    String billingEmail = "v.abarnikova2017@gmail.com";
    String billingCity = "Orlando";
    String billingAddress = "Apopka, FL";
    String billingCode = "32703";
    String billingPhone = "386-878-2449";
    String billingMethodPurchaseOrder = "Purchase Order";
    String billingMethodCashOnDelivery = "Cash On Delivery (COD)";
    String billingMethodCheckMoneyOrder = "Check / Money Order";

    String purchaseNumber = "4567890";


    MainShopPage mainShopPage;
    BooksPage booksPage;
    ItemDetailsPage itemDetailsPage;
    CartPage cartPage;
    ShippingSettings shippingSettings;
    LogInPage logInPage;

    @BeforeClass(alwaysRun = true)
    public void Initial() {
        mainShopPage = new MainShopPage();
        logInPage = mainShopPage.clickOnLogIn();
        logInPage.setKeysToSignIn(correctEmail, correctPassword);
        mainShopPage = logInPage.submitLigInButton();
    }

    @BeforeMethod(alwaysRun = true)
    public void preconditions() {
        booksPage = mainShopPage.clickOnBooksCategory();
        AppiumDriverWaits.waitForPresentEl(booksPage.items);
        booksPage.items.get(0).click();
        itemDetailsPage = new ItemDetailsPage();
        cartPage = itemDetailsPage.getCartPage();
        AppiumDriverScroll.scrollToElement(driver, cartPage.shoppingCart);
        AppiumDriverWaits.waitForPresentEl(cartPage.shoppingCart);
        cartPage.shoppingCart.click();
    }

    @Test(groups = {"regression"})
    @Description("Verify applying empty coupon to get discount")
    public void verifyApplyingEmptyCoupon() {
        AppiumDriverWaits.waitForPresentEl(cartPage.applyCoupon);
        cartPage.applyCoupon.click();
        AppiumDriverWaits.waitForPresentEl(cartPage.errorApplyingMessage);
        String errorCouponMessage = Utils.getElementText(cartPage.errorApplyingMessage).trim();
        Assert.assertEquals(errorApplyingMessage, errorCouponMessage);
    }

    @Test(groups = {"regression"})
    @Description("Verify applying empty gift card to get discount")
    public void verifyApplyingEmptyGiftCard() {
        AppiumDriverWaits.waitForPresentEl(cartPage.addGiftCard);
        cartPage.addGiftCard.click();
        AppiumDriverWaits.waitForPresentEl(cartPage.errorApplyingMessage);
        String errorGiftCardMessage = Utils.getElementText(cartPage.errorApplyingMessage).trim();
        Assert.assertEquals(errorApplyingMessage, errorGiftCardMessage);
    }

    @Test(groups = {"regression"})
    @Description("Verify applying incorrect coupon to get discount")
    public void verifyApplyingIncorrectCoupon() {
        AppiumDriverWaits.waitForPresentEl(cartPage.addGiftCard);
        cartPage.applyCoupon.sendKeys(incorrectCoupon, Keys.ENTER);
        AppiumDriverWaits.waitForPresentEl(cartPage.errorApplyingMessage);
        String errorCouponMessage = Utils.getElementText(cartPage.errorApplyingMessage).trim();
        Assert.assertEquals(errorApplyingMessage, errorCouponMessage);
    }

    @Test(groups = {"regression"})
    @Description("Verify applying incorrect gift card to get discount")
    public void verifyApplyingIncorrectGiftCard() {
        AppiumDriverWaits.waitForPresentEl(cartPage.addGiftCard);
        cartPage.applyCoupon.sendKeys(incorrectGiftCard, Keys.ENTER);
        AppiumDriverWaits.waitForPresentEl(cartPage.errorApplyingMessage);
        String errorCouponMessage = Utils.getElementText(cartPage.errorApplyingMessage).trim();
        Assert.assertEquals(errorApplyingMessage, errorCouponMessage);
    }

    @Test(groups = {"regression"})
    @Description("Verify shipping estimation")
    public void verifyShippingEstimation() {
        //choose country
        AppiumDriverWaits.waitForPresentEl(cartPage.selectCountry);
        cartPage.selectCountry.click();
        cartPage.chooseAppropriateCountry.click();
        //choose zip code
        cartPage.zipCode.clear();
        cartPage.zipCode.sendKeys(zipCode);
        cartPage.estimateShipping.click();
        //estimation results
        AppiumDriverWaits.waitForPresentEl(cartPage.groundShipping);
        AppiumDriverScroll.scrollToElement(driver, cartPage.inStorePickupShipping);
        cartPage.groundShipping.isDisplayed();
        cartPage.nextDayAirShipping.isDisplayed();
        cartPage.secondDayAirShipping.isDisplayed();
        cartPage.inStorePickupShipping.isDisplayed();
    }

    @Test(groups = {"smoke", "regression"})
    @Description("Verify checkout method using credit card as payment method")
    public void verifyCheckoutUsingCreditCardPaymentMethod() {
        AppiumDriverScroll.scrollToElement(driver, cartPage.shoppingCart);
        AppiumDriverWaits.waitForPresentEl(cartPage.shoppingCart);
        cartPage.setQuantityInput(0, "1");
        AppiumDriverWaits.waitForPresentEl(cartPage.termsAgree);
        AppiumDriverScroll.scrollToElement(driver, cartPage.termsAgree);
        cartPage.termsAgree.click();
        cartPage.checkout.click();

        //billing address
        AppiumDriverWaits.waitForPresentEl(cartPage.billingAddressContinue);
        cartPage.selectBillingAddress.click();
        cartPage.selectNewAddress.click();
        cartPage.setBillingAddress(billingFirstName, billingLastName, billingEmail, billingCity, billingAddress, billingCode, billingPhone);
        cartPage.billingAddressContinue.click();

        //shipping address
        AppiumDriverWaits.waitForPresentEl(cartPage.pickup);
        cartPage.selectShippingAddress.click();
        AppiumDriverWaits.waitForPresentEl(cartPage.selectNewShippingAddress);
        cartPage.selectNewShippingAddress.click();
        cartPage.setShippingAddress(shippingFirstName, shippingLastName, shippingEmail, shippingCity, shippingAddress, shippingCode, shippingPhone);
        cartPage.shippingAddressContinue.click();

        shippingSettings = new ShippingSettings();
        AppiumDriverWaits.waitForPresentEl(cartPage.nextDayShippingMethod);
        shippingSettings.chooseShippingMethod(NEXT_DAY_AIR);
        shippingSettings.shippingMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.creditCard);
        shippingSettings.choosePaymentMethod(CREDIT_CARD);
        shippingSettings.paymentMethodContinue.click();

        //paymentInfo
        AppiumDriverWaits.waitForPresentEl(cartPage.cardHolderName);
        cartPage.setCardData(cardHolderName, cardNumber, cardCode);
        cartPage.paymentInfoContinue.click();

        //confirm order
        //assert billing address
        assertThat(Utils.getElementText(cartPage.billingName), equalToIgnoringCase(billingFirstName + " " + billingLastName));
        assertThat(Utils.getElementText(cartPage.billingEmail).split(" ")[1], equalToIgnoringCase(billingEmail));
        assertThat(Utils.getElementText(cartPage.billingPhone).split(" ")[1], equalToIgnoringCase(billingPhone));
        assertThat(Utils.getElementText(cartPage.billingAddress), equalToIgnoringCase(billingAddress));
        assertThat(Utils.getElementText(cartPage.billingZip).split(" ")[3], equalToIgnoringCase(billingCode));
        //assert shipping address
        assertThat(Utils.getElementText(cartPage.shippingName), equalToIgnoringCase(shippingFirstName + " " + shippingLastName));
        assertThat(Utils.getElementText(cartPage.shippingEmail).split(" ")[1], equalToIgnoringCase(shippingEmail));
        assertThat(Utils.getElementText(cartPage.shippingPhone).split(" ")[1], equalToIgnoringCase(shippingPhone));
        assertThat(Utils.getElementText(cartPage.shippingAddress), equalToIgnoringCase(shippingAddress));
        int zipLength = Utils.getElementText(cartPage.shippingZip).split(" ").length;
        assertThat(Utils.getElementText(cartPage.shippingZip).split(" ")[zipLength - 1], equalToIgnoringCase(shippingCode));

        double price = Double.parseDouble(Utils.getElementText(cartPage.totalPriceInfo));
        Assert.assertEquals(10, price);

        AppiumDriverWaits.waitForPresentEl(cartPage.confirmCheckout);
        cartPage.confirmCheckout.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.orderComplete);
        cartPage.orderComplete.click();
    }

    @Test(groups = {"regression"})
    @Description("Verify checkout method using purchase order as payment method")
    public void verifyCheckoutUsingPurchaseOrderPaymentMethod() {
        AppiumDriverScroll.scrollToElement(driver, cartPage.shoppingCart);
        cartPage.setQuantityInput(0, "1");
        AppiumDriverWaits.waitForPresentEl(cartPage.termsAgree);
        AppiumDriverScroll.scrollToElement(driver, cartPage.termsAgree);
        cartPage.termsAgree.click();
        cartPage.checkout.click();

        //billing address
        AppiumDriverWaits.waitForPresentEl(cartPage.billingAddressContinue);
        cartPage.selectBillingAddress.click();
        cartPage.selectNewAddress.click();
        cartPage.setBillingAddress(billingFirstName, billingLastName, billingEmail, billingCity, billingAddress, billingCode, billingPhone);
        cartPage.billingAddressContinue.click();

        //shipping address
        AppiumDriverWaits.waitForPresentEl(cartPage.pickup);
        cartPage.selectShippingAddress.click();
        AppiumDriverWaits.waitForPresentEl(cartPage.selectNewShippingAddress);
        cartPage.selectNewShippingAddress.click();
        cartPage.setShippingAddress(shippingFirstName, shippingLastName, shippingEmail, shippingCity, shippingAddress, shippingCode, shippingPhone);
        cartPage.shippingAddressContinue.click();

        shippingSettings = new ShippingSettings();
        AppiumDriverWaits.waitForPresentEl(cartPage.nextDayShippingMethod);
        shippingSettings.chooseShippingMethod(NEXT_DAY_AIR);
        shippingSettings.shippingMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.creditCard);
        shippingSettings.choosePaymentMethod(PURCHASE_ORDER);
        shippingSettings.paymentMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.purchaseNumber);
        cartPage.purchaseNumber.sendKeys(purchaseNumber);
        cartPage.paymentInfoContinue.click();

        assertThat(Utils.getElementText(cartPage.billingName), equalToIgnoringCase(billingFirstName + " " + billingLastName));
        assertThat(Utils.getElementText(cartPage.billingPaymentMethod).trim(), equalToIgnoringCase(billingMethodPurchaseOrder));

        assertThat(Utils.getElementText(cartPage.shippingName), equalToIgnoringCase(shippingFirstName + " " + shippingLastName));

        AppiumDriverWaits.waitForPresentEl(cartPage.confirmCheckout);
        cartPage.confirmCheckout.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.orderComplete);
        cartPage.orderComplete.click();
    }

    @Test(groups = {"regression"})
    @Description("Verify checkout method using cash on delivery as payment method")
    public void verifyCheckoutUsingCashOnDeliveryPaymentMethod() {
        AppiumDriverScroll.scrollToElement(driver, cartPage.shoppingCart);
        cartPage.setQuantityInput(0, "1");
        AppiumDriverWaits.waitForPresentEl(cartPage.termsAgree);
        AppiumDriverScroll.scrollToElement(driver, cartPage.termsAgree);
        cartPage.termsAgree.click();
        cartPage.checkout.click();

        //billing address
        AppiumDriverWaits.waitForPresentEl(cartPage.billingAddressContinue);
        cartPage.selectBillingAddress.click();
        cartPage.selectNewAddress.click();
        cartPage.setBillingAddress(billingFirstName, billingLastName, billingEmail, billingCity, billingAddress, billingCode, billingPhone);
        cartPage.billingAddressContinue.click();

        //shipping address
        AppiumDriverWaits.waitForPresentEl(cartPage.pickup);
        cartPage.selectShippingAddress.click();
        AppiumDriverWaits.waitForPresentEl(cartPage.selectNewShippingAddress);
        cartPage.selectNewShippingAddress.click();
        cartPage.setShippingAddress(shippingFirstName, shippingLastName, shippingEmail, shippingCity, shippingAddress, shippingCode, shippingPhone);
        cartPage.shippingAddressContinue.click();

        shippingSettings = new ShippingSettings();
        AppiumDriverWaits.waitForPresentEl(cartPage.nextDayShippingMethod);
        shippingSettings.chooseShippingMethod(NEXT_DAY_AIR);
        shippingSettings.shippingMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.cashOnDelivery);
        shippingSettings.choosePaymentMethod(CASH_ON_DELIVERY);
        shippingSettings.paymentMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.paymentInfoContinue);
        cartPage.paymentInfoContinue.click();

        assertThat(Utils.getElementText(cartPage.billingName), equalToIgnoringCase(billingFirstName + " " + billingLastName));
        assertThat(Utils.getElementText(cartPage.billingPaymentMethod).trim(), equalToIgnoringCase(billingMethodCashOnDelivery));

        assertThat(Utils.getElementText(cartPage.shippingName), equalToIgnoringCase(shippingFirstName + " " + shippingLastName));

        AppiumDriverWaits.waitForPresentEl(cartPage.confirmCheckout);
        cartPage.confirmCheckout.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.orderComplete);
        cartPage.orderComplete.click();
    }

    @Test(groups = {"regression"})
    @Description("Verify checkout method using check money order as payment method")
    public void verifyCheckoutUsingCheckMoneyOrderPaymentMethod() {
        shippingSettings = new ShippingSettings();
        AppiumDriverScroll.scrollToElement(driver, cartPage.shoppingCart);
        cartPage.setQuantityInput(0, "1");
        AppiumDriverWaits.waitForPresentEl(cartPage.termsAgree);
        AppiumDriverScroll.scrollToElement(driver, cartPage.termsAgree);
        cartPage.termsAgree.click();
        cartPage.checkout.click();

        //billing address
        AppiumDriverWaits.waitForPresentEl(cartPage.billingAddressContinue);
        cartPage.selectBillingAddress.click();
        cartPage.selectNewAddress.click();
        cartPage.setBillingAddress(billingFirstName, billingLastName, billingEmail, billingCity, billingAddress, billingCode, billingPhone);
        cartPage.billingAddressContinue.click();

        //shipping address
        AppiumDriverWaits.waitForPresentEl(cartPage.pickup);
        cartPage.selectShippingAddress.click();
        AppiumDriverWaits.waitForPresentEl(cartPage.selectNewShippingAddress);
        cartPage.selectNewShippingAddress.click();
        cartPage.setShippingAddress(shippingFirstName, shippingLastName, shippingEmail, shippingCity, shippingAddress, shippingCode, shippingPhone);
        cartPage.shippingAddressContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.nextDayShippingMethod);
        shippingSettings.chooseShippingMethod(NEXT_DAY_AIR);
        shippingSettings.shippingMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.checkMoneyOrders);
        shippingSettings.choosePaymentMethod(CHECK_MONEY_ORDER);
        shippingSettings.paymentMethodContinue.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.paymentInfoContinue);
        cartPage.paymentInfoContinue.click();

        assertThat(Utils.getElementText(cartPage.billingName), equalToIgnoringCase(billingFirstName + " " + billingLastName));
        assertThat(Utils.getElementText(cartPage.billingPaymentMethod).trim(), equalToIgnoringCase(billingMethodCheckMoneyOrder));

        assertThat(Utils.getElementText(cartPage.shippingName), equalToIgnoringCase(shippingFirstName + " " + shippingLastName));

        AppiumDriverWaits.waitForPresentEl(cartPage.confirmCheckout);
        cartPage.confirmCheckout.click();

        AppiumDriverWaits.waitForPresentEl(cartPage.orderComplete);
        cartPage.orderComplete.click();
    }
}
