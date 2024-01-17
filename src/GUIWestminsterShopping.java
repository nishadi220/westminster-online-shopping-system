import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIWestminsterShopping {
    static String selectedCategory;
    static Object productId;
    static Object productName;
    static Object category;
    static Object price;
    static Object information;

    public static void main(String[] args) {

        WestminsterShoppingManager.load_products();

        JFrame frame = new JFrame("Westminster Online Shopping System");

        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        JButton shoppingCartButton = new JButton("Shopping Cart");
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        JTextArea itemDetailsTextArea = new JTextArea();

        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable productsTable = new JTable(tableModel);

        productsTable.setDefaultEditor(Object.class, null);

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        JScrollPane tableScrollPane = new JScrollPane(productsTable);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Creating a scroll pane for the table

        // Creating the table
        ArrayList<Object[]> tableData = new ArrayList<>();

        // Set layout

        // Top panel with category label, dropdown, and shopping cart button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel categoryLabel = new JLabel("Select Product Category");
        topPanel.add(categoryLabel);
        topPanel.add(categoryComboBox);

        JPanel shoppingCartPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        shoppingCartPanel.add(shoppingCartButton);
        topPanel.add(shoppingCartPanel);
        topPanel.add(tableScrollPane, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(800, 400));

        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // User has selected a cell
                    int selectedRow = productsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get the product information based on the selected row
                        productId = productsTable.getValueAt(selectedRow, 0);
                        productName = productsTable.getValueAt(selectedRow, 1);
                        category = productsTable.getValueAt(selectedRow, 2);
                        price = productsTable.getValueAt(selectedRow, 3);
                        information = productsTable.getValueAt(selectedRow, 4);
                    }
                }
            }
        });

        JPanel bottomPanel = new JPanel(new GridLayout(0,1));
        JLabel SelectedProductLabel = new JLabel("Select Product - Details ");
        JLabel ProductIDLabel = new JLabel("Product ID : " + productId);
        JLabel CategoryLabel = new JLabel("Category : " + category);
        JLabel NameLabel = new JLabel("Name : " + productName);
        JLabel SizeLabel = new JLabel("Size : " + information);
        JLabel ColourLabel = new JLabel("Colour : " + information);
        JLabel ItemsAvailableLabel = new JLabel("Items Available : " + information);

        bottomPanel.add(SelectedProductLabel);
        bottomPanel.add(ProductIDLabel);
        bottomPanel.add(CategoryLabel);
        bottomPanel.add(NameLabel);
        bottomPanel.add(SizeLabel);
        bottomPanel.add(ColourLabel);
        bottomPanel.add(ItemsAvailableLabel);

        JPanel addToCartPanel = new JPanel(new BorderLayout());
        addToCartPanel.add(addToCartButton, BorderLayout.SOUTH);
        bottomPanel.add(addToCartPanel);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
        bottomPanel.setPreferredSize(new Dimension(800, 400));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        categoryComboBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selectedCategory = (String) categoryComboBox.getSelectedItem();

                // Clear the existing data in the table
                tableModel.setRowCount(0);

                // Populate the tableData list based on the selected category
                for (Product product : WestminsterShoppingManager.list_of_products){

                    String Category = "";
                    String Information = "";
                    Object[] ProductArray;

                    if (selectedCategory.equals("All")){
                        if (product instanceof Electronics){
                            Category = "Electronics";
                            Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();
                        }
                        else if (product instanceof Clothing){
                            Category = "Clothes";
                            Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();
                        }

                        ProductArray = new Object []{
                                product.getProduct_ID(),
                                product.getProduct_name(),
                                Category,
                                product.getPrice(),
                                Information
                        };

                        tableModel.addRow(ProductArray);

                    }
                    else if (selectedCategory.equals("Electronics")){
                        if (!(product instanceof Electronics)) {
                                continue;
                        }

                        Category = "Electronics";
                        Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();

                        ProductArray = new Object []{
                                product.getProduct_ID(),
                                product.getProduct_name(),
                                Category,
                                product.getPrice(),
                                Information
                        };

                        tableModel.addRow(ProductArray);

                    }
                    else if (selectedCategory.equals("Clothes")){
                        if (!(product instanceof Clothing)) {
                            continue;
                        }

                        Category = "Clothes";
                        Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();

                        ProductArray = new Object []{
                                product.getProduct_ID(),
                                product.getProduct_name(),
                                Category,
                                product.getPrice(),
                                Information
                        };

                        tableModel.addRow(ProductArray);
                    }
                }
            }
        });

        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // User has selected a cell
                    int selectedRow = productsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get the product information based on the selected row
                        productId = productsTable.getValueAt(selectedRow, 0);
                        productName = productsTable.getValueAt(selectedRow, 1);
                        category = productsTable.getValueAt(selectedRow, 2);
                        price = productsTable.getValueAt(selectedRow, 3);
                        information = productsTable.getValueAt(selectedRow, 4);
                    }
                }
            }
        });

        // Initializing the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

}
