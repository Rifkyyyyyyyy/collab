package productRepo;
import java.sql.SQLException;
import java.util.List;

import domain.model.ProductModel;

public interface ProductRepository {
     void addProduct(ProductModel product) throws SQLException;
    List<ProductModel> getAllProducts() throws SQLException;
    ProductModel getProductById(int id) throws SQLException;
    void updateProduct(ProductModel product) throws SQLException;
    void deleteProduct(int id) throws SQLException;
}
