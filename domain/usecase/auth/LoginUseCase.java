package domain.usecase.auth;

import domain.model.UserModel;
import userRepo.UserRepository;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import core.usecase.UseCaseApp;

public class LoginUseCase extends UseCaseApp<UserModel, Map<String, Object>> {

    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<UserModel> call(Map<String, Object> params) {
        final String userName = (String) params.get("userName");
        final String password = (String) params.get("password");

        return userRepository.getUserByUsernameAndPassword(userName, password);
    }

}
