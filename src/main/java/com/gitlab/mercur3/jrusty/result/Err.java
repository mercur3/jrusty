package com.gitlab.mercur3.jrusty.result;

import java.util.Objects;

/**
 * If the operation is not successful it must return <code>Err&lt;E&gt;</code>.
 * <br><br>
 * <b>Notes:</b>
 * <ul>
 *     <li>
 * 			neglect the signature <code>&lt;T, E&gt;</code>. Only <code>T</code> is used,
 * 			<code>E</code>
 * 			is needed only in order to compile without warnings.
 *     </li>
 *     <li>
 * 			<code>Err&lt;E, T&lt;</code> is a HACK in order to circumnavigate the problem that the
 * 			constructor accepts only 1 parameter therefore it is assigned to <code>T</code> rather
 * 			than <code>E</code> which is what we want.
 *     </li>
 * </ul>
 */
public final class Err<E, T> extends Result<T, E> {
	/**
	 * @param err initialize with this error type
	 * @throws NullPointerException if <code>null</code>
	 */
	public Err(E err) {
		super(null, err);
		Objects.requireNonNull(err);
	}

	/**
	 * @return <code>false</code>
	 */
	@Override
	public boolean isOk() {
		return false;
	}

	/**
	 * @return <code>true</code>
	 */
	@Override
	public boolean isErr() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Err<?, ?> other) {
			return err().equals(other.err());
		}
		return false;
	}
}
