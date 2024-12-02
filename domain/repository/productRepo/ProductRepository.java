package productRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import domain.model.ProductModel;

public interface ProductRepository {
    public CompletableFuture<Void> addProduct(ProductModel product) throws SQLException;

    public CompletableFuture<List<ProductModel>> getAllProducts() throws SQLException;

    public CompletableFuture<ProductModel> getProductById(int id) throws SQLException;

    public CompletableFuture<Void> updateProduct(ProductModel product) throws SQLException;

    public CompletableFuture<Void> deleteProduct(int id) throws SQLException;
}
