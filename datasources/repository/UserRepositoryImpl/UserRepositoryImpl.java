package UserRepositoryImpl;

import java.util.concurrent.CompletableFuture;

import domain.model.UserModel;
import userRepo.UserRepository;
import userServices.UserService;

public class UserRepositoryImpl implements UserRepository {

    private final UserService service;

    UserRepositoryImpl(UserService service) {
        this.service = service;
    }

    @Override
    public CompletableFuture<Boolean> registerUser(UserModel user) {
        return service.registerUser(user);
    }

    @Override
    public CompletableFuture<UserModel> getUserByUsernameAndPassword(String username, String password) {
        return service.getUserByUsernameAndPassword(username, password);
    }

}
