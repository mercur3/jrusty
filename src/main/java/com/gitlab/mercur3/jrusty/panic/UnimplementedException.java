package com.gitlab.mercur3.jrusty.panic;

/**
 * Indicates that code is <b>unimplemented</b>.
 * {@snippet :
 * class x {
 *     public void func() {
 *         throw new UnimplementedException();
 *     }
 * }}
 *
 * <p>
 * The above code declares an arbitrary function that is not implemented yet. It allows compilation,
 * but as soon as the function is called it should throw an {@code UnimplementedException}.
 * </p>
 *
 * @see <a href="https://doc.rust-lang.org/std/macro.unimplemented.html"><code>unimplemented!()</code></a>
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
