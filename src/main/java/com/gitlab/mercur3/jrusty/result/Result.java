package com.gitlab.mercur3.jrusty.result;

import java.util.Optional;
import java.util.function.Function;

/**
 * Representation of Rust's {@code std::result::Result}. The only allowed subclasses are {@code Ok}
 * and {@code Err}. If the instance is {@code Ok}, {@code err} is set to {@code null}. If the
 * instance is {@code Err}, {@code ok} is set to {@code null}.
 *
 * <h2>Equality</h2>
 * 2 <code>Result</code>'s are equal if and only if one of the following is true:
 * <ol>
 *     <li>Both are <code>Ok&lt;?&gt;</code> and the content inside are also equal</li>
 *     <li>Both are <code>Err&lt;?&gt;</code> and the content inside are also equal</li>
 * </ol>
 *
 * <h2>Examples</h2>
 * <h3>Working around {@code try-catch}</h3>
 * {@snippet :
 * public static Result<Boolean, ErrorKind> parseBoolean(String str) {
 *     try {
 *         return new Ok<>(Boolean.parseBoolean(str));
 *     }
 *     catch (NumberFormatException e) {
 *         return new Err<>(ErrorKind.FORMAT_ERROR);
 *     }
 * }}
 *
 * <h3>Pattern matching (Java >= 21)</h3>
 * {@snippet :
 * public static handleRes(Result<Integer, String> res) {
 *     // Notice that the second type in the diamond operator (<>) is irrelevant, but we still need to write it. Also,
 *     // the Err branch has the types in reverse order. Again, the second type is irrelevant and should not bother you.
 *     switch (res) {
 *         case Ok<Integer, String> ok -> System.out.println("Received number = " + ok.unwrap());
 *         case Err<String, Integer> err -> System.err.println("Error message = " + err.unwrapErr());
 *     }
 * }}
 *
 * @param <T> the type operation returns if it is <code>Ok</code>
 * @param <E> the type operation returns if it is <code>Err</code>
 * @see <a href="https://doc.rust-lang.org/std/result/enum.Result.html"><code>std::result::Result</code></a>
 */
public sealed abstract class Result<T, E> permits Ok, Err {
	private final T ok;
	private final E err;

	public Result(T ok, E err) {
		this.ok = ok;
		this.err = err;
	}

	/**
	 * @return <code>true</code> if <code>Ok</code>, <code>false</code> if <code>Err</code>
	 */
	public abstract boolean isOk();

	/**
	 * @return <code>false</code> if <code>Ok</code>, <code>true</code> if <code>Err</code>
	 */
	public abstract boolean isErr();

	public Optional<T> ok() {
		return Optional.ofNullable(ok);
	}

	public Optional<E> err() {
		return Optional.ofNullable(err);
	}

	/**
	 * @param msg message
	 * @return the value if <code>Ok</code>
	 * @throws IllegalStateException with the specific message if <code>Err</code>
	 */
	public T expect(String msg) {
		if (isOk()) {
			return ok;
		}
		throw new IllegalStateException(msg);
	}

	/**
	 * @return the containing @{code Err} value or throws a runtime exception with the specified
	 * message @{code msg}.
	 * @throws IllegalStateException with the specific message if @{code Ok}
	 */
	public E expectErr(String msg) {
		if (isErr()) {
			return err;
		}
		throw new IllegalStateException(msg);
	}

	/**
	 * Converts a <code>Result&lt;Result&lt;T, E&gt;, E&gt;</code> to
	 * <code>Result&lt;T, E&gt;</code>. Flattening removes only one level. If it cannot be
	 * further flatten, this function returns
	 * <code>this</code>.
	 *
	 * @return flattening result
	 */
	public Result<T, E> flatten() {
		if (ok instanceof Result<?, ?> innerRes) {
			return (Result<T, E>) innerRes;
		}
		return this;
	}

	/**
	 * Maps a <code>Result&lt;T, E&gt;</code> to <code>Result&lt;U, E&gt;</code> by applying the
	 * function. In any case the value of <code>err</code> is left untouched.
	 *
	 * @param mapper a function <code>T -> U</code>
	 * @param <U>    the new <code>ok</code> type
	 * @return mapping result
	 */
	public <U> Result<U, E> map(Function<T, U> mapper) {
		if (isOk()) {
			return new Ok<>(mapper.apply(ok));
		}
		return new Err<>(err);
	}

	/**
	 * Maps a <code>Result&lt;T, E&gt;</code> to <code>Result&lt;T, O&gt;</code> by applying the
	 * function. In any case the value of <code>ok</code> is left untouched.
	 *
	 * @param f   a function <code>E -> O</code>
	 * @param <O> the new <code>err</code> type
	 * @return the mapping
	 */
	public <O> Result<T, O> mapErr(Function<E, O> f) {
		if (isErr()) {
			return new Err<>(f.apply(err));
		}
		return new Ok<>(ok);
	}

	/**
	 * Maps a <code>Result&lt;T, E&gt;</code> to <code>U</code> by applying the function
	 * <code>f</code> to a present <code>Ok</code> value or the default function
	 * <code>defaultFunc</code> to a present <code>Err</code> value
	 *
	 * @param defaultFunc a function <code>E -> U</code>
	 * @param f           a function <code>T -> U</code>
	 * @param <U>         the new <code>err</code> type
	 * @return mapping result
	 */
	public <U> U mapOrElse(Function<E, U> defaultFunc, Function<T, U> f) {
		if (isOk()) {
			return f.apply(ok);
		}
		return defaultFunc.apply(err);
	}

	/**
	 * Maps a <code>Result&lt;T, E&gt;</code> to <code>U</code> by applying the function
	 * <code>f</code> to a present <code>Ok</code> value or if {@code Err} returns the default value
	 * <code>defaultValue</code>.
	 *
	 * @param <U>          the associated type
	 * @param defaultValue the default value
	 * @param f            the function
	 * @return the default value of type <code>U</code> if <code>Err</code>, applies the function
	 * <code>f</code>
	 */
	public <U> U mapOr(U defaultValue, Function<T, U> f) {
		if (isOk()) {
			return f.apply(ok);
		}
		return defaultValue;
	}

	/**
	 * @return associated type <code>T</code> if <code>Ok</code>
	 * @throws IllegalStateException if <code>Err</code>.
	 */
	public T unwrap() {
		if (isErr()) {
			throw new IllegalStateException("Trying to access value from a result that is error");
		}
		return ok;
	}

	/**
	 * @return the contained @{code Err} value.
	 * @throws IllegalStateException if @{code Ok}
	 */
	public E unwrapErr() {
		if (isErr()) {
			return err;
		}
		throw new IllegalStateException("Trying to access value from a result that is ok");
	}

	/**
	 * @param other the return value if <code>Err</code>
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
	 * @param mapper a function
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
