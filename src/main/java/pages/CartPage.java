package pages;

import core.AppiumDriverSettings;
import core.AppiumDriverWaits;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;
import java.util.logging.Logger;

public class CartPage extends BasePage {

    private final By name = By.cssSelector(".product-name");
    private final By pricePerUnitOfItem = By.cssSelector(".product-unit-price");
    private final By totalPrice = By.cssSelector(".product-subtotal");
    private final By quantityInput = By.cssSelector(".qty-input");

    @FindBy(how = How.CSS, using = "input[value=\"Apply coupon\"]")
    public WebElement applyCoupon;

    @FindBy(how = How.CSS, using = "input[name=\"applygiftcardcouponcode\"]")
    public WebElement addGiftCard;

    @FindBy(how = How.CSS, using = ".message")
    public WebElement errorApplyingMessage;

    //shipping estimation
    @FindBy(how = How.CSS, using = "select[name=\"CountryId\"]")
    public WebElement selectCountry;

    @FindBy(how = How.XPATH, using = "//option[text() = \"Andorra\"]")
    public WebElement chooseAppropriateCountry;

    @FindBy(how = How.CSS, using = "input[name=\"ZipPostalCode\"]")
    public WebElement zipCode;

    @FindBy(how = How.CSS, using = "input[value=\"Estimate shipping\"]")
    public WebElement estimateShipping;

