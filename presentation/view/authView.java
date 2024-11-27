package presentation.view;

import javax.swing.*;

import Role;
import domain.model.UserModel;
import presentation.viewModel.auth.authViewModel;

import java.awt.*;

public class authView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private final authViewModel authViewModel;
    private final homeView homeView;
    private final dashboardView dashboardView;

    public authView(authViewModel authViewModel, homeView homeView, dashboardView dashboardView) {
        this.authViewModel = authViewModel;
        this.homeView = homeView;
        this.dashboardView = dashboardView;

        // Set up frame
        setTitle("Authentication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Center frame and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // display method to show the login form initially
    public void display() {
        showLoginForm();  // Show the login form when the app starts
    }

    private void showLoginForm() {
        // Clear current frame content
        getContentPane().removeAll();

        // Create input panel for login
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Username field
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        // Password field
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        inputPanel.add(loginButton);

        // Register button
        registerButton = new JButton("Register");
        inputPanel.add(registerButton);

        // Add input panel to frame
        add(inputPanel, BorderLayout.CENTER);

        // Add action listeners
        loginButton.addActionListener(_ -> handleLogin());
        registerButton.addActionListener(_ -> showRegisterForm());

        // Revalidate the layout to reflect changes
        revalidate();
        repaint();
    }

    private void showRegisterForm() {
        // Clear current frame content
        getContentPane().removeAll();

        // Create input panel for registration
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // Increased row count to 5
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Username field
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        // Password field
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // Role dropdown
        inputPanel.add(new JLabel("Role:"));
        JComboBox<role> roleComboBox = new JComboBox<>(role.values()); // Populating JComboBox with Role enum
        inputPanel.add(roleComboBox);

        // Register button
        registerButton = new JButton("Register");
        inputPanel.add(registerButton);

        JButton backToLoginButton = new JButton("Back to Login");
        inputPanel.add(backToLoginButton);

        // Add input panel to frame
        add(inputPanel, BorderLayout.CENTER);

        // Add action listeners
        registerButton.addActionListener(_ -> handleRegister(roleComboBox)); // Pass JComboBox to handleRegister
        backToLoginButton.addActionListener(_ -> showLoginForm());

        // Revalidate the layout to reflect changes
        revalidate();
        repaint();
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        authViewModel.login(username, password)
                .thenAccept(user -> {
                    if (user != null) {
                        JOptionPane.showMessageDialog(this, "Login Successful: " + user.getRole());
                        if (user.getRole().name().equalsIgnoreCase("SELLER")) {
                            routeToSellerPage(username); // Pass the username
                        } else {
                            routeToBuyerPage(username); // Pass the username
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }).exceptionally(ex -> {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return null;
                });
    }

    private void handleRegister(JComboBox<role> roleComboBox) {
        int randomId = (int) (Math.random() * Integer.MAX_VALUE);

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        role selectedRole = (role) roleComboBox.getSelectedItem();

        UserModel newUser = new UserModel(randomId, username, password, selectedRole);

        authViewModel.register(newUser)
                .thenAccept(isRegistered -> {
                    if (isRegistered) {
                        JOptionPane.showMessageDialog(this, "Registration Successful! Please log in.");
                        showLoginForm();
                        usernameField.setText(""); // Clear the username field
                        passwordField.setText(""); // Clear the password field
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration failed. Try again.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }).exceptionally(ex -> {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return null;
                });
    }

    private void routeToSellerPage(String user) {
        JOptionPane.showMessageDialog(this, "Welcome to the Seller Dashboard!");
        this.setVisible(false);
        dashboardView.setUser(user);
        dashboardView.setVisible(true);
    }

    private void routeToBuyerPage(String user) {
        JOptionPane.showMessageDialog(this, "Welcome to the Buyer Dashboard!");
        this.setVisible(false);
        homeView.setUser(user);
        homeView.setVisible(true);
    }

    // Getters for the fields and buttons
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
