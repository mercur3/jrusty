package result;

public final class Ok<T> extends Result {
    public Ok(T ok) {
        super(ok, null);
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return true;
    }
}
