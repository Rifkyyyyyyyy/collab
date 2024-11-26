package domain.usecase.product;

import java.sql.SQLException;

import domain.model.ProductModel;
import productRepo.ProductRepository;

public class UpdateProductUsecase {
    private final ProductRepository productRepository;

    UpdateProductUsecase(ProductRepository product) {
        this.productRepository = product;
    }

   void updateProduct (ProductModel model) throws SQLException {
    productRepository.updateProduct(model);
   }
}
