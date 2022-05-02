package com.gitlab.mercur3.jrusty.option;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Option {
	private Option() {}

	/**
	 * @return the default value {@code defaultVal} if empty, or applies the function {@code f} to
	 * the inner value
	 */
	public static <T, U> U mapOr(Optional<T> option, U defaultVal, Function<T, U> f) {
		if (option.isEmpty()) {
			return defaultVal;
		}
		return f.apply(option.get());
	}

	/**
	 * @return the provided value of the supplier if empty, or applies the function {@code f}
	 */
	public static <T, U> U mapOrElse(
			Optional<T> option,
			Supplier<U> defaultVal,
			Function<T, U> f
	) {
		if (option.isEmpty()) {
			return defaultVal.get();
		}
		return f.apply(option.get());
	}
}
