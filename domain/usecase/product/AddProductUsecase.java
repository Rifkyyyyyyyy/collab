package domain.usecase.product;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

import domain.model.ProductModel;
import productRepo.ProductRepository;
import core.usecase.UseCaseApp;

public class AddProductUsecase extends UseCaseApp<Void, ProductModel> {

    private final ProductRepository productRepository;

    public AddProductUsecase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CompletableFuture<Void> call(ProductModel product) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                productRepository.addProduct(product);
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e.toString());
            }
        });
    }
}
