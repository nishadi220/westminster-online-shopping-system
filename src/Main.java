import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static String errorHandlerString(String input){
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

    public static int errorHandlerInt(String input){
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

    public static Double errorHandlerDouble(String input){
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

    public static void UserMenu(){
        System.out.println();
    }

    public static void ManagerMenu(){

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
                WestminsterShoppingManager.load_products();
                Main.ManagerMenu();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    public static void main(String[] args) {
        boolean iterate = true;

        System.out.println("..............................................");
        System.out.println("Welcome to Westminster Online Shopping Center ");
        System.out.println("..............................................");
        System.out.println();

        WestminsterShoppingManager.load_products();

        while (iterate){
            Scanner sc = new Scanner(System.in);
            iterate = false;
            String username;
            String password;

            int Answer = Main.errorHandlerInt("Are you a Customer or a Manager.( 1 - Customer/ 2 - Manager) : ");

            switch (Answer){
                case 1 :

                    System.out.println("Are you a registered / unregistered cusstomer ( 1 or 2 ) : ");
                    int answer = sc.nextInt();

                    switch (answer){
                        case 1 :
                            System.out.print("Enter Username : ");
                            username = sc.next();
                            System.out.print("Enter Password : ");
                            password = sc.next();

                            for (User RegistredUser : User.users){
                                if ((RegistredUser.getUsername() == username) && (RegistredUser.getPassword() == password)){
                                    System.out.println("Log in successful");
                                }
                                else{
                                    System.out.println("Log in information invalid");
                                }
                                break;
                            }

                        case 2 :
                            System.out.print("Enter Username : ");
                            username = sc.next();
                            System.out.print("Enter Password : ");
                            password = sc.next();

                            User newUser = new User(username,password,new ArrayList<>());
                            User.users.add(newUser);

                            System.out.println("Registration completed successfully !");

                            break;

                        default :
                            System.out.println("Invalid input !");
                            break;
                    }
                    break;
                case 2 :
                    ManagerMenu();
                    break;
                default:
                    System.out.println("Invalid Input. Please enter again.");
                    System.out.println();
                    iterate = true;
                }
            }
        }
    }