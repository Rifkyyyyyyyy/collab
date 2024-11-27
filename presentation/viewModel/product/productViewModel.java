package presentation.viewModel.product;

import domain.model.ProductModel;
import domain.usecase.product.addProductUsecase;
import domain.usecase.product.deleteProductUseCase;
import domain.usecase.product.getProductByIdUsecase;
import domain.usecase.product.updateProductUsecase;
import domain.usecase.product.readProductUseCase;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class productViewModel {

    private final addProductUsecase addProductUsecase;
    private final deleteProductUseCase deleteProductUseCase;
    private final updateProductUsecase updateProductUsecase;
    private final readProductUseCase readProductUseCase;
    private final getProductByIdUsecase getProductByIdUsecase;

    // Konstruktor untuk inisialisasi dependensi
    public productViewModel(addProductUsecase addProductUsecase,
            deleteProductUseCase deleteProductUseCase,
            updateProductUsecase updateProductUsecase,
            readProductUseCase readProductUseCase,
            getProductByIdUsecase getProductByIdUsecase) {
        this.addProductUsecase = addProductUsecase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.updateProductUsecase = updateProductUsecase;
        this.readProductUseCase = readProductUseCase;
        this.getProductByIdUsecase = getProductByIdUsecase;
    }

    // Metode untuk menambahkan produk
    public CompletableFuture<Void> addProduct(ProductModel product) {
        return addProductUsecase.call(product)
                .thenRun(() -> System.out.println("Produk berhasil ditambahkan: " + product.getName()));
    }

    // Metode untuk menghapus produk
    public CompletableFuture<Void> deleteProduct(int productId) {
        return deleteProductUseCase.call(productId)
                .thenRun(() -> System.out.println("Produk dengan ID " + productId + " berhasil dihapus."));
    }

    // Metode untuk memperbarui produk
    public CompletableFuture<Void> updateProduct(ProductModel product) {
        return updateProductUsecase.call(product)
                .thenRun(() -> System.out.println("Produk berhasil diperbarui: " + product.getName()));
    }

    // Metode untuk membaca semua produk
    public CompletableFuture<List<ProductModel>> getAllProducts() {
        return readProductUseCase.call(null)
                .thenApply(products -> {
                    System.out.println("Berhasil mengambil daftar produk: " + products.size() + " item ditemukan.");
                    return products;
                });
    }

    public CompletableFuture<ProductModel> getProductById(int productId) {
        return getProductByIdUsecase.call(productId)
                .thenApply(product -> {
                    if (product != null) {
                        System.out.println("Produk ditemukan: " + product.getName());
                    } else {
                        System.out.println("Produk dengan ID " + productId + " tidak ditemukan.");
                    }
                    return product;
                });
    }
}