    //estimation shipping results
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Ground')]")
    public WebElement groundShipping;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Next Day Air')]")
    public WebElement nextDayAirShipping;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'2nd Day Air')]")
    public WebElement secondDayAirShipping;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'In-Store Pickup')]")
    public WebElement inStorePickupShipping;

    //checkout
    @FindBy(how = How.CSS, using = "input[name=\"termsofservice\"]")
    public WebElement termsAgree;

    @FindBy(how = How.CSS, using = "button[name=\"checkout\"]")
    public WebElement checkout;

    @FindBy(how = How.CSS, using = "select[name=\"billing_address_id\"]")
    public WebElement selectBillingAddress;

    @FindBy(how = How.XPATH, using = "//*[text() = \"New Address\"]")
    public WebElement selectNewAddress;

    //billing address
    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.FirstName\"]")
    public WebElement billingAddressFirstName;

    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.LastName\"]")
    public WebElement billingAddressLastName;

    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.Email\"]")
    public WebElement billingAddressEmail;

    @FindBy(how = How.CSS, using = "select[name=\"BillingNewAddress.CountryId\"]")
    public WebElement chooseCountry;

    @FindBy(how = How.XPATH, using = "//option[text() = \"United States\"]")
    public WebElement selectAppropriateCountry;

    @FindBy(how = How.CSS, using = "select[name=\"BillingNewAddress.StateProvinceId\"]")
    public WebElement chooseState;

    @FindBy(how = How.XPATH, using = "//option[text() = \"Florida\"]")
    public WebElement selectAppropriateState;

    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.City\"]")
    public WebElement addressCity;

    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.Address1\"]")
    public WebElement addressFirst;

    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.ZipPostalCode\"]")
    public WebElement addressZipCode;

    @FindBy(how = How.CSS, using = "input[name=\"BillingNewAddress.PhoneNumber\"]")
    public WebElement addressPhoneNumber;

    @FindBy(how = How.XPATH, using = "//div[@id=\"billing-buttons-container\"]/input[@value=\"Continue\"]")
    public WebElement billingAddressContinue;

    //shipping address
    @FindBy(how = How.CSS, using = "select[name=\"shipping_address_id\"]")
    public WebElement selectShippingAddress;

    @FindBy(how = How.XPATH, using = "//select[@name=\"shipping_address_id\"]//*[contains(text(), \"New Address\")]")
    public WebElement selectNewShippingAddress;

    @FindBy(how = How.CSS, using = "input[id=\"ShippingNewAddress_FirstName\"]")
    public WebElement addressFirstName;

    @FindBy(how = How.CSS, using = "input[id=\"ShippingNewAddress_LastName\"]")
    public WebElement addressLastName;

    @FindBy(how = How.CSS, using = "input[name=\"ShippingNewAddress.Email\"]")
    public WebElement addressEmail;

    @FindBy(how = How.XPATH, using = "//select[@id=\"ShippingNewAddress_CountryId\"]")
    public WebElement chooseShippingCountry;

    @FindBy(how = How.XPATH, using = "//select[@id=\"ShippingNewAddress_CountryId\"]/*[text() = \"United States\"]")
    public WebElement selectShippingCountry;

    @FindBy(how = How.CSS, using = "input[name=\"ShippingNewAddress.City\"]")
    public WebElement shippingAddressCity;

    @FindBy(how = How.CSS, using = "input[name=\"ShippingNewAddress.Address1\"]")
    public WebElement shippingAddressFirst;

    @FindBy(how = How.CSS, using = "input[name=\"ShippingNewAddress.ZipPostalCode\"]")
    public WebElement shippingZipCode;

    @FindBy(how = How.CSS, using = "input[name=\"ShippingNewAddress.PhoneNumber\"]")
    public WebElement shippingPhoneNumber;

    @FindBy(how = How.XPATH, using = "//div[@id=\"shipping-buttons-container\"]/input[@value=\"Continue\"]")
    public WebElement shippingAddressContinue;

    @FindBy(how = How.XPATH, using = "//input[@id=\"PickUpInStore\"]")
    public WebElement pickup;

    @FindBy(how = How.XPATH, using = "//input[contains(@class,\"shipping-method-next-step\")]")
    public WebElement shippingMethodContinue;

    //payment method
    @FindBy(how = How.CSS, using = "input[value=\"Payments.CashOnDelivery\"]")
    public WebElement cashOnDelivery;

    @FindBy(how = How.CSS, using = "input[value=\"Payments.CheckMoneyOrder\"]")
    public WebElement checkMoneyOrders;

    @FindBy(how = How.CSS, using = "input[id=\"paymentmethod_2\"]")
    public WebElement creditCard;

    @FindBy(how = How.CSS, using = "input[value=\"Payments.PurchaseOrder\"]")
    public WebElement purchaseOrder;

    @FindBy(how = How.XPATH, using = "//input[contains(@class, \"payment-method-next-step\")]")
    public WebElement paymentMethodContinue;

    //payment information
    @FindBy(how = How.CSS, using = "input[name=\"CardholderName\"]")
    public WebElement cardHolderName;

    @FindBy(how = How.CSS, using = "input[name=\"CardNumber\"]")
    public WebElement cardNumber;

    @FindBy(how = How.CSS, using = "select[name=\"ExpireMonth\"]")
    public WebElement expireMonth;

    @FindBy(how = How.XPATH, using = "//option[text() = \"03\"]")
    public WebElement appropriateMonth;

    @FindBy(how = How.CSS, using = "select[name=\"ExpireYear\"]")
    public WebElement expireYear;

    @FindBy(how = How.XPATH, using = "//option[text() = \"2030\"]")
    public WebElement appropriateYear;

    @FindBy(how = How.CSS, using = "input[name=\"CardCode\"]")
    public WebElement cardCode;

    @FindBy(how = How.XPATH, using = "//input[contains(@class, \"payment-info-next-step\")]")
    public WebElement paymentInfoContinue;

    //purchase number
    @FindBy(how = How.CSS, using = "input[name=\"PurchaseOrderNumber\"]")
    public WebElement purchaseNumber;

    //confirm order
    @FindBy(how = How.XPATH, using = "//span[@class=\"nobr\" and contains(text(), \" Total:\")]//parent::td//following-sibling::td/span")
    public WebElement totalPriceInfo;

    @FindBy(how = How.CSS, using = "input[value=\"Confirm\"]")
    public WebElement confirmCheckout;

    @FindBy(how = How.XPATH, using = "//input[contains(@class, \"order-completed-continue\")]")
    public WebElement orderComplete;

    @FindBy(how = How.CSS, using = ".shipping-info .name")
    public WebElement shippingName;

    @FindBy(how = How.CSS, using = ".shipping-info .email")
    public WebElement shippingEmail;

    @FindBy(how = How.CSS, using = ".shipping-info .phone")
    public WebElement shippingPhone;

    @FindBy(how = How.CSS, using = ".shipping-info .address1")
    public WebElement shippingAddress;

    @FindBy(how = How.CSS, using = ".shipping-info .city-state-zip")
    public WebElement shippingZip;

    @FindBy(how = How.CSS, using = ".shipping-info .country")
    public WebElement shippingCountry;

    @FindBy(how = How.CSS, using = ".billing-info .name")
    public WebElement billingName;

    @FindBy(how = How.CSS, using = ".billing-info .email")
    public WebElement billingEmail;

    @FindBy(how = How.CSS, using = ".billing-info .phone")
    public WebElement billingPhone;

    @FindBy(how = How.CSS, using = ".billing-info .address1")
    public WebElement billingAddress;

    @FindBy(how = How.CSS, using = ".billing-info .city-state-zip")
    public WebElement billingZip;

    @FindBy(how = How.CSS, using = ".billing-info .payment-method")
    public WebElement billingPaymentMethod;

    @FindBy(how = How.CSS, using = ".billing-info .country")
    public WebElement billingCountry;

    //shipping method
    @FindBy(how = How.CSS, using = "input[value=\"Ground___Shipping.FixedRate\"]")
    protected WebElement groundShippingMethod;

    @FindBy(how = How.CSS, using = "input[value=\"Next Day Air___Shipping.FixedRate\"]")
    public WebElement nextDayShippingMethod;

    @FindBy(how = How.CSS, using = "input[value=\"2nd Day Air___Shipping.FixedRate\"]")
    protected WebElement secondDayShippingMethod;

    private Logger log = Logger.getLogger(CartPage.class.getName());

    @FindBy(how = How.CSS, using = "tr[class=\"cart-item-row\"]")
    private List<WebElement> cartsItems;


    public CartPage() {
        PageFactory.initElements(AppiumDriverSettings.getDriver(), this);
        log.info("------  Open Cart page ------");
    }

    @Step("Get cart page title")
    public String getProductsTitle(int index) {
        AppiumDriverWaits.waitForPresentEl(cartsItems);
        return Utils.getElementText(cartsItems.get(index).findElement(name));
    }

    @Step("Get price per unit of item")
    public Double getPricePerUnitOfItem(int index) {
        return Double.parseDouble(Utils.getElementText(cartsItems.get(index).findElement(pricePerUnitOfItem)));
    }

    @Step("Get total price of item")
    public Double getTotalPriceOfItem(int index) {
        AppiumDriverWaits.waitForPresentEl(cartsItems);
        return Double.parseDouble(Utils.getElementText(cartsItems.get(index).findElement(totalPrice)));
    }

    @Step("Set quantity input")
    public void setQuantityInput(int index, String number) {
        cartsItems.get(index).findElement(quantityInput).clear();
        cartsItems.get(index).findElement(quantityInput).sendKeys(number, Keys.ENTER);
    }

    @Step("Get product quantity")
    public Integer getProductQuantity(int index) {
        return Integer.parseInt(Utils.getElementText(cartsItems.get(index).findElement(quantityInput)));
    }

    @Step("Set billing address")
    public void setBillingAddress(String firstName, String lastName, String email, String city, String address, String code, String phone) {
        AppiumDriverWaits.waitForPresentEl(billingAddressFirstName);
        billingAddressFirstName.clear();
        billingAddressFirstName.sendKeys(firstName);
        billingAddressLastName.clear();
        billingAddressLastName.sendKeys(lastName);
        billingAddressEmail.clear();
        billingAddressEmail.sendKeys(email);
        chooseCountry.click();
        selectAppropriateCountry.click();
        chooseState.click();
        selectAppropriateState.click();
        addressCity.sendKeys(city);
        addressFirst.sendKeys(address);
        addressZipCode.sendKeys(code);
        addressPhoneNumber.sendKeys(phone);
    }

    @Step("Set shipping address")
    public void setShippingAddress(String firstName, String lastName, String email, String city, String address, String code, String phone) {
        AppiumDriverWaits.waitForPresentEl(addressFirstName);
        addressFirstName.clear();
        addressFirstName.sendKeys(firstName);
        addressLastName.clear();
        addressLastName.sendKeys(lastName);
        addressEmail.clear();
        addressEmail.sendKeys(email);
        chooseShippingCountry.click();
        selectShippingCountry.click();
        shippingAddressCity.sendKeys(city);
        shippingAddressFirst.sendKeys(address);
        shippingZipCode.sendKeys(code);
        shippingPhoneNumber.sendKeys(phone);
    }

    @Step("Set card data")
    public void setCardData(String cardName, String cardNumb, String code) {
        cardHolderName.sendKeys(cardName);
        cardNumber.sendKeys(cardNumb);
        expireMonth.click();
        appropriateMonth.click();
        expireYear.click();
        appropriateYear.click();
        cardCode.sendKeys(code);
    }
}
