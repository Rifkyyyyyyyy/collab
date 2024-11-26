package domain.model;


public final class ProductModel {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final int stock;
    private final int userId;

    // Constructor to initialize the properties
    public ProductModel(int id, String name, String description, double price, int stock, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        return "ProductModel{id=" + id + ", name='" + name + "', description='" + description + "', price=" + price + ", stock=" + stock + ", userId=" + userId + "}";
    }
}
