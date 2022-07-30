package com.gitlab.mercur3.jrusty.util;

/**
 * A representation of absence of a value, similar to @{code nil}, @{null}, @{nullptr}, @{code ()}.
 */
public final class Void {
	private static final Void instance = new Void();

	private Void() {}

	/**
	 * @return the only instance of @{code Void} class
	 */
	public static Void getInstance() {
		return instance;
	}
}
