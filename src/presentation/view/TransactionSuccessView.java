package presentation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import domain.model.product.ProductModel;

public class TransactionSuccessView extends JFrame {

    // Constructor
    public TransactionSuccessView(List<ProductModel> productList, UserView userView) {
        if (productList == null || productList.isEmpty() || userView == null) {
            throw new IllegalArgumentException("ProductList tidak boleh kosong dan UserView tidak boleh null");
        }

        // Konfigurasi utama frame
        setTitle("Struk Pembayaran - Yoto App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Pusatkan jendela

        // App Bar (Title + Back Button)
        JPanel appBarPanel = new JPanel();
        appBarPanel.setLayout(new BorderLayout());
        appBarPanel.setBackground(Color.decode("#238b45"));
        appBarPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding 15px di semua sisi

        // Panel untuk menyusun title dan tombol kembali
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS)); // Menyusun secara horizontal
        titlePanel.setBackground(Color.decode("#238b45"));

        JButton backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(Color.decode("#238b45"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        backButton.addActionListener(e -> {
            dispose();
            userView.reloadProducts();
            userView.setVisible(true);
        });

        JLabel titleLabel = new JLabel("Yoto App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        // Menambahkan backButton ke kiri dan titleLabel ke tengah dalam titlePanel
        titlePanel.add(backButton); // Letakkan backButton di kiri
        titlePanel.add(Box.createHorizontalGlue()); // Membuat spasi agar titleLabel ada di tengah
        titlePanel.add(titleLabel); // Letakkan titleLabel di tengah

        // Menambahkan titlePanel ke appBarPanel
        appBarPanel.add(titlePanel, BorderLayout.CENTER);

        // Panel untuk menampilkan daftar produk
        JPanel productListPanel = new JPanel();
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
        productListPanel.setBackground(Color.WHITE);
        productListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel itemList = new JLabel("Item yang dibeli:");
        productListPanel.add(itemList);
        productListPanel.add(Box.createVerticalStrut(10));
        double totalPrice = 0;
        for (ProductModel product : productList) {
            productListPanel.add(createLabel("- " + product.getName()));
            totalPrice += product.getPrice();
        }

        // Total harga
        JLabel totalLabel = new JLabel("Total Harga: Rp " + String.format("%,.0f", totalPrice), SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.BLACK);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        productListPanel.add(Box.createVerticalStrut(10)); // Spasi
        productListPanel.add(totalLabel);

        // Tambahkan scroll pane jika daftar panjang
        JScrollPane scrollPane = new JScrollPane(productListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Alamat dan informasi kontak
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBackground(new Color(245, 245, 245));
        contactPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tanggal dan waktu transaksi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String transactionDateTime = LocalDateTime.now().format(formatter);

        // Add the transaction date and time
        contactPanel.add(createLabel("Waktu Transaksi: " + transactionDateTime));
        contactPanel.add(createLabel("Alamat: Jalan Medan Merdeka Selatan No. 8-9, Jakarta Pusat"));
        contactPanel.add(createLabel("DKI Jakarta - 10110"));
        contactPanel.add(createLabel("Telp: 3456058, 3822800, 3456388"));
        contactPanel.add(createLabel("E-mail: Yoto@id, Yotoshop@gmail.com"));
        contactPanel.add(createLabel("Website: www.yoto.id"));
        contactPanel.add(Box.createVerticalStrut(20));
        contactPanel.add(createLabel("Terima kasih telah menggunakan Yoto App!"));

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Only keep backButton
        buttonPanel.add(backButton);

        // Tambahkan komponen ke frame
        add(appBarPanel, BorderLayout.NORTH); // Tempatkan App Bar di atas
        add(scrollPane, BorderLayout.CENTER); // Tempatkan productListPanel di tengah
        add(contactPanel, BorderLayout.SOUTH); // Tempatkan contactPanel di bawah

        // Tampilkan frame
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    // Helper untuk membuat label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);
        return label;
    }
}
