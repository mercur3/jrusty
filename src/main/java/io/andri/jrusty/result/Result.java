package io.andri.jrusty.result;

import java.util.Optional;
import java.util.function.Function;

/**
 * Representation of Rust's <code>std::result::Result</code>. The only allowed subclasses are
 * <code>Ok</code> and <code>Err</code>. If the instance is <code>Ok</code>, <code>err</code> is set
 * to <code>null</code>. If the instance is <code>Err</code>, <code>ok</code> is set to
 * <code>null</code>.
 * <p>
 * 2 <code>Result</code>'s are equal if and only if:
 * <ol>
 *     <li>Both are <code>Ok<?></code> and the content inside are also equal</li>
 *     <li>Both are <code>Err<?></code> and the content inside are also equal</li>
 * </ol>
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
	 * If <code>ok</code> return the value, else throw a <code>RuntimeException</code> with the
	 * specific message.
	 */
	public T expect(String msg) {
		if (isOk()) {
			return ok;
		}
		throw new RuntimeException(msg);
	}

	/**
	 * Converts a <code>Result<Result<T, E>, E></code> to <code>Result<T, E></code>. Flattening
	 * removes only one level. If it cannot be further flatten, this function returns
	 * <code>this</code>.
	 */
	public Result<T, E> flatten() {
		if (ok instanceof Result<?, ?> innerRes) {
			return (Result<T, E>) innerRes;
		}
		return this;
	}

	/**
	 * Maps a <code>Result<T, E></code> to <code>Result<T, E></code> by applying the function. In
	 * any case the value of <code>err</code> is left untouched.
	 *
	 * @param mapper a function <code>T -> U</code>
	 * @param <U>    the new <code>ok</code> type
	 */
	public <U> Result<U, E> map(Function<T, U> mapper) {
		if (isOk()) {
			return new Ok<>(mapper.apply(ok));
		}
		return new Err<>(err);
	}

	/**
	 * Maps a <code>Result<T, E></code> to <code>Result<T, O></code> by applying the function. In
	 * any case the value of <code>ok</code> is left untouched.
	 *
	 * @param mapper a function <code>E -> O</code>
	 * @param <O>    the new <code>err</code> type
	 */
	public <O> Result<T, O> mapErr(Function<E, O> mapper) {
		if (isErr()) {
			return new Err<>(mapper.apply(err));
		}
		return new Ok<>(ok);
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
