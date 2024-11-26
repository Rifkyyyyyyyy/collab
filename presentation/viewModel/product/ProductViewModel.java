package presentation.viewModel.product;

import domain.model.ProductModel;
import domain.usecase.product.AddProductUsecase;
import domain.usecase.product.DeleteProductUseCase;
import domain.usecase.product.UpdateProductUsecase;
import domain.usecase.product.ReadProductUseCase;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductViewModel {

    private final AddProductUsecase addProductUsecase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUsecase updateProductUsecase;
    private final ReadProductUseCase readProductUseCase;

    // Konstruktor untuk inisialisasi dependensi
    public ProductViewModel(AddProductUsecase addProductUsecase,
                            DeleteProductUseCase deleteProductUseCase,
                            UpdateProductUsecase updateProductUsecase,
                            ReadProductUseCase readProductUseCase) {
        this.addProductUsecase = addProductUsecase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.updateProductUsecase = updateProductUsecase;
        this.readProductUseCase = readProductUseCase;
    }

    // Metode untuk menambahkan produk
    public CompletableFuture<Void> addProduct(ProductModel product) {
        return CompletableFuture.runAsync(() -> {
            try {
                addProductUsecase.execute(product);
            } catch (Exception e) {
                throw new RuntimeException("Gagal menambahkan produk: " + e.getMessage(), e);
            }
        });
    }

    // Metode untuk membaca daftar produk
    public CompletableFuture<List<ProductModel>> getProducts() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return readProductUseCase.execute();
            } catch (Exception e) {
                throw new RuntimeException("Gagal membaca produk: " + e.getMessage(), e);
            }
        });
    }

    // Metode untuk menghapus produk berdasarkan ID
    public CompletableFuture<Void> deleteProduct(int productId) {
        return CompletableFuture.runAsync(() -> {
            try {
                deleteProductUseCase.execute(productId);
            } catch (Exception e) {
                throw new RuntimeException("Gagal menghapus produk: " + e.getMessage(), e);
            }
        });
    }

    // Metode untuk memperbarui data produk
    public CompletableFuture<Void> updateProduct(ProductModel product) {
        return CompletableFuture.runAsync(() -> {
            try {
                updateProductUsecase.execute(product);
            } catch (Exception e) {
                throw new RuntimeException("Gagal memperbarui produk: " + e.getMessage(), e);
            }
        });
    }
}
