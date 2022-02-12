package com.gitlab.mercur3.jrusty.result;

import java.util.Objects;

/**
 * If the operation is not successful it should return <code>Err&lt;E&gt;</code>.
 *
 * <h3>Notes</h3>
 * <ul>
 *     <li>
 * 			Neglect the signature <code>extends Result&lt;T, E&gt;</code> and <code>Err&lt;E, T&gt;</code>.
 * 			Only {@code E} in {@code Err} is used, {@code T} is needed only in order to compile
 * 			without warnings.
 *     </li>
 *     <li>
 * 			<code>Err&lt;E, T&gt;</code> is a <i>HACK</i> in order to circumnavigate the problem
 * 			that we are extending <code>Result&lt;T, E&gt;</code>. Compiler maps {@code E} of
 *            {@code Err} to {@code T} of {@code Result} and {@code T} of {@code Err} to {@code E} of
 *            {@code Result}. The constructor accepts only 1 parameter therefore it is assigned to
 *            {@code E} of {@code Err} and {@code Result} which is what we wanted.
 *     </li>
 * </ul>
 *
 * @see com.gitlab.mercur3.jrusty.result.Result
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
