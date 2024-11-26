package core.usecase;

import java.util.concurrent.CompletableFuture;

public abstract class UseCaseApp<Type, Params> {

    public CompletableFuture<Type> call(Params params) {
        return CompletableFuture.completedFuture(null);
    }

}
