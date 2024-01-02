import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager {
    ArrayList<Product> list_of_products = new ArrayList<>();

    @Override
    public void add_product () {
        System.out.println("1");
    }

    @Override
    public void delete_product(){
        System.out.println("2");
    }

    @Override
    public void print_products(){
        System.out.println("3");
    }

    @Override
    public void save_products(){
        System.out.println("4");
    }

    @Override
    public void load_products(){
        System.out.println("4");
    }
}
