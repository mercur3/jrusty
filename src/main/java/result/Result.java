package result;

import java.util.Optional;

public sealed abstract class Result<T, E> permits Ok, Err {
    private final T ok;
    private final E err;

    public Result(T ok, E err) {
        this.ok = ok;
        this.err = err;
    }

    public abstract boolean isOk();

    public abstract boolean isErr();

    public Optional<T> ok() {
        return Optional.ofNullable(ok);
    }

    public Optional<E> err() {
        return Optional.ofNullable(err);
    }

    public T unwrap() {
        if (isErr()) {
            throw new IllegalStateException("Trying to access value from a result that is in error");
        }
        return ok;
    }

    public T unwrapOr(T other) {
        if (isErr()) {
            return other;
        }
        return ok;
    }
}
