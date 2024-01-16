// ShoppingCart class

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends JFrame {
    private List<String[]> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(String[] itemDetails) {
        items.add(itemDetails);
    }

    public void displayShoppingCart() {
        // Display or perform operations on the shopping cart data
        for (String[] item : items) {
            System.out.println("Product: " + item[1] + ", Price: " + item[3]);
            // You can customize the display based on your requirements
        }
    }
}
