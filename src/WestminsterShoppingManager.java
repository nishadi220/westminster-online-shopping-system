import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    ArrayList<Product> list_of_products = new ArrayList<>();

    @Override
    public void add_product (Product product) {

        if ( list_of_products.size() <= 50 ){
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Product ID");
            String product_ID = sc.next();

            System.out.println("Enter Product name");
            String product_name = sc.next();

            System.out.println("Enter Number of available items");
            int no_available_products  = sc.nextInt();

            System.out.println("Enter Product ID");
            double price = sc.nextDouble();

            System.out.println("Enter Product Type");
            System.out.println(" Enter \"1\" for Electronics and \"2\" for Clothing.");
            int option = sc.nextInt();

            if (option == 1){

                System.out.print("Enter Product Brand: ");
                String product_brand = sc.next();

                System.out.print("Enter Product Warranty: ");
                int product_warranty = sc.nextInt();

                Electronics object = new Electronics(product_ID,product_name,no_available_products,price,product_brand,product_warranty);
                list_of_products.add(object);
            }
            else if (option == 2){

                System.out.print("Enter Product Size: ");
                String product_size = sc.next();

                System.out.print("Enter Product Color: ");
                String product_color = sc.next();

                Clothing object = new Clothing(product_ID,product_name,no_available_products,price,product_size,product_color);
                list_of_products.add(object);
            }
        }

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
