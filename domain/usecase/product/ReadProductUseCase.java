package domain.usecase.product;

import domain.model.ProductModel;
import productRepo.ProductRepository;
import core.usecase.UseCaseApp;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReadProductUseCase extends UseCaseApp<List<ProductModel>, Void> {

    private final ProductRepository productRepository;

    // Konstruktor untuk menerima ProductRepository
    public ReadProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CompletableFuture<List<ProductModel>> call(Void params) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Mengambil semua produk dari repositori
                return productRepository.getAllProducts();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to fetch products: " + e.getMessage(), e);
            }
        });
    }
}
