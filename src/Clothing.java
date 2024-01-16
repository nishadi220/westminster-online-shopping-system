import java.io.Serializable;

public class Clothing extends Product implements Serializable {

    private String product_size;
    private String product_color;

    public Clothing(String product_ID, String product_name, int no_available_items, double price, String product_size, String product_color) {
        super(product_ID, product_name, no_available_items, price);
        this.product_size = product_size;
        this.product_color = product_color;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }
}
