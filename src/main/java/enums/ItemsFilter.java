package enums;

public enum ItemsFilter {
    UNDER_25("Under 25"),
    FROM_25_TO_50("From 25 To 50"),
    OVER_50("Over 50");

    private final String filter;

    ItemsFilter(String filter) {
        this.filter = filter;
    }

    public String getItemsFilter() {
        return this.filter;
    }
}




