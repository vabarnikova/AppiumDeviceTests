package enums;

public enum ShippingMethod {
    GROUND("Ground"),
    NEXT_DAY_AIR("Next Day Air"),
    SECOND_DAY_AIR("Second Day Air");

    private final String method;

    ShippingMethod(String method) {
        this.method = method;
    }

    public String getShippingMethod() {
        return this.method;
    }
}

