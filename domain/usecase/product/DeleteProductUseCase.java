package domain.usecase.product;

import productRepo.ProductRepository;
import core.usecase.UseCaseApp;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class DeleteProductUseCase extends UseCaseApp<Void, Integer> {

    private final ProductRepository productRepository;

    // Konstruktor untuk menerima ProductRepository
    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CompletableFuture<Void> call(Integer productId) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                productRepository.deleteProduct(productId);
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
