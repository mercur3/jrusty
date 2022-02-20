package com.gitlab.mercur3.jrusty.result;

import java.util.Objects;

/**
 * If operation is successful it should return <code>Ok&lt;T&gt;</code>.
 *
 * <h2>Notes</h2>
 * <p>
 * Neglect the signature <code>Ok&lt;T, E&gt;</code>. Only {@code T} is used, {@code E} is needed
 * only in order to compile without warnings.
 * </p>
 *
 * @see com.gitlab.mercur3.jrusty.result.Result
 */
public final class Ok<T, E> extends Result<T, E> {
	/**
	 * @param ok <code>Ok</code> type
	 * @throws NullPointerException if {@code null}
	 */
	public Ok(T ok) {
		super(ok, null);
		Objects.requireNonNull(ok);
	}

	/**
	 * @return {@code true}
	 */
	@Override
	public boolean isOk() {
		return true;
	}

	/**
	 * @return {@code false}
	 */
	@Override
	public boolean isErr() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Ok<?, ?> other) {
			return ok().equals(other.ok());
		}
		return false;
	}
}
