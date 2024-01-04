public class Electronics extends Product{

    private String product_brand;
    private int product_warranty;
    public Electronics(String product_ID, String product_name, int no_available_items, double price, String product_brand, int product_warranty) {
        super(product_ID, product_name, no_available_items, price);
        this.product_brand = product_brand;
        this.product_warranty = product_warranty;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public int getProduct_warranty() {
        return product_warranty;
    }

    public void setProduct_warranty(int product_warranty) {
        this.product_warranty = product_warranty;
    }
}
