package io.andri.jrusty.result;

import java.util.Optional;
import java.util.function.Function;

/**
 * Representation of Rust's <code>std::result::Result</code>. The only allowed subclasses are
 * <code>Ok</code> and <code>Err</code>. If the instance is <code>Ok</code>, <code>err</code> is set
 * to <code>null</code>. If the instance is <code>Err</code>, <code>ok</code> is set to
 * <code>null</code>.
 *
 * @param <T> the type operation returns if it is <code>Ok</code>
 * @param <E> the type operation returns if it is <code>Err</code>
 */
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

	/**
	 * @return associated type <code>T</code> if <code>Ok</code> or throws
	 * <code>IllegalStateException</code> if <code>Err</code>.
	 */
	public T unwrap() {
		if (isErr()) {
			throw new IllegalStateException("Trying to access value from a result that is in error");
		}
		return ok;
	}

	/**
	 * @return associated type <code>T</code> if <code>Ok</code> or <code>other</code> if
	 * <code>Err</code>.
	 */
	public T unwrapOr(T other) {
		if (isErr()) {
			return other;
		}
		return ok;
	}

	/**
	 * @return associated type <code>T</code> if <code>Ok</code> or if <code>Err</code>, the
	 * mapping of associated type <code>E</code> to <code>T</code>
	 */
	public T unwrapOrElse(Function<E, T> mapper) {
		if (isErr()) {
			return mapper.apply(err);
		}
		return ok;
	}
}
