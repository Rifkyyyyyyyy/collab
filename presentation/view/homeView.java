package presentation.view;

import presentation.viewModel.product.productViewModel;
import domain.model.ProductModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class homeView extends JFrame {
    private final productViewModel productViewModel;
    private String user;

    public homeView(productViewModel productViewModel) {
        this.productViewModel = productViewModel;

        setTitle("Home");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel greetingLabel = new JLabel("Hello, " + user + "!", JLabel.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 3, 10, 10));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(greetingLabel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        displayProducts(contentPanel);
    }

    private void displayProducts(JPanel contentPanel) {
        CompletableFuture<List<ProductModel>> productsFuture = productViewModel.getAllProducts();

        productsFuture.thenAccept(products -> {
            for (ProductModel product : products) {

                JPanel card = createProductCard(product);
                contentPanel.add(card);
            }
            contentPanel.revalidate();
            contentPanel.repaint();
        }).exceptionally(e -> {
            JOptionPane.showMessageDialog(this, "Error fetching products: " + e.getMessage());
            return null;
        });
    }

    private JPanel createProductCard(ProductModel product) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cardPanel.setPreferredSize(new Dimension(250, 350)); // Set card size

        // Product name
        JLabel nameLabel = new JLabel("Name: " + product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Product description
        JLabel descriptionLabel = new JLabel("Description: " + product.getDescription());
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Product price
        JLabel priceLabel = new JLabel("Price: $" + product.getPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel stockLabel = new JLabel("Stock: " + product.getStock());
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        cardPanel.add(nameLabel);
        cardPanel.add(descriptionLabel);
        cardPanel.add(priceLabel);
        cardPanel.add(stockLabel);

        JButton detailsButton = new JButton("View Details");
        detailsButton.addActionListener(_ -> {

            JOptionPane.showMessageDialog(this, "Viewing details for: " + product.getName());
        });

        cardPanel.add(detailsButton);

        return cardPanel;
    }

    public void setUser(String user) {
        this.user = user;
        JLabel welcomeLabel = (JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(0);
        welcomeLabel.setText("Welcome, " + user + "!");
    }

}
