import java.util.ArrayList;
public class ShoppingCart {

    ArrayList<Product> Cart_items = new ArrayList<>();

    public void add_products(Product product) {
        Cart_items.add(product) ;
    }

    public void remove_products(String product_ID) {
        for (Product product : Cart_items) {
            if (product.getProduct_ID().equals(product_ID)) {
                Cart_items.remove(product);
                break;
            }
        }
    }

    public double calculate_total () {
        double total_cost = 0.0;
        for (Product product : Cart_items) {
            total_cost =+ product.getPrice();
        }
        return total_cost ;
    }

}

