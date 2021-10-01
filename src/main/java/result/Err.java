package result;

public final class Err<E> extends Result {
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
