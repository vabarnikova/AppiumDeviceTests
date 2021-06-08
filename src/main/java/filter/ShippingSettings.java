package filter;

import core.AppiumDriverWaits;
import enums.PaymentMethod;
import enums.ShippingMethod;
import io.qameta.allure.Step;
import pages.CartPage;

public class ShippingSettings extends CartPage {

    @Step("Get shipping method {method.getShippingMethod()}")
    public void chooseShippingMethod(ShippingMethod method) {
        AppiumDriverWaits.waitForPresentEl(nextDayShippingMethod);
        switch (method) {
            case GROUND:
                groundShippingMethod.click();
                break;
            case NEXT_DAY_AIR:
                nextDayShippingMethod.click();
                break;
            case SECOND_DAY_AIR:
                secondDayShippingMethod.click();
                break;
        }
    }

    @Step("Get payment method {method.getPaymentMethod()}")
    public void choosePaymentMethod(PaymentMethod method) {
        AppiumDriverWaits.waitForPresentEl(creditCard);
        switch (method) {
            case CASH_ON_DELIVERY:
                cashOnDelivery.click();
                break;
            case CHECK_MONEY_ORDER:
                checkMoneyOrders.click();
                break;
            case CREDIT_CARD:
                creditCard.click();
                break;
            case PURCHASE_ORDER:
                purchaseOrder.click();
                break;
        }
    }

}
