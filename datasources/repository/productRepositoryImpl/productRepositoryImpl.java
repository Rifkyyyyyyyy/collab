
package productRepositoryImpl;

import domain.model.ProductModel;
import productRepo.ProductRepository;
import productServices.productService;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class productRepositoryImpl implements ProductRepository {
    final productService productService;

    public productRepositoryImpl(productService service) {
        this.productService = service;
    }

    @Override
    public CompletableFuture<Void> addProduct(ProductModel product) throws SQLException {
         return productService.addProduct(product);
    }

    @Override
    public CompletableFuture<List<ProductModel>> getAllProducts() throws SQLException {
       return productService.getAllProducts();
    }

    @Override
    public CompletableFuture<ProductModel> getProductById(int id) throws SQLException {
        return productService.getProductById(id);
    }

    @Override
    public CompletableFuture<Void> updateProduct(ProductModel product) throws SQLException {
       return productService.updateProduct(product);
    }

    @Override
    public CompletableFuture<Void> deleteProduct(int id) throws SQLException {
        return productService.deleteProduct(id);
    }

  
}
