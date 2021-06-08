package enums;

public enum PaymentMethod {
    CASH_ON_DELIVERY("Cash On Delivery"),
    CHECK_MONEY_ORDER("Check Money Order"),
    CREDIT_CARD("Credit Card"),
    PURCHASE_ORDER("Purchase Order");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getPaymentMethod() {
        return this.method;
    }
}
