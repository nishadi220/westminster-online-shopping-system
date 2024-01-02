public class Clothing extends Product{

    private double size ;
    private String colour ;

    public Clothing(String product_ID, String product_name, int no_available_items, double price,double size, String colour) {
        super(product_ID, product_name, no_available_items, price);
        this.size = size ;
        this.colour = colour ;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
