package com.gitlab.mercur3.jrusty.panic;

/**
 * Indicates that code is <b>unimplemented</b>.
 *
 * <pre>
 *     class x {
 *         public void func() {
 *             throw new UnimplementedException();
 *         }
 *     }
 * </pre>
 *
 * The above code declares an arbitrary function that is not implemented yet. It allows compilation,
 * but as soon as the function is called it should throw an {@code UnimplementedException}.
 */
public final class UnimplementedException extends UnsupportedOperationException {
	/**
	 * Throws an {@code UnimplementedException} with the message <i>TODO</i>.
	 */
	public UnimplementedException() {
		super("TODO");
	}

	/**
	 * @param message custom message
	 */
	public UnimplementedException(String message) {
		super(message);
	}
}
