package io.andri.jrusty.result;

import java.util.Objects;

/**
 * If operation is successful it must return <code>Ok<T></code>.
 * <br><br>
 * <b>Note:</b> neglect the signature <code><T, E></code>. Only <code>T</code> is used,
 * <code>E</code> is needed only in order to compile without warnings.
 */
public final class Ok<T, E> extends Result<T, E> {
	/**
	 * @throws NullPointerException if <code>null</code>
	 */
	public Ok(T ok) {
		super(ok, null);
		Objects.requireNonNull(ok);
	}

	@Override
	public boolean isOk() {
		return true;
	}

	@Override
	public boolean isErr() {
		return false;
	}
}
