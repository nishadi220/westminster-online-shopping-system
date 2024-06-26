import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIWestminsterShopping {

    public ArrayList<Product> productValues = WestminsterShoppingManager.list_of_products;
    private JFrame productSelectInterface;
    private JFrame shoppingCart;
    private JPanel productsDetailsPanel;
    private ArrayList<Product> cartItems = new ArrayList<>();
    private JTable cartTable = new JTable();
    private DefaultTableModel cartTableModel = new DefaultTableModel();


    public GUIWestminsterShopping() {
        productsDetailsPanel = new JPanel(); // Initialize the panel here
        shoppingCart = shoppingCart();
    }

    public JFrame productSelectInterface(User user) {
        JFrame productSelect = new JFrame("Westminster Shopping Centre");

        JPanel productSelectPanel = new JPanel(new BorderLayout());

        JLabel categoryLabel = new JLabel("Select Product Category: ");

        String[] options = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(options);

        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(e -> {

            shoppingCart = shoppingCart();
            shoppingCart.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(categoryLabel);
        buttonPanel.add(categoryComboBox);
        buttonPanel.add(shoppingCartButton);

        JScrollPane productTableScrollPane = productTable(categoryComboBox, user);
        productTableScrollPane.setPreferredSize(new Dimension(800, 400));

        productsDetailsPanel = new JPanel (new GridLayout(0,1));
        productsDetailsPanel.setPreferredSize(new Dimension(800, 300));
        productsDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        productSelectPanel.add(buttonPanel, BorderLayout.NORTH);
        productSelectPanel.add(productTableScrollPane, BorderLayout.CENTER);
        productSelectPanel.add(productsDetailsPanel, BorderLayout.SOUTH);

        productSelect.add(productSelectPanel);
        productSelect.setSize(800, 800);
        productSelect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return productSelect;
    }

    public JScrollPane productTable(JComboBox<String> categoryComboBox,User user) {
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        table.setDefaultEditor(Object.class, null);

        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateTable(model, selectedCategory, table);
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object productId = table.getValueAt(selectedRow, 0);
                        productsDetailsOutput(productId, model, user);
                    }
                }
            }
        });
        return scrollPane;
    }

    private void updateTable(DefaultTableModel model, String selectedCategory, JTable table) {
        ArrayList<Object[]> tableDataList = new ArrayList<>();

        for (Product product : WestminsterShoppingManager.list_of_products) {

            String category = null;
            String information = null;
            Object[] rowData;

            switch (selectedCategory) {
                case "All":
                    if (product instanceof Electronics) {
                        category = "Electronics";
                    } else if (product instanceof Clothing) {
                        category = "Clothes";
                    }

                    if (product instanceof Electronics) {
                        information = ((Electronics) product).getProduct_brand() + ", " + ((Electronics) product).getProduct_warranty();
                    } else if (product instanceof Clothing) {
                        information = ((Clothing) product).getProduct_size() + ", " + ((Clothing) product).getProduct_color();
                    }
                    break;

                case "Electronics":
                    if (!(product instanceof Electronics)) {
                        continue;
                    }
                    category = "Electronics";
                    information = ((Electronics) product).getProduct_brand() + ", " + ((Electronics) product).getProduct_warranty();
                    break;

                case "Clothing":
                    if (!(product instanceof Clothing)) {
                        continue;
                    }
                    category = "Clothes";
                    information = ((Clothing) product).getProduct_size() + ", " + ((Clothing) product).getProduct_color();
                    break;

                default:
                    continue;
            }

            rowData = new Object[]{
                    product.getProduct_ID(),
                    product.getProduct_name(),
                    category,
                    product.getPrice(),
                    information
            };
            tableDataList.add(rowData);
        }

        Collections.sort(tableDataList, Comparator.comparing(row -> (String) row[1]));

        model.setRowCount(0);
        for (Object[] rowData : tableDataList) {
            model.addRow(rowData);
        }
    }

    public void productsDetailsOutput(Object productId, DefaultTableModel model, User user) {
        String category = null;
        productsDetailsPanel.removeAll();
        for (Product product : productValues) {
            if (productId.equals(product.getProduct_ID())) {
                JLabel productIDLabel = new JLabel("Product ID: " + product.getProduct_ID());

                if (product instanceof Electronics) {
                    category = "Electronics";
                } else if (product instanceof Clothing) {
                    category = "Clothes";
                }

                JLabel categoryLabel = new JLabel("Category: " + category);
                JLabel nameLabel = new JLabel("Name: " + product.getProduct_name());
                JLabel itemsAvailableLabel = new JLabel("Items Available: " + product.getNo_available_products());

                if (product instanceof Electronics) {
                    productsDetailsPanel.add(productIDLabel);
                    productsDetailsPanel.add(categoryLabel);
                    productsDetailsPanel.add(nameLabel);
                    JLabel brandLabel = new JLabel("Brand: " + ((Electronics) product).getProduct_brand());
                    JLabel warrantyLabel = new JLabel("Warranty: " + ((Electronics) product).getProduct_warranty());
                    productsDetailsPanel.add(brandLabel);
                    productsDetailsPanel.add(warrantyLabel);

                } else if (product instanceof Clothing) {
                    productsDetailsPanel.add(productIDLabel);
                    productsDetailsPanel.add(categoryLabel);
                    productsDetailsPanel.add(nameLabel);
                    JLabel sizeLabel = new JLabel("Size: " + ((Clothing) product).getProduct_size());
                    JLabel colorLabel = new JLabel("Colour: " + ((Clothing) product).getProduct_color());
                    productsDetailsPanel.add(sizeLabel);
                    productsDetailsPanel.add(colorLabel);

                }
                productsDetailsPanel.add(itemsAvailableLabel);
                JButton addToCart = new JButton("Add To Shopping Cart");
                productsDetailsPanel.add(addToCart);

                addToCart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cartItems.add(product);
                        System.out.println("Items in the shopping cart:");
                        for (Product item : cartItems) {
                            System.out.println(item);
                        }
                        User.setHistory(product);
                        JOptionPane.showMessageDialog(null, "Product added successfully !");
                    }
                });
                productsDetailsPanel.revalidate();
                productsDetailsPanel.repaint();
            }
        }
    }

    public JFrame shoppingCart() {
        JFrame cart = new JFrame("Shopping Cart");
        JPanel cartPanel = new JPanel(new BorderLayout());
        DefaultTableModel cartTableModel = new DefaultTableModel();
        JTable cartTable = new JTable(cartTableModel);
        String[] columnNames = {"Product", "Quantity", "Price"};
        cartTable.setDefaultEditor(Object.class, null);
        for (String columnName : columnNames) {
            cartTableModel.addColumn(columnName);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < cartTable.getColumnCount(); i++) {
            cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        System.out.println(cartItems);
        for (Product item : cartItems){
            String[] rowData = { item.getProduct_name() , String.valueOf(item.getNo_available_products()) , String.valueOf(item.getPrice()) };
            cartTableModel.addRow(rowData);
        }
        JScrollPane scrollPane = new JScrollPane(cartTable);
        cartPanel.add(scrollPane, BorderLayout.CENTER);
        double totalPrice = calculatetotalPrice();

        double finalPrice = totalPrice - threeItemsDiscount(totalPrice);

        JPanel billSummary = new JPanel(new GridLayout(4, 1));
        JLabel totalLabel = new JLabel("Total : " + totalPrice);
        JLabel firstPurchaseLabel = new JLabel("First Purchase Discount (10 %) : ");
        JLabel threeProductLabel = new JLabel("Three items in the same Category Discount (20 %) : " + threeItemsDiscount(totalPrice));
        JLabel finalTotalLabel = new JLabel("Final Total : " + finalPrice);

        billSummary.add(totalLabel);
        billSummary.add(firstPurchaseLabel);
        billSummary.add(threeProductLabel);
        billSummary.add(finalTotalLabel);

        billSummary.setBorder(new EmptyBorder(10, 10, 10, 10));

        cart.add(cartPanel, BorderLayout.CENTER);
        cart.add(billSummary, BorderLayout.SOUTH);

        cart.setSize(400, 400);
        cart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return cart;
    }

    public double calculatetotalPrice() {
        double totalPrice = 0;
        for (Product product : cartItems) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public double threeItemsDiscount(double totalPrice){
        int electronicsNUM = 0;
        int clothingNUM = 0;
        double discount = 0.0;

        for (Product product : cartItems){
            if (product instanceof Electronics){
                electronicsNUM++;
            }else if (product instanceof Clothing){
                clothingNUM++;
            }
        }

        if (electronicsNUM >= 3){
            discount = discount + (totalPrice * (0.2));
        }

        if (clothingNUM >= 3){
            discount = discount + (totalPrice * (0.2));
        }
        return discount;
    }

    public static void main(String[] args) {
        GUIWestminsterShopping shoppingGUI = new GUIWestminsterShopping();

        JFrame productSelectFrame = shoppingGUI.productSelectInterface(new User("admin", "admin", new ArrayList<>()));

        productSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productSelectFrame.setVisible(true);
    }
}