package com.gitlab.mercur3.jrusty.result;

import java.util.Objects;

/**
 * If operation is successful it must return <code>Ok&lt;T&gt;</code>.
 * <br><br>
 * <b>Note:</b> neglect the signature <code>&lt;T, E&lt;</code>. Only <code>T</code> is used,
 * <code>E</code> is needed only in order to compile without warnings.
 */
public final class Ok<T, E> extends Result<T, E> {
	/**
	 * @param ok <code>Ok</code> type
	 * @throws NullPointerException if <code>null</code>
	 */
	public Ok(T ok) {
		super(ok, null);
		Objects.requireNonNull(ok);
	}

	/**
	 * @return <code>true</code>
	 */
	@Override
	public boolean isOk() {
		return true;
	}

	/**
	 * @return <code>false</code>
	 */
	@Override
	public boolean isErr() {
		return false;
	}

	/**
	 * @param o other object
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Ok<?, ?> other) {
			return ok().equals(other.ok());
		}
		return false;
	}
}
