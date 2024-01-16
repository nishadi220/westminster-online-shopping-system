public class Main {

    public static void display_menu(){

        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        System.out.println("Welcome to Westminster Online Shopping Center Managing System");
        System.out.println(".............................................................");

        System.out.println("Menu");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of the products");
        System.out.println("4. Save in a file");
        System.out.println("5. Load from file");
        System.out.println();

        int answer = manager.errorHandlerInt("Enter your option                                             : ");

        switch (answer) {
            case 1:
                manager.add_product();
                break;
            case 2:
                manager.delete_product();
                break;
            case 3:
                manager.print_products();
                break;
            case 4:
                manager.save_products();
                break;
            case 5:
                manager.load_products();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
    public static void main(String[] args) {

        System.out.println("..............................................");
        System.out.println("Welcome to Westminster Online Shopping Center ");
        System.out.println("..............................................");
        System.out.println();

        Main.display_menu();
        }
    }