public class Electronics extends Product{

    private String brand ;
    private String warranty_period ;
    public Electronics(String product_ID, String product_name, int no_available_items, double price, String brand, String warranty_period) {
        super(product_ID, product_name, no_available_items, price);
        this.brand = brand ;
        this.warranty_period = warranty_period ;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarranty_period() {
        return warranty_period;
    }

    public void setWarranty_period(String warranty_period) {
        this.warranty_period = warranty_period;
    }
}
