package com.gitlab.mercur3.jrusty.panic;

/**
 * Indicates that the following {@code if-else} or {@code switch-case} branch is logically
 * <b>unreachable</b>.
 *
 * @see <a href="https://doc.rust-lang.org/std/macro.unreachable.html">std::unreachable</a>
 */
public class UnreachableException extends IllegalStateException {
	/**
	 * @see IllegalStateException#IllegalStateException()
	 */
	public UnreachableException() {
		super();
	}

	/**
	 * @param s custom message
	 */
	public UnreachableException(String s) {
		super(s);
	}
}
