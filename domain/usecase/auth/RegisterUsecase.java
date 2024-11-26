package domain.usecase.auth;

import domain.model.UserModel;
import userRepo.UserRepository;

public class RegisterUsecase {
    private final UserRepository userRepository;

    public RegisterUsecase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(UserModel data) {
        return userRepository.registerUser(data);

    }
}