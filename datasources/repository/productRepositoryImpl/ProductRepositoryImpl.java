
package productRepositoryImpl;

import domain.model.ProductModel;
import productRepo.ProductRepository;
import productServices.ProductService;

import java.sql.*;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    final ProductService productService;

    ProductRepositoryImpl(ProductService service) {
        this.productService = service;
    }

    @Override
    public void addProduct(ProductModel product) throws SQLException {
        productService.addProduct(product).join();
    }

    @Override
    public List<ProductModel> getAllProducts() throws SQLException {
        return productService.getAllProducts().join();
    }

    @Override
    public ProductModel getProductById(int id) throws SQLException {
        return productService.getProductById(id).join();
    }

    @Override
    public void updateProduct(ProductModel product) throws SQLException {
        productService.updateProduct(product).join();
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        productService.deleteProduct(id).join();
    }

}
