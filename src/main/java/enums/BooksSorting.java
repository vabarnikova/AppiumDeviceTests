package enums;

public enum BooksSorting {
    NAME_FROM_A_TO_Z("Name From A To Z"),
    NAME_FROM_Z_TO_A("Name From Z To A"),
    PRICE_FROM_LOW_TO_HIGH("Price From Low To High"),
    PRICE_FROM_HIGH_TO_LOW("Price From High To Low");

    private final String sort;

    BooksSorting(String sort) {
        this.sort = sort;
    }

    public String getBooksSorting() {
        return this.sort;
    }
}
