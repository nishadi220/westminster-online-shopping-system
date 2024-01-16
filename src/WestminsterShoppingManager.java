import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static ArrayList<Product> list_of_products = new ArrayList<>();
    static final int maximum = 50;
    private static final String Data_File = "productStocks.txt";

    @Override
    public void add_product () {
        int no_of_products = errorHandlerInt("How many products do you wish to add                          : ");

        for (int i = 0; i < no_of_products; i++) {
            boolean var = true ;

            if ( list_of_products.size() <= 50 ){
                String product_ID = errorHandlerString("Enter Product ID                                              : ");
                String product_name = errorHandlerString("Enter Product name                                            : ");
                int no_available_products  = errorHandlerInt("Enter Number of available items                               : ");
                double price = errorHandlerDouble("Enter Product Price                                           : ");

                while (var){
                    int option = errorHandlerInt("Enter Product Type - \"1\" for Electronics and \"2\" for Clothing : ");

                    if (option == 1){
                        String product_brand = errorHandlerString("Enter Product Brand                                           : ");
                        int product_warranty = errorHandlerInt("Enter Product Warranty                                        : ");

                        Electronics object = new Electronics(product_ID,product_name,no_available_products,price,product_brand,product_warranty);
                        list_of_products.add(object);
                        var = false;

                    }
                    else if (option == 2){
                        String product_size = errorHandlerString("Enter Product Size                                            : ");
                        String product_color = errorHandlerString("Enter Product Color                                           : ");

                        Clothing object = new Clothing(product_ID,product_name,no_available_products,price,product_size,product_color);
                        list_of_products.add(object);
                        var = false;
                    }
                    else {
                        System.out.println("Invalid input. ");
                    }
                }
                System.out.println();
                System.out.println("Product successfully added !");
                System.out.println(".............................................................");
                System.out.println();
            }
            else {
                System.out.println("Product capacity out of range. Remove existing products to add new products !");
                System.out.println();
            }
        }
        Main.display_menu();
    }

    @Override
    public void delete_product(){
        int no_of_products = errorHandlerInt("How many products do you wish to delete                       : ");
        boolean state = true;
        String delete;
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < no_of_products; i++) {
            boolean var = true;

            while (var) {
                String product_ID = errorHandlerString("Enter Product ID                                              : ");

                for (Product item : list_of_products) {
                    if (item.getProduct_ID().equals(product_ID)) {

                        do {
                            delete = validInput(errorHandlerString("Do you want to delete the ProductID you entered? (Y/N)        : "));
                        }while (delete != null);

                        if ("Y".equalsIgnoreCase(delete)) {
                            System.out.println("Product ID              : " + item.getProduct_ID());
                            System.out.println("Product Name            : " + item.getProduct_name());
                            System.out.println("No. of available items  : " + item.getNo_available_products());
                            System.out.println("Product Price           : " + item.getPrice());

                            if (item instanceof Electronics) {
                                Electronics electronicsProduct = (Electronics) item;
                                System.out.println("Type                    : Electronics");
                                System.out.println("Brand                   : " + electronicsProduct.getProduct_brand());
                                System.out.println("Warranty                : " + electronicsProduct.getProduct_warranty());
                            } else if (item instanceof Clothing) {
                                Clothing clothesProduct = (Clothing) item;
                                System.out.println("Type                    : Clothes");
                                System.out.println("Size                    : " + clothesProduct.getProduct_size());
                                System.out.println("Color                   : " + clothesProduct.getProduct_color());
                            }
                            System.out.println("------------------------------------------------");
                            System.out.println();

                            list_of_products.remove(item);
                            System.out.println();
                            System.out.println("Product Deleted.");
                            System.out.println();
                            var = false;

                        } else if ("N".equalsIgnoreCase(delete)) {
                            System.out.println("Deleting product terminated.");
                            var = false;
                        } else {
                            System.out.println("Invalid response.Try again !");
                        }
                        break;
                    }
                }

                if (var) {
                    System.out.println("Product ID invalid. Try Again.");
                }
            }
            Main.display_menu();

        }
    }

    @Override
    public void print_products(){
        for (Product item : list_of_products){
            System.out.println("------------------------------------------------");
            System.out.println();
            System.out.println("Product ID              : " + item.getProduct_ID());
            System.out.println("Product Name            : " + item.getProduct_name());
            System.out.println("No. of available items  : " + item.getNo_available_products());
            System.out.println("Product Price           : " + item.getPrice());

            if(item instanceof Electronics){
                Electronics electronicsProduct = (Electronics) item;
                System.out.println("Type                    : Electronics");
                System.out.println("Brand                   : " + electronicsProduct.getProduct_brand());
                System.out.println("Warranty                : " + electronicsProduct.getProduct_warranty());

            } else if (item instanceof Clothing) {
                Clothing clothesProduct = (Clothing) item;
                System.out.println("Type                    : Clothes");
                System.out.println("Size                    : " + clothesProduct.getProduct_size());
                System.out.println("Color                   : " + clothesProduct.getProduct_color());
            }
            System.out.println("------------------------------------------------");
            System.out.println();
        }
        Main.display_menu();
    }

    @Override
    public void save_products(){
        System.out.println("4");
    }
    @Override
    public void load_products(){
        System.out.println("4");
    }

    public String errorHandlerString(String input){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.print(input);
                return sc.next();
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Datatype Wrong. Re-enter!");
            }
        }
    }

    public int errorHandlerInt(String input){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.print(input);
                return sc.nextInt();
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Datatype Wrong. Re-enter!");
            }
        }
    }

    public Double errorHandlerDouble(String input){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.print(input);
                return sc.nextDouble();
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Datatype Wrong. Re-enter!");
            }
        }
    }

    public String validInput(String input){
        while (true){
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")){
                return input;
            }
            else {
                System.out.println("Enter Y or N. Try Again!!");
                return null;
            }
        }
    }

}
