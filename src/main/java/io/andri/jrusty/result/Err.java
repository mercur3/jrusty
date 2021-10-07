package io.andri.jrusty.result;

import java.util.Objects;

/**
 * If the operation is not successful it must return <code>Err<E></code>.
 * <br><br>
 * <b>Notes:</b>
 * <ul>
 *     <li>
 * 			neglect the signature <code><T, E></code>. Only <code>T</code> is used, <code>E</code>
 * 			is needed only in order to compile without warnings.
 *     </li>
 *     <li>
 * 			<code>Err<E, T></code> is a HACK in order to circumnavigate the problem that the
 * 			constructor accepts only 1 parameter therefore it is assigned to <code>T</code> rather
 * 			than <code>E</code> which is what we want.
 *     </li>
 * </ul>
 */
public final class Err<E, T> extends Result<T, E> {
	/**
	 * @throws NullPointerException if <code>null</code>
	 */
	public Err(E err) {
		super(null, err);
		Objects.requireNonNull(err);
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
