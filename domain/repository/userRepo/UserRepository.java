package userRepo;
import java.util.concurrent.CompletableFuture;

import domain.model.UserModel;

public interface UserRepository {
   public CompletableFuture <UserModel> getUserByUsernameAndPassword(String username, String password);
   public CompletableFuture<Boolean> registerUser(UserModel user);
  
}
