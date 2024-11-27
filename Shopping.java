import java.sql.SQLException;

import UserRepositoryImpl.userRepositoryImpl;
import domain.usecase.auth.loginUseCase;
import domain.usecase.auth.registerUsecase;
import domain.usecase.product.addProductUsecase;
import domain.usecase.product.deleteProductUseCase;
import domain.usecase.product.getProductByIdUsecase;
import domain.usecase.product.readProductUseCase;
import domain.usecase.product.updateProductUsecase;
import presentation.view.authView;
import presentation.view.dashboardView;
import presentation.view.homeView;
import presentation.viewModel.auth.authViewModel;
import presentation.viewModel.product.productViewModel;
import productRepo.ProductRepository;
import productRepositoryImpl.productRepositoryImpl;
import productServices.p;
import userRepo.UserRepository;
import userServices.UserService;
import conectionServices.connectionServices;

// main file
public class Shopping {
    // Services
    private connectionServices connectionServices;
    private p productService;
    private UserService userService;

    // Use cases
    private loginUseCase loginUseCase;
    private registerUsecase registerUsecase;
    private addProductUsecase addProductUsecase;
    private deleteProductUseCase deleteProductUseCase;
    private getProductByIdUsecase getProductByIdUsecase;
    private updateProductUsecase updateProductUsecase;
    private readProductUseCase readProductUseCase;

    // Repositories
    private ProductRepository productRepository;
    private UserRepository userRepository;

    // ViewModels
    private authViewModel authViewModel;
    private productViewModel productViewModel;

    // Views
    private authView authView;
    private dashboardView dashboardView;
    private homeView homeView;

    public static void main(String[] args) throws SQLException {
        Shopping shoppingApp = new Shopping();
        shoppingApp.startApp();
    }

    private Shopping() throws SQLException {
        initServices();
        initRepositories();
        initUsecases();
        initViewModels();
    }

    private void initServices() throws SQLException {
        connectionServices = new connectionServices();
        productService = new p();
        userService = new UserService();
    }

    private void initRepositories() {
        productRepository = new productRepositoryImpl(productService);
        userRepository = new userRepositoryImpl(userService);
    }

    private void initUsecases() {
        loginUseCase = new loginUseCase(userRepository);
        registerUsecase = new registerUsecase(userRepository);
        readProductUseCase = new readProductUseCase(productRepository);
        addProductUsecase = new addProductUsecase(productRepository);
        deleteProductUseCase = new deleteProductUseCase(productRepository);
        updateProductUsecase = new updateProductUsecase(productRepository);
        getProductByIdUsecase = new getProductByIdUsecase(productRepository);
    }

    private void initViewModels() {
        authViewModel = new authViewModel(loginUseCase, registerUsecase);
        productViewModel = new productViewModel(
                addProductUsecase,
                deleteProductUseCase,
                updateProductUsecase,
                readProductUseCase,
                getProductByIdUsecase);
    }

    private void startApp() {
        homeView = new homeView(productViewModel);
        dashboardView = new dashboardView(productViewModel);
        authView = new authView(authViewModel, homeView, dashboardView);
        authView.display();
    }
}
