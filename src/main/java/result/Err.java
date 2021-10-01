package result;

// <code>Err<E, T></code> is a HACK in order to circumnavigate the problem that the constructor
// accepts only 1 parameter therefore it is assigned to <code>T</code> rather than <code>E</code>
// which is what we want
public final class Err<E, T> extends Result<T, E> {
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
