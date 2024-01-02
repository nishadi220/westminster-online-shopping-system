abstract public class Product {

    private String product_ID ;
    private String product_name ;
    private int no_available_products;
    private double price ;

    public Product(String product_ID, String product_name, int no_available_products, double price) {
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.no_available_products = no_available_products;
        this.price = price;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getNo_available_products() {
        return no_available_products;
    }

    public void setNo_available_products(int no_available_products) {
        this.no_available_products = no_available_products;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
