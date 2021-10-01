package result;

public final class Err<T, E> extends Result<T, E> {
    public Err(E err) {
        super(null, err);
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }
}
