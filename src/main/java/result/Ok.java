package result;

public final class Ok<T, E> extends Result<T, E> {
    public Ok(T ok) {
        super(ok, null);
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }
}
