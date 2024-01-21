import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestminsterShoppingGUI {

ArrayList<java.lang.Object[]> data = new ArrayList<java.lang.Object[]>();

    private JFrame frame;
    private JComboBox<String> categoryComboBox;
    private JButton shoppingCartButton, addToCartButton;
    private JTable productsTable;
    private JTextArea itemDetailsTextArea;
    private ShoppingCart shoppingCart;

    public WestminsterShoppingGUI() {
        // Initialize the frame
        frame = new JFrame("Westminster Online Shopping System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create components
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        shoppingCartButton = new JButton("Shopping Cart");
        addToCartButton = new JButton("Add to Shopping Cart");
        DefaultTableModel tableModel = null;
        productsTable = new JTable(tableModel);

        // Change table column size
        Object[][] tableData = new Object[WestminsterShoppingManager.list_of_products.size()][5];

//        String Category = null;
//        String Information = null;
//        for (int i = 0; i < WestminsterShoppingManager.list_of_products.size(); i++) {
//            Product product = WestminsterShoppingManager.list_of_products.get(i);
//
//            if (product instanceof Electronics){
//                Category = "Electronics";
//                Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();
//            }else if((product instanceof Clothing)){
//                Category = "Clothing";
//                Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();
//            }
//
//            tableData[i][0] = product.getProduct_ID();
//            tableData[i][1] = product.getProduct_name();
//            tableData[i][2] = Category;
//            tableData[i][3] = product.getPrice();
//            tableData[i][4] = Information;
//        }


        // Column names
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        // Create a table model
        tableModel = new DefaultTableModel(tableData, columnNames);

        // Create the JTable with the table model
        productsTable = new JTable(tableModel);

        // Set column widths
//        int[] columnWidths = {100, 150, 100, 120, 150}; // Adjust these values as needed

//        for (int i = 0; i < columnWidths.length; i++) {
//            TableColumn column = productsTable.getColumnModel().getColumn(i);
//            column.setPreferredWidth(columnWidths[i]);
//        }

//        product details to the center of cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < productsTable.getColumnCount(); i++) {
            productsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        itemDetailsTextArea = new JTextArea();

        // Set layout manager
        frame.setLayout(new BorderLayout());

        // Top panel with category label, dropdown, and shopping cart button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel categoryLabel = new JLabel("Select Product Category");
        topPanel.add(categoryLabel);
        topPanel.add(categoryComboBox);

        JPanel shoppingCartPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        shoppingCartPanel.add(shoppingCartButton);
        topPanel.add(shoppingCartPanel);

        // Center panel with product table
        JScrollPane tableScrollPane = new JScrollPane(productsTable);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 50, 20));

        JPanel centerPanel = new JPanel(new BorderLayout());
//        centerPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Bottom panel with item details and add to cart button
        JPanel bottomPanel = new JPanel(new BorderLayout());
//        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        itemDetailsTextArea.setPreferredSize(new Dimension(400, 200)); // Set your preferred size

        bottomPanel.add(new JScrollPane(itemDetailsTextArea), BorderLayout.CENTER);
        bottomPanel.add(addToCartButton, BorderLayout.SOUTH);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Set up ActionListener for the buttons
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic for the shopping cart button
                shoppingCart.displayShoppingCart();
                JOptionPane.showMessageDialog(frame, "Shopping Cart button clicked");
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                // Add your logic for the add to cart button
                JOptionPane.showMessageDialog(frame, "Add to Cart button clicked");
                addToShoppingCart();
            }

            private void addToShoppingCart() {
                String itemDetails = itemDetailsTextArea.getText();
                if (!itemDetails.isEmpty()) {
                    // Split the itemDetails and add to the shopping cart
                    String[] detailsArray = itemDetails.split("\\s+");
                    shoppingCart.addItem(detailsArray);
                    JOptionPane.showMessageDialog(frame, "Item added to Shopping Cart");
                }
            }
        });

        // Set up ActionListener for the combo box
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Category = null;
                String Information = null;
                String selectedCategory = (String) categoryComboBox.getSelectedItem();

                // Print corresponding values based on the selected item
                if ("All".equals(selectedCategory)) {
                    System.out.println("1");

                    for (int i = 0; i < WestminsterShoppingManager.list_of_products.size(); i++) {
                        Product product = WestminsterShoppingManager.list_of_products.get(i);

                        if (product instanceof Electronics) {
                            Category = "Electronics";
                            Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();
                        } else if ((product instanceof Clothing)) {
                            Category = "Clothing";
                            Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();
                        }

                        tableData[i][0] = product.getProduct_ID();
                        tableData[i][1] = product.getProduct_name();
                        tableData[i][2] = Category;
                        tableData[i][3] = product.getPrice();
                        tableData[i][4] = Information;
                    }

                } else if ("Electronics".equals(selectedCategory)) {
                    System.out.println("2");

                    for (int i = 0; i < WestminsterShoppingManager.list_of_products.size(); i++) {
                        Product product = WestminsterShoppingManager.list_of_products.get(i);

                        if (product instanceof Electronics) {
                            Category = "Electronics";
                            Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();

                            tableData[i][0] = product.getProduct_ID();
                            tableData[i][1] = product.getProduct_name();
                            tableData[i][2] = Category;
                            tableData[i][3] = product.getPrice();
                            tableData[i][4] = Information;
                        }
                    }

                } else if ("Clothes".equals(selectedCategory)) {
                    System.out.println("3");

                    for (int i = 0; i < WestminsterShoppingManager.list_of_products.size(); i++) {
                        Product product = WestminsterShoppingManager.list_of_products.get(i);

                        if ((product instanceof Clothing)) {
                            Category = "Clothing";
                            Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();

                            tableData[i][0] = product.getProduct_ID();
                            tableData[i][1] = product.getProduct_name();
                            tableData[i][2] = Category;
                            tableData[i][3] = product.getPrice();
                            tableData[i][4] = Information;
                        }
                    }
                }
            }
        });

        productsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productsTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Retrieve information from the selected row and display it in the itemDetailsTextArea
                    StringBuilder productInfo = new StringBuilder();
                    for (int i = 0; i < productsTable.getColumnCount(); i++) {
                        productInfo.append(productsTable.getValueAt(selectedRow, i)).append(" ");
                    }
                    itemDetailsTextArea.setText(productInfo.toString());
                }
            }
        });
        // Set frame visibility
        frame.setVisible(true);
    }


    public void runGUI(){
        WestminsterShoppingGUI.main(null);
    }

    public static void main(String[] args) {


        WestminsterShoppingGUI gui = new WestminsterShoppingGUI();


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WestminsterShoppingGUI gui = new WestminsterShoppingGUI();
                gui.shoppingCart = new ShoppingCart();
            }
        });
    }
}
