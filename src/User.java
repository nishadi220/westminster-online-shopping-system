import java.util.ArrayList;

public class User {

    private String username ;
    private String password ;
    private ArrayList<Product> products = new ArrayList<Product>();
    static public ArrayList<User> users = new ArrayList<User>();

    public User(String username, String password, ArrayList<Product> products) {
        this.username = username;
        this.password = password;
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public static void setHistory(Product product) {
    }
}
