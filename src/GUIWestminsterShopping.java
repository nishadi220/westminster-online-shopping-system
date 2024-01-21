import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIWestminsterShopping {
    static String selectedCategory;
    static Object productId;
    static Object productName;
    static Object category;
    static Object price;
    static Object information;

    static public JPanel ProductDetails(Object productId, JTable productsTable){
        JPanel bottomPanel = new JPanel(new GridLayout(0, 1));

        for (Product item : WestminsterShoppingManager.list_of_products){
            if (productId == item.getProduct_ID()){
                if (item instanceof Electronics){
                    JLabel SelectedProductLabel = new JLabel("Select Product - Details ");
                    JLabel ProductIDLabel = new JLabel("Product ID : " + item.getProduct_ID());
                    JLabel CategoryLabel = new JLabel("Category : Electronics");
                    JLabel NameLabel = new JLabel("Name : " + item.getProduct_name());
                    JLabel BrandLabel = new JLabel("Brand : " + ((Electronics) item).getProduct_brand());
                    JLabel WarrantyLabel = new JLabel("Warranty : " + ((Electronics) item).getProduct_warranty());
                    JLabel ItemsAvailableLabel = new JLabel("Items Available : " + item.getNo_available_products());

                    bottomPanel.add(SelectedProductLabel);
                    bottomPanel.add(ProductIDLabel);
                    bottomPanel.add(CategoryLabel);
                    bottomPanel.add(NameLabel);
                    bottomPanel.add(BrandLabel);
                    bottomPanel.add(WarrantyLabel);
                    bottomPanel.add(ItemsAvailableLabel);

                } else if (item instanceof Clothing) {
                    JLabel SelectedProductLabel = new JLabel("Select Product - Details ");
                    JLabel ProductIDLabel = new JLabel("Product ID : " + item.getProduct_ID());
                    JLabel CategoryLabel = new JLabel("Category : Clothing ");
                    JLabel NameLabel = new JLabel("Name : " + item.getProduct_name());
                    JLabel SizeLabel = new JLabel("Size : " + ((Clothing) item).getProduct_size());
                    JLabel ColourLabel = new JLabel("Colour : " + ((Clothing) item).getProduct_color());
                    JLabel ItemsAvailableLabel = new JLabel("Items Available : " + item.getNo_available_products());

                    bottomPanel.add(SelectedProductLabel);
                    bottomPanel.add(ProductIDLabel);
                    bottomPanel.add(CategoryLabel);
                    bottomPanel.add(NameLabel);
                    bottomPanel.add(SizeLabel);
                    bottomPanel.add(ColourLabel);
                    bottomPanel.add(ItemsAvailableLabel);

                }
            }
        }
        return bottomPanel;
    }

    public static void productInfo(){
        JFrame ProductInfoFrame = new JFrame("Westminster Online Shopping System");

        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        JButton shoppingCartButton = new JButton("Shopping Cart");
        JButton addToCartButton = new JButton("Add to Shopping Cart");

        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Information"};

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable productsTable = new JTable(tableModel);

        productsTable.setDefaultEditor(Object.class, null);

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        JScrollPane tableScrollPane = new JScrollPane(productsTable);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set layout
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel categoryLabel = new JLabel("Select Product Category");
        topPanel.add(categoryLabel);
        topPanel.add(categoryComboBox);

        JPanel shoppingCartPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        shoppingCartPanel.add(shoppingCartButton);
        topPanel.add(shoppingCartPanel);
        topPanel.add(tableScrollPane, BorderLayout.CENTER);
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
        topPanel.setPreferredSize(new Dimension(800, 400));

        JPanel bottomPanel = new JPanel(new GridLayout(0, 1));

        JPanel addToCartPanel = new JPanel(new BorderLayout());
        addToCartPanel.add(addToCartButton, BorderLayout.SOUTH);

        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Object productId = productsTable.getValueAt(selectedRow, 0);
                        JPanel updatedPanel = GUIWestminsterShopping.ProductDetails(productId, productsTable);
                        bottomPanel.removeAll();
                        bottomPanel.add(updatedPanel);
                        bottomPanel.add(addToCartPanel);

                        bottomPanel.revalidate();
                        bottomPanel.repaint();
                    }
                }
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ProductInfoFrame, "Product added to shopping cart!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
        bottomPanel.setPreferredSize(new Dimension(800, 300));

        ProductInfoFrame.add(topPanel, BorderLayout.NORTH);
        ProductInfoFrame.add(bottomPanel, BorderLayout.SOUTH);

        categoryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedCategory = (String) categoryComboBox.getSelectedItem();
                tableModel.setRowCount(0);

                for (Product product : WestminsterShoppingManager.list_of_products) {
                    String Category = "";
                    String Information = "";
                    Object[] ProductArray;

                    if (selectedCategory.equals("All")) {
                        if (product instanceof Electronics) {
                            Category = "Electronics";
                            Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();
                        } else if (product instanceof Clothing) {
                            Category = "Clothes";
                            Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();
                        }

                        ProductArray = new Object[]{
                                product.getProduct_ID(),
                                product.getProduct_name(),
                                Category,
                                product.getPrice(),
                                Information
                        };

                        tableModel.addRow(ProductArray);
                    } else if (selectedCategory.equals("Electronics")) {
                        if (!(product instanceof Electronics)) {
                            continue;
                        }

                        Category = "Electronics";
                        Information = ((Electronics) product).getProduct_brand() + "," + ((Electronics) product).getProduct_warranty();

                        ProductArray = new Object[]{
                                product.getProduct_ID(),
                                product.getProduct_name(),
                                Category,
                                product.getPrice(),
                                Information
                        };

                        tableModel.addRow(ProductArray);
                    } else if (selectedCategory.equals("Clothes")) {
                        if (!(product instanceof Clothing)) {
                            continue;
                        }

                        Category = "Clothes";
                        Information = ((Clothing) product).getProduct_color() + "," + ((Clothing) product).getProduct_size();

                        ProductArray = new Object[]{
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

        // Initializing the ProductInfoFrame
        ProductInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProductInfoFrame.setSize(800, 800);
        ProductInfoFrame.setVisible(true);
    }

    public static void main(String[] args) {

        WestminsterShoppingManager.load_products();

        JFrame MainFrame = new JFrame("Westminster Shopping Centre");

        JPanel MainPanel = new JPanel(new GridLayout(0, 1));

        JPanel bottomPanel = new JPanel(new GridLayout(0, 1));

        for (Product item : WestminsterShoppingManager.list_of_products){
            if (productId == item.getProduct_ID()){
                if (item instanceof Electronics){
                    JLabel SelectedProductLabel = new JLabel("Select Product - Details ");
                    JLabel ProductIDLabel = new JLabel("Product ID : " + item.getProduct_ID());
                    JLabel CategoryLabel = new JLabel("Category : Electronics");
                    JLabel NameLabel = new JLabel("Name : " + item.getProduct_name());
                    JLabel BrandLabel = new JLabel("Brand : " + ((Electronics) item).getProduct_brand());
                    JLabel WarrantyLabel = new JLabel("Warranty : " + ((Electronics) item).getProduct_warranty());
                    JLabel ItemsAvailableLabel = new JLabel("Items Available : " + item.getNo_available_products());

                    bottomPanel.add(SelectedProductLabel);
                    bottomPanel.add(ProductIDLabel);
                    bottomPanel.add(CategoryLabel);
                    bottomPanel.add(NameLabel);
                    bottomPanel.add(BrandLabel);
                    bottomPanel.add(WarrantyLabel);
                    bottomPanel.add(ItemsAvailableLabel);

                } else if (item instanceof Clothing) {
                    JLabel SelectedProductLabel = new JLabel("Select Product - Details ");
                    JLabel ProductIDLabel = new JLabel("Product ID : " + item.getProduct_ID());
                    JLabel CategoryLabel = new JLabel("Category : Clothing ");
                    JLabel NameLabel = new JLabel("Name : " + item.getProduct_name());
                    JLabel SizeLabel = new JLabel("Size : " + ((Clothing) item).getProduct_size());
                    JLabel ColourLabel = new JLabel("Colour : " + ((Clothing) item).getProduct_color());
                    JLabel ItemsAvailableLabel = new JLabel("Items Available : " + item.getNo_available_products());

                    bottomPanel.add(SelectedProductLabel);
                    bottomPanel.add(ProductIDLabel);
                    bottomPanel.add(CategoryLabel);
                    bottomPanel.add(NameLabel);
                    bottomPanel.add(SizeLabel);
                    bottomPanel.add(ColourLabel);
                    bottomPanel.add(ItemsAvailableLabel);

                }
            }
        }

        MainPanel.add(bottomPanel);

        MainFrame.add(MainPanel);

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(300, 400);
        MainFrame.setVisible(true);
    }

}
