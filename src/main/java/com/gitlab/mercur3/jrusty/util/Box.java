package com.gitlab.mercur3.jrusty.util;

import java.util.Objects;

/**
 * This class should be used as a {@code void *} or {@code void **} pointer. Since {@code Java}
 * does not support pointers the way {@code C} does it is impossible to write the following code
 * directly without using a wrapper.
 * <pre>
 *     #include &lt;assert.h&gt;
 *
 *     void point_to_5(int *ptr) {
 *         *ptr = 5;
 *     }
 *
 *     int main() {
 *         int x = 0;
 *         point_to_5(&amp;x);
 *
 *         assert(x != 0);
 *         assert(x == 5);
 *
 *         return 0;
 *     }
 * </pre>
 * <br>
 * <b>Note</b>: Not to be confused with
 * <code><a href="https://doc.rust-lang.org/std/boxed/struct.Box.html">std::boxed::Box</a></code>.
 *
 * @param <T>
 */
public final class Box<T> {
	public T value;

	private Box(T value) {
		this.value = value;
	}

	/**
	 * Create a {@code Box} with the inner value set to {@code null}
	 */
	public static <T> Box<T> empty() {
		return new Box<>(null);
	}

	/**
	 * Create a {@code Box} with the given default {@code value}
	 *
	 * @throws NullPointerException if {@code value} is {@code null}
	 */
	public static <T> Box<T> withDefault(T value) {
		Objects.requireNonNull(
				value,
				"Error: Initializing a =Box= with =null= inner content. Use =Box.empty()= instead"
		);
		return new Box<>(value);
	}
}
