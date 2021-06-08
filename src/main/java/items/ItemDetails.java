package items;


public class ItemDetails {

    private String itemTitle;
    private Double price;

    public ItemDetails() {
    }

    public ItemDetails(String itemTitle, Double price) {
        this.itemTitle = itemTitle;
        this.price = price;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
