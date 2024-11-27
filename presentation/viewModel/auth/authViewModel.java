package presentation.viewModel.auth;

import domain.model.UserModel;
import domain.usecase.auth.loginUseCase;
import domain.usecase.auth.registerUsecase;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class authViewModel {
    private final loginUseCase loginUseCase;
    private final registerUsecase registerUsecase;
    private String userName;

    public authViewModel(loginUseCase login, registerUsecase register) {
        this.loginUseCase = login;
        this.registerUsecase = register;
    }

    // Login method
    public CompletableFuture<UserModel> login(String username, String password) {
        Map<String, Object> params = Map.of(
                "userName", username,
                "password", password);

        return loginUseCase.call(params)
                .thenApply(user -> {
                    if (user != null) {
                        this.userName = user.getUsername(); // Store username in field
                        System.out.println("Login berhasil untuk pengguna: " + user.getUsername());
                    } else {
                        System.out.println("Login gagal. Periksa kredensial Anda.");
                    }
                    return user;
                });
    }

    // Register method
    public CompletableFuture<Boolean> register(UserModel user) {
        return registerUsecase.call(user)
                .thenApply(success -> {
                    if (success) {
                        System.out.println("Registrasi berhasil untuk pengguna: " + user.getUsername());
                    } else {
                        System.out.println("Registrasi gagal. Periksa data yang dimasukkan.");
                    }
                    return success;
                });
    }

    public String getUserName() {
        return this.userName;
    }

}
